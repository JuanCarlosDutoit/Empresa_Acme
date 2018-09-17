package controller;

import javax.swing.JOptionPane;

import dataBase.DBsqlServer;
import logic.LogicApp;
import util.Utilidades;

public class CtrlApp {

	public static void inicio() {
		//leer archivo de conf conex.
		if (LogicApp.leerFicheroConexion("conexion.txt")) {
			//Lanzamiento de la aplicacion
			if(DBsqlServer.testConexion()) {
				controller.CtrlPrincipal.inicio();
			}else {
				if (LogicApp.leerFicheroConexion("conexion_casa.txt")) {
					if(DBsqlServer.testConexion()) {
						controller.CtrlPrincipal.inicio();
					}else {
						JOptionPane.showMessageDialog(null, "Error en la conexion a la BD", "Error", 1);
					}
				}
			}	
		}
	}
}