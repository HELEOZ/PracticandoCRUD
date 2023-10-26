package usuario;

import java.io.IOException;

import controlador.UsuarioDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


@WebServlet("/ActivarUsuario")
public class ActivarUsuario extends HttpServlet {
	private static final long serialVersionUID = 1L;

  
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String idUsuario = request.getParameter("usuario");
		int estatus = Integer.parseInt(request.getParameter("estatus"));
		UsuarioDAO.activar(idUsuario, estatus);
		response.sendRedirect("ConsultarUsuario");
	
	}

}
