package sistemaDeEstacionamientoMedido.gestorDeSaldo;

public interface IGestorDeSaldo {
	
	public void aņadirSaldo(Integer nroCelular, Double saldo);
	
	public Double getSaldo(Integer nroCelular);
	
	public void disminuirSaldo(Integer nroCelular, Double saldoARestar);
	
	
}
