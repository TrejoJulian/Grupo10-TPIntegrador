package sistemaDeEstacionamientoMedido.gestorDeSaldo;

public interface IGestorDeSaldo {
	
	public void a�adirSaldo(Integer nroCelular, Double saldo);
	
	public Double getSaldo(Integer nroCelular);
	
	public void disminuirSaldo(Integer nroCelular, Double saldoARestar);
	
	
}
