package pe.gob.pj.guse.domain.model.sidenum;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor @AllArgsConstructor
@Data
public class MedidaAdoptada implements Serializable{/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String tipoMedida;
	private String descripcion;
	private String tipoParte;
	private String primerApellidoParte;
	private String segundoApellidoParte;
	private String nombresParte;

}
