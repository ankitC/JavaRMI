package util;

public class Config {
	private static int registrySocket= 15440;
	private static int serverSocket = 15441;
	private static int clientSocket = 15442;
	public static int getRegistrySocket() {
		return registrySocket;
	}
	public static void setRegistrySocket(int registrySocket) {
		Config.registrySocket = registrySocket;
	}
	public static int getServerSocket() {
		return serverSocket;
	}
	public static void setServerSocket(int serverSocket) {
		Config.serverSocket = serverSocket;
	}
	public static int getClientSocket() {
		return clientSocket;
	}
	public static void setClientSocket(int clientSocket) {
		Config.clientSocket = clientSocket;
	}
	
	
}
