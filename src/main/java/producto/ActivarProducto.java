package producto;

import java.io.IOException;

import controlador.ProductoDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


@WebServlet("/ActivarProducto")
public class ActivarProducto extends HttpServlet {
	private static final long serialVersionUID = 1L;

  
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String idProducto = request.getParameter("producto");
		int estatus = Integer.parseInt(request.getParameter("estatus"));
		ProductoDAO.activar(idProducto, estatus);
		response.sendRedirect("ConsultarProducto");
	
	}

}
