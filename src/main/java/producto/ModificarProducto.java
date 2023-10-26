package producto;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import controlador.ProductoDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ModificarProducto
 */
@WebServlet("/ModificarProducto")
public class ModificarProducto extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String idProducto;
		response.setContentType("text/html");
		PrintWriter salida = response.getWriter();
		
		salida.println("<link rel='stylesheet' href='css/FormUsuario.css'>");
		salida.println("<h1>Modificar Producto</h1>");
		
		idProducto = request.getParameter("IdProducto");
		Producto u = ProductoDAO.ConsultarProductoPorCodigo(idProducto);
		//llenar el formulario
		salida.print("<form action='ModificarProducto2' method='post'>");
		salida.print("<table>");
		salida.print("<tr><td>Producto</td><td><input type='number' name='CodProducto' value='" + u.getProducto() + "' readonly/></td></tr>");
		salida.print("<tr><td>Nombre</td><td><input type='text' name='nombre' value='" + u.getNombre() + "'></td></tr>");
		salida.print("<tr><td>Tipo</td><td><select name='IdTipo' required>");
	
		// Mostrar el elemento seleccionado como la primera opción
		salida.print("<option value='" + u.getIdTipo() + "' selected>" + u.getTipo() + "</option>");

		// Mostrar todas las opciones disponibles excepto la seleccionada
		List<Producto> tiposDisponibles = ProductoDAO.ConsultarTipoExecto(u.getIdTipo());
		for (Producto td : tiposDisponibles) { 
		    salida.print("<option value='" + td.getIdTipo() + "'>" + td.getTipo() + "</option>");
		}
		 salida.print("</select></td></tr>");
		salida.print("<tr><td colspan='2'><input type='submit' value='Guardar' /></td></tr>");
		salida.print("</table>");
		salida.print("</form>");
		salida.print("<a href='ConsultarProducto'>Consultar Producto</a>");
		salida.close();
	
	 }
}
	
