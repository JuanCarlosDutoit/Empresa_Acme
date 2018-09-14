package logic;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.rowset.CachedRowSet;
import javax.swing.table.DefaultTableModel;

import controller.CtrlEmpleados;
import dataBase.DBsqlServer;

public class LogicEmpleado {

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

	public static void addEmpleado(String nombre, String apellidos, String dni, String genero, String cargo) {
		String sqlQuery;
		Connection conexion;
		CachedRowSet rowset;
		String n_cargo;
		int n_codigo;
		
		//Tendriamos que comprobar que los datos enviados estan correctos
		
		conexion = DBsqlServer.conectarBD();

		try {	
			
			sqlQuery = "SELECT MAX(CODIGO_EMPLEADO) "
					+  " FROM JCD_EMPLEADOS";
			
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
			
			
			sqlQuery = "INSERT INTO JCD_EMPLEADOS VALUES"
					+  "(" + String.valueOf(n_codigo) + ","
					+  "'" + nombre + "',"
					+  "'" +apellidos + "',"
					+   genero + ","
					+   n_cargo + ","
					+  "'" +dni + "')";
			DBsqlServer.ejecutarQueryUpdate(sqlQuery,conexion);
			DBsqlServer.cerrarConexion(conexion);
			
			CtrlEmpleados.cargarListaEmpleados();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public static void editarEmpleado(String nombre, String apellidos, String dni, String genero, String cargo) {
		String sqlQuery;
		Connection conexion;
		CachedRowSet rowset;
		String n_cargo;
		
		//Tendriamos que comprobar que los datos enviados estan correctos
		
		conexion = DBsqlServer.conectarBD();

		try {	
			
			sqlQuery = "SELECT CODIGO_CARGO"
					+  " FROM JCD_CARGOS"
					+  " WHERE NOMBRE = '" + cargo + "'";
			
			rowset = DBsqlServer.ejecutarQuery(sqlQuery,conexion);
			rowset.next();
			n_cargo= rowset.getString(1);
			
			
			sqlQuery = "UPDATE JCD_EMPLEADOS SET "
					+  "NOMBRE = '"+ nombre + "',"
					+  "APELLIDOS = '"+ apellidos + "',"
					+  "DNI = '"+ dni + "',"
					+  "GENERO = "+ genero + ","
					+  "CARGO_PRINCIPAL = "+ n_cargo 
					+ " WHERE CODIGO_EMPLEADO = " + CtrlEmpleados.empleadoSelecc;
			
			DBsqlServer.ejecutarQueryUpdate(sqlQuery,conexion);
			DBsqlServer.cerrarConexion(conexion);
			
			CtrlEmpleados.cargarListaEmpleados();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
