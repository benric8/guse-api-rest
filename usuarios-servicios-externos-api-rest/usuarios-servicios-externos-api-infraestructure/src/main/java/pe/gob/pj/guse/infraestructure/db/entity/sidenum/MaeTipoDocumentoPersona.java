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
@Table(name = "MAE_TIPO_DOCUMENTO_PERSONA", schema = ProjectConstants.Esquema.SIDENUM)
public class MaeTipoDocumentoPersona extends Auditoria implements Serializable {/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public static final String P_ACTIVO = "activo";
	public static final String P_CODIGO = "codigo";
	
	@Id
	@Column(name = "N_TIPO_DOCUMENTO_PERSONA", nullable = false)
	private Integer id;
	@Column(name = "C_TIPO_DOCUMENTO", nullable = false)
	private String codigo;
	@Column(name = "C_TIPO_DOCUMENTO_SIJ", nullable = false)
	private String codigoSij;
	@Column(name = "X_TIPO_DOCUMENTO", nullable = false)
	private String nombre;

}
