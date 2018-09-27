package controller;

import java.sql.SQLException;

import javax.swing.table.DefaultTableModel;

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
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
		int fil;
		int col;
		fil = view.FrmProyectos.tabProyectos.getSelectedRow();
		col = 0;
		proyectoSelecc = String.valueOf(view.FrmProyectos.tabProyectos.getValueAt(fil, col));
		try {
			logic.LogicProyectos.borrarProyecto(proyectoSelecc);
			cargarListaProyectos();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
