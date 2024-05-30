package pe.gob.pj.guse.infraestructure.db.entity.sidenum;

import java.io.Serializable;
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
@Table(name = "MOV_RESOLUCION", schema = ProjectConstants.Esquema.SIDENUM)
public class MovResolucion extends Auditoria implements Serializable {/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name="N_RESOLUCION", nullable = false)
	private Integer id;

	@Column(name="X_NUM_RESOLUCION", nullable = false)
	private String numeroResolucion;
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="F_RESOLUCION", nullable = false)
	private Date fechaResolucion;
	@Column(name="X_SUMILLA", nullable = false)
	private String sumilla;
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="F_AUDIENCIA", nullable = true)
	private Date fechaAudiencia;
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="F_INGRESO_ACTO", nullable = false)
	private Date fechaIngresoActo;
	@Column(name="X_RESOLUCION", nullable = true)
	private String resolucion;
	@Column(name="N_FOJAS", nullable = false)
	private Integer numeroFojas;
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="F_RESOLUCION_EDITOR", nullable = true)
	private Date fechaResolucionEditor;
	@Column(name="N_ANIO_GRUPO_NOTIF", nullable = false)
	private Integer anioGrupoNotificacion;
	@Column(name="X_NOM_JUEZ", nullable = false)
	private String juez;
	@Column(name="X_SECRETARIO", nullable = false)
	private String secretario;
	@Column(name="C_ACTO_PROCESAL", nullable = false)
	private String codigoActoProcesal;
	@Column(name="X_ACTO_PROCESAL", nullable = false)
	private String nombreActoProcesal;
	@Column(name="L_ENVIO_RUVA", nullable = true)
	private String envioRuva;
	@Column(name="L_ENVIO_PNP", nullable = true)
	private String envioPnp;
	@Column(name="N_CANTIDAD_ENVIO", nullable = true)
	private Integer cantidadEnvio;
	
	@ManyToOne(optional = false, cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
	@JoinColumn(name = "N_INSTANCIA_EXPEDIENTE")
	private MovInstanciaExpediente instanciaExpediente;
	
	@OneToMany(mappedBy = "resolucion")
	private List<MovMedidaProteccion> medidaProteccions;

}
