package model;
// adiosssss
public class Empleado {
	private int codigo;
	private String dni;
	private String nombre;
	private String apellido;
	private int genero;
	private int puesto;

	public Empleado(int codigo,String dni, String nombre, String apellido, int genero, int puesto) {

		this.codigo = codigo;
		this.dni = dni;
		this.nombre = nombre;
		this.apellido = apellido;
		this.genero = genero;
		this.puesto = puesto;
	}

	public Empleado() {

	}

	public String getDni() {
		return dni;
	}

	public String getNombre() {
		return nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public int getGenero() {
		return genero;
	}

	public int getPuesto() {
		return puesto;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public void setGenero(int genero) {
		this.genero = genero;
	}

	public void setPuesto(int puesto) {
		this.puesto = puesto;
	}

	@Override
	public String toString() {
		return "Empleado [dni=" + dni + ", nombre=" + nombre + ", apellido=" + apellido + ", genero=" + genero
				+ ", puesto=" + puesto + "]";
	}

}
