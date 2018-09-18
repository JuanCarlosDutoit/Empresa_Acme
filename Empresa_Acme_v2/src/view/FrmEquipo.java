package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextField;

import controller.CtrlEquipo;
import controller.CtrlProyecto;
import javax.swing.JTextArea;


public class FrmEquipo extends JDialog {

	private static final long serialVersionUID = 1L;
	
	public static JTextField txtNombre;
	public static JTextArea txtDes;
	
	public static JButton btnGuardar;
	public static JButton btnOk;
	public static JButton btnEditar;

	public FrmEquipo() {
		
		setAlwaysOnTop(true);
		setBounds(100, 100, 268, 218);
		getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Nombre");
		lblNewLabel.setBounds(10, 11, 64, 14);
		getContentPane().add(lblNewLabel);
		
		JLabel lblApellidos = new JLabel("Descripcion");
		lblApellidos.setBounds(10, 36, 64, 14);
		getContentPane().add(lblApellidos);
		
		txtNombre = new JTextField();
		txtNombre.setBounds(84, 8, 142, 20);
		getContentPane().add(txtNombre);
		txtNombre.setColumns(10);
		
		txtDes = new JTextArea();
		txtDes.setBounds(84, 43, 142, 91);
		getContentPane().add(txtDes);
		
		btnGuardar = new JButton("GUARDAR");
		btnGuardar.setBounds(72, 145, 102, 23);
		getContentPane().add(btnGuardar);
		
		btnOk = new JButton("OK");
		btnOk.setBounds(72, 145, 102, 23);
		getContentPane().add(btnOk);
		
		btnEditar = new JButton("EDITAR");
		btnEditar.setBounds(72, 145, 102, 23);
		getContentPane().add(btnEditar);
		
		//Listeners
		btnGuardar.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				CtrlEquipo.addEquipo();
				dispose();
			}
		
		});

		btnEditar.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				CtrlEquipo.editarEquipo();
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