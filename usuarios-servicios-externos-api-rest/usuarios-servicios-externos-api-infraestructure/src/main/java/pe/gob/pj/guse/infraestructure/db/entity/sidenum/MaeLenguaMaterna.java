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
@Table(name = "MAE_LENGUA_MATERNA", schema = ProjectConstants.Esquema.SIDENUM)
public class MaeLenguaMaterna extends Auditoria implements Serializable{/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name="N_LENGUA_MATERNA", nullable = false)
	private Integer id;
	@Column(name="C_LENGUA_MATERNA", nullable = false)
	private String codigo;
	@Column(name="X_LENGUA_MATERNA", nullable = false)
	private String nombre;

}
