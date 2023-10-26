package cliente;

import java.io.IOException;

import controlador.ClienteDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


@WebServlet("/ActivarCliente")
public class ActivarCliente extends HttpServlet {
	private static final long serialVersionUID = 1L;

  
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int idCliente = Integer.parseInt(request.getParameter("IdCliente"));
		int estatus = Integer.parseInt(request.getParameter("estatus"));
		ClienteDAO.activar(idCliente, estatus);
		response.sendRedirect("ConsultarCliente");
	
	}

}
