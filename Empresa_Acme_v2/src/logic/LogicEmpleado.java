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
		
		//conexion = DBsqlServer.conectarBD();
		conexion = DBsqlServer.establecerConexion();
		
		sqlQuery = "SELECT CODIGO_CARGO,NOMBRE "
				+  " FROM JCD_CARGOS";
		CachedRowSet rowset = DBsqlServer.ejecutarQuery(sqlQuery,conexion);
		DBsqlServer.cerrarConexion(conexion);
		
		return rowset;
	}
	
	public static void addEmpleado(Empleado emp) throws SQLException {
		String sqlQuery;
		Connection conexion;
		CachedRowSet rowset;
		int n_codigo;
		
		//Tendriamos que comprobar que los datos enviados estan correctos
			
		conexion = DBsqlServer.establecerConexion();
		//--> PASAR EL CODIGO DE EMPLEADO A IDENTITY PARA EVITAR ESTO
		sqlQuery = "SELECT MAX(CODIGO_EMPLEADO) "
				+  " FROM JCD_EMPLEADOS";
			
		rowset = DBsqlServer.ejecutarQuery(sqlQuery,conexion);
		rowset.next();
		n_codigo= Integer.parseInt(rowset.getString(1));
		n_codigo++;
		emp.setCodigo(n_codigo);
			
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
		
	}
	public static void editarEmpleado(Empleado emp) throws SQLException {
		String sqlQuery;
		Connection conexion;
		
		//Tendriamos que comprobar que los datos enviados estan correctos
			
		conexion = DBsqlServer.establecerConexion();
			
		sqlQuery = "UPDATE JCD_EMPLEADOS SET "
				+  "NOMBRE = '"+ emp.getNombre() + "',"
				+  "APELLIDOS = '"+ emp.getApellido() + "',"
				+  "DNI = '"+ emp.getDni() + "',"
				+  "GENERO = "+ emp.getGenero() + ","
				+  "CARGO_PRINCIPAL = "+ emp.getPuesto()
				+ " WHERE CODIGO_EMPLEADO = " + CtrlEmpleados.empleadoSelecc;
			
		DBsqlServer.ejecutarQueryUpdate(sqlQuery,conexion);
		DBsqlServer.cerrarConexion(conexion);
			
		CtrlEmpleados.cargarListaEmpleados();	
		
	}

}
