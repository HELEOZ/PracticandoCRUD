package usuario;

import java.io.IOException;
import java.io.PrintWriter;

import controlador.UsuarioDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ModificarUsuario
 */
@WebServlet("/ModificarUsuario")
public class ModificarUsuario extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String idUsuario;
		response.setContentType("text/html");
		PrintWriter salida = response.getWriter();
		
		salida.println("<link rel='stylesheet' href='css/FormUsuario.css'>");
		salida.println("<h1>Modificar Usuario</h1>");
		
		idUsuario = request.getParameter("IdUsuario");
		Usuario u = UsuarioDAO.ConsultarUsuarioPorCodigo(idUsuario);
		
		//llenar el formulario
		salida.print("<form action='ModificarUsuario2' method='post'>");
		salida.print("<table>");
		salida.print("<tr><td>Usuario</td><td><input type='text' name='usuario' value='" + u.getUsuario() + "' readonly/></td></tr>");
		salida.print("<tr><td>Nombre</td><td><input type='text' name='nombre' value='" + u.getNombre() + "'></td></tr>");
		salida.print("<tr><td>Contraseña</td><td><input type='password' name='contrasena' value='" + u.getContrasena() + "'/></td></tr>");
		salida.print("<tr><td>email</td><td><input type='email' name='email' value='" + u.getEmail() + "'/></td></tr>");
		salida.print("<tr><td>País</td><td><input type='text' name='IdPais' value='" + u.getPais() + "'/>");
		salida.print("<tr><td colspan='2'><input type='submit' value='Guardar' /></td></tr>");
		salida.print("</table>");
		salida.print("</form>");
		salida.print("<a href='ConsultarUsuario'>Consultar usuario</a>");
		salida.close();
	}

}
