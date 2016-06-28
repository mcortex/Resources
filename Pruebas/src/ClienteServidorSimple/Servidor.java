package ClienteServidorSimple;

import java.net.ServerSocket;
import java.net.Socket;
import java.io.DataOutputStream;

public class Servidor {
	
	public Servidor(int puerto){
		try {
			ServerSocket socket = new ServerSocket(puerto); // Define el socket
			System.out.println("Servidor iniciado.");
			System.out.println("Quedo escuchando conexiones porque soy un servidor boton.");
			for (int i = 0; i < 10; i++) {
				Socket conexion = socket.accept(); // Acepta el pedido de conexion
				//Defino el flujo de datos
				DataOutputStream flujoTexto = new DataOutputStream(conexion.getOutputStream()); 
				flujoTexto.writeUTF("HolaCliente"+(i+1)); // Datos a mostrar en el cliente
				conexion.close(); // cierro la conexion
				if (i==9) {
					System.out.println("Se conectaron 10 clientes cierro el servidor.");
				}
			}
		} catch (Exception e) {
			System.err.println("Ocurrio un error con el servidor.");
		}
	}
	
	public static void main(String[] args) {
		new Servidor(5000);
	}

}
