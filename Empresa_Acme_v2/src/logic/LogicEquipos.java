package logic;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.rowset.CachedRowSet;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import controller.CtrlEquipos;
import dataBase.DBsqlServer;
import util.Utilidades;

public class LogicEquipos {

	public static DefaultTableModel iniciaListaEquipos() throws SQLException {
		String sqlQuery;
		DefaultTableModel modelo;
		Connection conexion;
		
		//conexion = DBsqlServer.conectarBD();
		
		DBsqlServer.crearCadenaConexion();
		conexion = DBsqlServer.establecerConexion();
		
		sqlQuery = "SELECT CODIGO_EQUIPO,"
				        + "NOMBRE,"
				        + "DESCRIPCION"
				+  " FROM JCD_EQUIPOS";
		CachedRowSet rowset = DBsqlServer.ejecutarQuery(sqlQuery,conexion);
		modelo = Utilidades.creaModeloTablas(rowset);
		DBsqlServer.cerrarConexion(conexion);
		return modelo;
	}

	public static DefaultTableModel iniciaListaPersonal()throws SQLException {
		String sqlQuery;
		DefaultTableModel modelo;
		Connection conexion;
		
		//conexion = DBsqlServer.conectarBD();
		DBsqlServer.crearCadenaConexion();
		conexion = DBsqlServer.establecerConexion();
		
		sqlQuery = "SELECT PEQ.CODIGO_PERSONAL,E.NOMBRE,E.APELLIDOS,C.NOMBRE PUESTO" 
				+  " FROM JCD_EMPLEADOS E INNER JOIN JCD_PERSONAL_EQUIPOS PEQ"
			    +  "  ON E.Codigo_Empleado = PEQ.Empleado"
				+  " INNER JOIN JCD_Equipos EQ"
				+  "  ON PEQ.Equipo = EQ.Codigo_Equipo"
				+  " INNER JOIN JCD_CARGOS C"
				+  " ON C.Codigo_Cargo = PEQ.Puesto"
			    +  " WHERE PEQ.Equipo =" + CtrlEquipos.equipoSelecc;
		CachedRowSet rowset = DBsqlServer.ejecutarQuery(sqlQuery,conexion);
		modelo = Utilidades.creaModeloTablas(rowset);
		DBsqlServer.cerrarConexion(conexion);
		return modelo;
	}

	public static void borrarEquipos() throws SQLException {
		String sqlQuery;
		boolean elimina = false;
		Connection conexion;

		conexion = DBsqlServer.establecerConexion();
		
		sqlQuery = "SELECT COUNT(EMPLEADO)"
				+  " FROM JCD_PERSONAL_EQUIPOS"
				+  " WHERE EQUIPO = " + CtrlEquipos.equipoSelecc; 
		CachedRowSet rowset = DBsqlServer.ejecutarQuery(sqlQuery,conexion);
		
		rowset.next();
	
		if(rowset.getString(1).equals("0")) {
			elimina = true;
		}else {
			if(JOptionPane.showConfirmDialog(null, "Hay Personal asignado, desea continuar?",
					                               "AVISO",JOptionPane.YES_NO_OPTION)==0) {
					
				sqlQuery = "DELETE" 
						+  " FROM JCD_PERSONAL_EQUIPOS"
						+  " WHERE EQUIPO = " + CtrlEquipos.equipoSelecc; 
					
				DBsqlServer.ejecutarQueryUpdate(sqlQuery,conexion);
				elimina = true;
			}else{
				elimina = false;
				DBsqlServer.cerrarConexion(conexion);
			};
		}
		if(elimina) {
			System.out.println("Eliminamos equipo");

			conexion = DBsqlServer.establecerConexion();
				
			sqlQuery = "DELETE" 
					+  " FROM JCD_EQUIPOS"
					+  " WHERE CODIGO_EQUIPO = " + CtrlEquipos.equipoSelecc; 
				
			DBsqlServer.ejecutarQueryUpdate(sqlQuery,conexion);
			DBsqlServer.cerrarConexion(conexion);
		}
	}

	public static void borrarPersonalEquipos()throws SQLException {
		String sqlQuery;
		Connection conexion;

		conexion = DBsqlServer.establecerConexion();
		
		sqlQuery = "DELETE" 
				+  " FROM JCD_PERSONAL_EQUIPOS"
				+  " WHERE CODIGO_PERSONAL = " + CtrlEquipos.personalSelecc; 
			
		DBsqlServer.ejecutarQueryUpdate(sqlQuery,conexion);
		DBsqlServer.cerrarConexion(conexion);	
		
	}

}
