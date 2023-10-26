package factura;

import java.time.LocalDate;

public class Factura {
	private int IdFactura;
	
	private LocalDate fechaEmision = LocalDate.now();
	
	private int IdDetalleFfactura;
	
	private int cantidad;
	
	private double descuento;
	
	private double subtotal;
	
	private double ISV;
	
	private double Total;
	

	public int getIdFactura() {
		return IdFactura;
	}

	public void setIdFactura(int idFactura) {
		IdFactura = idFactura;
	}

	
	public int getIdDetalleFfactura() {
		return IdDetalleFfactura;
	}

	public void setIdDetalleFfactura(int idDetalleFfactura) {
		IdDetalleFfactura = idDetalleFfactura;
	}

	public LocalDate getFechaEmision() {
		return fechaEmision;
	}

	public void setFechaEmision(LocalDate fechaEmision) {
		this.fechaEmision = fechaEmision;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	public double getDescuento() {
		return descuento;
	}

	public void setDescuento(double descuento) {
		this.descuento = descuento;
	}

	public double getSubtotal() {
		return subtotal;
	}

	public void setSubtotal(double subtotal) {
		this.subtotal = subtotal;
	}

	public double getISV() {
		return ISV;
	}

	public void setISV(double iSV) {
		ISV = iSV;
	}

	public double getTotal() {
		return Total;
	}

	public void setTotal(double total) {
		Total = total;
	}
	
}
