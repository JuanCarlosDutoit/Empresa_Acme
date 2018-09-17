package controller;

import javax.swing.table.DefaultTableModel;

import view.FrmProyecto;

public class CtrlProyecto {

	public static int state;

	public static void inicio() {
		new view.FrmProyecto();
		switch (controller.CtrlProyecto.state) {
		case 0:
			//Nuevo
			view.FrmProyecto.btnOk.setVisible(false);
			view.FrmProyecto.btnEditar.setVisible(false);
			break;
		case 1:
			//Editar
			view.FrmProyecto.btnGuardar.setVisible(false);
			view.FrmProyecto.btnOk.setVisible(false);
			break;
		case 2:
			//Info
			view.FrmProyecto.btnGuardar.setVisible(false);
			view.FrmProyecto.btnEditar.setVisible(false);

			view.FrmProyecto.txtNombre.setEnabled(false);
			view.FrmProyecto.txtPresupuesto.setEnabled(false);
			view.FrmProyecto.txtFechaInicio.setEnabled(false);
			view.FrmProyecto.txtFechaFin.setEnabled(false);
			break;
		}

		switch(controller.CtrlProyecto.state) {
		case 0://Nuevo
			break;
		case 1://Editar
			rellenarDatosProyecto();
			break;
		case 2:
			rellenarDatosProyecto();
			break;
		}

	}
	
	private static void rellenarDatosProyecto() {
		int fila;
		DefaultTableModel modelo;
		System.out.println("Relleno datos del proyecto");
		fila = view.FrmProyectos.tabProyectos.getSelectedRow();
		CtrlProyectos.proyectoSelecc = String.valueOf(view.FrmProyectos.tabProyectos.getValueAt(fila, 0));
		
		System.out.println("asignando.." + String.valueOf(view.FrmProyectos.tabProyectos.getValueAt(fila, 1)));
		view.FrmProyecto.txtNombre.setText(String.valueOf(view.FrmProyectos.tabProyectos.getValueAt(fila, 1)));
		view.FrmProyecto.txtPresupuesto.setText(String.valueOf(view.FrmProyectos.tabProyectos.getValueAt(fila, 2)));
		view.FrmProyecto.txtFechaInicio.setText(String.valueOf(view.FrmProyectos.tabProyectos.getValueAt(fila, 3)));
		view.FrmProyecto.txtFechaFin.setText(String.valueOf(view.FrmProyectos.tabProyectos.getValueAt(fila, 4)));
	}
	public static void addProyecto() {
		// TODO Auto-generated method stub
		
	}
	public static void editarProyecto() {
		// TODO Auto-generated method stub
		
	}		
	

}
