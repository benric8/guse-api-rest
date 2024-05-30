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
@Table(name = "MOV_MEDIDA_PROTECCION", schema = ProjectConstants.Esquema.SIDENUM)
public class MovMedidaProteccion extends Auditoria implements Serializable {/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name="N_MEDIDA_PROTECCION", nullable = false)
	private Integer id;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="F_EJECUCION", nullable = false)
	private Date fechaEjecucion;
	@Column(name="L_ESTADO_DOCUMENTO", nullable = false)
	private String estadoDocumento;
	@Column(name="X_OBSERVACION", nullable = true)
	private String observacion;
	@Column(name="X_CUMPLIMIENTO", nullable = true)
	private String cumplimiento;
	@Column(name="L_CARGA_BP", nullable = true)
	private String cargaBp;
	
	@ManyToOne(optional = false, cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
	@JoinColumn(name="N_RESOLUCION")
	private MovResolucion resolucion;
	@ManyToOne(optional = false, cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
	@JoinColumn(name="N_VICTIMA", referencedColumnName = "N_PARTE")
	private MovParte victima;
	
	@OneToMany(mappedBy = "medidaProteccion")
	private List<MovMedidaAdoptada> medidaAdoptadas;

}
