package controller;

import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

@SuppressWarnings("unused")
public class CtrlEmpleados {
	
	public static String empleadoSelecc; 
	
	public static void inicio() {
		new view.FrmEmpleados();
		cargarListaEmpleados();
	}
	public static void cargarListaEmpleados() {
		DefaultTableModel modelo;
		modelo = logic.LogicEmpleados.iniciaListaEmpleados();
		rellenarListaEmpleados(modelo);
	}
	public static void rellenarListaEmpleados(DefaultTableModel modelo) {
		view.FrmEmpleados.tabEmpleados.setModel(modelo);
		view.FrmEmpleados.tabEmpleados.getColumnModel().getColumn(0).setPreferredWidth(0);		
	}
	public static void borrarEmpleado() {
		int fila;
		DefaultTableModel modelo;
		fila = view.FrmEmpleados.tabEmpleados.getSelectedRow();
		empleadoSelecc = String.valueOf(view.FrmEmpleados.tabEmpleados.getValueAt(fila, 0));
		logic.LogicEmpleados.borrarEmpleado(empleadoSelecc);
		cargarListaEmpleados();
	}	
	public static void addEmpleado() {
		CtrlEmpleado.inicio(0);
	}
	public static void editarEmpleado() {
		CtrlEmpleado.inicio(1);
	}
	public static void infoEmpleado() {
		CtrlEmpleado.inicio(2);
	}




}