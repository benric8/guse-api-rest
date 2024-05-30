package pe.gob.pj.guse.domain.port.repository;

import java.math.BigDecimal;
import java.util.List;

import pe.gob.pj.guse.domain.model.sidenum.Expediente;
import pe.gob.pj.guse.domain.model.sidenum.ExpedienteDetalle;

public interface ConsultaRepositoryPort {
	public List<Expediente> consultarExpedientes(String cuo, String tipoDocumentoPersona, String numeroDocumentoIdentidad) throws Exception;
	public ExpedienteDetalle consultarExpedienteDetalle(String cuo, BigDecimal numeroUnico, BigDecimal numeroIncidente) throws Exception;
	public String obtenerExpedienteResolucion(String cuo, BigDecimal numeroUnico, BigDecimal numeroIncidente, String fechaIngresoActo) throws Exception;
}
