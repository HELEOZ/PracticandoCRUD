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
 * Servlet implementation class AgregarUsuario
 */
@WebServlet("/AgregarCliente")
public class AgregarCliente extends HttpServlet {
	private static final long serialVersionUID = 1L;

    
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html");
		int sqlestatus = 0, estatus=1;
		Cliente u = new Cliente();
		PrintWriter imprime = response.getWriter();
		
		String nombre = request.getParameter("nombre");
		int Telefono = Integer.parseInt(request.getParameter("Telefono"));
		String email = request.getParameter("email");
		
		u.setNombre(nombre);
		u.setTelefono(Telefono);
		u.setEmail(email);
		u.setEstatus(estatus);
		sqlestatus = ClienteDAO.agregar(u);
		
		if (sqlestatus>0) {
			response.sendRedirect("AgregarC.html");
			imprime.print("<p>El Cliente ha sido agregado</p>");
		}
		else {
			imprime.println("No es posible agregar el registro");
		}				
	} //fin del método doPost

}
