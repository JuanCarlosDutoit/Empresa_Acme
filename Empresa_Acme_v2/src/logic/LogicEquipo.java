package logic;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.rowset.CachedRowSet;

import controller.CtrlEquipos;
import dataBase.DBsqlServer;
import model.Equipo;

public class LogicEquipo {

	public static void addEquipo(Equipo equipo) throws SQLException {
		String sqlQuery;
		Connection conexion;
		CachedRowSet rowset;
		int n_codigo;
			
		conexion = DBsqlServer.establecerConexion();
			
		//Compruebo que hay datos en las tablas
		sqlQuery = "SELECT COUNT(CODIGO_EQUIPO) "
				+  " FROM JCD_EQUIPOS";
		rowset = DBsqlServer.ejecutarQuery(sqlQuery,conexion);
		rowset.next();
		if(!rowset.getString(1).equals("0")) {
		
			sqlQuery = "SELECT MAX(CODIGO_EQUIPO) "
					+  " FROM JCD_EQUIPOS";
			
			rowset = DBsqlServer.ejecutarQuery(sqlQuery,conexion);
			rowset.next();
			n_codigo= Integer.parseInt(rowset.getString(1));
			n_codigo++;
			
		}else {
			n_codigo= 1;
		}
		
		sqlQuery = "INSERT INTO JCD_EQUIPOS VALUES"
				+  "(" + String.valueOf(n_codigo) + ","
				+  "'" + equipo.getNombre() + "',"
				+  "'" +equipo.getDescripcion() + "')";
		DBsqlServer.ejecutarQueryUpdate(sqlQuery,conexion);
		DBsqlServer.cerrarConexion(conexion);
			
		CtrlEquipos.cargarListaEquipos();
			
	}

	public static void editarEquipo(Equipo equipo) throws SQLException {
		String sqlQuery;
		Connection conexion;
		
		//Tendriamos que comprobar que los datos enviados estan correctos
		
		conexion = DBsqlServer.establecerConexion();
	
		sqlQuery = "UPDATE JCD_EQUIPOS SET "
				+  "NOMBRE = '"+ equipo.getNombre() + "',"
				+  "DESCRIPCION = '"+ equipo.getDescripcion() + "'"
				+ " WHERE CODIGO_EQUIPO = " + CtrlEquipos.equipoSelecc;
	
		DBsqlServer.ejecutarQueryUpdate(sqlQuery,conexion);
		DBsqlServer.cerrarConexion(conexion);
			
		CtrlEquipos.cargarListaEquipos();	
	}

}
