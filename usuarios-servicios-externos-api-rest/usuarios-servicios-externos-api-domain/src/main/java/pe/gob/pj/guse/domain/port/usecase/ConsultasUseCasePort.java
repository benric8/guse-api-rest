package pe.gob.pj.guse.domain.port.usecase;

import java.math.BigDecimal;
import java.util.List;

import pe.gob.pj.guse.domain.model.sidenum.Expediente;
import pe.gob.pj.guse.domain.model.sidenum.ExpedienteDetalle;

public interface ConsultasUseCasePort {
	public List<Expediente> consultarExpedientes(String cuo, String tipoDocumento, String numeroDocumentoIdentidad, String codigoVerificacion, String fechaNacimieno, String fechaEmision) throws Exception;
	public ExpedienteDetalle consultarExpedienteDetalle(String cuo, BigDecimal numeroUnico, BigDecimal numeroIncidente) throws Exception;
	public byte[] obtenerExpedienteResolucion(String cuo, BigDecimal numeroUnico, BigDecimal numeroIncidente, String fechaIngresoActo) throws Exception;
}
