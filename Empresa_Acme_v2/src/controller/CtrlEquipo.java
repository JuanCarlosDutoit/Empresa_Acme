package controller;

import javax.swing.table.DefaultTableModel;

import view.FrmEmpleado;
import view.FrmEquipo;
import view.FrmProyecto;

public class CtrlEquipo {

	public static int state;

	public static void inicio() {
		new view.FrmEquipo();
		switch (controller.CtrlEquipo.state) {
		case 0:
			//Nuevo
			view.FrmEquipo.btnOk.setVisible(false);
			view.FrmEquipo.btnEditar.setVisible(false);
			break;
		case 1:
			//Editar
			view.FrmEquipo.btnGuardar.setVisible(false);
			view.FrmEquipo.btnOk.setVisible(false);
			break;
		case 2:
			//Info
			view.FrmEquipo.btnGuardar.setVisible(false);
			view.FrmEquipo.btnEditar.setVisible(false);

			view.FrmEquipo.txtNombre.setEnabled(false);
			view.FrmEquipo.txtDes.setEnabled(false);
			break;
		}

		switch(controller.CtrlEquipo.state) {
		case 0://Nuevo
			break;
		case 1://Editar
			rellenarDatosEquipo();
			break;
		case 2:
			rellenarDatosEquipo();
			break;
		}

	}
	
	private static void rellenarDatosEquipo() {
		int fila;
		DefaultTableModel modelo;
		System.out.println("Relleno datos del proyecto");
		fila = view.FrmEquipos.tabEquipos.getSelectedRow();
		
		view.FrmEquipo.txtNombre.setText(String.valueOf(view.FrmEquipos.tabEquipos.getValueAt(fila, 1)));
		view.FrmEquipo.txtDes.setText(String.valueOf(view.FrmEquipos.tabEquipos.getValueAt(fila, 2)));
	}
	public static void addEquipo() {
		int fila;
		String nombre,descripcion;
		
		fila = view.FrmEquipos.tabEquipos.getSelectedRow();
		CtrlEquipos.equipoSelecc = String.valueOf(view.FrmEquipos.tabEquipos.getValueAt(fila, 0));
		
		nombre = FrmEquipo.txtNombre.getText();
		descripcion = FrmEquipo.txtDes.getText();
		
		logic.LogicEquipo.addEmpleado(nombre,descripcion);
		
	}
	public static void editarEquipo() {
		int fila;
		String nombre,descripcion;
		
		fila = view.FrmEquipos.tabEquipos.getSelectedRow();
		CtrlEquipos.equipoSelecc = String.valueOf(view.FrmEquipos.tabEquipos.getValueAt(fila, 0));
		
		nombre = FrmEquipo.txtNombre.getText();
		descripcion = FrmEquipo.txtDes.getText();
		
		logic.LogicEquipo.editarEquipo(nombre,descripcion);
		
	}		
	

}
