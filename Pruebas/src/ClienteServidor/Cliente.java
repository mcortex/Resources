package ClienteServidor;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;


public class Cliente {
	
	private Socket cliente;
	
	public Cliente(int puerto, String ip) {
		try {
			cliente = new Socket(ip, puerto);
			new ClienteHilo(cliente).start();			

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void escribe() throws IOException, NullPointerException {
		// OBtengo IP para saber quien es el que escribe
		InetAddress address = InetAddress.getLocalHost();
		String ip = address.getHostAddress();
		
		// Reader para el input
		InputStreamReader leer = new InputStreamReader(System.in);
		BufferedReader buffer = new BufferedReader(leer);
		
		// Prompt
		System.out.print(">: ");
		String texto = buffer.readLine();
		
		// Escribir
		while(!texto.equals("Salir")) {
			new DataOutputStream(cliente.getOutputStream()).writeUTF(ip + ": " + texto);
			System.out.print(">: ");
			texto = buffer.readLine();
		}
		cliente.close();
	}

	public static void main(String[] args) {
		try {
			new Cliente(10000, "localhost").escribe();
		} catch (Exception e) {
			System.err.println("Se cerro la conexión");
		}
	} 

}
