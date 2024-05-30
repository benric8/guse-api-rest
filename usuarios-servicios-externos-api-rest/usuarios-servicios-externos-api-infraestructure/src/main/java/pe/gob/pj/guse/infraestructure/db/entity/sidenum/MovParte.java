package pe.gob.pj.guse.infraestructure.db.entity.sidenum;

import java.io.Serializable;
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
@Table(name="MOV_PARTE", schema = ProjectConstants.Esquema.SIDENUM)
public class MovParte extends Auditoria implements Serializable{/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name="N_PARTE", nullable = false)
	private Integer id;
	@Column(name="L_PRISION_PREVENTIVA", nullable = false)
	private String prisionPreventiva;
	@Column(name="X_DIRECCION_PERSONA", nullable = false)
	private String direccion;
	@Column(name="X_TELEFONO", nullable = false)
	private String telefono;
	@Column(name="X_EMAIL", nullable = false)
	private String email;
	
	@ManyToOne(optional = true, cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
	@JoinColumn(name="N_EXPEDIENTE")
	private MovExpediente expediente;
	@ManyToOne(optional = false, cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
	@JoinColumn(name="N_PERSONA")
	private MovPersona persona;
	
	@OneToMany(mappedBy = "parte")
	private List<MovParteTipo> parteTipos = new ArrayList<MovParteTipo>();

}
