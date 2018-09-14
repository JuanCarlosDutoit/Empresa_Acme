package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.Color;

public class FrmPrincipal extends JFrame {

	private static final long serialVersionUID = 1L;
	
	public FrmPrincipal() {
		
		getContentPane().setBackground(Color.WHITE);
		setTitle("MI_EMPRESA");
		setBounds(100, 100, 450, 300);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);
		
		JLabel lblAcme = new JLabel("");
		//lblAcme.setIcon(new ImageIcon("C:\\Users\\Usuario\\eclipse-workspace\\Empresa ACME\\Empresa ACME\\Acme.jpg"));
		lblAcme.setIcon(new ImageIcon("Acme.jpg"));
		lblAcme.setBounds(224, 143, 210, 97);
		getContentPane().add(lblAcme);
		
		JLabel lblAcme2 = new JLabel("");
		//lblAcme2.setIcon(new ImageIcon("C:\\Users\\Usuario\\eclipse-workspace\\Empresa ACME\\Empresa ACME\\s-l300.jpg"));
		lblAcme2.setIcon(new ImageIcon("D:\\Eclipse\\eclipse-workspace\\Empresa_Acme_v2\\Coyote.jpg"));
		lblAcme2.setBounds(0, 0, 210, 132);
		getContentPane().add(lblAcme2);

		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		JMenu mnBase = new JMenu("BASE");
		menuBar.add(mnBase);
		JMenuItem mntmProyectos = new JMenuItem("Proyectos");
		mnBase.add(mntmProyectos);
		JMenuItem mntmTrabajadores = new JMenuItem("Trabajadores");
		mnBase.add(mntmTrabajadores);
		JMenuItem menuItem = new JMenuItem("_______________");
		mnBase.add(menuItem);
		JMenuItem mntmSalir = new JMenuItem("Salir");
		mnBase.add(mntmSalir);
		JMenu mnGestion = new JMenu("GESTION");
		menuBar.add(mnGestion);
		JMenuItem mntmEquipos = new JMenuItem("Equipos");
		mnGestion.add(mntmEquipos);

		// Listeners
		mntmProyectos.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				//controller.CtrlProyectos.inicio();
			}
		});

		mntmTrabajadores.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				controller.CtrlEmpleados.inicio();
			}
		});

		mntmEquipos.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				//controller.CtrlEquipos.inicio();
			}
		});

		mntmSalir.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}
		});
		
		setVisible(true);
	}
}
