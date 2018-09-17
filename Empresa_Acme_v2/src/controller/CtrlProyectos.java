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

	public static void borrarProyecto() {
		int fil, col;
		DefaultTableModel modelo;
		fil = view.FrmEmpleados.tabEmpleados.getSelectedRow();
		col = 0;
		proyectoSelecc = String.valueOf(view.FrmProyectos.tabProyectos.getValueAt(fil, col));
		logic.LogicEmpleados.borrarEmpleado(proyectoSelecc);
		cargarListaProyectos();
	}
}
