package ClienteServidorSimple;

import java.io.DataInputStream;
import java.net.Socket;

public class Cliente {
	
	public Cliente(int puerto, String ip){
		try {
			Socket conexion = new Socket(ip,puerto); //configuro el socket del cliente
			DataInputStream flujo = new DataInputStream(conexion.getInputStream());
			System.out.println(flujo.readUTF());
			conexion.close(); // Cierro la conexion
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		try {
			new Cliente(5000,"localhost");
		} catch (Exception e) {
			System.err.println("Se cerro la conexion.");
		}
	}
	
}
