package controller;

import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import view.FrmEmpleados;

public class CtrlEmpleados {
	
	public static int eltoSelecc; 
	
	public static void inicio() {
		new view.FrmEmpleados();
	}
	public static void borrar() {
		
	}
	public static void add() { 

	}
	public static void editar() {

	}
	public static void rellenarListaEmpleados() {
		DefaultTableModel modelo;
		
		modelo = logic.LogicEmpleados.rellenarListaEmpleados();
		FrmEmpleados.tabEmpleados.setModel(modelo);
		FrmEmpleados.tabEmpleados.getColumnModel().getColumn(0).setPreferredWidth(0);
		
		FrmEmpleados.tabEmpleados.setCellSelectionEnabled(true);
		ListSelectionModel cellSelectionModel = FrmEmpleados.tabEmpleados.getSelectionModel();
		cellSelectionModel.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
	    cellSelectionModel.addListSelectionListener(new ListSelectionListener() {
	        public void valueChanged(ListSelectionEvent e) {
	      
	        }

	      });
		
		
		
		/*table.setCellSelectionEnabled(true);
	    ListSelectionModel cellSelectionModel = table.getSelectionModel();
	    cellSelectionModel.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
	    
	    cellSelectionModel.addListSelectionListener(new ListSelectionListener() {
	        public void valueChanged(ListSelectionEvent e) {
	          String selectedData = null;

	          int[] selectedRow = table.getSelectedRows();
	          int[] selectedColumns = table.getSelectedColumns();

	          for (int i = 0; i < selectedRow.length; i++) {
	            for (int j = 0; j < selectedColumns.length; j++) {
	              selectedData = (String) table.getValueAt(selectedRow[i], selectedColumns[j]);
	            }
	          }
	          System.out.println("Selected: " + selectedData);
	        }

	      });*/
		
		
	}

}
