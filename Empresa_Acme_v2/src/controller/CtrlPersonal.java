package controller;

import java.sql.SQLException;

import javax.sql.rowset.CachedRowSet;

import view.FrmPersonal;

public class CtrlPersonal {

	public static void inicio() {
		new view.FrmPersonal();
		rellenarComboEmpleado();
		rellenarcomboCargo();
	}

	private static void rellenarComboEmpleado() {
		CachedRowSet rowset;
		System.out.println("Rellenando combo");
		try {
			rowset = logic.LogicPersonal.rellenaComboEmpleado();
			while(rowset.next()) {
				String Empleado = rowset.getString(1)+"-"+rowset.getString(2)+"-"+rowset.getString(3);
				FrmPersonal.cmbEmpleados.addItem(Empleado);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println("fin Rellenando combo");
	}

	private static void rellenarcomboCargo() {
		CachedRowSet rowset;
		System.out.println("Rellenando combo");
		try {
			rowset = logic.LogicPersonal.rellenaComboCargo();
			while(rowset.next()) {
				FrmPersonal.cmbCargos.addItem(rowset.getString(2));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println("fin Rellenando combo");	
	}
	public static void addPersonal() {
		String empleado,cargo;
		System.out.println(String.valueOf(FrmPersonal.cmbEmpleados.getSelectedItem()));
		System.out.println(String.valueOf(FrmPersonal.cmbEmpleados.getSelectedItem()).substring(0,String.valueOf(FrmPersonal.cmbEmpleados.getSelectedItem()).indexOf("-")));
		empleado = String.valueOf(FrmPersonal.cmbEmpleados.getSelectedItem()).substring(0,String.valueOf(FrmPersonal.cmbEmpleados.getSelectedItem()).indexOf("-"));
		System.out.println(empleado);
		cargo = String.valueOf(FrmPersonal.cmbCargos.getSelectedItem());
	
		
		logic.LogicPersonal.addEmpleado(empleado,cargo);
		
	}

}
