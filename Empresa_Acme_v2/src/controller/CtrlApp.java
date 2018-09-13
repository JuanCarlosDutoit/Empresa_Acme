package controller;

import logic.LogicApp;
import util.Utilidades;

public class CtrlApp {

	public static void start() {
		//leer archivo de conf conex.
		if (LogicApp.leerFicheroConexion("conexion_casa.txt")) {
			//Lanzamiento de la aplicacion
			new view.FrmPrincipal();
		}
	}
}