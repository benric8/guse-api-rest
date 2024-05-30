package pe.gob.pj.guse.domain.model.sidenum;

import java.io.Serializable;
import java.math.BigDecimal;

import lombok.Data;

@Data
public class ExpedienteBasico implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private BigDecimal numeroUnico;
	private BigDecimal numeroIncidente;
	private String fechaIngreso;
	private String formato;
	private String distritoJudicial;
	private String juzgado;
	private String especialidad;
	private String materia;
	private String proceso;
	private String estado;

}
