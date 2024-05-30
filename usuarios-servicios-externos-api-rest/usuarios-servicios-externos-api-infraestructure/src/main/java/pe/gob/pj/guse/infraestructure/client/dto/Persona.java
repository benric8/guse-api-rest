package pe.gob.pj.guse.infraestructure.client.dto;

import java.io.Serializable;

import lombok.Data;

@Data
public class Persona implements Serializable{/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String documentoIdentidad;
	private String codigoVerificacion;
	private String fechaEmision;
	private String primerApellido;
	private String segundoApellido;
	private String nombres;

}
