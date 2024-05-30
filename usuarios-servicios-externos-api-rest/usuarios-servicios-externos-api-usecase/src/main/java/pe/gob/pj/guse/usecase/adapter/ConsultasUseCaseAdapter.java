package pe.gob.pj.guse.usecase.adapter;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import lombok.extern.slf4j.Slf4j;
import pe.gob.pj.guse.domain.exceptions.ErrorException;
import pe.gob.pj.guse.domain.model.client.PersonaReniec;
import pe.gob.pj.guse.domain.model.sidenum.Expediente;
import pe.gob.pj.guse.domain.model.sidenum.ExpedienteDetalle;
import pe.gob.pj.guse.domain.port.client.ReniecClientPort;
import pe.gob.pj.guse.domain.port.repository.ConsultaRepositoryPort;
import pe.gob.pj.guse.domain.port.usecase.ConsultasUseCasePort;
import pe.gob.pj.guse.domain.utils.ProjectConstants;
import pe.gob.pj.guse.domain.utils.ProjectProperties;
import pe.gob.pj.guse.domain.utils.ProjectUtils;
import pe.gob.pj.guse.domain.utils.file.CMISManager;
import pe.gob.pj.guse.domain.utils.file.CMISManagerImpl;

@Slf4j
@Service("consultasUseCasePort")
public class ConsultasUseCaseAdapter implements ConsultasUseCasePort, Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Autowired
	@Qualifier("consultaRepositoryPort")
	private ConsultaRepositoryPort repo;
	
	@Autowired
	@Qualifier("reniecClientPort")
	private ReniecClientPort client;

	@Override
	@Transactional(transactionManager = "txManagerSidenum", propagation = Propagation.REQUIRES_NEW, readOnly = true, rollbackFor = { Exception.class, SQLException.class})
	public List<Expediente> consultarExpedientes(String cuo, String tipoDocumento, String numeroDocumentoIdentidad, String codigoVerificacion, String fechaNacimieno,
			String fechaEmision) throws Exception {
		log.info("{} INICIO_SERVICE CONSULTA EXPEDIENTES", cuo);
		PersonaReniec personaReniec = client.consultaReniecPorDni(cuo, numeroDocumentoIdentidad);
		if(personaReniec==null || ProjectUtils.isNullOrEmpty(personaReniec.getNumeroDocumenoIdentidad()) || 
				(!ProjectUtils.isNullOrEmpty(personaReniec.getNumeroDocumenoIdentidad()) && (!personaReniec.getFechaExpedicion().equalsIgnoreCase(fechaEmision) || !personaReniec.getFechaNacimiento().equalsIgnoreCase(fechaNacimieno) || !personaReniec.getFlagVerificacion().equalsIgnoreCase(codigoVerificacion)))) {
			log.error("{} La persona con datos dni:{}, código verificación: {}, fecha nacimiento:{} y fecha emisión:{} no se encontro en RENIEC {}",cuo,numeroDocumentoIdentidad, codigoVerificacion, fechaNacimieno,fechaEmision);
			log.error("{} Datos para el dni:{} devueltos por RENIEC {}",cuo,numeroDocumentoIdentidad, personaReniec.toString());
			throw new ErrorException(ProjectConstants.C_ERROR_VALIDAR_PERSONA_RENIEC, 
					ProjectConstants.X_ERROR+ProjectConstants.Proceso.CONSULTA_EXPEDIENTES+ProjectConstants.X_ERROR_VALIDAR_PERSONA_RENIEC);
		}
		log.info("{} PASO VALIDACION RENIEC PersonaReniec {}", cuo,personaReniec.toString());
		List<Expediente> lista = repo.consultarExpedientes(cuo, tipoDocumento, numeroDocumentoIdentidad);
		log.info("{} FIN_SERVICE CONSULTA EXPEDIENTES", cuo);
		return lista;
	}

	@Override
	@Transactional(transactionManager = "txManagerSidenum", propagation = Propagation.REQUIRES_NEW, readOnly = true, rollbackFor = { Exception.class, SQLException.class})
	public ExpedienteDetalle consultarExpedienteDetalle(String cuo, BigDecimal numeroUnico, BigDecimal numeroIncidente) throws Exception {
		ExpedienteDetalle expedienteDetalle = repo.consultarExpedienteDetalle(cuo, numeroUnico, numeroIncidente);
		if(expedienteDetalle.getActoProcesal().size()<1) {
			log.error("{} No se encontro resolución para el expediente => {}",cuo,expedienteDetalle.toString());
			throw new ErrorException(ProjectConstants.C_ERROR_EXPEDIENTE_DETALLE, 
					ProjectConstants.X_ERROR+ProjectConstants.Proceso.CONSULTA_EXPEDIENTES+ProjectConstants.X_ERROR_EXPEDIENTE_DETALLE);
		}
		return expedienteDetalle;
	}

	@Override
	@Transactional(transactionManager = "txManagerCasillero", propagation = Propagation.REQUIRES_NEW, readOnly = true, rollbackFor = { Exception.class, SQLException.class})
	public byte[] obtenerExpedienteResolucion(String cuo, BigDecimal numeroUnico, BigDecimal numeroIncidente,
			String fechaIngresoActo) throws Exception {
		String uuidResolucion = repo.obtenerExpedienteResolucion(cuo, numeroUnico, numeroIncidente, fechaIngresoActo);
		
		String HOST_ALFRESCO = ProjectProperties.getInstance().getAlfrescoCasilleroIp();
		String PUERTO_ALFRESCO = ProjectProperties.getInstance().getAlfrescoCasilleroPuerto();
		String USUARIO_ALFRESCO = ProjectProperties.getInstance().getAlfrescoCasilleroUsuario();
		String CLAVE_ALFRESCO = ProjectProperties.getInstance().getAlfrescoCasilleroClave();
		String PATH_ALFRESCO = ProjectProperties.getInstance().getAlfrescoCasilleroPath();
		String VERSION_ALFRESCO = ProjectProperties.getInstance().getAlfrescoCasilleroVersion();
		CMISManager cmisManager = new CMISManagerImpl(cuo,HOST_ALFRESCO, PUERTO_ALFRESCO, USUARIO_ALFRESCO, CLAVE_ALFRESCO, PATH_ALFRESCO, VERSION_ALFRESCO);
		
		byte[] resolucion = cmisManager.getFileByUuid(uuidResolucion);
		
		if(resolucion==null) {
			log.error("{} No se pudo obtener la resolución número unico: {}, número incidente: {}, ingreso acto: {} y  uuid : {} con origen: Alfresco CASILLERO.", cuo,numeroUnico,numeroIncidente,fechaIngresoActo,uuidResolucion);
			throw new ErrorException(ProjectConstants.C_ERROR_EXPEDIENTE_RESOLUCION, 
					ProjectConstants.X_ERROR+ProjectConstants.Proceso.CONSULTA_EXPEDIENTE_RESOLUCION+ProjectConstants.X_ERROR_EXPEDIENTE_RESOLUCION);
		}
		
		return resolucion;
	}

}
