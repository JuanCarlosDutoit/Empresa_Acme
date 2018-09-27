package controller;

import java.sql.SQLException;

import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import util.Utilidades;

public class CtrlEmpleados {
	
	public static String empleadoSelecc; 
	
	public static void inicio() {
		new view.FrmEmpleados();
		empleadoSelecc = "-1";
		cargarListaEmpleados();
		
	}
	public static void cargarListaEmpleados() {
		DefaultTableModel modelo;
		try {
			modelo = logic.LogicEmpleados.iniciaListaEmpleados();
			rellenarListaEmpleados(modelo);
			//1 linea como seleccionada
			view.FrmEmpleados.tabEmpleados.getSelectionModel().setSelectionInterval(0,0);
		} catch (SQLException e) {
			/*if(e.getErrorCode()==207) {
				e.printStackTrace();
				JOptionPane.showMessageDialog(null, "Error en la ejecucion de Sql\n" 
				                                    + e.getMessage(), "Error", 1);
			}
			else {
				JOptionPane.showMessageDialog(null, "vavavaa." 
                        + e.getMessage(), "Error", 1);
			}*/
			Utilidades.gestionaErrorSql(e);
		}
	}
	public static void rellenarListaEmpleados(DefaultTableModel modelo) {
		//Establezco el modelo de la tabla
		view.FrmEmpleados.tabEmpleados.setModel(modelo);
		//Oculto la columna del codigo
		view.FrmEmpleados.tabEmpleados.getColumnModel().getColumn(0).setMaxWidth(0);
		view.FrmEmpleados.tabEmpleados.getColumnModel().getColumn(0).setMinWidth(0);
		view.FrmEmpleados.tabEmpleados.getTableHeader().getColumnModel().getColumn(0).setMaxWidth(0);
		view.FrmEmpleados.tabEmpleados.getTableHeader().getColumnModel().getColumn(0).setMinWidth(0);
		//Establezco el tipo de seleccion 
		view.FrmEmpleados.tabEmpleados.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		//Añadimos Listener de cambio de valores
		ListSelectionModel rowSM = view.FrmEmpleados.tabEmpleados.getSelectionModel();
		rowSM.addListSelectionListener(new ListSelectionListener(){

		    public void valueChanged(ListSelectionEvent e) {
		    	if (!e.getValueIsAdjusting()) {
		    		//Esto es porque si pincho este listener salta dos veces
		    		int fila;
		    		fila = view.FrmEmpleados.tabEmpleados.getSelectedRow();
		    		if(fila>=0) {
		    			empleadoSelecc = String.valueOf(view.FrmEmpleados.tabEmpleados.getValueAt(fila,0));
		    		}
		    	}
		    }
		});
	}
	public static void borrarEmpleado() {
		/*int fil, col;
		DefaultTableModel modelo;
		fil = view.FrmEmpleados.tabEmpleados.getSelectedRow();
		col = 0;
		empleadoSelecc = String.valueOf(view.FrmEmpleados.tabEmpleados.getValueAt(fil, col));
		*/
		//logic.LogicEmpleados.borrarEmpleado(empleadoSelecc);
		System.out.println("quiero borrar el :" + empleadoSelecc);
		if (!CtrlEmpleados.empleadoSelecc.equals("-1")) {
			if(JOptionPane.showConfirmDialog(null, "Va a eliminar este registro, desea continuar?",
					"AVISO",JOptionPane.YES_NO_OPTION)==0) {
				logic.LogicEmpleados.borrarEmpleado();
				cargarListaEmpleados();
			}
		}else {
			JOptionPane.showMessageDialog(null, "No ha seleccionado ningun empleado", "Error", 1);
		}
	}	
	public static void addEmpleado() {
		CtrlEmpleado.state = 0;
		CtrlEmpleado.inicio();
	}
	public static void editarEmpleado() {
		CtrlEmpleado.state = 1;
		CtrlEmpleado.inicio();
	}
	public static void infoEmpleado() {
		CtrlEmpleado.state = 2;
		CtrlEmpleado.inicio();
	}

}