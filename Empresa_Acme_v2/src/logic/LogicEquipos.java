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

	public static DefaultTableModel iniciaListaEquipos() {
		String sqlQuery;
		DefaultTableModel modelo;
		Connection conexion;
		
		conexion = DBsqlServer.conectarBD();
		sqlQuery = "SELECT CODIGO_EQUIPO,"
				        + "NOMBRE,"
				        + "DESCRIPCION"
				+  " FROM JCD_EQUIPOS";
		CachedRowSet rowset = DBsqlServer.ejecutarQuery(sqlQuery,conexion);
		modelo = Utilidades.creaModeloTablas(rowset);
		DBsqlServer.cerrarConexion(conexion);
		return modelo;
	}

	public static DefaultTableModel iniciaListaPersonal() {
		String sqlQuery;
		DefaultTableModel modelo;
		Connection conexion;
		
		conexion = DBsqlServer.conectarBD();
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

	public static void borrarEquipos(String equipoSelecc) {
		String sqlQuery;
		boolean elimina = false;
		DefaultTableModel modelo;
		Connection conexion;

		try {
			conexion = DBsqlServer.conectarBD();
		
			sqlQuery = "SELECT COUNT(EMPLEADO)"
					+  " FROM JCD_PERSONAL_EQUIPOS"
					+  " WHERE EQUIPO = " + equipoSelecc; 
			CachedRowSet rowset = DBsqlServer.ejecutarQuery(sqlQuery,conexion);
			DBsqlServer.cerrarConexion(conexion);
			rowset.next();
			System.out.println(rowset.getString(1));
			if(rowset.getString(1).equals("0")) {
				elimina = true;
				System.out.println("No hay curritos elimino");
			}else {
				if(JOptionPane.showConfirmDialog(null, "Hay Personal asignado, desea continuar?","AVISO",JOptionPane.YES_NO_OPTION)==0) {
					//Elimina el personal relacionado
					System.out.println("Eliminamos el personal tb");
					conexion = DBsqlServer.conectarBD();
					sqlQuery = "DELETE" 
							+  " FROM JCD_PERSONAL_EQUIPOS"
							+  " WHERE EQUIPO = " + equipoSelecc; 
					
					DBsqlServer.ejecutarQueryUpdate(sqlQuery,conexion);
					DBsqlServer.cerrarConexion(conexion);
					elimina = true;
				}else{
					elimina = false;
					System.out.println("No hacemos nada");
				};
			}
			if(elimina) {
				System.out.println("Eliminamos equipo");
				conexion = DBsqlServer.conectarBD();
				sqlQuery = "DELETE" 
						+  " FROM JCD_EQUIPOS"
						+  " WHERE CODIGO_EQUIPO = " + equipoSelecc; 
				
				DBsqlServer.ejecutarQueryUpdate(sqlQuery,conexion);
				DBsqlServer.cerrarConexion(conexion);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void borrarPersonalEquipos(String personalSelecc) {
		String sqlQuery;
		boolean elimina = false;
		DefaultTableModel modelo;
		Connection conexion;

		
		System.out.println("Eliminamos personal");
		conexion = DBsqlServer.conectarBD();
		sqlQuery = "DELETE" 
				+  " FROM JCD_PERSONAL_EQUIPOS"
				+  " WHERE CODIGO_PERSONAL = " + personalSelecc; 
			
		DBsqlServer.ejecutarQueryUpdate(sqlQuery,conexion);
		DBsqlServer.cerrarConexion(conexion);	
		
	}

}
