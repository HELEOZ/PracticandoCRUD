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
 * Servlet implementation class ModificarUsuario2
 */
@WebServlet("/ModificarCliente2")
public class ModificarCliente2 extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public ModificarCliente2() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		int sqlestatus = 0;
		PrintWriter imprime = response.getWriter();
		
		String nombre = request.getParameter("nombre");
		int Telefono= Integer.parseInt(request.getParameter("Telefono"));
		String email= request.getParameter("email");
		
		Cliente u = new Cliente();
		u.setNombre(nombre);
		u.setTelefono(Telefono);
		u.setEmail(email);
		sqlestatus = ClienteDAO.actualizar(u);
		
		if (sqlestatus>0) {
			response.sendRedirect("ConsultarCliente");
			imprime.print("<p>El Cliente ha sido agregado</p>");
		} else {
			imprime.println("Existe un error!!");
		}
		imprime.close();
}
}