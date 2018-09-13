package logic;

import dataBase.DBsqlServer;
import util.Utilidades;

public class LogicApp {

	public static boolean leerFicheroConexion(String nfichero) {
		boolean resp;
		resp = Utilidades.leerFicheroConexion(nfichero);
		if(resp) {
			DBsqlServer.asignarDatosConexion();
			return resp;
		}else {
			return false;
		}
	}
}
