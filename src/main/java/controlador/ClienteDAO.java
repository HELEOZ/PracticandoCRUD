package controlador;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import cliente.Cliente;
import conexion.Conexion;

public class ClienteDAO {

	public static int agregar(Cliente u) {
		int estatus = 0;
		
		try {			
			Connection con = Conexion.establecerCon();
			PreparedStatement ps = con.prepareStatement("Insert into cliente (nombre, Telefono, email, estatus) " +
					                                     "values (?, ?, ?, ?);");
			ps.setString(1, u.getNombre());
			ps.setInt(2, u.getTelefono());
			ps.setString(3, u.getEmail());
			ps.setInt(4, u.getEstatus());
			
			estatus = ps.executeUpdate();
			con.close();
			ps.close();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		} 
		return estatus;
		
	} //fin método agregar
	
	public static List<Cliente> ConsultarTodosclientes(){
		List<Cliente> lista = new ArrayList<Cliente>();
		
		try {
			Connection con = Conexion.establecerCon();
			PreparedStatement ps = con.prepareStatement("Select * from cliente;");
			ResultSet rs = ps.executeQuery();
						
			while (rs.next()) {
				Cliente u = new Cliente();
				u.setIdCliente(rs.getInt(1));
				u.setNombre(rs.getString(2));
				u.setTelefono(rs.getInt(3));
				u.setEmail(rs.getString(4));
				u.setEstatus(rs.getInt(5));
				lista.add(u);
			} //fin del while
			con.close();
			ps.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return lista;
		
	}//fin método ConsultarTodosclientes
	
	public static Cliente ConsultarclientePorCodigo(String id) {
		
		Cliente u = new Cliente();
		
		try {
			Connection con = Conexion.establecerCon();
			PreparedStatement ps = con.prepareStatement("Select * from cliente where IdCliente = ?;");
			ps.setString(1, id);
			ResultSet rs = ps.executeQuery();
			
			if (rs.next()) {
				u.setIdCliente(rs.getInt(1));
				u.setNombre(rs.getString(2));
				u.setTelefono(rs.getInt(3));
				u.setEmail(rs.getString(4));
				u.setEstatus(rs.getInt(5));
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return u;
	}//fin del método ConsultarEmpleadoPorCodigo
	
	public static int actualizar(Cliente u) {
		int estatus = 0;
		
		try {
			Connection con = Conexion.establecerCon();
			PreparedStatement ps = con.prepareStatement("Update cliente set nombre=?, Telefono=?, " +
					"email=? where IdCliente=?;");
						
			ps.setString(1, u.getNombre());
			ps.setInt(2, u.getTelefono());
			ps.setString(3, u.getEmail());
			ps.setInt(4, u.getIdCliente());
			estatus = ps.executeUpdate();
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return estatus;
	}//fin método actualizar
	
	public static int activar(int id, int estatus) {
		int sqlestatus = 0;
				
		if (estatus==1) {
			estatus=0;
		} else {
			estatus=1;
		}
		
		try(Connection con = Conexion.establecerCon();
				PreparedStatement ps = con.prepareStatement("Update cliente set estatus=? where IdCliente =?;"))
		{
			ps.setInt(1, estatus);
			ps.setInt(2, id);
			
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
				PreparedStatement ps = con.prepareStatement("Select Idcliente, contrasena from cliente " +
						"where Idcliente=? and contrasena=?;"))
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
	
} //fin clase clienteDAO

