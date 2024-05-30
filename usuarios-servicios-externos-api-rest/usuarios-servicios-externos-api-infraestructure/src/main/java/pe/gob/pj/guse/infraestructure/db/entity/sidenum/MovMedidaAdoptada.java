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
@Table(name = "MOV_MEDIDA_ADOPTADA", schema = ProjectConstants.Esquema.SIDENUM)
public class MovMedidaAdoptada extends Auditoria implements Serializable {/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name="N_MEDIDA_ADOPTADA", nullable = false)
	private Integer id;
	@Column(name="X_DESCRIPCION", nullable = true)
	private String descripcion;
	
	@ManyToOne(optional = false, cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
	@JoinColumn(name="N_MEDIDA_PROTECCION")
	private MovMedidaProteccion medidaProteccion;
	@ManyToOne(optional = true, cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
	@JoinColumn(name="N_PARTE")
	private MovParte parte;
	@ManyToOne(optional = false, cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
	@JoinColumn(name="N_TIPO_MEDIDA_PROTECCION")
	private MaeTipoMedidaProteccion tipoMedidaProteccion;

}
