package producto;

import java.io.IOException;
import java.io.PrintWriter;

import controlador.ProductoDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class AgregarProducto
 */
@WebServlet("/AgregarProducto")
public class AgregarProducto extends HttpServlet {
	private static final long serialVersionUID = 1L;

    
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html");
		int sqlestatus = 0, estatus=1;
		Producto u = new Producto();
		PrintWriter imprime = response.getWriter();
		
		String idProducto = request.getParameter("CodProducto");
		String nombre = request.getParameter("nombre");
		String IdTipo = request.getParameter("IdTipo");
		
		u.setProducto(Integer.parseInt(idProducto));
		u.setNombre(nombre);
		u.setIdTipo(Integer.parseInt(IdTipo));
		u.setEstatus(estatus);
		
		sqlestatus = ProductoDAO.agregar(u);
		if (sqlestatus>0) {
			response.sendRedirect("ConsultarProducto");
			imprime.print("<p>El Producto ha sido agregado</p>");
		}
		else {
			imprime.println("No es posible agregar el registro");
		}				
	} //fin del método doPost

}
