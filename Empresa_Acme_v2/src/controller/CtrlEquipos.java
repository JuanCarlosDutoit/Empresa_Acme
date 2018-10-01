package controller;

import java.sql.SQLException;

import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import util.Utilidades;

public class CtrlEquipos {

	public static String equipoSelecc;
	public static String personalSelecc; 
	
	public static void inicio() {
		equipoSelecc = "-1";
		personalSelecc = "-1"; 
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
			if(modelo.getRowCount()!=0) {
				view.FrmEquipos.tabEquipos.getSelectionModel().setSelectionInterval(0,0);
			}
		} catch (SQLException e) {
			Utilidades.gestionaErrorSql(e);
		}
	}
	public static void rellenarListaEquipos(DefaultTableModel modelo) {
		view.FrmEquipos.tabEquipos.setModel(modelo);
		//Oculto la columna del codigo
		view.FrmEquipos.tabEquipos.getColumnModel().getColumn(0).setMaxWidth(0);
		view.FrmEquipos.tabEquipos.getColumnModel().getColumn(0).setMinWidth(0);
		view.FrmEquipos.tabEquipos.getTableHeader().getColumnModel().getColumn(0).setMaxWidth(0);
		view.FrmEquipos.tabEquipos.getTableHeader().getColumnModel().getColumn(0).setMinWidth(0);
		//Establezco el tipo de seleccion 
		view.FrmEquipos.tabEquipos.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		//Añadimos Listener de cambio de valores
		ListSelectionModel rowSM = view.FrmEquipos.tabEquipos.getSelectionModel();
		rowSM.addListSelectionListener(new ListSelectionListener(){

		    public void valueChanged(ListSelectionEvent e) {
		    	if (!e.getValueIsAdjusting()) {
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
	public static void cargarListaPersonal() {
		DefaultTableModel modelo;
		try {
			modelo = logic.LogicEquipos.iniciaListaPersonal();
			rellenarListaPersonal(modelo);
		} catch (SQLException e) {
			Utilidades.gestionaErrorSql(e);
		}
	}
	private static void rellenarListaPersonal(DefaultTableModel modelo) {
		view.FrmEquipos.tabPersonal.setModel(modelo);
		//Oculto la columna del codigo
		view.FrmEquipos.tabPersonal.getColumnModel().getColumn(0).setMaxWidth(0);
		view.FrmEquipos.tabPersonal.getColumnModel().getColumn(0).setMinWidth(0);
		view.FrmEquipos.tabPersonal.getTableHeader().getColumnModel().getColumn(0).setMaxWidth(0);
		view.FrmEquipos.tabPersonal.getTableHeader().getColumnModel().getColumn(0).setMinWidth(0);
		//Establezco el tipo de seleccion 
		view.FrmEquipos.tabPersonal.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		//Añadimos Listener de cambio de valores
		ListSelectionModel rowSM = view.FrmEquipos.tabPersonal.getSelectionModel();
		rowSM.addListSelectionListener(new ListSelectionListener(){

		    public void valueChanged(ListSelectionEvent e) {
		    	if (!e.getValueIsAdjusting()) {
		    		//Esto es porque si pincho este listener salta dos veces
		    		int fila;
		    		fila = view.FrmEquipos.tabPersonal.getSelectedRow();
		    		if(fila>=0) {
		    			personalSelecc = String.valueOf(view.FrmEquipos.tabPersonal.getValueAt(fila,0));
		    		}
		    	}
		    }
		});
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
	public static void borrarEquipo() {
		if (!CtrlEquipos.equipoSelecc.equals("-1")) {
			if(JOptionPane.showConfirmDialog(null, "Va a eliminar este registro, desea continuar?",
					"AVISO",JOptionPane.YES_NO_OPTION)==0) {
				try {
					logic.LogicEquipos.borrarEquipos();
					cargarListaEquipos();
				} catch (SQLException e) {
					Utilidades.gestionaErrorSql(e);
				}
			}
		}else {
			JOptionPane.showMessageDialog(null, "No ha seleccionado ningun equipo", "Error", 1);
		}
	}
	public static void addPersonalEquipo() {
		CtrlPersonal.inicio();
	}
	public static void borrarPersonalEquipo() {
		if (!CtrlEquipos.personalSelecc.equals("-1")) {
			if(JOptionPane.showConfirmDialog(null, "Va a eliminar este registro, desea continuar?",
					"AVISO",JOptionPane.YES_NO_OPTION)==0) {
				try {
					logic.LogicEquipos.borrarPersonalEquipos();
					cargarListaPersonal();
				} catch (SQLException e) {
					Utilidades.gestionaErrorSql(e);
				}
			}
		}else {
			JOptionPane.showMessageDialog(null, "No ha seleccionado ningun miembro del equipo", "Error", 1);
		}
	}
}
