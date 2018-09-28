package logic;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.rowset.CachedRowSet;
import javax.swing.table.DefaultTableModel;

import controller.CtrlProyectos;
import dataBase.DBsqlServer;
import util.Utilidades;

public class LogicProyectos {

	public static DefaultTableModel iniciaListaProyectos()throws SQLException {
		String sqlQuery;
		DefaultTableModel modelo;
		Connection conexion;
		
		//conexion = DBsqlServer.conectarBD();
		DBsqlServer.crearCadenaConexion();
		conexion = DBsqlServer.establecerConexion();
		
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

	public static void borrarProyecto() throws SQLException {
		String sqlQuery;
		Connection conexion;
		
		//--> Antes de eliminar hay que comprobar que no haya equipos asignados a ese proyecto
		DBsqlServer.crearCadenaConexion();
		conexion = DBsqlServer.establecerConexion();
		sqlQuery = "DELETE" 
				+  " FROM JCD_PROYECTOS"
				+  " WHERE CODIGO_PROYECTO = " + CtrlProyectos.proyectoSelecc; 
		
		DBsqlServer.ejecutarQueryUpdate(sqlQuery,conexion);
		DBsqlServer.cerrarConexion(conexion);
		
	}

}
