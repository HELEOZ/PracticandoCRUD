package controlador;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import conexion.Conexion;
import usuario.Usuario;

public class UsuarioDAO {

	public static int agregar(Usuario u) {
		int estatus = 0;
		
		try {			
			Connection con = Conexion.establecerCon();
			PreparedStatement ps = con.prepareStatement("Insert into usuario (IdUsuario, nombre, contrasena, email, IdPais, estatus, Intentos) " +
					                                     "values (?, ?, ?, ?, ?, ?, ?);");
			ps.setString(1, u.getUsuario());
			ps.setString(2, u.getNombre());
			ps.setString(3, u.getContrasena());
			ps.setString(4, u.getEmail());
			ps.setString(5, u.getPais());
			ps.setInt(6, u.getEstatus());
			ps.setInt(7, u.getIntentos());
			
			estatus = ps.executeUpdate();
			con.close();
			ps.close();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		} 
		return estatus;
		
	} //fin método agregar
	
	public static List<Usuario> ConsultarTodosUsuarios(){
		List<Usuario> lista = new ArrayList<Usuario>();
		
		try {
			Connection con = Conexion.establecerCon();
			PreparedStatement ps = con.prepareStatement("Select * from usuario;");
			ResultSet rs = ps.executeQuery();
						
			while (rs.next()) {
				Usuario u = new Usuario();
				u.setUsuario(rs.getString(1));
				u.setNombre(rs.getString(2));
				u.setContrasena(rs.getString(3));
				u.setEmail(rs.getString(4));
				u.setPais(rs.getString(5));
				u.setEstatus(rs.getInt(6));
				u.setIntentos(rs.getInt(7));
				lista.add(u);
			} //fin del while
			con.close();
			ps.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return lista;
		
	}//fin método ConsultarTodosUsuarios
	
	public static Usuario ConsultarUsuarioPorCodigo(String id) {
		
		Usuario u = new Usuario();
		
		try {
			Connection con = Conexion.establecerCon();
			PreparedStatement ps = con.prepareStatement("Select * from usuario where IdUsuario = ?;");
			ps.setString(1, id);
			ResultSet rs = ps.executeQuery();
			
			if (rs.next()) {
				u.setUsuario(rs.getString(1));
				u.setNombre(rs.getString(2));
				u.setContrasena(rs.getString(3));
				u.setEmail(rs.getString(4));
				u.setPais(rs.getString(5));
				u.setEstatus(rs.getInt(6));
				u.setIntentos(rs.getInt(7));
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return u;
	}//fin del método ConsultarEmpleadoPorCodigo
	
	public static int actualizar( Usuario u) {
		int estatus = 0;
		
		try {
			Connection con = Conexion.establecerCon();
			PreparedStatement ps = con.prepareStatement("Update usuario set nombre=?, contrasena=?, " +
					"email=?, IdPais=? where IdUsuario=?;");
						
			ps.setString(1, u.getNombre());
			ps.setString(2, u.getContrasena());
			ps.setString(3, u.getEmail());
			ps.setString(4, u.getPais());
			ps.setString(5, u.getUsuario());
			estatus = ps.executeUpdate();
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return estatus;
	}//fin método actualizar
	
	public static int activar(String id, int estatus) {
		int sqlestatus = 0;
		int intentos=0;
				
		if (estatus==1) {
			estatus=0;
		} else {
			estatus=1;
		}
		
		try(Connection con = Conexion.establecerCon();
				PreparedStatement ps = con.prepareStatement("Update usuario set estatus=?, intentos=? where IdUsuario =?;"))
		{
			ps.setInt(1, estatus);
			ps.setInt(2, intentos);
			ps.setString(3, id);
			
			sqlestatus = ps.executeUpdate();
			con.close();
		}
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return sqlestatus;
	}//fin Activar

	public static boolean validar(String user, String pass){  
		boolean sqlestatus=false;  
		
		try(Connection con = Conexion.establecerCon();
				PreparedStatement ps = con.prepareStatement("Select IdUsuario, contrasena from usuario " +
						"where IdUsuario=? and contrasena=?;"))
		{
			ps.setString(1, user);
			ps.setString(2, pass);
			
			ResultSet rs = ps.executeQuery();
			sqlestatus = rs.next();
			
			con.close();
		}
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return sqlestatus;
	}//fin validar
		
	public static void acumIntentos(String usuario, int intentos) {
		int sqlestatus = 0;
		try(Connection con = Conexion.establecerCon();
				PreparedStatement ps = con.prepareStatement("Update usuario set intentos = ? where IdUsuario =?;"))
		{			
			ps.setInt(1, intentos);
			ps.setString(2, usuario);
			sqlestatus = ps.executeUpdate();
			con.close();
		}
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}//fin acumIntentos
	
} //fin clase UsuarioDAO

