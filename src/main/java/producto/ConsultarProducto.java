package producto;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import controlador.ProductoDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


@WebServlet("/ConsultarProducto")
public class ConsultarProducto extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter imprimir = response.getWriter();
		imprimir.println("<link rel='stylesheet' href='css/styles.css'>");
		imprimir.println("<a href='AgregarProducto.html'>Agregar Producto</a>");
		imprimir.println("<a href='MenuPizza.html'>Regresar al menu</a>");
		imprimir.println("<h1>Lista de Productos</h1>");


		List<Producto> lista = ProductoDAO.ConsultarTodosProductos();
		
		imprimir.print("<table border='1'>");
		imprimir.print("<tr><th>codigoProducto</th><th>Nombre</th><th>Tipo</th><th>Estatus</th>" +
				"<th>Modificar</th><th>Activar</th></tr>");
		
		for (Producto u:lista) {
			String ActivoONo;
			if(u.getEstatus()==1) {
				ActivoONo ="Producto Activo";
			}else {
				ActivoONo = "Producto Desactivado";
			}
			imprimir.print("<tr><td>" + u.getProducto() + "</td><td>" + u.getNombre() + "</td><td>" + u.getTipo() + "</td>" + 
						"<td>" + ActivoONo + "</td>"
							+ "<td><a href='ModificarProducto?IdProducto=" + u.getProducto() + "'>modificar</a></td>"
							+ "<td><a href='ActivarProducto?Producto=" + u.getProducto() + 
							"&estatus=" + u.getEstatus() + "'>activo</a></td></tr>");
		}//fin del ciclo for
		imprimir.print("</table>");
		imprimir.close();
	}//fin doGet

}
