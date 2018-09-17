package model;

public class Proyecto {
	
	private int codigo;
	private String nombre;
	private Double presupuesto;
	private String fecInicio;
	private String fecFin;

	public Proyecto(int codigo,String nombre, double presupuesto, String fecInicio, String fecFin) {
		this.codigo = codigo;
		this.nombre = nombre;
		this.presupuesto = presupuesto;
		this.fecInicio = fecInicio;
		this.fecFin = fecFin;
	}

	public Proyecto() {
	}

	public String getNombre() {
		return nombre;
	}

	public int getCodigo() {
		return codigo;
	}

	public double getPresupuesto() {
		return presupuesto;
	}

	public String getFecInicio() {
		return fecInicio;
	}

	public String getFecFin() {
		return fecFin;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public void setPresupuesto(double presupuesto) {
		this.presupuesto = presupuesto;
	}

	public void setFecInicio(String fecInicio) {
		this.fecInicio = fecInicio;
	}

	public void setFecFin(String fecFin) {
		this.fecFin = fecFin;
	}

	@Override
	public String toString() {
		return "Proyecto [codigo=" + codigo + ", nombre=" + nombre + ", presupuesto=" + presupuesto + ", fecInicio="
				+ fecInicio + ", fecFin=" + fecFin + "]";
	}



}