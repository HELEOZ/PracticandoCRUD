package cliente;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import controlador.ClienteDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


@WebServlet("/ConsultarCliente")
public class ConsultarCliente extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter imprimir = response.getWriter();
		imprimir.println("<link rel='stylesheet' href='css/styles.css'>");
		imprimir.println("<a href='AgregarC.html'>Agregar Cliente</a>");
		imprimir.println("<a href='MenuPizza.html'>Regresar al menu</a>");
		imprimir.println("<h1>Lista de Clientes</h1>");


		List<Cliente> lista = ClienteDAO.ConsultarTodosclientes();
		
		imprimir.print("<table border='1'>");
		imprimir.print("<th>Nombre</th><th>Telefono</th><th>email</th>" +
				"<th>Estatus</th>" +
				"<th>Modificar</th><th>Activar</th></tr>");
		
		for (Cliente u:lista) {
			String ActivoONo;
			if(u.getEstatus()==1) {
				ActivoONo ="Cliente Activo";
			}else {
				ActivoONo = "Cliente Desactivado";
			}
			imprimir.print("<tr><td>" + u.getNombre() + "</td><td>" + u.getTelefono() + "</td>" + 
						"<td>" + u.getEmail() + "</td><td>" + ActivoONo + "</td>"
							+ "<td><a href='ModificarCliente?IdCliente=" + u.getIdCliente() + "'>modificar</a></td>"
							+ "<td><a href='ActivarCliente?Cliente=" + u.getIdCliente() + 
							"&estatus=" + u.getEstatus() + "'>activo</a></td></tr>");
		}//fin del ciclo for
		imprimir.print("</table>");
		imprimir.close();
	}//fin doGet

}
