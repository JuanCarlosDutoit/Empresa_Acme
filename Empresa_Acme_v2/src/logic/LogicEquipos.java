package logic;

import java.sql.Connection;

import javax.sql.rowset.CachedRowSet;
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
		sqlQuery = "SELECT E.NOMBRE,E.APELLIDOS,C.NOMBRE PUESTO" 
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

}
