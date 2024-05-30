package pe.gob.pj.guse.infraestructure.rest.adapter;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;
import pe.gob.pj.guse.domain.exceptions.ErrorDaoException;
import pe.gob.pj.guse.domain.exceptions.ErrorException;
import pe.gob.pj.guse.domain.model.sidenum.Expediente;
import pe.gob.pj.guse.domain.model.sidenum.ExpedienteDetalle;
import pe.gob.pj.guse.domain.port.usecase.ConsultasUseCasePort;
import pe.gob.pj.guse.domain.utils.CaptchaUtils;
import pe.gob.pj.guse.domain.utils.ProjectConstants;
import pe.gob.pj.guse.domain.utils.ProjectUtils;
import pe.gob.pj.guse.infraestructure.rest.request.ConsultaExpedientesRequest;
import pe.gob.pj.guse.infraestructure.rest.response.GlobalResponse;


@Slf4j
@Validated
@RestController
@RequestMapping(value="consulta", produces = MediaType.APPLICATION_JSON_VALUE)
public class ConsultaController implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Autowired
	@Qualifier("consultasUseCasePort")
	private ConsultasUseCasePort consultaUC;

	@PostMapping(value = "expedientes", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<GlobalResponse> consultaExpedientes(@RequestAttribute(name = ProjectConstants.AUD_CUO) String cuo, 
			@RequestAttribute(name=ProjectConstants.AUD_IP) String ipRemota,
			@Validated @RequestBody ConsultaExpedientesRequest captcha
			){
		GlobalResponse res = new GlobalResponse();
		res.setCodigoOperacion(cuo.substring(1, cuo.length()-1));
		try {
			try {
				
				if(!captcha.getAplicaCaptcha().equalsIgnoreCase(ProjectConstants.ESTADO_ACTIVO_S) || (captcha.getAplicaCaptcha().equalsIgnoreCase(ProjectConstants.ESTADO_ACTIVO_S) && !ProjectUtils.isNullOrEmpty(captcha.getTokenCaptcha()))) {
					if(!captcha.getAplicaCaptcha().equalsIgnoreCase(ProjectConstants.ESTADO_ACTIVO_S) || CaptchaUtils.validCaptcha(captcha.getTokenCaptcha(), ipRemota, cuo) ) {
						List<Expediente> lista = consultaUC.consultarExpedientes(cuo, captcha.getTipoDocumento(), captcha.getDocumentoIdentidad(), captcha.getCodigoVerificacion(), captcha.getFechaNacimiento(), captcha.getFechaEmision());
						res.setCodigo(ProjectConstants.C_EXITO);
						res.setDescripcion(ProjectConstants.X_EXITO);
						res.setData(lista);
					}else {
						log.error("{} Dstos de validación captcha -> indicador de validación: {}, token captcha: {} y la ip de la petición", cuo, captcha.getAplicaCaptcha(), captcha.getTokenCaptcha(), ipRemota);
						throw new ErrorException(ProjectConstants.C_ERROR_CAPTCHA, 
								ProjectConstants.X_ERROR+ProjectConstants.Proceso.CONSULTA_EXPEDIENTES+ProjectConstants.X_ERROR_CAPTCHA);
					}
				}else {
					log.error("{} Dstos de validación captcha -> indicador de validación: {}, token captcha: {} y la ip de la petición", cuo, captcha.getAplicaCaptcha(), captcha.getTokenCaptcha(), ipRemota);
					throw new ErrorException(ProjectConstants.C_ERROR_CAPTCHA, 
							ProjectConstants.X_ERROR+ProjectConstants.Proceso.CONSULTA_EXPEDIENTES+ProjectConstants.X_ERROR_CAPTCHA);
				}	
			} catch (ErrorDaoException | ErrorException e){
				throw e;
			} catch (Exception e) {
				e.printStackTrace();
				throw new ErrorException(ProjectConstants.C_ERROR_INESPERADO, 
						ProjectConstants.X_ERROR+ProjectConstants.Proceso.CONSULTA_EXPEDIENTES+ProjectConstants.X_ERROR_INESPERADO,
						e.getMessage(),
						e.getCause());
			}
		} catch (ErrorException e) {
			e.printStackTrace();
			res.setCodigo(e.getCodigo());
			res.setDescripcion(e.getDescripcion());
			log.error("{} Error al consultar expedientes, Descripción: {}", cuo , e.getDescripcion());
			log.error("{} Error al consultar expedientes, Detalle => ERROR: {} | CAUSA {}", cuo , e.getMessage(), ProjectUtils.obtenerCausaException(e));
		} catch (ErrorDaoException e) {
			e.printStackTrace();
			res.setCodigo(e.getCodigo());
			res.setDescripcion(e.getDescripcion());
			log.error("{} Error al consultar expedientes, Descripción: {}", cuo , e.getDescripcion());
			log.error("{} Error al consultar expedientes, ERROR SP: {} ERROR DB: {}", cuo , e.getDescripcionErrorSP()!=null?e.getDescripcionErrorSP():"", e.getDescripcionErrorDB()!=null?e.getDescripcionErrorDB():"");
			log.error("{} Error al consultar expedientes, Detalle => ERROR: {} | CAUSA {}", cuo , e.getMessage(), ProjectUtils.obtenerCausaException(e));
		} catch (Exception e) {
			e.printStackTrace();
			res.setCodigo(ProjectConstants.C_ERROR_INESPERADO);
			res.setDescripcion(ProjectConstants.X_ERROR+ProjectConstants.Proceso.CONSULTA_EXPEDIENTES+ProjectConstants.X_ERROR_INESPERADO);
			log.error("{} Error al consultar expedientes, Descripción : {}", cuo , res.getDescripcion());
			log.error("{} Error al consultar expedientes, Detalle => ERROR: {} | CAUSA {}", cuo , e.getMessage(), ProjectUtils.obtenerCausaException(e));
		}
		
		return new ResponseEntity<GlobalResponse>(res, HttpStatus.OK);
	}

	@GetMapping(value = "expediente/detalle")
	public ResponseEntity<GlobalResponse> consultaExpedienteDetalle(@RequestAttribute(name = ProjectConstants.AUD_CUO) String cuo,
			@NotNull(message = "El parámetro numeroUnico no puede tener un valor nulo.")
			@RequestParam(required = true) BigDecimal numeroUnico,
			@NotNull(message = "El parámetro numeroIncidente no puede tener un valor nulo.")
			@RequestParam(required = true) BigDecimal numeroIncidente){
		GlobalResponse res = new GlobalResponse();
		res.setCodigoOperacion(cuo.substring(1, cuo.length()-1));
		try {
			try {
				ExpedienteDetalle expedienteDetalle = consultaUC.consultarExpedienteDetalle(cuo, numeroUnico, numeroIncidente);
				res.setCodigo(ProjectConstants.C_EXITO);
				res.setDescripcion(ProjectConstants.X_EXITO);
				res.setData(expedienteDetalle);
			} catch (ErrorDaoException | ErrorException e){
				throw e;
			} catch (Exception e) {
				throw new ErrorException(ProjectConstants.C_ERROR_INESPERADO, 
						ProjectConstants.X_ERROR+ProjectConstants.Proceso.CONSULTA_EXPEDIENTE_DETALLE+ProjectConstants.X_ERROR_INESPERADO,
						e.getMessage(),
						e.getCause());
			}
		} catch (ErrorException e) {
			res.setCodigo(e.getCodigo());
			res.setDescripcion(e.getDescripcion());
			log.error("{} Error al consultar expediente detalle, Descripción: {}", cuo , e.getDescripcion());
			log.error("{} Error al consultar expediente detalle, Detalle => ERROR: {} | CAUSA {}", cuo , e.getMessage(), ProjectUtils.obtenerCausaException(e));
		} catch (ErrorDaoException e) {
			res.setCodigo(e.getCodigo());
			res.setDescripcion(e.getDescripcion());
			log.error("{} Error al consultar expediente detalle, Descripción: {}", cuo , e.getDescripcion());
			log.error("{} Error al consultar expediente detalle, ERROR SP: {} ERROR DB: {}", cuo , e.getDescripcionErrorSP()!=null?e.getDescripcionErrorSP():"", e.getDescripcionErrorDB()!=null?e.getDescripcionErrorDB():"");
			log.error("{} Error al consultar expediente detalle, Detalle => ERROR: {} | CAUSA {}", cuo , e.getMessage(), ProjectUtils.obtenerCausaException(e));
		} catch (Exception e) {
			res.setCodigo(ProjectConstants.C_ERROR_INESPERADO);
			res.setDescripcion(ProjectConstants.X_ERROR+ProjectConstants.Proceso.CONSULTA_EXPEDIENTE_DETALLE+ProjectConstants.X_ERROR_INESPERADO);
			log.error("{} Error al consultar expediente detalle, Descripción : {}", cuo , res.getDescripcion());
			log.error("{} Error al consultar expediente detalle, Detalle => ERROR: {} | CAUSA {}", cuo , e.getMessage(), ProjectUtils.obtenerCausaException(e));
		}
		
		return new ResponseEntity<GlobalResponse>(res, HttpStatus.OK);
	}

	@GetMapping(value = "expediente/resolucion")
	public ResponseEntity<GlobalResponse> consultaExpedienteResolucion(@RequestAttribute(name = ProjectConstants.AUD_CUO) String cuo,
			@NotNull(message = "El parámetro numeroUnico no puede tener un valor nulo.")
			@RequestParam(required = true) BigDecimal numeroUnico,
			@NotNull(message = "El parámetro numeroIncidente no puede tener un valor nulo.")
			@RequestParam(required = true) BigDecimal numeroIncidente,
			@Pattern(regexp = ProjectConstants.PATTERN_FECHA_YYYY_MM_DD_HH_MM_SS_SSS, message = "El parámetro fechaIngresoActo no tiene un formato valid (yyy-MM-dd HH:mm:ss.sss).")
			@RequestParam(required = true) String fechaIngresoActo){
		GlobalResponse res = new GlobalResponse();
		res.setCodigoOperacion(cuo.substring(1, cuo.length()-1));
		try {
			try {
				byte[] archivoResolucion = consultaUC.obtenerExpedienteResolucion(cuo, numeroUnico, numeroIncidente, fechaIngresoActo);
				res.setCodigo(ProjectConstants.C_EXITO);
				res.setDescripcion(ProjectConstants.X_EXITO);
				res.setData(archivoResolucion);
			} catch (ErrorDaoException | ErrorException e){
				throw e;
			} catch (Exception e) {
				throw new ErrorException(ProjectConstants.C_ERROR_INESPERADO, 
						ProjectConstants.X_ERROR+ProjectConstants.Proceso.CONSULTA_EXPEDIENTE_RESOLUCION+ProjectConstants.X_ERROR_INESPERADO,
						e.getMessage(),
						e.getCause());
			}
		} catch (ErrorException e) {
			res.setCodigo(e.getCodigo());
			res.setDescripcion(e.getDescripcion());
			log.error("{} Error al consultar expediente resolución, Descripción: {}", cuo , e.getDescripcion());
			log.error("{} Error al consultar expediente resolución, Detalle => ERROR: {} | CAUSA {}", cuo , e.getMessage(), ProjectUtils.obtenerCausaException(e));
		} catch (ErrorDaoException e) {
			res.setCodigo(e.getCodigo());
			res.setDescripcion(e.getDescripcion());
			log.error("{} Error al consultar expediente resolución, Descripción: {}", cuo , e.getDescripcion());
			log.error("{} Error al consultar expediente resolución, ERROR SP: {} ERROR DB: {}", cuo , e.getDescripcionErrorSP()!=null?e.getDescripcionErrorSP():"", e.getDescripcionErrorDB()!=null?e.getDescripcionErrorDB():"");
			log.error("{} Error al consultar expediente resolución, Detalle => ERROR: {} | CAUSA {}", cuo , e.getMessage(), ProjectUtils.obtenerCausaException(e));
		} catch (Exception e) {
			res.setCodigo(ProjectConstants.C_ERROR_INESPERADO);
			res.setDescripcion(ProjectConstants.X_ERROR+ProjectConstants.Proceso.CONSULTA_EXPEDIENTE_RESOLUCION+ProjectConstants.X_ERROR_INESPERADO);
			log.error("{} Error al consultar expediente resolución, Descripción : {}", cuo , res.getDescripcion());
			log.error("{} Error al consultar expediente resolución, Detalle => ERROR: {} | CAUSA {}", cuo , e.getMessage(), ProjectUtils.obtenerCausaException(e));
		}
		
		return new ResponseEntity<GlobalResponse>(res, HttpStatus.OK);
	}

}
