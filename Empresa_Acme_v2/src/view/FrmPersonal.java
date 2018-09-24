package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;

import controller.CtrlPersonal;

public class FrmPersonal extends JDialog {

	private static final long serialVersionUID = 1L;
	public static JComboBox<String> cmbCargos;
	public static JComboBox<String> cmbEmpleados;
	public static JButton btnGuardar;
	
	public FrmPersonal() {
		setAlwaysOnTop(true);
		setBounds(100, 100, 268, 159);
		getContentPane().setLayout(null);
		//setModal(true);
		
		JLabel lblEmpleado = new JLabel("Empleado");
		lblEmpleado.setBounds(10, 11, 64, 14);
		getContentPane().add(lblEmpleado);
		
		JLabel lblCargo = new JLabel("Cargo");
		lblCargo.setBounds(10, 45, 64, 14);
		getContentPane().add(lblCargo);
		
		cmbCargos = new JComboBox<String>();
		cmbCargos.setBounds(84, 45, 139, 20);
		getContentPane().add(cmbCargos);
		
		cmbEmpleados = new JComboBox<String>();
		cmbEmpleados.setBounds(84, 8, 139, 20);
		getContentPane().add(cmbEmpleados);
		
		btnGuardar = new JButton("GUARDAR");
		btnGuardar.setBounds(72, 76, 102, 23);
		getContentPane().add(btnGuardar);
		
		//Listeners
		btnGuardar.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				CtrlPersonal.addPersonal();
				dispose();
			}
		
		});
		
		setVisible(true);
	}
}
