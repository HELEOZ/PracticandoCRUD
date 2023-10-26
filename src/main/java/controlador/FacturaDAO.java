package controlador;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import conexion.Conexion;
import producto.Producto;

public class FacturaDAO {
	
	public static int agregar(Producto u) {
		int estatus = 0;
		
		try {			
			Connection con = Conexion.establecerCon();
			PreparedStatement ps = con.prepareStatement("Insert into Producto (IdProducto, nombre, IdTipo, estatus, Precio) " +
					                                     "values (?, ?, ?, ?, ?);");
			ps.setInt(1, u.getProducto());
			ps.setString(2, u.getNombre());
			
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
	
}
