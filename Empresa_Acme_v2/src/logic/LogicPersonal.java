package logic;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.rowset.CachedRowSet;

import controller.CtrlEquipos;
import dataBase.DBsqlServer;

public class LogicPersonal {

	public static CachedRowSet rellenaComboEmpleado() {
		String sqlQuery;
		Connection conexion;
		
		conexion = DBsqlServer.conectarBD();
		sqlQuery = "SELECT CODIGO_EMPLEADO,NOMBRE,APELLIDOS "
				+  " FROM JCD_EMPLEADOS"
				+  " WHERE CODIGO_EMPLEADO NOT IN("
				+  " SELECT EMPLEADO" 
				+  " FROM JCD_PERSONAL_EQUIPOS"
				+  " WHERE EQUIPO = " + CtrlEquipos.equipoSelecc +")";
		CachedRowSet rowset = DBsqlServer.ejecutarQuery(sqlQuery,conexion);
		DBsqlServer.cerrarConexion(conexion);
		
		return rowset;
	}

	public static CachedRowSet rellenaComboCargo() {
		String sqlQuery;
		Connection conexion;
		
		conexion = DBsqlServer.conectarBD();
		sqlQuery = "SELECT CODIGO_CARGO,NOMBRE "
				+  " FROM JCD_CARGOS";
		CachedRowSet rowset = DBsqlServer.ejecutarQuery(sqlQuery,conexion);
		DBsqlServer.cerrarConexion(conexion);
		
		return rowset;
	}

	public static void addEmpleado(String empleado, String cargo) {
		String sqlQuery;
		Connection conexion;
		CachedRowSet rowset;
		String n_cargo;
		int n_codigo;
		
		//Tendriamos que comprobar que los datos enviados estan correctos
		
		try {	
			conexion = DBsqlServer.conectarBD();
			sqlQuery = "SELECT MAX(CODIGO_PERSONAL) "
					+  " FROM JCD_PERSONAL_EQUIPOS";
			
			rowset = DBsqlServer.ejecutarQuery(sqlQuery,conexion);
			rowset.next();
			n_codigo= Integer.parseInt(rowset.getString(1));
			n_codigo++;
			
			sqlQuery = "SELECT CODIGO_CARGO"
					+  " FROM JCD_CARGOS"
					+  " WHERE NOMBRE = '" + cargo + "'";
			
			rowset = DBsqlServer.ejecutarQuery(sqlQuery,conexion);
			rowset.next();
			n_cargo= rowset.getString(1);
			
			
			sqlQuery = "INSERT INTO JCD_PERSONAL_EQUIPOS VALUES"
					+  "(" + String.valueOf(n_codigo) + ","
					+  empleado + ","
					+  CtrlEquipos.equipoSelecc + ","
					+  n_cargo +")";
			DBsqlServer.ejecutarQueryUpdate(sqlQuery,conexion);
			DBsqlServer.cerrarConexion(conexion);
			
			CtrlEquipos.cargarListaPersonal();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		
	}

}
