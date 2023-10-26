package usuario;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import controlador.UsuarioDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


@WebServlet("/ConsultarUsuario")
public class ConsultarUsuario extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter imprimir = response.getWriter();
		imprimir.println("<link rel='stylesheet' href='css/styles.css'>");
		imprimir.println("<a href='AgregarU.html'>Agregar usuario</a>");
		imprimir.println("<a href='MenuPizza.html'>Regresar al menu</a>");
		imprimir.println("<h1>Lista de usuarios</h1>");


		List<Usuario> lista = UsuarioDAO.ConsultarTodosUsuarios();
		
		imprimir.print("<table border='1'>");
		imprimir.print("<tr><th>Usuario</th><th>Nombre</th><th>Contraseña</th><th>email</th>" +
				"<th>País</th><th>Estatus</th>" +
				"<th>Modificar</th><th>Activar</th></tr>");
		
		for (Usuario u:lista) {
			String ActivoONo;
			if(u.getEstatus()==1) {
				ActivoONo ="Usuario Activo";
			}else {
				ActivoONo = "Usuario Desactivado";
			}
			imprimir.print("<tr><td>" + u.getUsuario() + "</td><td>" + u.getNombre() + "</td><td>" + u.getContrasena() + "</td>" + 
						"<td>" + u.getEmail() + "</td><td>" + u.getPais() + "</td>" +
						"<td>" + ActivoONo + "</td>"
							+ "<td><a href='ModificarUsuario?IdUsuario=" + u.getUsuario() + "'>modificar</a></td>"
							+ "<td><a href='ActivarUsuario?usuario=" + u.getUsuario() + 
							"&estatus=" + u.getEstatus() + "'>activo</a></td></tr>");
		}//fin del ciclo for
		imprimir.print("</table>");
		imprimir.close();
	}//fin doGet

}
