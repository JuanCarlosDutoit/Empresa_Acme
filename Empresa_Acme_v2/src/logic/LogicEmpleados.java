package logic;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.rowset.CachedRowSet;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import controller.CtrlEmpleados;
import dataBase.DBsqlServer;
import util.Utilidades;

public class LogicEmpleados {

	public static DefaultTableModel iniciaListaEmpleados() throws SQLException {
		String sqlQuery;
		DefaultTableModel modelo;
		Connection conexion;
		
		conexion = DBsqlServer.establecerConexion();
		
		sqlQuery = "SELECT CODIGO_EMPLEADO,"
				        + "JCD_EMPLEADOS.NOMBRE AS NOMBRE,"
				        + "APELLIDOS,"
				        + "DNI,"
				        + "JCD_GENEROS.NOMBRE AS GENERO,"
				        + "JCD_CARGOS.NOMBRE AS PUESTO"
				+  " FROM JCD_EMPLEADOS,JCD_CARGOS,JCD_GENEROS"
				+  " WHERE JCD_EMPLEADOS.GENERO = JCD_GENEROS.CODIGO_GENERO"
				+  "  AND JCD_EMPLEADOS.CARGO_PRINCIPAL = JCD_CARGOS.CODIGO_CARGO"
				+  " ORDER BY 3,2";
		
		CachedRowSet rowset = DBsqlServer.ejecutarQuery(sqlQuery,conexion);
		modelo = Utilidades.creaModeloTablas(rowset);
		DBsqlServer.cerrarConexion(conexion);
		
		return modelo;
	}

	public static void borrarEmpleado() throws SQLException {
		String sqlQuery;
		Connection conexion;
		boolean elimina;
		
		elimina = true;
		//--> Antes de eliminar hay que comprobar que no pertenezca a equipos
		conexion = DBsqlServer.establecerConexion();
			
		sqlQuery = "SELECT COUNT(*)" 
				+  " FROM JCD_PERSONAL_EQUIPOS"
				+  " WHERE EMPLEADO = " + CtrlEmpleados.empleadoSelecc;
		CachedRowSet rowset =DBsqlServer.ejecutarQuery(sqlQuery,conexion);
		
		rowset.next();
		
		if(!rowset.getString(1).equals("0")) {
			if(JOptionPane.showConfirmDialog(null, "Este empleado pertenece a equipos,desea continuar?",
						                     "AVISO",JOptionPane.YES_NO_OPTION)==0) {
				//Elimino a este usuario de los equipos
				//conexion = DBsqlServer.conectarBD();
				sqlQuery = "DELETE" 
						+  " FROM JCD_PERSONAL_EQUIPOS"
						+  " WHERE EMPLEADO = " + CtrlEmpleados.empleadoSelecc; 
				DBsqlServer.ejecutarQueryUpdate(sqlQuery,conexion);
			}else {
				elimina = false;
				DBsqlServer.cerrarConexion(conexion);
			}
		}
		if(elimina) {
			sqlQuery = "DELETE" 
					+  " FROM JCD_EMPLEADOS"
					+  " WHERE CODIGO_EMPLEADO = " + CtrlEmpleados.empleadoSelecc; 
				
			DBsqlServer.ejecutarQueryUpdate(sqlQuery,conexion);
			DBsqlServer.cerrarConexion(conexion);
		}
	}

}
