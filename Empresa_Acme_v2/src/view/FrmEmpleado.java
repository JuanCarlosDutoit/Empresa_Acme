package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import controller.CtrlEmpleado;

public class FrmEmpleado extends JDialog {

	private static final long serialVersionUID = 1L;

	public static JTextField txtNombre;
	public static JTextField txtApellidos;
	public static JTextField txtDni;
	public static JComboBox cbCargos;
	public static JRadioButton rdbtnHombre;
	public static JRadioButton rdbtnMujer;
	public static JButton btnGuardar;
	public static JButton btnOk;
	public static JButton btnEditar;
	
	public FrmEmpleado() {
		setAlwaysOnTop(true);
		setBounds(100, 100, 268, 218);
		getContentPane().setLayout(null);
		setModal(true);
		
		JLabel lblNewLabel = new JLabel("Nombre");
		lblNewLabel.setBounds(10, 11, 64, 14);
		getContentPane().add(lblNewLabel);
		
		JLabel lblApellidos = new JLabel("Apellidos");
		lblApellidos.setBounds(10, 36, 64, 14);
		getContentPane().add(lblApellidos);
		
		JLabel lblDni = new JLabel("DNI");
		lblDni.setBounds(10, 61, 64, 14);
		getContentPane().add(lblDni);
		
		JLabel lblGenero = new JLabel("Genero");
		lblGenero.setBounds(10, 86, 64, 14);
		getContentPane().add(lblGenero);
		
		txtNombre = new JTextField();
		txtNombre.setBounds(87, 8, 139, 20);
		getContentPane().add(txtNombre);
		txtNombre.setColumns(10);
		
		txtApellidos = new JTextField();
		txtApellidos.setColumns(10);
		txtApellidos.setBounds(87, 33, 139, 20);
		getContentPane().add(txtApellidos);
		
		txtDni = new JTextField();
		txtDni.setColumns(10);
		txtDni.setBounds(87, 58, 139, 20);
		getContentPane().add(txtDni);
		
		JLabel lblCargo = new JLabel("Cargo");
		lblCargo.setBounds(10, 111, 64, 14);
		getContentPane().add(lblCargo);
		
		
		ButtonGroup chkSexo = new ButtonGroup();
		
		rdbtnHombre = new JRadioButton("Hombre");
		rdbtnHombre.setBounds(80, 82, 75, 23);
		getContentPane().add(rdbtnHombre);
		
		rdbtnMujer = new JRadioButton("Mujer");
		rdbtnMujer.setBounds(157, 82, 69, 23);
		getContentPane().add(rdbtnMujer);
		
		chkSexo.add(rdbtnHombre);
		chkSexo.add(rdbtnMujer);
		
		btnGuardar = new JButton("GUARDAR");
		btnGuardar.setBounds(72, 145, 102, 23);
		getContentPane().add(btnGuardar);
		
		btnOk = new JButton("OK");
		btnOk.setBounds(72, 145, 102, 23);
		getContentPane().add(btnOk);
		
		btnEditar = new JButton("EDITAR");
		btnEditar.setBounds(72, 145, 102, 23);
		getContentPane().add(btnEditar);
		
		cbCargos = new JComboBox();
		cbCargos.setBounds(87, 108, 139, 20);
		getContentPane().add(cbCargos);
		
		/*switch (controller.CtrlEmpleado.state) {
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
		}*/
		
		//Listeners
		btnGuardar.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				CtrlEmpleado.addEmpleado();
				dispose();
			}
		
		});
		btnEditar.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {

				CtrlEmpleado.editarEmpleado();
				dispose();
			}
		});
		btnOk.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		
		});
		
		setVisible(true);
	}
}
