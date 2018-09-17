package view;

import java.awt.Dialog.ModalExclusionType;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;

import model.Proyecto;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class FrmProyectos extends JFrame {

	private static final long serialVersionUID = 1L;

	public static DefaultListModel<String> lista;
	public static JTable tabProyectos;

	public FrmProyectos() {
		initialize();
		rellenarListaProyectos();
		setVisible(true);
	}

	private void initialize() {
		setAlwaysOnTop(true);
		setModalExclusionType(ModalExclusionType.APPLICATION_EXCLUDE);
		setTitle("PROYECTOS");
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(null);

		lista = new DefaultListModel<String>();

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
		
		tabProyectos = new JTable();
		scrollPane.setViewportView(tabProyectos);

		// Listeners
		btnNuevo.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				//System.out.println("Proyecto");
				//new view.FrmProyecto(0);
			}
		});

		btnBorrar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				//controller.CtrlProyectos.eltoSelecc = list.getSelectedIndex();
				//controller.CtrlProyectos.borrar();
				//rellenarListaProyectos();
			}

		});

		btnEditar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				//if (list.getSelectedIndex() != -1) {
					//controller.CtrlProyectos.eltoSelecc = list.getSelectedIndex();
					//new FrmProyecto(1);
				//}
			}
		});

		btnInfo.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				//if (list.getSelectedIndex() != -1) {
					//controller.CtrlProyectos.eltoSelecc = list.getSelectedIndex();
					//new FrmProyecto(2);
				//}
			}
		});
		
		setVisible(true);

	}

	public static void rellenarListaProyectos() {
		//lista.removeAllElements();
		//for (Proyecto p : controller.CtrlProyectos.lstProyecto) {
		//	lista.addElement(p.getNombre());
		//}
	}
}