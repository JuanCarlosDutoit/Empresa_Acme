package controller;

import java.sql.SQLException;

import javax.sql.rowset.CachedRowSet;

import model.Empleado;
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
			rellenarComboCargo();
			break;
		case 1:
			//Editar
			FrmEmpleado.btnGuardar.setVisible(false);
			FrmEmpleado.btnOk.setVisible(false);
			rellenarComboCargo();
			rellenarDatosEmpleado();
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
			rellenarComboCargo();
			rellenarDatosEmpleado();
			
			break;
		}
	}
	private static void rellenarDatosEmpleado() {
		int fila;
		
		System.out.println("Relleno datos del usuario");
		fila = view.FrmEmpleados.tabEmpleados.getSelectedRow();
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
		String cargo;
		System.out.println("Rellenando combo Empleados.Cargos");
		try {
			rowset = logic.LogicEmpleado.rellenaComboCargo();
			while(rowset.next()) {
				cargo = rowset.getString(1) + "-" + rowset.getString(2);
				FrmEmpleado.cbCargos.addItem(cargo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println("fin Rellenando combo");
	}
	public static void addEmpleado() {
		String cargo;
		
		Empleado emp;
		emp = new Empleado();
		
		emp.setNombre(FrmEmpleado.txtNombre.getText());
		emp.setApellido(FrmEmpleado.txtApellidos.getText());
		emp.setDni(FrmEmpleado.txtDni.getText());
		emp.setGenero(FrmEmpleado.rdbtnHombre.isSelected() ? 1 : 2);
		cargo = FrmEmpleado.cbCargos.getSelectedItem().toString().substring(0, FrmEmpleado.cbCargos.getSelectedItem().toString().indexOf("-"));
		emp.setPuesto(Integer.valueOf(cargo));
		
		/*nombre = FrmEmpleado.txtNombre.getText();
		apellidos = FrmEmpleado.txtApellidos.getText();
		dni = FrmEmpleado.txtDni.getText();
		genero = FrmEmpleado.rdbtnHombre.isSelected() ? "1" : "2";
		cargo = FrmEmpleado.cbCargos.getSelectedItem().toString().substring(0, FrmEmpleado.cbCargos.getSelectedItem().toString().indexOf("-"));
		*/
		//logic.LogicEmpleado.addEmpleado(nombre,apellidos,dni,genero,cargo);
		logic.LogicEmpleado.addEmpleado(emp);
		
	}
	public static void editarEmpleado() {
		String nombre,apellidos,dni,genero,cargo;
		
		nombre = FrmEmpleado.txtNombre.getText();
		apellidos = FrmEmpleado.txtApellidos.getText();
		dni = FrmEmpleado.txtDni.getText();
		genero = FrmEmpleado.rdbtnHombre.isSelected() ? "1" : "2";
		cargo = FrmEmpleado.cbCargos.getSelectedItem().toString().substring(0, FrmEmpleado.cbCargos.getSelectedItem().toString().indexOf("-"));
		
		logic.LogicEmpleado.editarEmpleado(nombre,apellidos,dni,genero,cargo);
	}
}
