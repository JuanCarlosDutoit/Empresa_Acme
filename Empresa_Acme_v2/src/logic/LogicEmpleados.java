package logic;

import java.sql.Connection;

import javax.sql.rowset.CachedRowSet;
import javax.swing.table.DefaultTableModel;

import dataBase.DBsqlServer;
import util.Utilidades;
import view.FrmEmpleados;

public class LogicEmpleados {

	public static DefaultTableModel iniciaListaEmpleados() {
		String sqlQuery;
		DefaultTableModel modelo;
		Connection conexion;
		
		conexion = DBsqlServer.conectarBD();
		sqlQuery = "SELECT CODIGO_EMPLEADO,"
				        + "JCD_EMPLEADOS.NOMBRE AS NOMBRE,"
				        + "APELLIDOS,"
				        + "DNI,"
				        + "JCD_GENEROS.NOMBRE AS GENERO,"
				        + "JCD_CARGOS.NOMBRE AS PUESTO"
				+  " FROM JCD_EMPLEADOS,JCD_CARGOS,JCD_GENEROS"
				+  " WHERE JCD_EMPLEADOS.GENERO = JCD_GENEROS.CODIGO_GENERO"
				+  "  AND JCD_EMPLEADOS.CARGO_PRINCIPAL = JCD_CARGOS.CODIGO_CARGO";
		
		CachedRowSet rowset = DBsqlServer.ejecutarQuery(sqlQuery,conexion);
		modelo = Utilidades.creaModeloTablas(rowset);
		DBsqlServer.cerrarConexion(conexion);
		
		return modelo;
	}

	public static void borrarEmpleado(String empleadoSelecc) {
		String sqlQuery;
		DefaultTableModel modelo;
		Connection conexion;
		
		conexion = DBsqlServer.conectarBD();
		sqlQuery = "DELETE" 
				+  " FROM JCD_EMPLEADOS"
				+  " WHERE CODIGO_EMPLEADO = " + empleadoSelecc; 
		
		DBsqlServer.ejecutarQueryUpdate(sqlQuery,conexion);
		DBsqlServer.cerrarConexion(conexion);
	}

}
