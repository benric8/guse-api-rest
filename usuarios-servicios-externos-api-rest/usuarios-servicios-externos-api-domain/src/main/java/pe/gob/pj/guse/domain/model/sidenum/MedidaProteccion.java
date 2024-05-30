package pe.gob.pj.guse.domain.model.sidenum;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor @AllArgsConstructor
@Data
public class MedidaProteccion implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String fechaEjecucion;
	private String estadoDocumento;
	
	private List<MedidaAdoptada> medidasAdoptadas = new ArrayList<MedidaAdoptada>();
}
