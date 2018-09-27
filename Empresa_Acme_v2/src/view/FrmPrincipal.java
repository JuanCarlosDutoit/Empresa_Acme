package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.net.URL;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import controller.CtrlPrincipal;

import javax.swing.JLabel;
import javax.help.HelpBroker;
import javax.help.HelpSet;
import javax.swing.ImageIcon;
import java.awt.Color;

public class FrmPrincipal extends JFrame {

	private static final long serialVersionUID = 1L;
	
	private static JMenuItem mntmAyuda;
	
	public FrmPrincipal() {
		
		getContentPane().setBackground(Color.BLACK);
		setTitle("MI_EMPRESA");
		setBounds(100, 100, 450, 300);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);
		
		JLabel lblAcme = new JLabel("");
		lblAcme.setBackground(Color.BLACK);
		//lblAcme.setIcon(new ImageIcon("C:\\Users\\Usuario\\eclipse-workspace\\Empresa ACME\\Empresa ACME\\Acme.jpg"));
		lblAcme.setIcon(new ImageIcon("Acme.jpg"));
		lblAcme.setBounds(107, 65, 210, 97);
		getContentPane().add(lblAcme);
		
		//JLabel lblAcme2 = new JLabel("");
		//lblAcme2.setIcon(new ImageIcon("C:\\Users\\Usuario\\eclipse-workspace\\Empresa ACME\\Empresa ACME\\s-l300.jpg"));
		//lblAcme2.setIcon(new ImageIcon("Coyote.jpg"));
		//lblAcme2.setBounds(0, 0, 210, 132);
		//getContentPane().add(lblAcme2);

		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		JMenu mnBase = new JMenu("BASE");
		menuBar.add(mnBase);
		JMenuItem mntmProyectos = new JMenuItem("Proyectos");
		mnBase.add(mntmProyectos);
		JMenuItem mntmTrabajadores = new JMenuItem("Trabajadores");
		mnBase.add(mntmTrabajadores);
		JMenuItem mntmEquipos = new JMenuItem("Equipos");
		mnBase.add(mntmEquipos);
		JMenuItem menuItem = new JMenuItem("_______________");
		mnBase.add(menuItem);
		
		mntmAyuda = new JMenuItem("Ayuda");
		mnBase.add(mntmAyuda);
		JMenuItem mntmSalir = new JMenuItem("Salir");
		mnBase.add(mntmSalir);
		
		JMenu mnGestion = new JMenu("GESTION");
		menuBar.add(mnGestion);
		JMenuItem mntmEquiposGestion = new JMenuItem("Equipos");
		mnGestion.add(mntmEquiposGestion);

		// Listeners
		mntmProyectos.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				CtrlPrincipal.getionProyectos();
			}
		});

		mntmTrabajadores.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				CtrlPrincipal.getionTrabajadores();
			}
		});
		mntmEquipos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				CtrlPrincipal.gestionEquipos();
			}
		});
		mntmEquiposGestion.addActionListener(new ActionListener() {
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
		mntmAyuda.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		cargarAyuda();
		setVisible(true);
	}
	
	private void cargarAyuda() {
		System.out.println("cargo ayuda");
		try {
			// Carga el fichero de ayuda
			File fichero = new File("src/proyecto/help/help.hs");
			URL hsURL = fichero.toURI().toURL();
		 
			// Crea el HelpSet y el HelpBroker
			HelpSet helpset = new HelpSet(getClass().getClassLoader(), hsURL);
			HelpBroker hb = helpset.createHelpBroker();
		 
			// Pone ayuda a item de menu al pulsar F1. mntmIndice es el JMenuitem
			hb.enableHelpOnButton(mntmAyuda, "manual", helpset);
			//hb.enableHelpKey(getContentPane(), "manual", helpset);
		 
		} catch (Exception e) {
			System.out.println("Error al cargar la ayuda: " + e);
		}
	}
}
