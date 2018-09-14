package controller;

import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import javax.sql.rowset.CachedRowSet;

import view.FrmEmpleado;

public class CtrlEmpleado {
	
	public static void inicio(int tipo) {
		new view.FrmEmpleado(tipo);
		switch(tipo) {
		case 0://Nuevo
			rellenarComboCargo();
			break;
		case 1://Editar
			rellenarComboCargo();
			break;
		case 2:
			break;
		}
		FrmEmpleado.frame.setVisible(true);
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
}
