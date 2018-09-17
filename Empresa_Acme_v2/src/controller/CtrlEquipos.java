package controller;

import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
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
	public static void cargarListaPersonal() {
		DefaultTableModel modelo;
		modelo = logic.LogicEquipos.iniciaListaPersonal();
		rellenarListaPersonal(modelo);
	}
	private static void rellenarListaPersonal(DefaultTableModel modelo) {
		view.FrmEquipos.tabPersonal.setModel(modelo);
		view.FrmEquipos.tabPersonal.getColumnModel().getColumn(0).setPreferredWidth(0);
		
		view.FrmEquipos.tabPersonal.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
	}

	public static void rellenarListaEquipos(DefaultTableModel modelo) {
		view.FrmEquipos.tabEquipos.setModel(modelo);
		view.FrmEquipos.tabEquipos.getColumnModel().getColumn(0).setPreferredWidth(0);
		
		view.FrmEquipos.tabEquipos.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		ListSelectionModel rowSM = view.FrmEquipos.tabEquipos.getSelectionModel();
		rowSM.addListSelectionListener(new ListSelectionListener(){

		    public void valueChanged(ListSelectionEvent e) {
		    	System.out.println(e.toString());
		    	equipoSelecc =String.valueOf(view.FrmEquipos.tabEquipos.getSelectedRow()+1);
		    	cargarListaPersonal();
		    }
		});
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
