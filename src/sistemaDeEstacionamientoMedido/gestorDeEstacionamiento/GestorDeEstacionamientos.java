package sistemaDeEstacionamientoMedido.gestorDeEstacionamiento;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import sistemaDeEstacionamientoMedido.estacionamiento.Estacionamiento;

public class GestorDeEstacionamientos implements IGestorDeEstacionamientos {
	
	private LocalDateTime horaInicioEstacionamiento;
	private LocalDateTime horaFinEstacionamiento;
	private Double costoPorHora;
	private IRegistroCelularPatente registroCelularPatente;
	private List <Estacionamiento> estacionamientos;
	
	
	public GestorDeEstacionamientos(LocalDateTime horaInicioEstacionamiento, LocalDateTime horaFinEstacionamiento,
			Double costoPorHoraDeEstacionamiento, IRegistroCelularPatente registroCelularPatente) {
		
		this.setHoraInicioEstacionamiento(horaInicioEstacionamiento);
		this.setHoraFinEstacionamiento(horaFinEstacionamiento);
		this.setCostoPorHora(costoPorHoraDeEstacionamiento);
		this.registroCelularPatente = registroCelularPatente;
		this.estacionamientos = new ArrayList <Estacionamiento>();
		
	}

	public LocalDateTime getHoraInicioEstacionamiento() {
		return this.horaInicioEstacionamiento;
	}

	public LocalDateTime getHoraFinEstacionamiento() {
		return this.horaFinEstacionamiento;
	}

	public IRegistroCelularPatente getRegistroCelularPatente() {
		return this.registroCelularPatente;
	}
	
	public List <Estacionamiento> getEstacionamientos() {
		return this.estacionamientos;
	}

	public void setHoraInicioEstacionamiento(LocalDateTime horaInicioEstacionamiento) {
		this.horaInicioEstacionamiento = horaInicioEstacionamiento;
	}

	public void setHoraFinEstacionamiento(LocalDateTime horaFinEstacionamiento) {
		this.horaFinEstacionamiento = horaFinEstacionamiento;
	}

	public void setCostoPorHora(Double costoPorHora) {
		this.costoPorHora = costoPorHora;
	}

	@Override
	public void aņadirEstacionamiento(Estacionamiento estacionamiento, Integer nroCelular) {
		this.getRegistroCelularPatente().asociar(nroCelular, estacionamiento.getPatente());
		this.aņadirEstacionamiento(estacionamiento);

	}

	@Override
	public void aņadirEstacionamiento(Estacionamiento estacionamiento) {
		this.getEstacionamientos().add(estacionamiento);

	}

	@Override
	public Estacionamiento finalizarVigenciaDeEstacionamiento(Integer nroCelular) {
		String patente = this.getRegistroCelularPatente().getPatente(nroCelular);
		return this.finalizarVigenciaDeEstacionamientoAuxiliar(patente);
	}

	private Estacionamiento finalizarVigenciaDeEstacionamientoAuxiliar(String patente) {
		Estacionamiento estacionamientoAFinalizar = 
				this.getEstacionamientos().stream()
											.filter(estacionamiento -> estacionamiento.estaVigente())
											.filter(estacionamiento -> estacionamiento.getPatente() == patente)
											.findFirst().get();
		estacionamientoAFinalizar.finalizar();
		return estacionamientoAFinalizar;
	}

	@Override
	public boolean hayEstacionamientoVigente(String patente) {
		
		return this.getEstacionamientos().stream()
				.filter(estacionamiento -> estacionamiento.estaVigente())
				.anyMatch(estacionamiento -> estacionamiento.getPatente() == patente);
	}

	@Override
	public boolean hayEstacionamientoEnRegla(String patente) {
		Estacionamiento estacionamientoPedido =
		this.getEstacionamientos().stream()
				.filter(estacionamiento -> estacionamiento.estaVigente())
				.filter(estacionamiento -> estacionamiento.getPatente() == patente)
				.findFirst().get();
		 return estacionamientoPedido.getHoraDeFin().isAfter(LocalDateTime.now());
	}

	@Override
	public boolean esHorarioDeEstacionamiento(LocalDateTime date) {
		return date.isAfter(this.getHoraInicioEstacionamiento()) && date.isBefore(this.getHoraFinEstacionamiento()) ;
	}

	@Override
	public boolean esSaldoSuficiente(Double saldo) {
		return saldo > this.getCostoPorHora();
	}

	@Override
	public Double getCostoPorHora() {
		return this.costoPorHora;
	}
	
	
	/**
	 * Indica la hora maxima posible de fin comparando la hora de finalizacion del sistema de estcionamientos
	 * con la hora resultante de sumarle a la hora actual la cantidad de horas que puede costear el usuario con el saldo indicado
	 * @return LocalDateTime horaMaxima
	 */
	@Override
	public LocalDateTime horaMaximaDeFin(Double saldo) {
		
		LocalDateTime horaActual = LocalDateTime.now();
		Integer horasPosiblesDeEstacionamiento = (int)(saldo / this.getCostoPorHora());
		LocalDateTime horaMaximaDeEstacionamientoSegunSaldo = horaActual.plusHours(horasPosiblesDeEstacionamiento);
		
		if (horaMaximaDeEstacionamientoSegunSaldo.isBefore(this.getHoraFinEstacionamiento())) {
			return horaMaximaDeEstacionamientoSegunSaldo;
		} else {
			return this.getHoraFinEstacionamiento();
		}
	}
	
}
