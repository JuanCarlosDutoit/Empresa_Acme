package logic;

import java.sql.Connection;

import javax.sql.rowset.CachedRowSet;
import javax.swing.table.DefaultTableModel;

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

}
