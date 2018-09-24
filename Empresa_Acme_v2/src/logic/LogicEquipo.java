package logic;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.rowset.CachedRowSet;

import controller.CtrlEquipos;
import dataBase.DBsqlServer;

public class LogicEquipo {

	public static void addEmpleado(String nombre, String descripcion) {
		String sqlQuery;
		Connection conexion;
		CachedRowSet rowset;
		int n_codigo;
		
		//Tendriamos que comprobar que los datos enviados estan correctos
		
		conexion = DBsqlServer.conectarBD();

		try {	
			
			sqlQuery = "SELECT MAX(CODIGO_EQUIPO) "
					+  " FROM JCD_EQUIPOS";
			
			rowset = DBsqlServer.ejecutarQuery(sqlQuery,conexion);
			rowset.next();
			n_codigo= Integer.parseInt(rowset.getString(1));
			n_codigo++;
			
			sqlQuery = "INSERT INTO JCD_EQUIPOS VALUES"
					+  "(" + String.valueOf(n_codigo) + ","
					+  "'" + nombre + "',"
					+  "'" +descripcion + "')";
			DBsqlServer.ejecutarQueryUpdate(sqlQuery,conexion);
			DBsqlServer.cerrarConexion(conexion);
			
			CtrlEquipos.cargarListaEquipos();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	public static void editarEquipo(String nombre, String descripcion) {
		String sqlQuery;
		Connection conexion;
		
		//Tendriamos que comprobar que los datos enviados estan correctos
		
		conexion = DBsqlServer.conectarBD();
	
		sqlQuery = "UPDATE JCD_EQUIPOS SET "
				+  "NOMBRE = '"+ nombre + "',"
				+  "DESCRIPCION = '"+ descripcion + "'"
				+ " WHERE CODIGO_EQUIPO = " + CtrlEquipos.equipoSelecc;
	
		DBsqlServer.ejecutarQueryUpdate(sqlQuery,conexion);
		DBsqlServer.cerrarConexion(conexion);
			
		CtrlEquipos.cargarListaEquipos();	
	}

}
