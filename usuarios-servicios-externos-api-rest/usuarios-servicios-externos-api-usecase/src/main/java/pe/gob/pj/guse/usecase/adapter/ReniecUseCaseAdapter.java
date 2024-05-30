package pe.gob.pj.guse.usecase.adapter;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import pe.gob.pj.guse.domain.model.client.PersonaReniec;
import pe.gob.pj.guse.domain.port.client.ReniecClientPort;
import pe.gob.pj.guse.domain.port.usecase.ReniecUseCasePort;

@Service("reniecUseCasePort")
public class ReniecUseCaseAdapter implements Serializable, ReniecUseCasePort{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Autowired
	@Qualifier("reniecClientPort")
	private ReniecClientPort client;

	@Override
	public PersonaReniec consultaReniecPorDni(String cuo, String dni) throws Exception {
		return client.consultaReniecPorDni(cuo, dni);
	}

	@Override
	public List<PersonaReniec> consultaReniecPorApellidosNombres(String cuo, String primerApellido,
			String segundoApellido, String nombres) throws Exception {
		return client.consultaReniecPorApellidosNombres(cuo, primerApellido, segundoApellido, nombres);
	}

}
