package view;

import java.awt.Dialog.ModalExclusionType;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;

import model.Empleado;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import controller.CtrlEmpleados;
import controller.CtrlEquipos;

public class FrmEquipos extends JFrame {
	private static final long serialVersionUID = 1L;

	public static DefaultListModel<String> lista;
	public static JTable tabEquipos;
	private JTable tabPersonal;

	public FrmEquipos() {

		setAlwaysOnTop(true);
		setModalExclusionType(ModalExclusionType.APPLICATION_EXCLUDE);
		setTitle("EMPLEADOS");
		setBounds(100, 100, 450, 387);

		lista = new DefaultListModel<String>();
		getContentPane().setLayout(null);

		JButton btnNuevo = new JButton("NUEVO");
		btnNuevo.setBounds(318, 18, 89, 23);
		getContentPane().add(btnNuevo);

		JButton btnBorrar = new JButton("BORRAR");
		btnBorrar.setBounds(318, 50, 89, 23);
		getContentPane().add(btnBorrar);

		JButton btnEditar = new JButton("EDITAR");
		btnEditar.setBounds(318, 84, 89, 23);
		getContentPane().add(btnEditar);

		JButton btnInfo = new JButton("INFO");
		btnInfo.setBounds(318, 118, 89, 23);
		getContentPane().add(btnInfo);
		
		JButton BtnAsignar = new JButton("ASIGNAR");
		BtnAsignar.setBounds(318, 152, 89, 23);
		getContentPane().add(BtnAsignar);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(22, 14, 271, 161);
		getContentPane().add(scrollPane);
		
		tabEquipos = new JTable();
		scrollPane.setViewportView(tabEquipos);
		
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(40, 218, 345, 103);
		getContentPane().add(scrollPane_1);
		
		tabPersonal = new JTable();
		scrollPane_1.setViewportView(tabPersonal);

		// Listeners
		btnNuevo.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				CtrlEquipos.addEquipos();
			}
		});

		btnBorrar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				CtrlEquipos.borrarEquipos();
			}

		});

		btnEditar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				CtrlEquipos.editarEquipos();
			}
		});

		btnInfo.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				CtrlEmpleados.infoEquipos();
			}
		});
		
		setVisible(true);
	}
}
