package controller;

import javax.swing.table.DefaultTableModel;

public class CtrlEquipos {

	public static String equipoSelecc; 
	
	public static void inicio() {
		new view.FrmEquipos();
		cargarListaEquipos();
	}

	public static void cargarListaEquipos() {
		DefaultTableModel modelo;
		modelo = logic.LogicEquipos.iniciaListaEquipos();
		rellenarListaEquipos(modelo);
	}
	public static void rellenarListaEquipos(DefaultTableModel modelo) {
		view.FrmEquipos.tabEquipos.setModel(modelo);
		view.FrmEquipos.tabEquipos.getColumnModel().getColumn(0).setPreferredWidth(0);		
	}

	public static void addEquipos() {
		// TODO Auto-generated method stub
		
	}

	public static void borrarEquipos() {
		// TODO Auto-generated method stub
		
	}

	public static void editarEquipos() {
		// TODO Auto-generated method stub
		
	}

}
