package util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import javax.sql.rowset.CachedRowSet;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class Utilidades {

	public static String ip;
	public static String port;
	public static String bd;
	public static String usu;
	public static String pass;
	
	public static boolean leerFicheroConexion(String nfichero){
		//Leo fichero de conexion y recupero los datos
		int lineasFicheroConexion = 5;
		String linea = "";
		
		System.out.println("Leyendo fichero");
		try {
	
			FileReader fichero = new FileReader(nfichero);
			BufferedReader buffer = new BufferedReader(fichero);
			
			for(int i=1;i<=lineasFicheroConexion;i++) {
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
					buffer.close();
					JOptionPane.showMessageDialog(null, "Faltan datos en el fichero de conexion " + nfichero, "Error", 1);
					return false;
				}
			}
			buffer.close();
			return true;
			
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Error de lectura en el fichero de conexion " 
		                                        + nfichero + "\n" 
		                                        + e.getMessage(), "Error", 1);
			return false;
		}
			
	}
	public static DefaultTableModel creaModeloTablas(CachedRowSet r) throws SQLException {
		
		DefaultTableModel modelo = new DefaultTableModel();
		ResultSetMetaData md;
		int totalcampos;
		
		//try {
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
		/*} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Error en la creacion del modelo de tabla \n" 
		                                         + e.getMessage(), "Error", 1);
			return null;
		}*/
	}
	public static void gestionaErrorSql(SQLException e) {
		String error = "";
		switch(e.getErrorCode()) {
		case 0:
			error = "Error en la conexion a la BD\n"
		          + "No se pudo realizar la conexión TCP/IP al host";
			break;
		case 207://Invalid colum name 			
			error= "Error en la ejecucion de Sql\n" + e.getMessage() ;
			break;
		case 208://Invalid object name (tablas)
			error= "Error en la ejecucion de Sql\n" + e.getMessage() ;
			break;
		default:
			error= "Error indefinido\n" + e.getErrorCode()+"\n"+ e.getMessage() ;
			break;
		}
		JOptionPane.showMessageDialog(null,error,"Error", 1);
	}
}
