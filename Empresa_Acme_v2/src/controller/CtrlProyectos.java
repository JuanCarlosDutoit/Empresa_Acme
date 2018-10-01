package controller;

import java.sql.SQLException;

import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import util.Utilidades;

public class CtrlProyectos {
	
	public static String proyectoSelecc; 
	
	public static void inicio() {
		new view.FrmProyectos();
		cargarListaProyectos();
	}

	public static void cargarListaProyectos() {
		DefaultTableModel modelo;
		try {
			modelo = logic.LogicProyectos.iniciaListaProyectos();
			rellenarListaProyectos(modelo);
			//1 linea como seleccionada
			if(modelo.getRowCount()!=0) {
				view.FrmProyectos.tabProyectos.getSelectionModel().setSelectionInterval(0,0);
			}
		} catch (SQLException e) {
			Utilidades.gestionaErrorSql(e);
		}
	}

	private static void rellenarListaProyectos(DefaultTableModel modelo) {
		view.FrmProyectos.tabProyectos.setModel(modelo);
		//Oculto la columna del codigo
		view.FrmProyectos.tabProyectos.getColumnModel().getColumn(0).setMaxWidth(0);
		view.FrmProyectos.tabProyectos.getColumnModel().getColumn(0).setMinWidth(0);
		view.FrmProyectos.tabProyectos.getTableHeader().getColumnModel().getColumn(0).setMaxWidth(0);
		view.FrmProyectos.tabProyectos.getTableHeader().getColumnModel().getColumn(0).setMinWidth(0);
		//Establezco el tipo de seleccion 
		view.FrmProyectos.tabProyectos.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		ListSelectionModel rowSM = view.FrmProyectos.tabProyectos.getSelectionModel();
		rowSM.addListSelectionListener(new ListSelectionListener(){

		    public void valueChanged(ListSelectionEvent e) {
		    	if (!e.getValueIsAdjusting()) {
		    		//Esto es porque si pincho este listener salta dos veces
		    		int fila;
		    		fila = view.FrmProyectos.tabProyectos.getSelectedRow();
		    		if(fila>=0) {
		    			proyectoSelecc = String.valueOf(view.FrmProyectos.tabProyectos.getValueAt(fila,0));
		    		}
		    	}
		    }
		});
	}
	public static void borrarProyecto() {
		if (!CtrlProyectos.proyectoSelecc.equals("-1")) {
			if(JOptionPane.showConfirmDialog(null, "Va a eliminar este registro, desea continuar?",
					"AVISO",JOptionPane.YES_NO_OPTION)==0) {
				try {
					logic.LogicProyectos.borrarProyecto();
					cargarListaProyectos();
				} catch (SQLException e) {
					Utilidades.gestionaErrorSql(e);
				}
			}
		}else {
			JOptionPane.showMessageDialog(null, "No ha seleccionado ningun proyecto", "Error", 1);
		}
	}
	public static void addProyecto() {
		CtrlProyecto.state = 0;
		CtrlProyecto.inicio();	
	}
	public static void editarProyecto() {
		CtrlProyecto.state = 1;
		CtrlProyecto.inicio();	
	}
	public static void infoProyecto() {
		CtrlProyecto.state = 2;
		CtrlProyecto.inicio();	
	}


}
