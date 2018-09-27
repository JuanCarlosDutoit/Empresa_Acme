package controller;

import java.sql.SQLException;

import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

public class CtrlEquipos {

	public static String equipoSelecc;
	public static String personalSelecc; 
	
	public static void inicio() {
		new view.FrmEquipos();
		cargarListaEquipos();
	}

	public static void cargarListaEquipos() {
		DefaultTableModel modelo;
		try {
			modelo = logic.LogicEquipos.iniciaListaEquipos();
			rellenarListaEquipos(modelo);
			//Para que se inice en la 1 fila porque la 1 vez no carga el evento del ValueChanged del listener 
			//de la tabla
			view.FrmEquipos.tabEquipos.getSelectionModel().setSelectionInterval(0,1);
			view.FrmEquipos.tabEquipos.getSelectionModel().setSelectionInterval(0,0);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static void cargarListaPersonal() {
		DefaultTableModel modelo;
		try {
			modelo = logic.LogicEquipos.iniciaListaPersonal();
			rellenarListaPersonal(modelo);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
		view.FrmEquipos.tabEquipos.getSelectionModel().setSelectionInterval(0,0);
		
		ListSelectionModel rowSM = view.FrmEquipos.tabEquipos.getSelectionModel();
		rowSM.addListSelectionListener(new ListSelectionListener(){

		    public void valueChanged(ListSelectionEvent e) {
		    	if (!e.getValueIsAdjusting()) {
		    		System.out.println("Adelante");
		    		int fila;
		    		fila = view.FrmEquipos.tabEquipos.getSelectedRow();
		    		System.out.println(fila);
		    		if(fila>=0) {
		    			equipoSelecc = String.valueOf(view.FrmEquipos.tabEquipos.getValueAt(fila,0));
		    			cargarListaPersonal();
		    		}
		    	}
		    }
		});
	}
	public static void borrarEquipo() {
		int fil;
		int col;
		
		fil = view.FrmEquipos.tabEquipos.getSelectedRow();
		col = 0;
		equipoSelecc = String.valueOf(view.FrmEquipos.tabEquipos.getValueAt(fil, col));
		logic.LogicEquipos.borrarEquipos(equipoSelecc);
		cargarListaEquipos();
	}
	public static void addEquipo () {
		CtrlEquipo.state = 0;
		CtrlEquipo.inicio();	
	}
	public static void editarEquipo() {
		CtrlEquipo.state = 1;
		CtrlEquipo.inicio();	
	}
	public static void infoEquipo() {
		CtrlEquipo.state = 2;
		CtrlEquipo.inicio();
	}
	public static void borrarPersonalEquipo() {
		int fil, col;
		fil = view.FrmEquipos.tabPersonal.getSelectedRow();
		col = 0;
		personalSelecc = String.valueOf(view.FrmEquipos.tabPersonal.getValueAt(fil, col));
		try {
			logic.LogicEquipos.borrarPersonalEquipos(personalSelecc);
			cargarListaEquipos();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static void addPersonalEquipo() {
		CtrlPersonal.inicio();
	}

}
