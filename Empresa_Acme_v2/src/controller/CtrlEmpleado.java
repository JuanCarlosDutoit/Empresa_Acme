package controller;

import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import javax.sql.rowset.CachedRowSet;
import javax.swing.table.DefaultTableModel;

import view.FrmEmpleado;
import view.FrmEmpleados;

public class CtrlEmpleado {
	
	public static void inicio(int tipo) {
		new view.FrmEmpleado(tipo);
		switch(tipo) {
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
		FrmEmpleado.frame.setVisible(true);
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
	public static void addEmpleado(String nombre, String apellidos, String dni, String genero, String cargo) {
		logic.LogicEmpleado.addEmpleado(nombre,apellidos,dni,genero,cargo);
		FrmEmpleado.frame.dispose();
	}
	public static void editarEmpleado(String nombre, String apellidos, String dni, String genero, String cargo) {
		logic.LogicEmpleado.editarEmpleado(nombre,apellidos,dni,genero,cargo);
		FrmEmpleado.frame.dispose();
	}
}
