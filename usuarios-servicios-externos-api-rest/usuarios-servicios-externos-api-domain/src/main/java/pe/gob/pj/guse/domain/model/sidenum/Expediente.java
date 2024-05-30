package pe.gob.pj.guse.domain.model.sidenum;

import java.io.Serializable;
import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor @AllArgsConstructor
@Data
public class Expediente implements Serializable{/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private BigDecimal numeroUnico;
	private BigDecimal numeroIncidente;
	private String fechaIngreso;
	private String formato;
	private String distritoJudicial;
	private String juzgado;
	private String tipoParte;
	private String apellidosNombresParte;

}
