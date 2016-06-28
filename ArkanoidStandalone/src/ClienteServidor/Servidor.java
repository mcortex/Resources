package ClienteServidor;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;


public class Servidor {
	
	private ArrayList<Socket> listaSocketClientes;
	
	public Servidor(int puerto) {
		
		int cantClientes = 0;
		listaSocketClientes = new ArrayList<Socket>();
		
		try {
			// Inicializo el socket del servidor
			ServerSocket servidor = new ServerSocket(puerto);
			System.out.println("Servidor en Línea...");
			
			// Conecto hasta 100 clientes
			while(cantClientes < 3) {
				// Inicializo la conexion con un cliente
				Socket cliente = servidor.accept();
				// Lo agrego a la lista
				listaSocketClientes.add(cliente);
				
				// Inicializo el hilo entre servidor y cliente
				new ServidorHilo(cliente, listaSocketClientes).start();
				cantClientes++;
			}
			
			servidor.close();
			System.out.println("El Servidor se ha cerrado");
						
		} catch (Exception e) {
			System.err.println("Ocurrió un problema con el Servidor");
		}
	}

	public static void main(String[] args) {
		new Servidor(10000);
	}
}
