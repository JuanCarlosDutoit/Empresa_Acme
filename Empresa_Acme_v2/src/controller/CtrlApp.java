package controller;

import javax.swing.JOptionPane;

import dataBase.DBsqlServer;
import logic.LogicApp;

public class CtrlApp {
	/**
	 * @author Juan Carlos Duoit
	 * @since 24/09/2018
	 * Este método inicializa la aplicación, lee el fichero de conexión de la base de datos
	 * y lanza el formulario de la aplicación si se ha podido realizar una conexión correcta
	 * a la base de datos.
	 */
	public static void inicio() {
		if (LogicApp.leerFicheroConexion("conexion.txt")) {
			//si la lectura del fichero es correcta realizo un test de conexión
			if(DBsqlServer.testConexion()) {
				//Si la conexión es correcta inicia el programa
				controller.CtrlPrincipal.inicio();
			}else {
				//Si hay un error con el primer fichero de conexión paso al fichero de conexion
				//auxiliar
				if (LogicApp.leerFicheroConexion("conexion_casa.txt")) {
					//si la lectura del fichero es correcta realizo un test de conexión
					if(DBsqlServer.testConexion()) {
						//Si la conexión es correcta inicia el programa
						controller.CtrlPrincipal.inicio();
					}else {
						JOptionPane.showMessageDialog(null, "Error en la conexion a la BD", "Error", 1);
					}
				}
			}	
		}
	}
}