package pe.gob.pj.guse.domain.port.usecase;

import java.util.List;

import pe.gob.pj.guse.domain.model.client.PersonaReniec;

public interface ReniecUseCasePort {
	public PersonaReniec consultaReniecPorDni(String cuo, String dni) throws Exception;
	public List<PersonaReniec> consultaReniecPorApellidosNombres(String cuo, String primerApellido, String segundoApellido, String nombres) throws Exception; 
}
