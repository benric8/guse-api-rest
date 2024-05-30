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
@Table(name="MAE_INSTANCIA", schema = ProjectConstants.Esquema.SIDENUM)
public class MaeInstancia extends Auditoria implements Serializable{/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name="N_INSTANCIA", nullable = false)
	private Integer id;
	@Column(name="C_INSTANCIA", nullable = false)
	private String codigo;
	@Column(name="X_INSTANCIA", nullable = false)
	private String nombre;
	@Column(name="X_NOMBRE_CORTO", nullable = false)
	private String nombreCorto;
	
	@Override
	public String toString() {
		return "MaeInstancia [id=" + id + ", codigo=" + codigo + ", nombre=" + nombre + ", nombreCorto=" + nombreCorto
				+ "]";
	}

}
