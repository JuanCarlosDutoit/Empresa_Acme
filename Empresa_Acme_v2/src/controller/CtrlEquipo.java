package controller;

import java.sql.SQLException;

import model.Equipo;
import util.Utilidades;
import view.FrmEquipo;

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
		case 2://Info
			rellenarDatosEquipo();
			break;
		}

	}
	
	private static void rellenarDatosEquipo() {
		int fila;

		fila = view.FrmEquipos.tabEquipos.getSelectedRow();
		
		view.FrmEquipo.txtNombre.setText(String.valueOf(view.FrmEquipos.tabEquipos.getValueAt(fila, 1)));
		view.FrmEquipo.txtDes.setText(String.valueOf(view.FrmEquipos.tabEquipos.getValueAt(fila, 2)));
	}
	public static void addEquipo() {
		Equipo equipo;
		
		equipo = new Equipo();
		
		equipo.setNombre(FrmEquipo.txtNombre.getText());
		equipo.setDescripcion(FrmEquipo.txtDes.getText());
		
		try {
			logic.LogicEquipo.addEquipo(equipo);
		} catch (SQLException e) {
			Utilidades.gestionaErrorSql(e);
		}
	}
	public static void editarEquipo() {
		Equipo equipo;
		
		equipo = new Equipo();
		
		equipo.setNombre(FrmEquipo.txtNombre.getText());
		equipo.setDescripcion(FrmEquipo.txtDes.getText());
		
		try {
			logic.LogicEquipo.editarEquipo(equipo);
		} catch (SQLException e) {
			Utilidades.gestionaErrorSql(e);
		}
		
	}		
	
}
