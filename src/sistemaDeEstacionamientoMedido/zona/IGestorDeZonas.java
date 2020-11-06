package sistemaDeEstacionamientoMedido.zona;

import java.awt.Point;
import java.util.List;

public interface IGestorDeZonas {

	public void a�adirZona(Zona zona);
	public boolean perteneceAUnaZona(Point punto);
	public List<Zona> getZonas();
	public Zona indicarZona(Point punto);
	
}
