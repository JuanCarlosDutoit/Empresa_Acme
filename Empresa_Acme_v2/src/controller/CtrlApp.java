package controller;

import javax.swing.JOptionPane;

import dataBase.DBsqlServer;
import logic.LogicApp;

public class CtrlApp {
	/**
	 * @author Juan Carlos Duoit
	 * @since 24/09/2018
	 * Este m�todo inicializa la aplicaci�n, lee el fichero de conexi�n de la base de datos
	 * y lanza el formulario de la aplicaci�n si se ha podido realizar una conexi�n correcta
	 * a la base de datos.
	 */
	public static void inicio() {
		if (LogicApp.leerFicheroConexion("conexion.txt")) {
			//si la lectura del fichero es correcta realizo un test de conexi�n
			if(DBsqlServer.testConexion()) {
				//Si la conexi�n es correcta inicia el programa
				controller.CtrlPrincipal.inicio();
			}else {
				//Si hay un error con el primer fichero de conexi�n paso al fichero de conexion
				//auxiliar
				if (LogicApp.leerFicheroConexion("conexion_casa.txt")) {
					//si la lectura del fichero es correcta realizo un test de conexi�n
					if(DBsqlServer.testConexion()) {
						//Si la conexi�n es correcta inicia el programa
						controller.CtrlPrincipal.inicio();
					}else {
						JOptionPane.showMessageDialog(null, "Error en la conexion a la BD", "Error", 1);
					}
				}
			}	
		}
	}
}