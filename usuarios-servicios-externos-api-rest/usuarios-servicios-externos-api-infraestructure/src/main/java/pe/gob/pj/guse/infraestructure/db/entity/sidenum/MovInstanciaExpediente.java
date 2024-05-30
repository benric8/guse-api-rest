package pe.gob.pj.guse.infraestructure.db.entity.sidenum;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
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
@Table(name = "MOV_INSTANCIA_EXPEDIENTE", schema = ProjectConstants.Esquema.SIDENUM)
public class MovInstanciaExpediente extends Auditoria implements Serializable {/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public static final String P_ID = "id";
	public static final String P_ACTIVO = "activo";
	public static final String P_NUMERO_UNICO = "numeroUnico";
	public static final String P_NUMERO_INCIDENTE = "numeroIncidente";
	
	@Id
	@Column(name="N_INSTANCIA_EXPEDIENTE", nullable = false)
	private Integer id;
	@Column(name="N_UNICO", nullable = false)
	private BigDecimal numeroUnico;
	@Column(name="N_INCIDENTE", nullable = false)
	private BigDecimal numeroIncidente;
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="F_INGRESO", nullable = false)
	private Date fechaIngreso;
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="F_HECHO", nullable = true)
	private Date fechaHecho;
	@Column(name="C_ORGANO_JURISDICCIONAL", nullable = false)
	private String codigoOrganoJurisdiccional;
	@Column(name="X_ORGANO_JURISDICCIONAL", nullable = false)
	private String nombreOrganoJurisdiccional;
	@Column(name="C_PROVINCIA_JUDICIAL", nullable = false)
	private String codigoProvinciaJudicial;
	@Column(name="X_PROVINCIA_JUDICIAL", nullable = false)
	private String nombreProvinciaJudicial;
	@Column(name="C_MOTIVO_INGRESO", nullable = false)
	private String codigoMotivoIngreso;
	@Column(name="X_MOTIVO_INGRESO", nullable = false)
	private String nombreMotivoIngreso;
	@Column(name="C_PROCESO", nullable = false)
	private String codigoProceso;
	@Column(name="X_PROCESO", nullable = false)
	private String nombreProceso;
	

	@ManyToOne(optional = false, cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
	@JoinColumn(name="N_EXPEDIENTE")
	private MovExpediente expediente;
	@ManyToOne(optional = false, cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
	@JoinColumn(name="N_ESPECIALIDAD")
	private MaeEspecialidad especialidad;
	@ManyToOne(optional = false, cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
	@JoinColumn(name="N_SUBESPECIALIDAD")
	private MaeSubespecialidad subespecialidad;
	@ManyToOne(optional = false, cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
	@JoinColumn(name="N_INSTANCIA")
	private MaeInstancia instancia;
	@ManyToOne(optional = false, cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
	@JoinColumn(name = "N_DISTRITO_JUDICIAL")
	private MaeDistritoJudicial distritoJudicial;
	
	@OneToMany(mappedBy = "instanciaExpediente")
	private List<MovResolucion> resolucions = new ArrayList<MovResolucion>();

	@Override
	public String toString() {
		return "MovInstanciaExpediente [id=" + id + ", numeroUnico=" + numeroUnico + ", numeroIncidente="
				+ numeroIncidente + ", fechaIngreso=" + fechaIngreso + ", fechaHecho=" + fechaHecho
				+ ", codigoOrganoJurisdiccional=" + codigoOrganoJurisdiccional + ", nombreOrganoJurisdiccional="
				+ nombreOrganoJurisdiccional + ", codigoProvinciaJudicial=" + codigoProvinciaJudicial
				+ ", nombreProvinciaJudicial=" + nombreProvinciaJudicial + ", codigoMotivoIngreso="
				+ codigoMotivoIngreso + ", nombreMotivoIngreso=" + nombreMotivoIngreso + ", codigoProceso="
				+ codigoProceso + ", nombreProceso=" + nombreProceso + ", expediente=" + expediente + ", especialidad="
				+ especialidad + ", subespecialidad=" + subespecialidad + ", instancia=" + instancia
				+ ", distritoJudicial=" + distritoJudicial +  "]";
	}

}
