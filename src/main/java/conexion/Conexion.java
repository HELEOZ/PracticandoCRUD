package conexion;

import java.sql.Connection;
import java.sql.DriverManager;

public class Conexion {
	public static Connection establecerCon() {
		Connection con = null;
		
		try {
			//inicializar la conexion
			String dbDriver = "com.mysql.jdbc.Driver";
			String dbURL = "jdbc:mysql://localhost:3306/";
			String dbNombre = "Pizza3769";
			String dbProducto = "root";
			String dbContrasena = "Zavala.10";
			Class.forName(dbDriver);
			con = DriverManager.getConnection(dbURL + dbNombre, dbProducto, dbContrasena);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return con;
	} //fin del método establecerCon

}
