package sistemaDeEstacionamientoMedido.zona;

import java.awt.geom.Point2D;
import java.util.List;

public interface IGestorDeZonas {

	public void a�adirZona(Zona zona);
	
	public Boolean perteneceAUnaZona(Point2D punto);
	
	public List<Zona> getZonas();
	
	public Zona indicarZona(Point2D punto);
	
}
