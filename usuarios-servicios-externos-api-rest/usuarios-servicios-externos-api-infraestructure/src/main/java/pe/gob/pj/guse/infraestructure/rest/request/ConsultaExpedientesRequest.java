package pe.gob.pj.guse.infraestructure.rest.request;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
import pe.gob.pj.guse.domain.utils.ProjectConstants;

@Data
public class ConsultaExpedientesRequest implements Serializable {
	/**
	* 
	*/
	private static final long serialVersionUID = 1L;
	
	@Pattern(regexp = ProjectConstants.PATTERN_NUMBER, message = "El parámetro tipoDocumento solo permite valores numericos.")
	@NotBlank(message = "El parámetro tipoDocumento no puede tener un valor vacio.")
	@Length(min = 1, max = 1, message = "El parámetro tipoDocumento tiene un tamaño no valido [min=8,max=12].") 
	@JsonProperty("tipoDocumento")
	private String tipoDocumento;
	
	@Pattern(regexp = ProjectConstants.PATTERN_NUMBER, message = "El parámetro documentoIdentidad solo permite valores numericos.")
	@NotBlank(message = "El parámetro documentoIdentidad no puede tener un valor vacio.")
	@Length(min = 8, max = 12, message = "El parámetro documentoIdentidad tiene un tamaño no valido [min=8,max=12].") 
	@JsonProperty("documentoIdentidad")
	private String documentoIdentidad;
	
	@Pattern(regexp = ProjectConstants.PATTERN_ALPHANUMBER, message = "El parámetro codigoVerificacion solo permite valores numericos.")
	@NotBlank(message = "El parámetro codigoVerificacion no puede tener un valor vacio.")
	@Length(min = 1, max = 1, message = "El parámetro codigoVerificacion tiene un tamaño no valido [min=1,max=1].") 
	@JsonProperty("codigoVerificacion")
	private String codigoVerificacion;
	
	@Pattern(regexp = ProjectConstants.PATTERN_FECHA, message = "El parámetro fechaNacimiento tiene un formato no válido (dd/MM/yyyy).")
	@JsonProperty("fechaNacimiento")
	private String fechaNacimiento;
	
	@Pattern(regexp = ProjectConstants.PATTERN_FECHA, message = "El parámetro fechaEmision tiene un formato no válido (dd/MM/yyyy).")
	@JsonProperty("fechaEmision")
	private String fechaEmision;

	@Pattern(regexp = ProjectConstants.PATTERN_S_N, message = "El parámetro aplicaCaptcha tiene un formato no válido [S|N].")
	@NotNull(message = "El parámetro aplicaCaptcha no puede ser nulo.")
	@JsonProperty("aplicaCaptcha")
	private String aplicaCaptcha;

	// @NotNull(message = "El captcha no puede ser null")
	// @NotBlank(message = "El captcha iene un valor incorrecto")
	@JsonProperty("tokenCaptcha")
	private String tokenCaptcha;

}
