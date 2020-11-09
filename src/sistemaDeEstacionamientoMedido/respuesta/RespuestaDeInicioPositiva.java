package sistemaDeEstacionamientoMedido.respuesta;

import java.time.LocalDateTime;

public class RespuestaDeInicioPositiva implements Respuesta {

	private LocalDateTime horaDeInicio, horaMaxima;
	
	public RespuestaDeInicioPositiva(Estacionamiento estacionamiento) {
		this.horaDeInicio = estacionamiento.getHoraDeInicio();
		this.horaMaxima = estacionamiento.getHoraDeFin();
	}

	public LocalDateTime getHoraDeInicio() {
		return this.horaDeInicio;
	}

	public LocalDateTime getHoraMaxima() {
		return this.horaMaxima;
	}
	
	public String toString() {
		return "Hora de Inicio: " + this.getHoraDeInicio() + 
			   "\nHora Maxima: " + this.getHoraMaxima();
	}

}