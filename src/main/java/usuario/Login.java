package usuario;

import java.io.IOException;
import java.io.PrintWriter;

import controlador.UsuarioDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");  		
		PrintWriter salida = response.getWriter();  
		int intentos=0, estatus;
		
		String usuario=request.getParameter("username");  
		String contra=request.getParameter("password");  		          		
				
		Usuario u = UsuarioDAO.ConsultarUsuarioPorCodigo(usuario);
		
		intentos = u.getIntentos();
		estatus = u.getEstatus();
		
			//Validar intentos fallidos
		    if (intentos <= 3) {
		    	//Validar estatus 0-inactivo / 1-activo
		    	if (estatus==0) {
		    		salida.print("<p><font size=10 color=#ffffff>El usuario está inactivo</font></p>");  
			        request.getRequestDispatcher("login.html").include(request, response);
		    	} else {
		    		//Validar usuario y contraseña		    		
		    		if (UsuarioDAO.validar(usuario, contra)){  
				    	UsuarioDAO.acumIntentos(usuario, 0);
				    	String nombre = request.getParameter("nombre");				        
				        
				    	response.sendRedirect("MenuPizza.html");
				   
				    }  
				    else{  
				    	UsuarioDAO.acumIntentos(usuario, intentos + 1);			    	
				    	salida.print("<p><font size=10 color= #ffffff >El usuario o contraseña son incorrectas</font></p>"); 
				    	request.getRequestDispatcher("login.html").include(request, response);				        
				        salida.close();  		
				    }	  
		    	}		  
		    } else { //+ de 3 intentos fallidos
		    	salida.print("<p><font size=10 color=red>El usuario está bloqueado por intentos fallidos</font></p>");  
		    	salida.print("<p><Comuníquese con el administrador del sistema</p>");
		        request.setCharacterEncoding("ISO-8859-1");
		    	request.getRequestDispatcher("login.html").include(request, response);
		    }		
	}  //fin doPost
} //fin servlet
	


