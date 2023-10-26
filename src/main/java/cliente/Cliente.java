package cliente;

public class Cliente {
	//atributos
	private int IdCliente;
	private String nombre;
	private int Telefono;
	private String email;
	private int estatus;
	
	public int getIdCliente() {
		return IdCliente;
	}
	public void setIdCliente(int idCliente) {
		IdCliente = idCliente;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public int getTelefono() {
		return Telefono;
	}
	public void setTelefono(int telefono) {
		Telefono = telefono;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public int getEstatus() {
		return estatus;
	}
	public void setEstatus(int estatus) {
		this.estatus = estatus;
	}
		
} //fin de la clase Usuario
