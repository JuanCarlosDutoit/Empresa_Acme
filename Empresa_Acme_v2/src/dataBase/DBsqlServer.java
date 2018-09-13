package dataBase;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.sql.rowset.CachedRowSet;
import javax.sql.rowset.RowSetFactory;
import javax.sql.rowset.RowSetProvider;
import javax.swing.JOptionPane;

import util.Utilidades;
import view.FrmPrincipal;

public class DBsqlServer {
	
	
	public static String cadenaConexion;
	public static String ip,port,bd,usu,pass;
	
	public static void asignarDatosConexion() {
		ip= Utilidades.ip;
		port= Utilidades.port;
		bd=Utilidades.bd;
		usu=Utilidades.usu;
		pass=Utilidades.pass;
	}
	public static Connection conectarBD() {
		Connection conexion;
		crearCadenaConexion();
		conexion = establecerConexion();
		return conexion;
	}
	public static void crearCadenaConexion() {
		
		String cadena  = "jdbc:sqlserver:";
			   cadena += "//" + ip;
			   cadena += ":" + port;
			   cadena += ";database=" + bd;
			   cadena += ";user=" + usu;
			   cadena += ";password=" + pass;

		cadenaConexion = cadena;
	}
	
	
	public static Connection establecerConexion(){
		Connection conexionAux;
		System.out.println("Conectando a "+ cadenaConexion);
		try {
			conexionAux =  DriverManager.getConnection(cadenaConexion);
			FrmPrincipal.lblConexion.setText("Conectado");
			return conexionAux;
		} catch (SQLException e) {
			FrmPrincipal.lblConexion.setText("Error en conexion");
			return null;
		}
	}
	public static void cerrarConexion(Connection conn){
		try {
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static CachedRowSet ejecutarQuery(String sqlQuery,Connection conexion) {
		try {
			System.out.println("amono");
			Statement s = conexion.createStatement();
			ResultSet r = s.executeQuery(sqlQuery);
			RowSetFactory factory = RowSetProvider.newFactory();
			CachedRowSet rowset = factory.createCachedRowSet();
			rowset.populate(r);
			return rowset;
			
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Error en la conexion a la BD" + e.getMessage(), "Error", 1);
			System.out.println("Error en la conexion a la BD");
			return null;
		}
		
	}
	
	
	/*public static CachedRowSet consultaSQL(String strSQL) throws SQLException {

		Connection c = databasemanager.DBsqlServer.establecerConexion();
		Statement s = c.createStatement();
		ResultSet r = s.executeQuery(strSQL);
		RowSetFactory factory = RowSetProvider.newFactory();
		CachedRowSet rowset = factory.createCachedRowSet();
		 
		rowset.populate(r);
		databasemanager.DBsqlServer.cerrarConexion(c);
		
		return rowset;
	}*/
}
