package pe.gob.pj.guse.infraestructure.db.entity.sidenum;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;
import lombok.EqualsAndHashCode;
import pe.gob.pj.guse.domain.utils.ProjectConstants;
import pe.gob.pj.guse.infraestructure.db.entity.Auditoria;

@EqualsAndHashCode(callSuper = false)
@Data
@Entity
@Table(name = "MOV_ESTADO_EXPEDIENTE", schema = ProjectConstants.Esquema.SIDENUM)
public class MovEstadoExpediente extends Auditoria implements Serializable{/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "N_ESTADO_EXPEDIENTE", nullable = false)
	private Integer id;

	@Column(name = "C_ESTADO", nullable = false)
	private String codigo;
	@Column(name = "X_ESTADO", nullable = false)
	private String nombre;
	
	@ManyToOne(optional = false, cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
	@JoinColumn(name="N_EXPEDIENTE", nullable = false)
	private MovExpediente expediente;

}
