package pe.gob.pj.guse.domain.model.sidenum;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import lombok.Data;

@Data
public class ExpedienteDetalle implements Serializable{/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private ExpedienteBasico expediente = new ExpedienteBasico();
	private List<ParteProcesal> partes = new ArrayList<ParteProcesal>();
	private List<ActoProcesal> actoProcesal = new ArrayList<ActoProcesal>();

}
