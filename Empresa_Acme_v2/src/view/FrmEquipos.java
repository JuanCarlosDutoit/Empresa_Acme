package view;

import java.awt.Dialog.ModalExclusionType;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

import javax.swing.JScrollPane;
import javax.swing.JTable;

import controller.CtrlEquipos;

public class FrmEquipos extends JFrame {
	private static final long serialVersionUID = 1L;

	public static JTable tabEquipos;
	public static JTable tabPersonal;

	public FrmEquipos() {

		setAlwaysOnTop(true);
		setModalExclusionType(ModalExclusionType.APPLICATION_EXCLUDE);
		setTitle("EQUIPOS");
		setBounds(100, 100, 450, 387);
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
		
		JButton btnAsignar = new JButton("+");
		btnAsignar.setBounds(377, 241, 41, 23);
		getContentPane().add(btnAsignar);
		
		JButton btnQuitar = new JButton("-");
		btnQuitar.setBounds(377, 275, 41, 23);
		getContentPane().add(btnQuitar);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(22, 14, 271, 161);
		getContentPane().add(scrollPane);
		
		tabEquipos = new JTable(){
			private static final long serialVersionUID = 1L;

			public boolean isCellEditable(int rowIndex, int colIndex) {
				return false; //Las celdas no son editables.
			}
			};
		scrollPane.setViewportView(tabEquipos);
				
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(25, 199, 342, 138);
		getContentPane().add(scrollPane_1);
		
		tabPersonal = new JTable();
		scrollPane_1.setViewportView(tabPersonal);
		

		// Listeners
		btnNuevo.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				CtrlEquipos.addEquipo();
			}
		});

		btnBorrar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				CtrlEquipos.borrarEquipo();
			}

		});
		btnEditar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				CtrlEquipos.editarEquipo();
			}
		});
		btnInfo.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				CtrlEquipos.infoEquipo();
			}
		});
		btnAsignar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				CtrlEquipos.addPersonalEquipo();
			}
		});
		btnQuitar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				CtrlEquipos.borrarPersonalEquipo();
			}
		});
		setVisible(true);
	}
}
