package pe.gob.pj.guse.domain.model.sidenum;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import lombok.Data;

@Data
public class ActoProcesal implements Serializable {/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String numeroResolucion;
	private String actoProcesal;
	private String sumilla;
	private BigDecimal numeroUnico;
	private BigDecimal numeroIncidente;
	private String fechaIngresoActo;
	private String juez;
	private String secretario;
	
	private List<MedidaProteccion> medidaProteccion = new ArrayList<MedidaProteccion>();

}
