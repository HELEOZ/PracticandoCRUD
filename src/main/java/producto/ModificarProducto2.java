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
 * Servlet implementation class ModificarProducto2
 */
@WebServlet("/ModificarProducto2")
public class ModificarProducto2 extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public ModificarProducto2() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		int sqlestatus = 0;
		PrintWriter imprime = response.getWriter();
		
		String idProducto = request.getParameter("CodProducto");
		String nombre = request.getParameter("nombre");
		String Tipo= request.getParameter("IdTipo");
		
		Producto u = new Producto();
		u.setProducto(Integer.parseInt(idProducto));
		u.setNombre(nombre);
		u.setIdTipo(Integer.parseInt(Tipo));
		
		sqlestatus = ProductoDAO.actualizar(u);
		
		if (sqlestatus>0) {
			response.sendRedirect("ConsultarProducto");
			imprime.print("<p>El Producto ha sido agregado</p>");
		} else {
			imprime.println("Existe un error!!");
		}
		imprime.close();
}
}