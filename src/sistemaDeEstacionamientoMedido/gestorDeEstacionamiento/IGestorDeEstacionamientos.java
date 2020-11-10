package sistemaDeEstacionamientoMedido.gestorDeEstacionamiento;

import java.time.LocalDateTime;

import sistemaDeEstacionamientoMedido.estacionamiento.Estacionamiento;

public interface IGestorDeEstacionamientos {
	
	public void a�adirSaldo(Integer nroCelular, Double saldo);

	public void a�adirEstacionamiento(Estacionamiento estacionamiento, Integer nroCelular);

	public void a�adirEstacionamiento(Estacionamiento estacionamiento);

	public Estacionamiento finalizarVigenciaDeEstacionamiento(Integer nroCelular);

	Estacionamiento finalizarVigenciaDeEstacionamiento(String patente);

	public boolean hayEstacionamientoVigente(String patente);

	public boolean hayEstacionamientoEnRegla(String patente);

	public boolean esHorarioDeEstacionamiento(LocalDateTime Date);

	public boolean esSaldoSuficiente(Double saldo);

	public Double getCostoPorHora();

	public Integer horaMaximaDeFin(Double hora);

}
