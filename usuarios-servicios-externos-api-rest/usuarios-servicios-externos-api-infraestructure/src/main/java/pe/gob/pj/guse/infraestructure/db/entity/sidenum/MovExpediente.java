package pe.gob.pj.guse.infraestructure.db.entity.sidenum;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
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

import lombok.Data;
import lombok.EqualsAndHashCode;
import pe.gob.pj.guse.domain.utils.ProjectConstants;
import pe.gob.pj.guse.infraestructure.db.entity.Auditoria;

@EqualsAndHashCode(callSuper = false)
@Data
@Entity
@Table(name="MOV_EXPEDIENTE", schema = ProjectConstants.Esquema.SIDENUM)
public class MovExpediente extends Auditoria implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name="N_EXPEDIENTE", nullable = false)
	private BigDecimal id;

	@Column(name="N_UNICO", nullable = false)
	private BigDecimal numeroUnico;
	@Column(name="N_INCIDENTE", nullable = false)
	private Integer numeroIncidente;
	@Column(name="X_NUM_EXPEDIENTE", nullable = false)
	private String numeroExpediente;
	@Column(name="X_NUM_CII", nullable = false)
	private String numeroCii;
	@Column(name="X_FORMATO", nullable = false)
	private String formatoExpediente;
	@Column(name="C_ORGANO_JURISDICCIONAL", nullable = false)
	private String codigoOrganoJurisdiccional;
	@Column(name="X_ORGANO_JURISDICCIONAL", nullable = false)
	private String nombreOrganoJurisdiccional;
	@Column(name="C_PROVINCIA_JUDICIAL", nullable = false)
	private String codigoProvinciaJudicial;
	@Column(name="X_PROVINCIA_JUDICIAL", nullable = false)
	private String nombreProvinciaJudicial;
	
	@ManyToOne(optional = false, cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
	@JoinColumn(name="N_INSTANCIA")
	private MaeInstancia instancia;
	@ManyToOne(optional = false, cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
	@JoinColumn(name = "N_DISTRITO_JUDICIAL")
	private MaeDistritoJudicial distritoJudicial;
	@ManyToOne(optional = false, cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
	@JoinColumn(name="N_ESPECIALIDAD")
	private MaeEspecialidad especialidad;
	@ManyToOne(optional = false, cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
	@JoinColumn(name="N_SUBESPECIALIDAD")
	private MaeSubespecialidad subespecialidad;
	@ManyToOne(optional = true, cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
	@JoinColumn(name="N_EXPEDIENTE_PADRE", referencedColumnName = "N_EXPEDIENTE")
	private MovExpediente expedientePadre;
	
	@OneToMany(mappedBy = "expediente")
	private List<MovParte> partes = new ArrayList<MovParte>();
	@OneToMany(mappedBy = "expediente")
	private List<MovInstanciaExpediente> instanciaExpedientes = new ArrayList<MovInstanciaExpediente>();
	@OneToMany(mappedBy = "expediente")
	private List<MovEstadoExpediente> estados = new ArrayList<MovEstadoExpediente>();
	

}
