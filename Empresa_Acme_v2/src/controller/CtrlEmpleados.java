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
		
		view.FrmEmpleados.tabEmpleados.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
	}
	public static void borrarEmpleado() {
		int fil, col;
		DefaultTableModel modelo;
		fil = view.FrmEmpleados.tabEmpleados.getSelectedRow();
		col = 0;
		empleadoSelecc = String.valueOf(view.FrmEmpleados.tabEmpleados.getValueAt(fil, col));
		logic.LogicEmpleados.borrarEmpleado(empleadoSelecc);
		cargarListaEmpleados();
	}	
	public static void addEmpleado() {
		CtrlEmpleado.state = 0;
		CtrlEmpleado.inicio();
	}
	public static void editarEmpleado() {
		CtrlEmpleado.state = 1;
		CtrlEmpleado.inicio();
	}
	public static void infoEmpleado() {
		CtrlEmpleado.state = 2;
		CtrlEmpleado.inicio();
	}

}