package cliente;

import java.io.IOException;
import java.io.PrintWriter;

import controlador.ClienteDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Modificarcliente
 */
@WebServlet("/Modificarcliente")
public class ModificarCliente extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String idcliente;
		response.setContentType("text/html");
		PrintWriter salida = response.getWriter();
		
		salida.println("<link rel='stylesheet' href='css/FormUsuario.css'>");
		salida.println("<h1>Modificar cliente</h1>");
		
		idcliente = request.getParameter("Idcliente");
		Cliente u = ClienteDAO.ConsultarclientePorCodigo(idcliente);
		
		//llenar el formulario
		salida.print("<form action='ModificarCliente2' method='post'>");
		salida.print("<table>");
		salida.print("<tr><td>Nombre</td><td><input type='text' name='nombre' value='" + u.getNombre() + "'></td></tr>");
		salida.print("<tr><td>Contraseña</td><td><input type='password' name='Telefono' value='" + u.getTelefono() + "'/></td></tr>");
		salida.print("<tr><td>email</td><td><input type='email' name='email' value='" + u.getEmail() + "'/></td></tr>");
		salida.print("<tr><td colspan='2'><input type='submit' value='Guardar' /></td></tr>");
		salida.print("</table>");
		salida.print("</form>");
		salida.print("<a href='ConsultarCliente'>Consultar cliente</a>");
		salida.close();
	}

}
