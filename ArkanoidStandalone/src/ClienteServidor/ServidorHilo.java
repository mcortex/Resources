package ClienteServidor;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;
import java.util.ArrayList;


public class ServidorHilo extends Thread {
	
	private Socket cliente;
	private ArrayList<Socket> lista;
	
	public ServidorHilo(Socket cliente, ArrayList<Socket> lista) {
		super();
		this.cliente = cliente;
		this.lista = lista;
	}
	
	public void run() {
		
		String texto = "";
		try {
			texto = new DataInputStream(cliente.getInputStream()).readUTF(); // lectura del string
			
			while(!texto.equals("Salir")) {
				
				System.out.println(texto);
				// Por cada cliente 
				for(Socket indice : lista) {
					if(indice != cliente)
						new DataOutputStream(indice.getOutputStream()).writeUTF(texto); // escribo lo que ponen
				}
				texto = new DataInputStream(cliente.getInputStream()).readUTF(); // leo nuevamente
			}
			System.out.println("El cliente se ha desconectado");
		} catch (Exception e) {
			System.out.println("El cliente se ha desconectado");
		}
	}
}
