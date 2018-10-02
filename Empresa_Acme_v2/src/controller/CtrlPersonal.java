package controller;

import java.sql.SQLException;

import javax.sql.rowset.CachedRowSet;

import util.Utilidades;
import view.FrmPersonal;

public class CtrlPersonal {

	public static void inicio() {
		new view.FrmPersonal();
		rellenarComboEmpleado();
		rellenarcomboCargo();
	}

	private static void rellenarComboEmpleado() {
		CachedRowSet rowset;
		try {
			rowset = logic.LogicPersonal.rellenaComboEmpleado();
			while(rowset.next()) {
				String Empleado = rowset.getString(1)+"-"+rowset.getString(2)+"-"+rowset.getString(3);
				FrmPersonal.cmbEmpleados.addItem(Empleado);
			}
		} catch (SQLException e) {
			Utilidades.gestionaErrorSql(e);
		}
	}

	private static void rellenarcomboCargo() {
		CachedRowSet rowset;
		try {
			rowset = logic.LogicPersonal.rellenaComboCargo();
			while(rowset.next()) {
				String Empleado = rowset.getString(1)+"-"+rowset.getString(2);
				FrmPersonal.cmbCargos.addItem(Empleado);
			}
		} catch (SQLException e) {
			Utilidades.gestionaErrorSql(e);
		}
	}
	public static void addPersonal() {
		String empleado,cargo;
		empleado = String.valueOf(FrmPersonal.cmbEmpleados.getSelectedItem()).substring(0,String.valueOf(FrmPersonal.cmbEmpleados.getSelectedItem()).indexOf("-"));
		cargo =String.valueOf(FrmPersonal.cmbCargos.getSelectedItem()).substring(0,String.valueOf(FrmPersonal.cmbCargos.getSelectedItem()).indexOf("-"));
		try {
			logic.LogicPersonal.addEmpleado(empleado,cargo);
		} catch (SQLException e) {
				Utilidades.gestionaErrorSql(e);
		}
	}

}
