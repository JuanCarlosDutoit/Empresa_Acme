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

/**
 * 
 * @author Juan Carlos Dutoit
 * @since 24/09/2018
 * Esta clase engloba todos los métodos que actuan sobre la base de datos
 *
 */
public class DBsqlServer {
		
	public static Connection conexion;
	public static String cadenaConexion;
	public static String ip;
	public static String port;
	public static String bd;
	public static String usu;
	public static String pass;
	
	public static boolean testConexion() {
		Connection test;
		boolean testOk;
		
		System.out.println("Testing");
		testOk = false;
		try {
			asignarDatosConexion();
			crearCadenaConexion();
			test = establecerConexion();
			if(test != null) {
				cerrarConexion(test);
				System.out.println("Test OK");
				testOk = true;
			}else {
				System.out.println("Error al conectar");
				testOk =  false;
			}
			return testOk;
		}catch (SQLException e){
			testOk =  false;
			Utilidades.gestionaErrorSql(e);
			return testOk;
		}
	}
	
	/**
	 * @author Juan Carlos Dutoit
	 * @since 24/09/2018
	 * Metodo que recoge los parametros de conexión de la base
	 * de datos previamente leidos de los ficheros de conexión
	 * y los asigna a las variables globales de la clase.
	 */
	public static void asignarDatosConexion() {
		ip = Utilidades.ip;
		port = Utilidades.port;
		bd = Utilidades.bd;
		usu = Utilidades.usu;
		pass = Utilidades.pass;
	}
	
	/**
	 * @author Juan Carlos Dutoit
	 * @since 24/09/2018
	 * Metodo que crea la cadena de conexion a la base de datos SQlServer con los 
	 * parametros leidos de los ficheros de conexión.
	 */
	public static void crearCadenaConexion() {
		
		String cadena  = "jdbc:sqlserver:";
			   cadena += "//" + ip;
			   cadena += ":" + port;
			   cadena += ";database=" + bd;
			   cadena += ";user=" + usu;
			   cadena += ";password=" + pass;

		cadenaConexion = cadena;
	}
	public static Connection establecerConexion() throws SQLException{
		Connection conexionAux;
		System.out.println("Conectando a BD");
		//System.out.println("Conectando a "+ cadenaConexion);
		conexionAux =  DriverManager.getConnection(cadenaConexion);
		return conexionAux;
	}
	
	public static void cerrarConexion(Connection conn){
		try {
			conn.close();
			System.out.println("Desonectando de BD");
		} catch (SQLException e) {
			System.out.println("Error Conectando a BD ");
			e.printStackTrace();
		}
	}

	/**
	 * @author Juan Carlos Dutoit Carmona
	 * @since 24/09/2018
	 * @deprecated Vamos a usar los metodos simples
	 * @return 
	 * @throws SQLException
	 */
	public static Connection conectarBD() throws SQLException {
		Connection conexion;
		crearCadenaConexion();
		conexion = establecerConexion();
		return conexion;
	}
	
	public static CachedRowSet ejecutarQuery(String sqlQuery,Connection conexion) throws SQLException  {
		System.out.println("Ejecutando consulta " + sqlQuery);
		Statement s = conexion.createStatement();
		ResultSet r = s.executeQuery(sqlQuery);
		RowSetFactory factory = RowSetProvider.newFactory();
		CachedRowSet rowset = factory.createCachedRowSet();
		rowset.populate(r);
		return rowset;
	}
	public static void ejecutarQueryUpdate(String sqlQuery, Connection conexion) {
		int r;
		
		try {
			System.out.println("Ejecutando consulta " + sqlQuery);
			Statement s = conexion.createStatement();
			r = s.executeUpdate(sqlQuery);
			JOptionPane.showMessageDialog(null, "Se ha modificado " + r + " registros", "Info", 1);
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Error en la ejecucion de Sql." + e.getMessage(), "Error", 1);
			System.out.println("Error en la ejecucion de Sql.");
		}
	}
	
}
