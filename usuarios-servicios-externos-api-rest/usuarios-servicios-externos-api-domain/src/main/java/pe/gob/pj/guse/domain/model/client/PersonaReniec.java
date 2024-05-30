package pe.gob.pj.guse.domain.model.client;

import java.io.Serializable;

import lombok.Data;

@Data
public class PersonaReniec implements Serializable{/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String numeroDocumenoIdentidad; // DNI de la persona
	private String flagVerificacion; // Digito de verificacion
	private String apellidoPaterno; // Apellido Paterno
	private String apellidoMaterno; // Apellido Materno
	private String apellidoCasado; // Apellido Casada
	private String nombres; // Nombres
	private String codUbiDepDom; // Ubigeo Departamento Domicilio
	private String codUbiProDom; // Ubigeo Provincia Domicilio
	private String codUbiDisDom; // Ubigeo Distrito Domicilio
	private String codUbiLocDom; // Ubigeo Localidad Domicilio
	private String depDom; // Departamento Domicilio
	private String proDom; // Provincia Domicilio
	private String disDom; // Distrito Domicilio
	private String locDom; // Localidad Domicilio
	private String estCiv; // Estado Civil
	private String graInst; // Código de Grado de Instrucción
	private String estatura; // Estatura
	private String sexo; // Sexo
	private String docSustTipDoc; // Documento Sustentatorio - Tipo Doc.
	private String docSustNroDoc; // Documento Sustentatorio - Nro Doc.
	private String codUbiDepNac; // Ubigeo Departamento Nacimiento
	private String codUbiProNac; // Ubigeo Provincia Nacimiento
	private String codUbiDisNac; // Ubigeo Distrito Nacimiento
	private String codUbiLocNac; // Ubigeo Localidad Nacimiento
	private String depNac; // Departamento Nacimiento
	private String proNac; // Provincia Nacimiento
	private String disNac; // Distrito Nacimiento
	private String locNac; // Localidad Nacimiento
	private String fechaNacimiento; // Fecha Nacimiento
	private String docPadTipDoc; // Documento Padre - Tipo Doc
	private String docPadNumDoc; // Documento Padre - Num Doc
	private String nomPad; // Nombre del Padre
	private String docMadTipDoc; // Documento Madre - Tipo Doc
	private String docMadNumDoc; // Documento Madre - Num Doc
	private String nomMad; // Nombre de la Madre
	private String fechaInscripcion; // Fecha de Inscripción
	private String fechaExpedicion; // Fecha de Expedición
	private String fechaFallecimiento; // Fecha de Fallecimiento
	private String consVot; // Constancia de Votación
	private String fecCad; // Fecha de caducidad
	private String restric; // Restricciones
	private String prefDir; // Prefijo direccion
	private String direccion; // Direccion
	private String nroDir; // Numero Direccion
	private String blocOChal; // Block o chalet
	private String interior; // Interior
	private String urbanizacion; // Urbanizacion
	private String etapa; // Etapa
	private String manzana; // Manzana
	private String lote; // Lote
	private String preBlocOChal; // Prefijo Bloc o Chalet
	private String preDptoPisoInt; // Prefijo Departamento Piso Interior
	private String preUrbCondResid; // Prefijo de urb cond resid.
	private String reservado; // Reservado
	private int longitudFoto; // Longitud de la Foto en Bytes
	private int longitudFirma; // Longitud de la Firma en Bytes
	private int reservadoFotoFirma1; // ReservadoFotoFirma 1
	private String reservadoFotoFirma2; // ReservadoFotoFirma 2
	private byte[] foto; // Foto
	private byte[] firma; // Firma
	private String flgImagen; // Indica si tiene foto o no
	private String tipFichaRegistral; // Tipo de Ficha registral
	private String datos; // Indica si debe mostrar datos
	
	@Override
	public String toString() {
		return "PersonaReniec [numeroDocumenoIdentidad=" + numeroDocumenoIdentidad + ", flagVerificacion="
				+ flagVerificacion + ", apellidoPaterno=" + apellidoPaterno + ", apellidoMaterno=" + apellidoMaterno
				+ ", apellidoCasado=" + apellidoCasado + ", nombres=" + nombres + ", codUbiDepDom=" + codUbiDepDom
				+ ", codUbiProDom=" + codUbiProDom + ", codUbiDisDom=" + codUbiDisDom + ", codUbiLocDom=" + codUbiLocDom
				+ ", depDom=" + depDom + ", proDom=" + proDom + ", disDom=" + disDom + ", locDom=" + locDom
				+ ", estCiv=" + estCiv + ", graInst=" + graInst + ", estatura=" + estatura + ", sexo=" + sexo
				+ ", docSustTipDoc=" + docSustTipDoc + ", docSustNroDoc=" + docSustNroDoc + ", codUbiDepNac="
				+ codUbiDepNac + ", codUbiProNac=" + codUbiProNac + ", codUbiDisNac=" + codUbiDisNac + ", codUbiLocNac="
				+ codUbiLocNac + ", depNac=" + depNac + ", proNac=" + proNac + ", disNac=" + disNac + ", locNac="
				+ locNac + ", fechaNacimiento=" + fechaNacimiento + ", docPadTipDoc=" + docPadTipDoc + ", docPadNumDoc="
				+ docPadNumDoc + ", nomPad=" + nomPad + ", docMadTipDoc=" + docMadTipDoc + ", docMadNumDoc="
				+ docMadNumDoc + ", nomMad=" + nomMad + ", fechaInscripcion=" + fechaInscripcion + ", fechaExpedicion="
				+ fechaExpedicion + ", fechaFallecimiento=" + fechaFallecimiento + ", consVot=" + consVot + ", fecCad="
				+ fecCad + ", restric=" + restric + ", prefDir=" + prefDir + ", direccion=" + direccion + ", nroDir="
				+ nroDir + ", blocOChal=" + blocOChal + ", interior=" + interior + ", urbanizacion=" + urbanizacion
				+ ", etapa=" + etapa + ", manzana=" + manzana + ", lote=" + lote + ", preBlocOChal=" + preBlocOChal
				+ ", preDptoPisoInt=" + preDptoPisoInt + ", preUrbCondResid=" + preUrbCondResid + ", reservado="
				+ reservado + ", longitudFoto=" + longitudFoto + ", longitudFirma=" + longitudFirma
				+ ", reservadoFotoFirma1=" + reservadoFotoFirma1 + ", reservadoFotoFirma2=" + reservadoFotoFirma2
				+ ", foto=" + ", firma=" + ", flgImagen=" + flgImagen
				+ ", tipFichaRegistral=" + tipFichaRegistral + ", datos=" + datos + "]";
	}
	
}
