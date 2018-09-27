package controller;

public class CtrlPrincipal {

	public static void getionTrabajadores() {
		CtrlEmpleados.inicio();
	}

	public static void getionProyectos() {
		CtrlProyectos.inicio();
	}

	public static void inicio() {
		new view.FrmPrincipal();
	}

	public static void gestionEquipos() {
		CtrlEquipos.inicio();
	}

}