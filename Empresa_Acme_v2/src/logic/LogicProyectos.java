package logic;

import java.sql.Connection;

import javax.sql.rowset.CachedRowSet;
import javax.swing.table.DefaultTableModel;

import dataBase.DBsqlServer;
import util.Utilidades;

public class LogicProyectos {

	public static DefaultTableModel iniciaListaProyectos() {
		String sqlQuery;
		DefaultTableModel modelo;
		Connection conexion;
		
		conexion = DBsqlServer.conectarBD();
		sqlQuery = "SELECT CODIGO_PROYECTO,"
				        + "NOMBRE,"
				        + "PRESUPUESTO,"
				        + "FECHA_INICIO,"
				        + "FECHA_FIN"
				+  " FROM JCD_PROYECTOS";
		CachedRowSet rowset = DBsqlServer.ejecutarQuery(sqlQuery,conexion);
		modelo = Utilidades.creaModeloTablas(rowset);
		DBsqlServer.cerrarConexion(conexion);
		
		return modelo;
	}

	public static void borrarProyecto(String proyectoSelecc) {
		String sqlQuery;
		DefaultTableModel modelo;
		Connection conexion;
		
		conexion = DBsqlServer.conectarBD();
		sqlQuery = "DELETE" 
				+  " FROM JCD_PROYECTOS"
				+  " WHERE CODIGO_PROYECTO = " + proyectoSelecc; 
		
		DBsqlServer.ejecutarQueryUpdate(sqlQuery,conexion);
		DBsqlServer.cerrarConexion(conexion);
		
	}

}
