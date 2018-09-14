package util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import javax.sql.rowset.CachedRowSet;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import view.FrmPrincipal;

public class Utilidades {

	public static String ip,port,bd,usu,pass;
	
	public static boolean leerFicheroConexion(String nfichero) {
		//Leo fichero de conexion y recupero los datos
		System.out.println("Leyendo fichero");
		try {
	
			FileReader fichero = new FileReader(nfichero);
			BufferedReader buffer = new BufferedReader(fichero);
			
			String linea = "";
			int aux = 5;
			
			for(int i=1;i<=aux;i++) {
				linea = buffer.readLine();
				if (linea != null && linea.isEmpty()==false) {
					switch(i) {
					case 1:
						ip=linea;
						break;
					case 2:
						port=linea;
						break;
					case 3:
						bd=linea;
						break;
					case 4:
						usu=linea;
						break;
					case 5:
						pass=linea;
						break;
					}
				}else {
					JOptionPane.showMessageDialog(null, "Faltan datos en el fichero de conexion", "Error", 1);
					return false;
				}
			}
			buffer.close();
			return true;
			
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Error de lectura en el fichero de conexion \n" + e.getMessage(), "Error", 1);
			return false;
		}
			
	}
	public static DefaultTableModel creaModeloTablas(CachedRowSet r) {
		
		DefaultTableModel modelo = new DefaultTableModel();
		ResultSetMetaData md;
		int totalcampos;
		
		try {
			md = r.getMetaData();
		    totalcampos = md.getColumnCount();
			
			for(int i=1;i<=totalcampos;i++ ) {
				modelo.addColumn(md.getColumnName(i));
			}
			String[] campo = new String[totalcampos];
			while (r.next()) {
				for(int i=1;i<=totalcampos;i++ ) {
					campo[i-1] = r.getString(i);
				}
				modelo.addRow(campo);
			}
			return modelo;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
}
