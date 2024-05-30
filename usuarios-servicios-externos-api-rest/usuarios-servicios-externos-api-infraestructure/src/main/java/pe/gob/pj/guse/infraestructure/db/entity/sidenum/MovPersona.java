package pe.gob.pj.guse.infraestructure.db.entity.sidenum;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Data;
import lombok.EqualsAndHashCode;
import pe.gob.pj.guse.domain.utils.ProjectConstants;
import pe.gob.pj.guse.infraestructure.db.entity.Auditoria;

@EqualsAndHashCode(callSuper = false)
@Data
@Entity
@Table(name = "MOV_PERSONA", schema = ProjectConstants.Esquema.SIDENUM)
public class MovPersona extends Auditoria implements Serializable {/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public static final String P_ACTIVO = "activo";
	public static final String P_NUMERO_DOCUMENTO = "numeroDocumento";
	
	@Id
	@Column(name="N_PERSONA" , nullable = false)
	private Integer id;
	@Column(name="N_PERSONA_ORIGEN" , nullable = false)
	private Integer idPersonaEnPolicia;
	@Column(name="X_APE_PATERNO" , nullable = false)
	private String primerApellido;
	@Column(name="X_APE_MATERNO" , nullable = false)
	private String segundoApellido;
	@Column(name="X_NOMBRE" , nullable = false)
	private String nombres;
	@Temporal(TemporalType.DATE)
	@Column(name="F_FEC_NACIMIENTO" , nullable = false)
	private Date fechaNacimiento;
	@Column(name="X_NUM_DOC_IDENTIDAD" , nullable = false)
	private String numeroDocumento;
	@Column(name="L_SEXO" , nullable = false)
	private String sexo;
	@Column(name="N_EDAD" , nullable = false)
	private Integer edad;
	@Column(name="L_FLAG_RENIEC" , nullable = false)
	private String flagReniec;
	
	@ManyToOne(optional = false, cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
	@JoinColumn(name = "N_ESTADO_CIVIL")
	private MaeEstadoCivil estadoCivil;
	@ManyToOne(optional = false, cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
	@JoinColumn(name = "N_TIPO_DOCUMENTO_PERSONA")
	private MaeTipoDocumentoPersona tipoDocumentoPersona;
	@ManyToOne(optional = false, cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
	@JoinColumn(name = "N_LENGUA_MATERNA")
	private MaeLenguaMaterna lenguaMaterna;
	@ManyToOne(optional = false, cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
	@JoinColumn(name = "N_PAIS")
	private MaePais pais;
}
