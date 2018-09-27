package logic;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.rowset.CachedRowSet;

import controller.CtrlProyectos;
import dataBase.DBsqlServer;

public class LogicProyecto {

	public static void addProyecto(String nombre, String presupuesto, String inicio, String fin) {
		
		String sqlQuery;
		Connection conexion;
		CachedRowSet rowset;
		int n_codigo;
		
		//Tendriamos que comprobar que los datos enviados estan correctos
		
		//conexion = DBsqlServer.conectarBD();

		try {	
			
			DBsqlServer.crearCadenaConexion();
			conexion = DBsqlServer.establecerConexion();
			
			sqlQuery = "SELECT MAX(CODIGO_PROYECTO) "
					+  " FROM JCD_PROYECTOS";
			
			rowset = DBsqlServer.ejecutarQuery(sqlQuery,conexion);
			rowset.next();
			n_codigo= Integer.parseInt(rowset.getString(1));
			n_codigo++;
						
			
			sqlQuery = "INSERT INTO JCD_PROYECTOS VALUES"
					+  "(" + String.valueOf(n_codigo) + ","
					+  "'" + nombre + "',"
					+  presupuesto + ","
					+  "'" + inicio + "',"
					+  "'" + fin + "')";
			DBsqlServer.ejecutarQueryUpdate(sqlQuery,conexion);
			DBsqlServer.cerrarConexion(conexion);
			
			CtrlProyectos.cargarListaProyectos();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}

	public static void editarProyecto(String nombre, String presupuesto, String inicio, String fin)throws SQLException {
		String sqlQuery;
		Connection conexion;
		
		//Tendriamos que comprobar que los datos enviados estan correctos
		
		//conexion = DBsqlServer.conectarBD();
		
		DBsqlServer.crearCadenaConexion();
		conexion = DBsqlServer.establecerConexion();

		sqlQuery = "UPDATE JCD_PROYECTOS SET "
				+  "NOMBRE = '"+ nombre + "',"
				+  "PRESUPUESTO = "+ presupuesto + ","
				+  "FECHA_INICIO = '"+ inicio + "',"
				+  "FECHA_FIN = '"+ fin + "'" 
				+ " WHERE CODIGO_PROYECTO = " + CtrlProyectos.proyectoSelecc;
			
		DBsqlServer.ejecutarQueryUpdate(sqlQuery,conexion);
		DBsqlServer.cerrarConexion(conexion);
			
		CtrlProyectos.cargarListaProyectos();

	}

}
