package controller;

import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import view.FrmEmpleados;

@SuppressWarnings("unused")
public class CtrlEmpleados {
	
	public static String empleadoSelecc; 
	
	public static void inicio() {
		new view.FrmEmpleados();
	}
	public static void borrarEmpleado() {
		int fila;
		DefaultTableModel modelo;
		fila = FrmEmpleados.tabEmpleados.getSelectedRow();
		empleadoSelecc = String.valueOf(FrmEmpleados.tabEmpleados.getValueAt(fila, 0));
		logic.LogicEmpleados.borrarEmpleado(empleadoSelecc);
		iniciaListaEmpleados();
	}
		
	public static void add() { 

	}
	public static void editar() {

	}
	public static void rellenarListaEmpleados(DefaultTableModel modelo) {
		
		FrmEmpleados.tabEmpleados.setModel(modelo);
		FrmEmpleados.tabEmpleados.getColumnModel().getColumn(0).setPreferredWidth(0);		
	}
	public static void iniciaListaEmpleados() {
		DefaultTableModel modelo;
		modelo = logic.LogicEmpleados.iniciaListaEmpleados();
		rellenarListaEmpleados(modelo);
	}

}
