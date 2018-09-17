package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextField;

import controller.CtrlProyecto;


public class FrmProyecto extends JDialog {

	private static final long serialVersionUID = 1L;
	
	public static JTextField txtNombre;
	public static JTextField txtPresupuesto;
	public static JTextField txtFechaInicio;
	public static JTextField txtFechaFin;
	
	public static JButton btnGuardar;
	public static JButton btnOk;
	public static JButton btnEditar;

	public FrmProyecto() {
		
		setAlwaysOnTop(true);
		setBounds(100, 100, 268, 218);
		getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Nombre");
		lblNewLabel.setBounds(10, 11, 64, 14);
		getContentPane().add(lblNewLabel);
		
		JLabel lblApellidos = new JLabel("Presupuesto");
		lblApellidos.setBounds(10, 36, 64, 14);
		getContentPane().add(lblApellidos);
		
		JLabel lblDni = new JLabel("Fecha_inicio");
		lblDni.setBounds(10, 61, 64, 14);
		getContentPane().add(lblDni);
		
		JLabel lblGenero = new JLabel("Fecha_Fin");
		lblGenero.setBounds(10, 86, 64, 14);
		getContentPane().add(lblGenero);
		
		txtNombre = new JTextField();
		txtNombre.setBounds(87, 8, 139, 20);
		getContentPane().add(txtNombre);
		txtNombre.setColumns(10);
		
		txtPresupuesto = new JTextField();
		txtPresupuesto.setColumns(10);
		txtPresupuesto.setBounds(87, 33, 139, 20);
		getContentPane().add(txtPresupuesto);
		
		txtFechaInicio = new JTextField();
		txtFechaInicio.setColumns(10);
		txtFechaInicio.setBounds(87, 58, 139, 20);
		getContentPane().add(txtFechaInicio);
		
		txtFechaFin = new JTextField();
		txtFechaFin.setColumns(10);
		txtFechaFin.setBounds(87, 83, 139, 20);
		getContentPane().add(txtFechaFin);
		
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
				CtrlProyecto.addProyecto();
				dispose();
			}
		
		});

		btnEditar.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				CtrlProyecto.editarProyecto();
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