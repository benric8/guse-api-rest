package pe.gob.pj.guse.infraestructure.client;

import java.io.Serializable;
import java.util.List;

import javax.xml.ws.Holder;

import org.springframework.stereotype.Component;

import pe.gob.pj.client.reniec.consultas.ws.ConsultaReniec;
import pe.gob.pj.client.reniec.consultas.ws.ConsultaReniecPortType;
import pe.gob.pj.client.reniec.consultas.ws.ConsultaReniecResponse;
import pe.gob.pj.guse.domain.model.client.PersonaReniec;
import pe.gob.pj.guse.domain.port.client.ReniecClientPort;
import pe.gob.pj.guse.domain.utils.ProjectConstants;
import pe.gob.pj.guse.domain.utils.ProjectProperties;
import pe.gob.pj.guse.domain.utils.ProjectUtils;
import pe.gob.pj.guse.infraestructure.client.utils.ReniecUtils;

@Component("reniecClientPort")
public class ReniecClientAdapter implements Serializable, ReniecClientPort {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public PersonaReniec consultaReniecPorDni(String cuo, String dni) throws Exception {

		String ENDPOINT = ProjectProperties.getInstance().getReniecEndpoint();
		int TIMEOUT = ProjectProperties.getInstance().getReniecTimeout();

		ConsultaReniecPortType portType = ReniecUtils.getPortReniec(ENDPOINT, TIMEOUT);

		ConsultaReniec consultaReniecRequest = new ConsultaReniec();
		consultaReniecRequest.setReqDni(dni);
		consultaReniecRequest.setReqDniConsultante(ProjectProperties.getInstance().getReniecDniConsultante());
		consultaReniecRequest.setReqTipoConsulta(ProjectConstants.RENIEC_TIPO_BUSQUEDA_DNI);
		consultaReniecRequest.setReqUsuario(ProjectConstants.RENIEC_USUARIO_CONSULTA_DEFAULT);
		consultaReniecRequest.setReqIp(ProjectUtils.getIp());

		ConsultaReniecResponse response = new ConsultaReniecResponse();
		Holder<String> resTrama = new Holder <String>() ;
		Holder<String> resCodigo = new Holder <String>() ;
		Holder<String> resDescripcion = new Holder <String>();
		Holder<String> resTotalRegistros = new Holder <String>();
		Holder<String> resPersona= new Holder <String>();
		Holder<byte[]> resFoto = new Holder <byte[]>();
		Holder<byte[]> resFirma =  new Holder <byte[]>();
		Holder<String> resListaPersonas = new Holder <String>();

		portType.consultaReniec(consultaReniecRequest.getReqTrama(), consultaReniecRequest.getReqDniConsultante(), consultaReniecRequest.getReqTipoConsulta(), consultaReniecRequest.getReqUsuario(), consultaReniecRequest.getReqIp(), consultaReniecRequest.getReqDni(), consultaReniecRequest.getReqNombres(), 
				consultaReniecRequest.getReqApellidoPaterno(), consultaReniecRequest.getReqApellidoMaterno(), consultaReniecRequest.getReqNroRegistros(), consultaReniecRequest.getReqGrupo(), consultaReniecRequest.getReqDniApoderado(), consultaReniecRequest.getReqTipoVinculoApoderado(), 
				resTrama, resCodigo, resDescripcion, resTotalRegistros, resPersona, resFoto, resFirma, resListaPersonas);
		
		response.setResCodigo(resCodigo.value);
		response.setResDescripcion(resDescripcion.value);
		response.setResFirma(resFirma.value);
		response.setResFoto(resFoto.value);
		response.setResListaPersonas(resListaPersonas.value);
		response.setResPersona(resPersona.value);
		response.setResTotalRegistros(resTotalRegistros.value);
		response.setResTrama(resTrama.value);
		
		return cargarPersonaReniecResponseBean(response);
	}

	@Override
	public List<PersonaReniec> consultaReniecPorApellidosNombres(String cuo, String primerApellido,
			String segundoApellido, String nombres) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
	
	public  PersonaReniec cargarPersonaReniecResponseBean(ConsultaReniecResponse consultaReniecResponse) {

		String[] arr_pers = consultaReniecResponse.getResPersona().split("\t");
		PersonaReniec persona = new PersonaReniec();
		
		int i = 0;

		try {
			persona.setNumeroDocumenoIdentidad(arr_pers[i++]);
		} catch (Throwable ex) {
		}
		;
		try {
			persona.setFlagVerificacion(arr_pers[i++]);
		} catch (Throwable ex) {
		}
		;
		try {
			persona.setApellidoPaterno(arr_pers[i++]);
		} catch (Throwable ex) {
		}
		;
		try {
			persona.setApellidoMaterno(arr_pers[i++]);
		} catch (Throwable ex) {
		}
		;
		try {
			persona.setApellidoCasado(arr_pers[i++]);
		} catch (Throwable ex) {
		}
		;
		try {
			persona.setNombres(arr_pers[i++]);
		} catch (Throwable ex) {
		}
		;
		try {
			persona.setCodUbiDepDom(arr_pers[i++]);
		} catch (Throwable ex) {
		}
		;
		try {
			persona.setCodUbiProDom(arr_pers[i++]);
		} catch (Throwable ex) {
		}
		;
		try {
			persona.setCodUbiDisDom(arr_pers[i++]);
		} catch (Throwable ex) {
		}
		;
		try {
			persona.setCodUbiLocDom(arr_pers[i++]);
		} catch (Throwable ex) {
		}
		;
		try {
			persona.setDepDom(arr_pers[i++]);
		} catch (Throwable ex) {
		}
		;
		try {
			persona.setProDom(arr_pers[i++]);
		} catch (Throwable ex) {
		}
		;
		try {
			persona.setDisDom(arr_pers[i++]);
		} catch (Throwable ex) {
		}
		;
		try {
			persona.setLocDom(arr_pers[i++]);
		} catch (Throwable ex) {
		}
		;
		try {
			persona.setEstCiv(arr_pers[i++]);
		} catch (Throwable ex) {
		}
		;
		try {
			persona.setGraInst(arr_pers[i++]);
		} catch (Throwable ex) {
		}
		;
		try {
			persona.setEstatura(arr_pers[i++]);
		} catch (Throwable ex) {
		}
		;
		try {
			persona.setSexo(arr_pers[i++]);
		} catch (Throwable ex) {
		}
		;
		try {
			persona.setDocSustTipDoc(arr_pers[i++]);
		} catch (Throwable ex) {
		}
		;
		try {
			persona.setDocSustNroDoc(arr_pers[i++]);
		} catch (Throwable ex) {
		}
		;
		try {
			persona.setCodUbiDepNac(arr_pers[i++]);
		} catch (Throwable ex) {
		}
		;
		try {
			persona.setCodUbiProNac(arr_pers[i++]);
		} catch (Throwable ex) {
		}
		;
		try {
			persona.setCodUbiDisNac(arr_pers[i++]);
		} catch (Throwable ex) {
		}
		;
		try {
			persona.setCodUbiLocNac(arr_pers[i++]);
		} catch (Throwable ex) {
		}
		;
		try {
			persona.setDepNac(arr_pers[i++]);
		} catch (Throwable ex) {
		}
		;
		try {
			persona.setProNac(arr_pers[i++]);
		} catch (Throwable ex) {
		}
		;
		try {
			persona.setDisNac(arr_pers[i++]);
		} catch (Throwable ex) {
		}
		;
		try {
			persona.setLocNac(arr_pers[i++]);
		} catch (Throwable ex) {
		}
		;
		try {
			persona.setFechaNacimiento(arr_pers[i++]);
		} catch (Throwable ex) {
		}
		;
		try {
			persona.setDocPadTipDoc(arr_pers[i++]);
		} catch (Throwable ex) {
		}
		;
		try {
			persona.setDocPadNumDoc(arr_pers[i++]);
		} catch (Throwable ex) {
		}
		;
		try {
			persona.setNomPad(arr_pers[i++]);
		} catch (Throwable ex) {
		}
		;
		try {
			persona.setDocMadTipDoc(arr_pers[i++]);
		} catch (Throwable ex) {
		}
		;
		try {
			persona.setDocMadNumDoc(arr_pers[i++]);
		} catch (Throwable ex) {
		}
		;
		try {
			persona.setNomMad(arr_pers[i++]);
		} catch (Throwable ex) {
		}
		;
		try {
			persona.setFechaInscripcion(arr_pers[i++]);
		} catch (Exception e) {
		}
		;
		try {
			persona.setFechaExpedicion(arr_pers[i++]);
		} catch (Throwable ex) {
		}
		;
		try {
			persona.setFechaFallecimiento(arr_pers[i++]);
		} catch (Throwable ex) {
		}
		;
		try {
			persona.setConsVot(arr_pers[i++]);
		} catch (Throwable ex) {
		}
		;
		try {
			persona.setFecCad(arr_pers[i++]);
		} catch (Throwable ex) {
		}
		;
		try {
			persona.setRestric(arr_pers[i++]);
		} catch (Throwable ex) {
		}
		;
		try {
			persona.setPrefDir(arr_pers[i++]);
		} catch (Throwable ex) {
		}
		;
		try {
			persona.setDireccion(arr_pers[i++]);
		} catch (Throwable ex) {
		}
		;
		try {
			persona.setNroDir(arr_pers[i++]);
		} catch (Throwable ex) {
		}
		;
		try {
			persona.setBlocOChal(arr_pers[i++]);
		} catch (Throwable ex) {
		}
		;
		try {
			persona.setInterior(arr_pers[i++]);
		} catch (Throwable ex) {
		}
		;
		try {
			persona.setUrbanizacion(arr_pers[i++]);
		} catch (Throwable ex) {
		}
		;
		try {
			persona.setEtapa(arr_pers[i++]);
		} catch (Throwable ex) {
		}
		;
		try {
			persona.setManzana(arr_pers[i++]);
		} catch (Throwable ex) {
		}
		;
		try {
			persona.setLote(arr_pers[i++]);
		} catch (Throwable ex) {
		}
		;
		try {
			persona.setPreBlocOChal(arr_pers[i++]);
		} catch (Throwable ex) {
		}
		;
		try {
			persona.setPreDptoPisoInt(arr_pers[i++]);
		} catch (Throwable ex) {
		}
		;
		try {
			persona.setPreUrbCondResid(arr_pers[i++]);
		} catch (Throwable ex) {
		}
		;
		try {
			persona.setReservado(arr_pers[i++]);
		} catch (Throwable ex) {
		}
		;
		try {
			persona.setLongitudFoto(Integer.parseInt(arr_pers[i++]));
		} catch (Throwable ex) {
		}
		;
		try {
			persona.setLongitudFirma(Integer.parseInt(arr_pers[i++]));
		} catch (Throwable ex) {
		}
		;
		try {
			persona.setReservadoFotoFirma1(Integer.parseInt(arr_pers[i++]));
		} catch (Throwable ex) {
		}
		;
		try {
			persona.setReservadoFotoFirma2(arr_pers[i++]);
		} catch (Throwable ex) {
		}
		;
		try {
			persona.setFoto(consultaReniecResponse.getResFoto());
		} catch (Throwable ex) {
		}
		;
		try {
			persona.setFirma(consultaReniecResponse.getResFirma());
		} catch (Throwable ex) {
		}
		;
		return persona;
	}

}
