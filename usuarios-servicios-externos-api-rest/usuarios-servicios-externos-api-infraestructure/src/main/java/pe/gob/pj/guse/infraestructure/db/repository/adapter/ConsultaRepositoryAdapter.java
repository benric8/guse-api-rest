package pe.gob.pj.guse.infraestructure.db.repository.adapter;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.TypedQuery;
import javax.validation.ConstraintViolationException;

import org.hibernate.SessionFactory;
import org.hibernate.exception.SQLGrammarException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.orm.hibernate5.SessionFactoryUtils;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;
import pe.gob.pj.guse.domain.exceptions.ErrorDaoException;
import pe.gob.pj.guse.domain.model.sidenum.ActoProcesal;
import pe.gob.pj.guse.domain.model.sidenum.Expediente;
import pe.gob.pj.guse.domain.model.sidenum.ExpedienteDetalle;
import pe.gob.pj.guse.domain.model.sidenum.MedidaAdoptada;
import pe.gob.pj.guse.domain.model.sidenum.MedidaProteccion;
import pe.gob.pj.guse.domain.model.sidenum.ParteProcesal;
import pe.gob.pj.guse.domain.port.repository.ConsultaRepositoryPort;
import pe.gob.pj.guse.domain.utils.ProjectConstants;
import pe.gob.pj.guse.domain.utils.ProjectUtils;
import pe.gob.pj.guse.infraestructure.db.entity.casillero.MovDocumentoArchivo;
import pe.gob.pj.guse.infraestructure.db.entity.sidenum.MaeTipoDocumentoPersona;
import pe.gob.pj.guse.infraestructure.db.entity.sidenum.MovExpediente;
import pe.gob.pj.guse.infraestructure.db.entity.sidenum.MovInstanciaExpediente;
import pe.gob.pj.guse.infraestructure.db.entity.sidenum.MovParte;
import pe.gob.pj.guse.infraestructure.db.entity.sidenum.MovPersona;
import pe.gob.pj.guse.infraestructure.db.entity.sidenum.MovResolucion;

@Slf4j
@Component("consultaRepositoryPort")
public class ConsultaRepositoryAdapter implements ConsultaRepositoryPort, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Autowired
	@Qualifier("sessionSidenum")
	private SessionFactory sf;
	
	@Autowired
	@Qualifier("sessionCasillero")
	private SessionFactory sfCasillero;

	@Override
	public List<Expediente> consultarExpedientes(String cuo, String tipoDocumentoPersona, String numeroDocumentoIdentidad) throws Exception {
		
		List<Expediente> lista = new ArrayList<Expediente>();
		log.info("{} INICIO_DAO CONSULTA EXPEDIENTES", cuo);
		
		try {
			StringBuilder stringQuery = new StringBuilder("SELECT DISTINCT mie FROM MovResolucion mr");
			stringQuery.append(" JOIN mr.instanciaExpediente mie");
			stringQuery.append(" JOIN mie.expediente me");
			stringQuery.append(" JOIN me.partes mps"); 
			stringQuery.append(" JOIN mr.medidaProteccions msp");
			stringQuery.append(" JOIN msp.medidaAdoptadas mpa");
			stringQuery.append(" JOIN mps.persona mpe");
			stringQuery.append(" JOIN mpe.tipoDocumentoPersona mtdp");
			stringQuery.append(" WHERE mie.activo='1'");
			stringQuery.append(" AND mtdp.codigoSij=:"+MaeTipoDocumentoPersona.P_CODIGO);
			stringQuery.append(" AND mpe.numeroDocumento=:"+MovPersona.P_NUMERO_DOCUMENTO);
			stringQuery.append(" AND mr.id = (SELECT MAX(mr2.id) FROM MovResolucion mr2 WHERE mr2.instanciaExpediente = mie)");
			stringQuery.append(" AND msp.estadoDocumento IN ('S','V') ");
			stringQuery.append(" ORDER BY mie.id DESC");
			
			TypedQuery<MovInstanciaExpediente> query = this.sf.getCurrentSession().createQuery(stringQuery.toString(), MovInstanciaExpediente.class);
			query.setParameter(MaeTipoDocumentoPersona.P_CODIGO, tipoDocumentoPersona);
			query.setParameter(MovPersona.P_NUMERO_DOCUMENTO, numeroDocumentoIdentidad);
			query.getResultStream().forEach(instanciaExpediente -> {
				log.info("{} Inicio recorrido instancia expediente. ", cuo);
				if(instanciaExpediente!=null && instanciaExpediente.getExpediente() != null && !instanciaExpediente.getExpediente().getPartes().isEmpty()) {
					MovParte parte = instanciaExpediente.getExpediente().getPartes().stream().filter(mp->mp.getPersona()!=null && !ProjectUtils.isNullOrEmpty(mp.getPersona().getNumeroDocumento()) && mp.getPersona().getNumeroDocumento().equalsIgnoreCase(numeroDocumentoIdentidad)).findFirst().orElse(null);
					if(parte!=null) {
						MovPersona persona = parte.getPersona();
						log.info("{} obteniendo datos de expediente..", cuo);
						Expediente expp = new Expediente();
						log.info("{} Número unico: {}", cuo,instanciaExpediente.getNumeroUnico());
						expp.setNumeroUnico(instanciaExpediente.getNumeroUnico());
						log.info("{} Número incidente: {}", cuo,instanciaExpediente.getNumeroIncidente());
						expp.setNumeroIncidente(instanciaExpediente.getNumeroIncidente());
						log.info("{} Fecha ingreso: {}", cuo,instanciaExpediente.getFechaIngreso());
						if(instanciaExpediente.getFechaIngreso()!=null)
							expp.setFechaIngreso(ProjectUtils.convertDateToString(instanciaExpediente.getFechaIngreso(), ProjectConstants.FORMATO_FECHA_DD_MM_YYYY_HH_MM_SS_SSS));
						log.info("{} Formato expediente: {}", cuo,instanciaExpediente.getExpediente().getFormatoExpediente());
						expp.setFormato(instanciaExpediente.getExpediente().getFormatoExpediente());
						log.info("{} Distrito judicial: {}", cuo,instanciaExpediente.getExpediente().getDistritoJudicial().toString());
						if(instanciaExpediente.getExpediente().getDistritoJudicial()!=null)
							expp.setDistritoJudicial(instanciaExpediente.getExpediente().getDistritoJudicial().getNombreDistrito());
						log.info("{} Órgano jurisdiccional: {}", cuo,instanciaExpediente.getNombreOrganoJurisdiccional());
						expp.setJuzgado(instanciaExpediente.getNombreOrganoJurisdiccional());
						if(parte.getParteTipos()!=null && !parte.getParteTipos().isEmpty() && parte.getParteTipos().get(0).getTipoParte()!=null) {
							log.info("{} Tipo parte : {}",cuo, parte.getParteTipos().get(0).getTipoParte().getNombre());
							expp.setTipoParte(parte.getParteTipos().get(0).getTipoParte().getNombre());
						}
						if(persona!=null) {
							log.info("{} Persona : {} ", cuo, (persona.getPrimerApellido()+" "+persona.getSegundoApellido()+" "+persona.getNombres()));
							expp.setApellidosNombresParte(persona.getPrimerApellido()+" "+persona.getSegundoApellido()+" "+persona.getNombres());
						}
						
						lista.add(expp);

						log.info("{} Datos de expediente cargado.", cuo);
//						lista.add(new Expediente(instanciaExpediente.getNumeroUnico(), 
//								instanciaExpediente.getNumeroIncidente(), 
//								ProjectUtils.convertDateToString(instanciaExpediente.getFechaIngreso(), ProjectConstants.FORMATO_FECHA_DD_MM_YYYY_HH_MM_SS_SSS), 
//								instanciaExpediente.getExpediente().getFormatoExpediente(), 
//								instanciaExpediente.getExpediente().getDistritoJudicial()==null?null:instanciaExpediente.getExpediente().getDistritoJudicial().getNombreDistrito(), 
//								instanciaExpediente.getNombreOrganoJurisdiccional(), 
//								parte.getParteTipos().get(0).getTipoParte().getNombre(), 
//								persona==null?null:persona.getPrimerApellido()+" "+persona.getSegundoApellido()+" "+persona.getNombres()));
					}
				}
			});
			
		} catch (SQLGrammarException | IllegalArgumentException | ConstraintViolationException | DataIntegrityViolationException e) {
			throw new ErrorDaoException(ProjectConstants.C_ERROR_EJECUCION_SENTENCIA, 
					ProjectConstants.X_ERROR+ProjectConstants.Proceso.CONSULTA_EXPEDIENTES+ProjectConstants.X_ERROR_EJECUCION_SENTENCIA, 
					getJNDI(), 
					e.getMessage(), 
					e.getCause());
		}  

		log.info("{} FIN_DAO CONSULTA EXPEDIENTES", cuo);
		return lista;
	}

	@Override
	public ExpedienteDetalle consultarExpedienteDetalle(String cuo, BigDecimal numeroUnico, BigDecimal numeroIncidente) throws Exception {
		
		ExpedienteDetalle expedienteDetalle = new ExpedienteDetalle();
		try {
			StringBuilder stringQuery = new StringBuilder("SELECT mr FROM MovResolucion mr");
			stringQuery.append(" JOIN mr.instanciaExpediente mie");
			stringQuery.append(" JOIN mie.expediente me");
			stringQuery.append(" WHERE mie.activo='1'");
			stringQuery.append(" AND mie.numeroUnico=:"+MovInstanciaExpediente.P_NUMERO_UNICO);
			stringQuery.append(" AND mie.numeroIncidente=:"+MovInstanciaExpediente.P_NUMERO_INCIDENTE);
			stringQuery.append(" ORDER BY mr.id DESC");
			
			TypedQuery<MovResolucion> query = this.sf.getCurrentSession().createQuery(stringQuery.toString(), MovResolucion.class);
			query.setParameter(MovInstanciaExpediente.P_NUMERO_UNICO, numeroUnico);
			query.setParameter(MovInstanciaExpediente.P_NUMERO_INCIDENTE, numeroIncidente);
			
			StringBuilder init = new StringBuilder(ProjectConstants.ESTADO_ACTIVO);
			query.getResultStream().forEachOrdered(resolucion->{
				if(init.toString().equalsIgnoreCase(ProjectConstants.ESTADO_ACTIVO)) {
					//Ingreso de expediente
					MovInstanciaExpediente moviexp = resolucion.getInstanciaExpediente();
					MovExpediente movexp = moviexp.getExpediente();
					expedienteDetalle.getExpediente().setNumeroUnico(numeroUnico);
					expedienteDetalle.getExpediente().setNumeroIncidente(numeroIncidente);
					expedienteDetalle.getExpediente().setFechaIngreso(ProjectUtils.convertDateToString(moviexp.getFechaIngreso(), ProjectConstants.FORMATO_FECHA_YYYY_MM_DD_HH_MM_SS_SSS));
					expedienteDetalle.getExpediente().setProceso(moviexp.getNombreProceso());
					expedienteDetalle.getExpediente().setFormato(movexp.getFormatoExpediente());
					expedienteDetalle.getExpediente().setJuzgado(movexp.getNombreOrganoJurisdiccional());
					expedienteDetalle.getExpediente().setEspecialidad(movexp.getEspecialidad().getNombre());
					expedienteDetalle.getExpediente().setDistritoJudicial(movexp.getDistritoJudicial().getNombreDistrito());
					movexp.getEstados().stream()
						.filter(estado->estado.getActivo().equalsIgnoreCase(ProjectConstants.ESTADO_ACTIVO))
						.forEach(estado->{
							expedienteDetalle.getExpediente().setEstado(estado.getNombre());
						});
					//Ingreso partes
					movexp.getPartes().forEach(movparte->{
						MovPersona personaParte = movparte.getPersona();
						ParteProcesal parte = new ParteProcesal();
						parte.setPrimerApellido(personaParte.getPrimerApellido());
						parte.setSegundoApellido(personaParte.getSegundoApellido());
						parte.setNombres(personaParte.getNombres());
						parte.setNumeroDocumento(personaParte.getNumeroDocumento());
						parte.setTipoDocumento(personaParte.getTipoDocumentoPersona().getNombre());
						parte.setTipoParteProcesal(movparte.getParteTipos().get(0).getTipoParte().getNombre());
						expedienteDetalle.getPartes().add(parte);
					});
					init.append("NO");
				}
				
				ActoProcesal actoProcesal = new ActoProcesal();
				//Ingreso resolución
				actoProcesal.setNumeroUnico(numeroUnico);
				actoProcesal.setNumeroIncidente(numeroIncidente);
				actoProcesal.setFechaIngresoActo(ProjectUtils.convertDateToString(resolucion.getFechaIngresoActo(), ProjectConstants.FORMATO_FECHA_YYYY_MM_DD_HH_MM_SS_SSS));
				actoProcesal.setNumeroResolucion(resolucion.getNumeroResolucion());
				actoProcesal.setJuez(resolucion.getJuez());
				actoProcesal.setSecretario(resolucion.getSecretario());
				actoProcesal.setActoProcesal(resolucion.getNombreActoProcesal());
				actoProcesal.setSumilla(resolucion.getSumilla());
				
				//Ingreso Medida Proteccion
				resolucion.getMedidaProteccions().forEach(mp->{
					MedidaProteccion medida = new MedidaProteccion();
					medida.setEstadoDocumento(mp.getEstadoDocumento());
					medida.setFechaEjecucion(ProjectUtils.convertDateToString(mp.getFechaEjecucion(), ProjectConstants.FORMATO_FECHA_DD_MM_YYYY_HH_MM_SS_SSS));
					mp.getMedidaAdoptadas().forEach(ma->{
						MovParte mmp = ma.getParte();
						MovPersona personaParte = mmp.getPersona();
						medida.getMedidasAdoptadas().add(new MedidaAdoptada(ma.getTipoMedidaProteccion().getNombre(), 
								ma.getDescripcion(),
								mmp.getParteTipos().get(0).getTipoParte().getNombre(),
								personaParte.getPrimerApellido(),
								personaParte.getSegundoApellido(),
								personaParte.getNombres()));
					});
					actoProcesal.getMedidaProteccion().add(medida);
				});
				expedienteDetalle.getActoProcesal().add(actoProcesal);
			});

		} catch (SQLGrammarException | IllegalArgumentException | ConstraintViolationException | DataIntegrityViolationException e) {
			throw new ErrorDaoException(ProjectConstants.C_ERROR_EJECUCION_SENTENCIA, 
					ProjectConstants.X_ERROR+ProjectConstants.Proceso.CONSULTA_EXPEDIENTE_DETALLE+ProjectConstants.X_ERROR_EJECUCION_SENTENCIA, 
					getJNDI(), 
					e.getMessage(), 
					e.getCause());
		} 
		return expedienteDetalle;
	}

	@Override
	public String obtenerExpedienteResolucion(String cuo, BigDecimal numeroUnico, BigDecimal numeroIncidente,
			String fechaIngresoActo) throws Exception {
		StringBuilder uuidResolucion = new StringBuilder("");
		try {
			
			StringBuilder stringQuery = new StringBuilder("SELECT mda FROM MovDocumentoArchivo mda");
			stringQuery.append(" WHERE 1=1");
			stringQuery.append(" AND mda.nUnico=:").append(MovDocumentoArchivo.P_NUMERO_UNICO);
			stringQuery.append(" AND mda.nIncidente=:").append(MovDocumentoArchivo.P_NUMERO_INCIDENTE);
			stringQuery.append(" AND substring(mda.fIngresoacto,1,23)=:").append(MovDocumentoArchivo.P_INGRESO_ACTO);
			
			TypedQuery<MovDocumentoArchivo> query = this.sfCasillero.getCurrentSession().createQuery(stringQuery.toString(),MovDocumentoArchivo.class);
			query.setParameter(MovDocumentoArchivo.P_NUMERO_UNICO, numeroUnico);
			query.setParameter(MovDocumentoArchivo.P_NUMERO_INCIDENTE, numeroIncidente.intValue());
			query.setParameter(MovDocumentoArchivo.P_INGRESO_ACTO, fechaIngresoActo);
			query.getResultStream().forEach(documento->{
				uuidResolucion.append(documento.getXUuidAlfresco());
			});
			
		} catch (SQLGrammarException | IllegalArgumentException | ConstraintViolationException | DataIntegrityViolationException e) {
			throw new ErrorDaoException(ProjectConstants.C_ERROR_EJECUCION_SENTENCIA, 
					ProjectConstants.X_ERROR+ProjectConstants.Proceso.CONSULTA_EXPEDIENTE_DETALLE+ProjectConstants.X_ERROR_EJECUCION_SENTENCIA, 
					getJNDI(), 
					e.getMessage(), 
					e.getCause());
		} 
		return uuidResolucion.toString();
	}
	
	public String getJNDI() throws Exception{
		return (SessionFactoryUtils.getDataSource(sf)).getConnection().getMetaData().getURL();
	}

}
