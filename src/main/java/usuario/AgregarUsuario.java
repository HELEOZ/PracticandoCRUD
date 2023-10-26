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
 * Servlet implementation class AgregarUsuario
 */
@WebServlet("/AgregarUsuario")
public class AgregarUsuario extends HttpServlet {
	private static final long serialVersionUID = 1L;

    
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html");
		int sqlestatus = 0, estatus=1, intentos=0;
		Usuario u = new Usuario();
		PrintWriter imprime = response.getWriter();
		
		String idUsuario = request.getParameter("usuario");
		String nombre = request.getParameter("nombre");
		String contrasena = request.getParameter("contrasena");
		String email = request.getParameter("email");
		String pais = request.getParameter("IdPais");
		
		u.setUsuario(idUsuario);
		u.setNombre(nombre);
		u.setContrasena(contrasena);
		u.setEmail(email);
		u.setPais(pais);
		u.setEstatus(estatus);
		u.setIntentos(intentos);
		
		sqlestatus = UsuarioDAO.agregar(u);
		if (sqlestatus>0) {
			imprime.print("<p>El usuario ha sido agregado</p>");
			request.getRequestDispatcher("AgregarU.html").include(request, response);;
		}
		else {
			imprime.println("No es posible agregar el registro");
		}				
	} //fin del método doPost

}
