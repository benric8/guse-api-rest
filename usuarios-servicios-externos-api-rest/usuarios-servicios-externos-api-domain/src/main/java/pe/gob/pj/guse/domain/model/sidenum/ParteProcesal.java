package pe.gob.pj.guse.domain.model.sidenum;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ParteProcesal implements Serializable{/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String tipoParteProcesal;
	private String tipoDocumento;
	private String numeroDocumento;
	private String primerApellido;
	private String segundoApellido;
	private String nombres;
	
}
