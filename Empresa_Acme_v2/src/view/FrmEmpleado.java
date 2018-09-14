package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import controller.CtrlEmpleado;

public class FrmEmpleado {
	
	public static JDialog frame;
	private JTextField txtNombre;
	private JTextField txtApellidos;
	private JTextField txtDni;
	public static JComboBox cbCargos;
	public static JRadioButton rdbtnHombre;
	public static JRadioButton rdbtnMujer;

	public FrmEmpleado(int tipo) {
		
		frame = new JDialog();
		frame.setAlwaysOnTop(true);
		frame.setBounds(100, 100, 268, 218);
		frame.getContentPane().setLayout(null);
		frame.setModal(true);
		
		JLabel lblNewLabel = new JLabel("Nombre");
		lblNewLabel.setBounds(10, 11, 64, 14);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblApellidos = new JLabel("Apellidos");
		lblApellidos.setBounds(10, 36, 64, 14);
		frame.getContentPane().add(lblApellidos);
		
		JLabel lblDni = new JLabel("DNI");
		lblDni.setBounds(10, 61, 64, 14);
		frame.getContentPane().add(lblDni);
		
		JLabel lblGenero = new JLabel("Genero");
		lblGenero.setBounds(10, 86, 64, 14);
		frame.getContentPane().add(lblGenero);
		
		txtNombre = new JTextField();
		txtNombre.setBounds(87, 8, 139, 20);
		frame.getContentPane().add(txtNombre);
		txtNombre.setColumns(10);
		
		txtApellidos = new JTextField();
		txtApellidos.setColumns(10);
		txtApellidos.setBounds(87, 33, 139, 20);
		frame.getContentPane().add(txtApellidos);
		
		txtDni = new JTextField();
		txtDni.setColumns(10);
		txtDni.setBounds(87, 58, 139, 20);
		frame.getContentPane().add(txtDni);
		
		JLabel lblCargo = new JLabel("Cargo");
		lblCargo.setBounds(10, 111, 64, 14);
		frame.getContentPane().add(lblCargo);
		
		
		ButtonGroup chkSexo = new ButtonGroup();
		
		rdbtnHombre = new JRadioButton("Hombre");
		rdbtnHombre.setBounds(80, 82, 75, 23);
		frame.getContentPane().add(rdbtnHombre);
		
		rdbtnMujer = new JRadioButton("Mujer");
		rdbtnMujer.setBounds(157, 82, 69, 23);
		frame.getContentPane().add(rdbtnMujer);
		
		chkSexo.add(rdbtnHombre);
		chkSexo.add(rdbtnMujer);
		
		JButton btnGuardar = new JButton("GUARDAR");
		btnGuardar.setBounds(72, 145, 102, 23);
		frame.getContentPane().add(btnGuardar);
		
		JButton btnOk = new JButton("OK");
		btnOk.setBounds(72, 145, 102, 23);
		frame.getContentPane().add(btnOk);
		
		JButton btnEditar = new JButton("EDITAR");
		btnEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnEditar.setBounds(72, 145, 102, 23);
		frame.getContentPane().add(btnEditar);
		
		cbCargos = new JComboBox();
		cbCargos.setBounds(87, 108, 139, 20);
		frame.getContentPane().add(cbCargos);
		
		switch (tipo) {
		case 0:
			//Nuevo
			btnOk.setVisible(false);
			btnEditar.setVisible(false);
			break;
		case 1:
			//Editar
			btnGuardar.setVisible(false);
			btnOk.setVisible(false);
			break;
		case 2:
			//Info
			btnGuardar.setVisible(false);
			btnEditar.setVisible(false);

			txtNombre.setEnabled(false);
			txtApellidos.setEnabled(false);
			txtDni.setEnabled(false);
			rdbtnHombre.setEnabled(false);
			rdbtnMujer.setEnabled(false);
			cbCargos.setEnabled(false);
			
			break;
		}
		
		//Listeners
		btnGuardar.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				String genero;
				
				if (rdbtnHombre.isSelected()) {
					genero = "1";
				}else {
					genero = "2";
				}
				CtrlEmpleado.addEmpleado(txtNombre.getText(),txtApellidos.getText(),txtDni.getText(),
						                 genero,cbCargos.getSelectedItem().toString());
			}
		
		});
		/*btnEditar.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				String dni,nombre,apellido;
				int genero ;
				Cargo puesto;
				
				dni = textField_2.getText();
				nombre = textField.getText();
				apellido = textField_1.getText();
				if (rdbtnHombre.isSelected()) {
					genero = 1;
				}else {
					genero = 2;
				}
				puesto = (Cargo) comboBox.getSelectedItem(); 
				controller.CtrlEmpleados.editar(dni,nombre,apellido,genero,puesto);
				FrmEmpleados.rellenarListaEmpleados();
			}
		
		});
		btnOk.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				frame.dispose();
			}
		
		});*/
		
		//frame.setVisible(true);
	}
	/*public void rellenarCombo() {
		for (Cargo car:Cargo.values()) {
			//comboBox.addItem(car.toString());
			FrmEmpleado.comboBox.addItem(car);
		}
	}*/
	/*public void rellenarCampos() {
		textField.setText(String.valueOf(controller.CtrlEmpleados.lstEmpleado.get(
		          controller.CtrlEmpleados.eltoSelecc).getDni()));
		textField_1.setText(String.valueOf(controller.CtrlEmpleados.lstEmpleado.get(
				controller.CtrlEmpleados.eltoSelecc).getNombre()));
		textField_2.setText(String.valueOf(controller.CtrlEmpleados.lstEmpleado.get(
					controller.CtrlEmpleados.eltoSelecc).getApellido()));
		if(controller.CtrlEmpleados.lstEmpleado.get(
				controller.CtrlEmpleados.eltoSelecc).getGenero()== 1) {
			rdbtnHombre.setSelected(true);
		}else{
			rdbtnMujer.setSelected(true);
		};
		comboBox.setSelectedItem(controller.CtrlEmpleados.lstEmpleado.get(
					controller.CtrlEmpleados.eltoSelecc).getPuesto());	
	}*/
}
