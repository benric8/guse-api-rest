package pe.gob.pj.guse.domain.utils;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
@Configuration
@PropertySources(value = { @PropertySource("classpath:usuarios-servicios-externos-api.properties") })
@Component
public class PropertyConfig implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	// #SEGURIDAD
	@Value("${configuracion.seguridad.secretToken:null}")
	private String seguridadSecretToken;
	@Value("${configuracion.seguridad.idaplicativo:0}")
	private Integer seguridadIdAplicativo;
	@Value("${configuracion.seguridad.authenticate.token.tiempo.expira.segundos:300}")
	private Integer seguridadTiempoExpiraSegundos;
	@Value("${configuracion.seguridad.authenticate.token.tiempo.refresh.segundos:180}")
	private Integer seguridadTiempoRefreshSegundos;

	// #RENIEC
	@Value("${wsreniec.endpoint:http://172.18.2.28:7800/ConsultaReniecService}")
	private String reniecEndpoint;
	@Value("${wsreniec.dniconsultante:09914629}")
	private String reniecDniConsultante;
	@Value("${wsreniec.timeout:50}")
	private Integer reniecTimeout;
	
	// #ALFRESCO CASILLERO
	@Value("${alfresco.casillero.ip:null}")
	private String alfrescoCasilleroIp;
	@Value("${alfresco.casillero.puerto:null}")
	private String alfrescoCasilleroPuerto;
	@Value("${alfresco.casillero.usuario:null}")
	private String alfrescoCasilleroUsuario;
	@Value("${alfresco.casillero.clave:null}")
	private String alfrescoCasilleroClave;
	@Value("${alfresco.casillero.path:/app:company_home/cm:CASILLERO}")
	private String alfrescoCasilleroPath;
	@Value("${alfresco.casillero.version:4.2}")
	private String alfrescoCasilleroVersion;

	// #CAPTCHA
	@Value("${captcha.url:null}")
	private String captchaUrl;
	@Value("${captcha.token:null}")
	private String captchaToken;
	
}
