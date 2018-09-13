package logic;

import dataBase.DBsqlServer;
import util.Utilidades;

public class LogicApp {

	public static boolean leerFicheroConexion() {
		boolean resp;
		resp = Utilidades.leerFicheroConexion();
		if(resp) {
			DBsqlServer.asignarDatosConexion();
			return resp;
		}else {
			return false;
		}
			
		
	}
}
