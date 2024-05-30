package pe.gob.pj.guse.infraestructure.db.entity.sidenum;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;
import lombok.EqualsAndHashCode;
import pe.gob.pj.guse.domain.utils.ProjectConstants;
import pe.gob.pj.guse.infraestructure.db.entity.Auditoria;

@EqualsAndHashCode(callSuper = false)
@Data
@Entity
@Table(name="MAE_SUBESPECIALIDAD", schema = ProjectConstants.Esquema.SIDENUM)
public class MaeSubespecialidad extends Auditoria implements Serializable {/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name="N_SUBESPECIALIDAD", nullable = false)
	private Integer id;
	@Column(name="C_SUBESPECIALIDAD", nullable = false)
	private String codigo;
	@Column(name="X_SUBESPECIALIDAD", nullable = false)
	private String nombre;

}