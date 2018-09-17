package controller;

import javax.swing.table.DefaultTableModel;

public class CtrlProyectos {
	public static String proyectoSelecc; 
	
	public static void inicio() {
		new view.FrmProyectos();
		cargarListaProyectos();
	}

	private static void cargarListaProyectos() {
		DefaultTableModel modelo;
		modelo = logic.LogicProyectos.iniciaListaProyectos();
		rellenarListaProyectos(modelo);
	}

	private static void rellenarListaProyectos(DefaultTableModel modelo) {
		view.FrmProyectos.tabProyectos.setModel(modelo);
		view.FrmProyectos.tabProyectos.getColumnModel().getColumn(0).setPreferredWidth(0);
	}
}
