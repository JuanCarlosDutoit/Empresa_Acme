package start;

import java.io.IOException;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import dataBase.DBsqlServer;
import util.Utilidades;

public class LunchtApp {

	public static void main(String[] args) {
		// Inicio de la aplicacion
		LunchtApp app = new LunchtApp();
		app.start();
	}

	private void start() {
		try {
			boolean puede = false;
			if (Utilidades.leerFicheroConexion("conexion.txt")) {
				DBsqlServer.asignarDatosConexion();
				puede = DBsqlServer.testConexion();
			}
			if (!puede) {
				if (Utilidades.leerFicheroConexion("conexion_casa.txt")) {
					DBsqlServer.asignarDatosConexion();
					puede = DBsqlServer.testConexion();
				}
			}
			if (puede) {
				controller.CtrlPrincipal.inicio();
			}/* else {
				JOptionPane.showMessageDialog(null, "Error en la conexion a la BD", "Error", 1);
//			}*/
/*
			if (LogicApp.leerFicheroConexion("conexion.txt")) {
				// si la lectura del fichero es correcta realizo un test de conexi�n
				if (DBsqlServer.testConexion()) {
					// Si la conexi�n es correcta inicia el programa
					controller.CtrlPrincipal.inicio();
				} else {
					// Si hay un error con el primer fichero de conexi�n paso al fichero de conexion
					// auxiliar
					if (LogicApp.leerFicheroConexion("conexion_casa.txt")) {
						// si la lectura del fichero es correcta realizo un test de conexi�n
						if (DBsqlServer.testConexion()) {
							// Si la conexi�n es correcta inicia el programa
							controller.CtrlPrincipal.inicio();
						} else {
							JOptionPane.showMessageDialog(null, "Error en la conexion a la BD", "Error", 1);
						}
					}
				}
			}
*/
			
		} catch (SQLException e) {
			Utilidades.gestionaErrorSql(e);
			//e.printStackTrace();
			//System.out.println(e.getErrorCode());
			//JOptionPane.showMessageDialog(null, "Error en la conexion a la BD\n"
			//		                            + "No se pudo realizar la conexi�n TCP/IP al host",
			//		                            "Error", 1);
		} catch (Exception e) {
			// Utilidades.gestionaErrores();
			e.printStackTrace();
		}
	}

}