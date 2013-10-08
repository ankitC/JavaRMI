package util;

public class Config {
	private static int registryPort= 15440;
	private static int serverPort = 15441;
	private static int clientSocket = 15442;
	
	public static int getRegistryPort() {
		return registryPort;
	}
	public static void setRegistryPort(int registrySocket) {
		Config.registryPort = registrySocket;
	}
	public static int getServerPort() {
		return serverPort;
	}
	public static void setServerPort(int serverSocket) {
		Config.serverPort = serverSocket;
	}
	public static int getClientSocket() {
		return clientSocket;
	}
	public static void setClientSocket(int clientSocket) {
		Config.clientSocket = clientSocket;
	}
	
	
}
