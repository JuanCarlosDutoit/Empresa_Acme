package controller;

import java.sql.SQLException;

import javax.sql.rowset.CachedRowSet;
import javax.swing.table.DefaultTableModel;

import view.FrmEmpleado;

public class CtrlEmpleado {
	
	public static int state;
	
	public static void inicio() {
		new view.FrmEmpleado();
		
		switch (controller.CtrlEmpleado.state) {
		case 0:
			//Nuevo
			FrmEmpleado.btnOk.setVisible(false);
			FrmEmpleado.btnEditar.setVisible(false);
			break;
		case 1:
			//Editar
			FrmEmpleado.btnGuardar.setVisible(false);
			FrmEmpleado.btnOk.setVisible(false);
			break;
		case 2:
			//Info
			FrmEmpleado.btnGuardar.setVisible(false);
			FrmEmpleado.btnEditar.setVisible(false);

			FrmEmpleado.txtNombre.setEnabled(false);
			FrmEmpleado.txtApellidos.setEnabled(false);
			FrmEmpleado.txtDni.setEnabled(false);
			FrmEmpleado.rdbtnHombre.setEnabled(false);
			FrmEmpleado.rdbtnMujer.setEnabled(false);
			FrmEmpleado.cbCargos.setEnabled(false);
			
			break;
		}
		switch(state) {
		case 0://Nuevo
			rellenarComboCargo();
			break;
		case 1://Editar
			rellenarComboCargo();
			rellenarDatosEmpleado();
			break;
		case 2:
			rellenarComboCargo();
			rellenarDatosEmpleado();
			break;
		}
	}
	private static void rellenarDatosEmpleado() {
		int fila;
		DefaultTableModel modelo;
		System.out.println("Relleno datos del usuario");
		fila = view.FrmEmpleados.tabEmpleados.getSelectedRow();
		CtrlEmpleados.empleadoSelecc = String.valueOf(view.FrmEmpleados.tabEmpleados.getValueAt(fila, 0));
		System.out.println(CtrlEmpleados.empleadoSelecc );
		FrmEmpleado.txtNombre.setText(String.valueOf(view.FrmEmpleados.tabEmpleados.getValueAt(fila, 1)));
		FrmEmpleado.txtApellidos.setText(String.valueOf(view.FrmEmpleados.tabEmpleados.getValueAt(fila, 2)));
		FrmEmpleado.txtDni.setText(String.valueOf(view.FrmEmpleados.tabEmpleados.getValueAt(fila, 3)));
		if(String.valueOf(view.FrmEmpleados.tabEmpleados.getValueAt(fila, 4)).equals("HOMBRE")){
			FrmEmpleado.rdbtnHombre.setSelected(true);
		}else {
			FrmEmpleado.rdbtnMujer.setSelected(true);
		}
		FrmEmpleado.cbCargos.setSelectedItem(String.valueOf(view.FrmEmpleados.tabEmpleados.getValueAt(fila, 5)));
	}
	public static void rellenarComboCargo() {
		CachedRowSet rowset;
		//hola
		System.out.println("Rellenando combo");
		try {
			rowset = logic.LogicEmpleado.rellenaComboCargo();
			while(rowset.next()) {
				FrmEmpleado.cbCargos.addItem(rowset.getString(2));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("fin Rellenando combo");
	}
	public static void addEmpleado() {
		String nombre,apellidos,dni,genero,cargo;
		
		nombre = FrmEmpleado.txtNombre.getText();
		apellidos = FrmEmpleado.txtApellidos.getText();
		dni = FrmEmpleado.txtDni.getText();
		genero = FrmEmpleado.rdbtnHombre.isSelected() ? "1" : "2";
		cargo = FrmEmpleado.cbCargos.getSelectedItem().toString();
		
		logic.LogicEmpleado.addEmpleado(nombre,apellidos,dni,genero,cargo);
		
	}
	public static void editarEmpleado() {
		String nombre,apellidos,dni,genero,cargo;
		
		nombre = FrmEmpleado.txtNombre.getText();
		apellidos = FrmEmpleado.txtApellidos.getText();
		dni = FrmEmpleado.txtDni.getText();
		genero = FrmEmpleado.rdbtnHombre.isSelected() ? "1" : "2";
		cargo = FrmEmpleado.cbCargos.getSelectedItem().toString();
		
		logic.LogicEmpleado.editarEmpleado(nombre,apellidos,dni,genero,cargo);
	}
}
