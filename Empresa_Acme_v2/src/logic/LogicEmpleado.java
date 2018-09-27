package logic;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.rowset.CachedRowSet;

import controller.CtrlEmpleados;
import dataBase.DBsqlServer;
import model.Empleado;

public class LogicEmpleado {

	public static CachedRowSet rellenaComboCargo() throws SQLException {
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
		int n_codigo;
		
		//Tendriamos que comprobar que los datos enviados estan correctos
		
		//conexion = DBsqlServer.conectarBD();

		try {	
			
			DBsqlServer.crearCadenaConexion();
			conexion = DBsqlServer.establecerConexion();
			
			sqlQuery = "SELECT MAX(CODIGO_EMPLEADO) "
					+  " FROM JCD_EMPLEADOS";
			
			rowset = DBsqlServer.ejecutarQuery(sqlQuery,conexion);
			rowset.next();
			n_codigo= Integer.parseInt(rowset.getString(1));
			n_codigo++;
			
			/*sqlQuery = "SELECT CODIGO_CARGO"
					+  " FROM JCD_CARGOS"
					+  " WHERE NOMBRE = '" + cargo + "'";
			
			rowset = DBsqlServer.ejecutarQuery(sqlQuery,conexion);
			rowset.next();
			n_cargo= rowset.getString(1);*/
			
			
			sqlQuery = "INSERT INTO JCD_EMPLEADOS VALUES"
					+  "(" + String.valueOf(n_codigo) + ","
					+  "'" + nombre + "',"
					+  "'" +apellidos + "',"
					+   genero + ","
					+   cargo + ","
					+  "'" +dni + "')";
			DBsqlServer.ejecutarQueryUpdate(sqlQuery,conexion);
			DBsqlServer.cerrarConexion(conexion);
			
			CtrlEmpleados.cargarListaEmpleados();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public static void addEmpleado(Empleado emp) {
		String sqlQuery;
		Connection conexion;
		CachedRowSet rowset;
		int n_codigo;
		
		//Tendriamos que comprobar que los datos enviados estan correctos
		
		//conexion = DBsqlServer.conectarBD();

		try {	
			
			DBsqlServer.crearCadenaConexion();
			conexion = DBsqlServer.establecerConexion();
			//--> PASAR EL CODIGO DE EMPLEADO A IDENTITY PARA EVITAR ESTO
			sqlQuery = "SELECT MAX(CODIGO_EMPLEADO) "
					+  " FROM JCD_EMPLEADOS";
			
			rowset = DBsqlServer.ejecutarQuery(sqlQuery,conexion);
			rowset.next();
			n_codigo= Integer.parseInt(rowset.getString(1));
			n_codigo++;
			emp.setCodigo(n_codigo);
			
			/*sqlQuery = "SELECT CODIGO_CARGO"
					+  " FROM JCD_CARGOS"
					+  " WHERE NOMBRE = '" + cargo + "'";
			
			rowset = DBsqlServer.ejecutarQuery(sqlQuery,conexion);
			rowset.next();
			n_cargo= rowset.getString(1);*/
			
			
			sqlQuery = "INSERT INTO JCD_EMPLEADOS VALUES"
					+  "(" + String.valueOf(n_codigo) + ","
					+  "'" + emp.getNombre() + "',"
					+  "'" + emp.getApellido() + "',"
					+   emp.getGenero() + ","
					+   emp.getPuesto() + ","
					+  "'" + emp.getDni() + "')";
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
		
		//conexion = DBsqlServer.conectarBD();

		try {	
			
			DBsqlServer.crearCadenaConexion();
			conexion = DBsqlServer.establecerConexion();
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
