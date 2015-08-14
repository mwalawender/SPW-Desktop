package pl.chat;

import java.rmi.*;
import java.rmi.server.*;

public class StartServer {
	public static void main(String[] args) {
		try {
			// System.setSecurityManager(new RMISecurityManager());
			java.rmi.registry.LocateRegistry.createRegistry(1099);

			ChatServerInt b = new ChatServer();
			// adres
			Naming.rebind("rmi://192.168.1.5/myabc", b);
			System.out.println("[System] Chat Server is ready.");
		} catch (Exception e) {
			System.out.println("Chat Server failed: " + e);
		}
	}
}
