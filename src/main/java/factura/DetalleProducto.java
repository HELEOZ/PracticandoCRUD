package factura;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import producto.Producto;

import java.io.IOException;
import java.io.PrintWriter;

import controlador.ProductoDAO;

@WebServlet("/DetalleProductos")
public class DetalleProducto extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
        
        String productoId = request.getParameter("productoId");
        Producto producto = ProductoDAO.ConsultarProductoPorCodigo(productoId); // Debes implementar este método
        Factura fac = new Factura(); 
        
        PrintWriter out = response.getWriter();
        out.println("<p><strong>Nombre Producto:</strong> " + producto.getNombre() + "</p>");
        out.println("<p><strong>Tipo Producto:</strong> " + producto.getTipo() + "</p>");
        out.println("<p><strong>Precio Producto:</strong> " + producto.getPrecio() + "</p>");
        out.println("<p><strong>Cantidad:</strong> " + fac.getCantidad() + "</p>");
        System.out.print("detalle de producto:" + fac.getCantidad());
        response.sendRedirect("facturas");
	}

}
