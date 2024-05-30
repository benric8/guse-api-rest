package pe.gob.pj.guse.infraestructure.db.entity.sidenum;

import java.io.Serializable;
import java.math.BigDecimal;

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
@Table(name="MAE_DISTRITO_JUDICIAL", schema = ProjectConstants.Esquema.SIDENUM)
public class MaeDistritoJudicial extends Auditoria implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name="N_DISTRITO_JUDICIAL", nullable = false)
	private BigDecimal id;
	@Column(name="C_DISTRITO_JUDICIAL", nullable = false)
	private String codigoDistrito;
	@Column(name="X_DISTRITO_JUDICIAL", nullable = false)
	private String nombreDistrito;
	
	@Override
	public String toString() {
		return "MaeDistritoJudicial [id=" + id + ", codigoDistrito=" + codigoDistrito + ", nombreDistrito="
				+ nombreDistrito + "]";
	}

}
