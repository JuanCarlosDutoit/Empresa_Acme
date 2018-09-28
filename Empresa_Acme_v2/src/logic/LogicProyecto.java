package logic;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.rowset.CachedRowSet;

import controller.CtrlProyectos;
import dataBase.DBsqlServer;
import model.Proyecto;

public class LogicProyecto {

	public static void addProyecto(Proyecto project) throws SQLException {
		
		String sqlQuery;
		Connection conexion;
		CachedRowSet rowset;
		int n_codigo;
		
		conexion = DBsqlServer.establecerConexion();
			
		sqlQuery = "SELECT MAX(CODIGO_PROYECTO) "
				+  " FROM JCD_PROYECTOS";
			
		rowset = DBsqlServer.ejecutarQuery(sqlQuery,conexion);
		rowset.next();
		n_codigo= Integer.parseInt(rowset.getString(1));
		n_codigo++;
						
			
		sqlQuery = "INSERT INTO JCD_PROYECTOS VALUES"
				+  "(" + String.valueOf(n_codigo) + ","
				+  "'" + project.getNombre() + "',"
				+  project.getPresupuesto() + ","
				+  "'" + project.getFecInicio() + "',"
				+  "'" + project.getFecFin() + "')";
		DBsqlServer.ejecutarQueryUpdate(sqlQuery,conexion);
		DBsqlServer.cerrarConexion(conexion);
			
		CtrlProyectos.cargarListaProyectos();
				
	}

	public static void editarProyecto(Proyecto project) throws SQLException {
		String sqlQuery;
		Connection conexion;
		
		//Tendriamos que comprobar que los datos enviados estan correctos
		conexion = DBsqlServer.establecerConexion();

		sqlQuery = "UPDATE JCD_PROYECTOS SET "
				+  "NOMBRE = '"+ project.getNombre() + "',"
				+  "PRESUPUESTO = "+ project.getPresupuesto() + ","
				+  "FECHA_INICIO = '"+ project.getFecInicio() + "',"
				+  "FECHA_FIN = '"+ project.getFecFin() + "'" 
				+ " WHERE CODIGO_PROYECTO = " + CtrlProyectos.proyectoSelecc;
			
		DBsqlServer.ejecutarQueryUpdate(sqlQuery,conexion);
		DBsqlServer.cerrarConexion(conexion);
			
		CtrlProyectos.cargarListaProyectos();

	}

}
