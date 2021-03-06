package sistemaDeEstacionamientoMedido.appMunicipio;

import java.time.LocalDateTime;

import sistemaDeEstacionamientoMedido.gestorDeEstacionamiento.IGestorDeEstacionamientos;
import sistemaDeEstacionamientoMedido.sem.ServicioAppMunicipio;
import sistemaDeEstacionamientoMedido.zona.Zona;

public class AppDelMunicipio {
	IGestorDeEstacionamientos gestorDeEstacionamientos;
	IGestorDeInfracciones gestorDeInfracciones;
	Zona zona;
	

	public AppDelMunicipio(ServicioAppMunicipio servicio, Zona zona) {
		this.zona = zona;
		this.gestorDeEstacionamientos = servicio.getGestorDeEstacionamientos();
		this.gestorDeInfracciones = servicio.getGestorDeInfracciones();
	}


	public IGestorDeEstacionamientos getGestorDeEstacionamientos() {
		return gestorDeEstacionamientos;
	}


	public IGestorDeInfracciones getGestorDeInfracciones() {
		return gestorDeInfracciones;
	}


	public Zona getZona() {
		return zona;
	}

	/**
	 * Registra una infracci�n al vehiculo con la patente indicada por par�metro.
	 * Se asume que el inspector a cargo ya comprob� que este vehiculo no ten�a en un
	 * estacionamiento en regla.
	 */
	public void registrarInfraccion(String patente) {
		
		this.getGestorDeInfracciones().registrarInfraccion(new Infraccion (patente,
																	  	   LocalDateTime.now(),
																	       zona.getInspector(),
																	       zona));
	}


	public Boolean tieneEstacionamientoEnRegla(String patente) {
		
		return this.getGestorDeEstacionamientos().hayEstacionamientoEnRegla(patente);
	}

}
