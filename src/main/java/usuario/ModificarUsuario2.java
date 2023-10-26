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
 * Servlet implementation class ModificarUsuario2
 */
@WebServlet("/ModificarUsuario2")
public class ModificarUsuario2 extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public ModificarUsuario2() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		int sqlestatus = 0;
		PrintWriter imprime = response.getWriter();
		
		String idUsuario = request.getParameter("usuario");
		String nombre = request.getParameter("nombre");
		String contrasena= request.getParameter("contrasena");
		String email= request.getParameter("email");
		String pais = request.getParameter("IdPais");
		
		Usuario u = new Usuario();
		u.setUsuario(idUsuario);
		u.setNombre(nombre);
		u.setContrasena(contrasena);
		u.setEmail(email);
		u.setPais(pais);
		sqlestatus = UsuarioDAO.actualizar(u);
		
		if (sqlestatus>0) {
			response.sendRedirect("ConsultarUsuario");
		} else {
			imprime.println("Existe un error!!");
		}
		imprime.close();
}
}