package controlador;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import conexion.Conexion;
import producto.Producto;

public class ProductoDAO {
	public static int agregar(Producto u) {
		int estatus = 0;
		
		try {			
			Connection con = Conexion.establecerCon();
			PreparedStatement ps = con.prepareStatement("Insert into Producto (IdProducto, nombre, IdTipo, estatus, Precio) " +
					                                     "values (?, ?, ?, ?, ?);");
			ps.setInt(1, u.getProducto());
			ps.setString(2, u.getNombre());
			ps.setInt(3, u.getIdTipo());
			ps.setInt(4, u.getEstatus());
			ps.setDouble(5, u.getPrecio());
			
			estatus = ps.executeUpdate();
			con.close();
			ps.close();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		} 
		return estatus;
		
	} //fin método agregar
	
	public static List<Producto> ConsultarTodosProductos(){
	    List<Producto> lista = new ArrayList<Producto>();
	    
	    try {
	    	Connection con = Conexion.establecerCon();
	        PreparedStatement ps = con.prepareStatement("SELECT p.IdProducto, p.Nombre, t.Nombre AS Tipo, p.Precio ,p.estatus FROM Producto p JOIN Tipo t ON p.IdTipo = t.IdTipo;");
	        ResultSet rs = ps.executeQuery();
	        
	        while (rs.next()) {
	            Producto u = new Producto();
	            u.setProducto(rs.getInt("IdProducto"));  
	            u.setNombre(rs.getString("Nombre"));
	            u.setTipo(rs.getString("Tipo"));
	            u.setPrecio(rs.getDouble("Precio"));
	            u.setEstatus(rs.getInt("estatus"));
	            lista.add(u);
	        } //fin del while
	        con.close();
	        ps.close();
	        
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return lista;
	}//fin método ConsultarTodosProductos
	public static List<Producto> ConsultarTipoExecto(int idTipoSelecionado){
	    List<Producto> listatipo = new ArrayList<Producto>();
	    
	    try {
	    	Connection con = Conexion.establecerCon();
	        PreparedStatement ps = con.prepareStatement("select * from tipo where not IdTipo=?;");
	        ps.setInt(1, idTipoSelecionado);
	        ResultSet rs = ps.executeQuery();
	        while (rs.next()) {
	            Producto u = new Producto();
	            u.setIdTipo(rs.getInt(1));
	            u.setTipo(rs.getString(2));
	            listatipo.add(u);
	        } //fin del while
	        con.close();
	        ps.close();
	        
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return listatipo;
	}
	
	public static Producto ConsultarProductoPorCodigo(String id) {
		
		Producto u = new Producto();
		
		try {
			Connection con = Conexion.establecerCon();
			PreparedStatement ps = con.prepareStatement("Select * from Producto where IdProducto = ?;");
			ps.setString(1, id);
			ResultSet rs = ps.executeQuery();
			
			if (rs.next()) {
				u.setProducto(rs.getInt(1));
				u.setNombre(rs.getString(2));
				u.setIdTipo(rs.getInt(3)); // Obtener el ID de tipo
				u.setPrecio(rs.getDouble(4));
	            u.setTipo(obtenerNombreIdTipo(u.getIdTipo())); // Obtener el nombre de tipo
	            u.setEstatus(rs.getInt(5));
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return u;
	}//fin del método ConsultarEmpleadoPorCodigo
	
	public static String obtenerNombreIdTipo(int IdTipo) {
		String nombreTipo = null;; // Valor por defecto en caso de no encontrar el nombre
	    
	    try {
	    	Connection con = Conexion.establecerCon();
	        PreparedStatement ps = con.prepareStatement("Select * from Tipo where IdTipo = ?;");
	        ps.setInt(1, IdTipo);
	        
	        ResultSet rs = ps.executeQuery();
	        
	        if (rs.next()) {
	        	nombreTipo = rs.getString("Nombre");
	        }
	        
	        con.close();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    
	    return nombreTipo;
	}
	
	public static int actualizar(Producto u) {
		int estatus = 0;
		
		try {
			Connection con = Conexion.establecerCon();
			PreparedStatement ps = con.prepareStatement("Update Producto set nombre=?, IdTipo=?, Precio=? where IdProducto=?;");
						
			ps.setString(1, u.getNombre());
			ps.setInt(2, u.getIdTipo());
			ps.setDouble(3, u.getPrecio());
			ps.setInt(4, u.getProducto());
			
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
				PreparedStatement ps = con.prepareStatement("Update Producto set estatus=? where IdProducto =?;"))
		{
			ps.setInt(1, estatus);
			ps.setString(2, id);
			
			sqlestatus = ps.executeUpdate();
			con.close();
		}
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return sqlestatus;
	}//fin Activar
	
} //fin clase ProductoDAO

