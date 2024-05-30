package pe.gob.pj.guse.infraestructure.db.entity.casillero;

import java.io.Serializable;
import javax.persistence.*;

import lombok.Data;
import lombok.EqualsAndHashCode;
import pe.gob.pj.guse.domain.utils.ProjectConstants;
import pe.gob.pj.guse.infraestructure.db.entity.Auditoria;

import java.math.BigDecimal;



@EqualsAndHashCode(callSuper = false)
@Data
@Entity
@Table(name="mov_documento_archivo", schema = ProjectConstants.Esquema.CASILLERO)
public class MovDocumentoArchivo extends Auditoria implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	public static final String P_NUMERO_UNICO = "numeroUnico";
	public static final String P_NUMERO_INCIDENTE = "numeroIncidente";
	public static final String P_INGRESO_ACTO= "ingresoActo";

	@Id
	@Column(name="n_documento_archivo")
	private Long nDocumentoArchivo;

	@Column(name="c_distrito")
	private String cDistritojudicial;

	@Column(name="c_bd")
	private String cBd;

	@Column(name="n_unico")
	private BigDecimal nUnico;

	@Column(name="n_incidente")
	private Integer nIncidente;

	@Column(name="c_orgjurisd")
	private String cOrgjurisd;

	@Column(name="x_ingresoacto")
	private String fIngresoacto;

	@Column(name="c_actoprocesal")
	private String cActoprocesal;

	@Column(name="c_provincia")
	private String cProvincia;

	@Column(name="x_ingreso")
	private String fIngreso;

	@Column(name="c_anio")
	private String cAnio;

	@Column(name="n_secuencia")
	private Integer nSecuencia;

	@Column(name="l_indbd")
	private String lIndbd;

	@Column(name="n_foja")
	private Integer nFoja;

	@Column(name="x_nombrearchivo")
	private String xNombrearchivo;

	@Column(name="x_rutaarchivo")
	private String xRutaarchivo;

	@Column(name="x_descarchivo")
	private String xDescarchivo;

	@Column(name="x_uuidpdf")
	private String xUuidpdf;

	@Column(name="n_correlativo")
	private Integer nCorrelativo;

	@Column(name="n_servicio")
	private Integer nServicio;

	@Column(name="x_actualizasij")
	private String fActualizasij;

	@Column(name="x_descripcion")
	private String xDescripcion;

	@Column(name="x_uuid_alfresco")
	private String xUuidAlfresco;

}