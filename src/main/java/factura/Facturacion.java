package factura;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import producto.Producto;
import usuario.Usuario;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import cliente.Cliente;
import controlador.ClienteDAO;
import controlador.ProductoDAO;
import controlador.UsuarioDAO;
@WebServlet("/facturas")
public class Facturacion extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter salida = response.getWriter();

        salida.println("<link rel='stylesheet' href='css/facturacion.css'>");
        salida.println("<h1>Pizzeria HELEOZ</h1>");
        // ... Código del formulario ...
        Factura fac = new Factura();
        salida.println("<form action='facturas' method='post'>");
        //clientes
        Usuario user = new Usuario();
        salida.print("<tr><td>Cajero:</td><td>");
		salida.print("<input type='text' name='cajero' value='" + user.getNombre() + "' maxlength='12' required/></td></tr>");
		salida.print("<input type='date' name='fechaEmision' value='" + fac.getFechaEmision() + "' maxlength='12' required/></td></tr>");
		List<Cliente> listacli = ClienteDAO.ConsultarTodosclientes();
		
		salida.print("<td>Cliente</td><td><select name='IdTipo' required>");
		for (Cliente td : listacli) { 
		    salida.print("<option value='" + td.getIdCliente() + "'>" + td.getNombre() + "</option>");
		}
		salida.print("</select></td></tr>");
		
		//Productos
        salida.print("<tr><td>Productos:</td><td>");
        salida.print("<table border='1'>");
        salida.print("<tr><th>Nombre</th><th>Precio</th><th>Cantidad</th></tr>");
        List<Producto> lisproducto = ProductoDAO.ConsultarTodosProductos();
        fac.setCantidad(1);
        for (Producto td : lisproducto) { 
            salida.print("<tr><td>" + td.getNombre() + "</td><td>" + td.getPrecio() + "</td>");
            salida.print("<td><input type='number' name='cantidad_" + td.getProducto() + "' min='1' value='1' /></td>");
            salida.print("<td><input type='submit' name='agregar' value='Agregar' /></td>");
            salida.print("</tr>");
            salida.print("<td><a href='DetalleProductos?productoId=" + td.getProducto() + "'>Agregar</a></td></tr>");
        }
        salida.print("</table>");
        /*
        salida.print("<input type='number' name='descuento' value='" + fac.getDescuento() + "'  /></td></tr>");
        salida.print("<input type='number' name='subtotal' value='" + fac.getSubtotal() + "' /></td></tr>");
        salida.print("<input type='number' name='isv' value='" + fac.getISV() + "'  /></td></tr>");
        salida.print("<input type='number' name='total' value='" + fac.getTotal() + "'/></td></tr>");*/
        salida.println("</form>");
       
        // Tabla carrito para productos agregados
        
        salida.println("<form action='Facturacion' method='post'>");
        salida.println("<h2>Carrito:</h2>");
        salida.print("<table border='1' id='tablaCarrito'>");
        salida.print("<tr><th>Producto</th><th>Precio</th><th>Cantidad</th><th>Subtotal</th><th>ISV</th><th>Total</th></tr>");
        
        /*for (Producto producto : carrito) {
            salida.println("<tr><td>" + producto.getNombre() + "</td><td>" + producto.getCantidad() + "</td></tr>");
        }*/
        salida.println("</table>");
        
        salida.print("</table>");   
        salida.println("</form>");
        salida.println("<br>");
    }
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	
        String productoNombre = request.getParameter("nombre");
        int productoCantidad = Integer.parseInt(request.getParameter("cantidad"));

        // Simulación: consulta a la base de datos para obtener el producto
       /* Producto producto = ProductoDAO.obtenerProductoPorcodigo(productoNombre);
        producto.setCantidad(productoCantidad);

        // Agregar el producto al carrito
        carrito.add(producto);*/

        response.sendRedirect("facturas");
    }
    
}
