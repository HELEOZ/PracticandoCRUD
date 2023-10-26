package producto;

public class Producto {
	//atributos
	private int producto;
	private String nombre;
	private int IdTipo;
	private String tipo;
	private double Precio;
	private int estatus;
	
	public int getProducto() {
		return producto;
	}
	public void setProducto(int producto) {
		this.producto = producto;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public int getIdTipo() {
		return IdTipo;
	}
	public void setIdTipo(int Idtipo) {
		IdTipo = Idtipo;
	}
	public String getTipo() {
		return tipo;
	}
	public double getPrecio() {
		return Precio;
	}
	public void setPrecio(double precio) {
		Precio = precio;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public int getEstatus() {
		return estatus;
	}
	public void setEstatus(int estatus) {
		this.estatus = estatus;
	}
		
} //fin de la clase Usuario
