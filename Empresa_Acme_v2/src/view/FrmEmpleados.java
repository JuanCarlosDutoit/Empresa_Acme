package view;

import java.awt.Dialog.ModalExclusionType;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;

import javax.swing.JScrollPane;
import javax.swing.JTable;

import controller.CtrlEmpleados;
import java.awt.Color;

public class FrmEmpleados extends JFrame {
	private static final long serialVersionUID = 1L;

	public static DefaultListModel<String> lista;
	public static JTable tabEmpleados;

	public FrmEmpleados() {
		getContentPane().setBackground(Color.BLACK);

		setAlwaysOnTop(true);
		setModalExclusionType(ModalExclusionType.APPLICATION_EXCLUDE);
		setTitle("EMPLEADOS");
		setBounds(100, 100, 450, 300);

		lista = new DefaultListModel<String>();
		getContentPane().setLayout(null);

		JButton btnNuevo = new JButton("NUEVO");
		btnNuevo.setBounds(40, 215, 89, 23);
		getContentPane().add(btnNuevo);

		JButton btnBorrar = new JButton("BORRAR");
		btnBorrar.setBounds(126, 215, 89, 23);
		getContentPane().add(btnBorrar);

		JButton btnEditar = new JButton("EDITAR");
		btnEditar.setBounds(213, 215, 89, 23);
		getContentPane().add(btnEditar);

		JButton btnInfo = new JButton("INFO");
		btnInfo.setBounds(300, 215, 89, 23);
		getContentPane().add(btnInfo);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(40, 31, 346, 160);
		getContentPane().add(scrollPane);
		
		tabEmpleados = new JTable();
		scrollPane.setViewportView(tabEmpleados);

		// Listeners
		btnNuevo.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				CtrlEmpleados.addEmpleado();
			}
		});

		btnBorrar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				CtrlEmpleados.borrarEmpleado();
			}

		});

		btnEditar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				CtrlEmpleados.editarEmpleado();
			}
		});

		btnInfo.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				CtrlEmpleados.infoEmpleado();
			}
		});
		
		setVisible(true);
	}
}
