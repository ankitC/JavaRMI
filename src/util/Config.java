package util;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class Config {
	private static int registryPort= 15440;
	private static int serverPort = 15441;
	private static int clientSocket = 15442;

    private static Map<String, String> properties;

    static {
        properties = new HashMap<String, String>();

        System.out.println("Reading properties file...");

        BufferedReader brn;
        try {
            brn = new BufferedReader(new InputStreamReader(
                    new FileInputStream("properties.txt")));

            String line = null;

            while ((line = brn.readLine()) != null) {
                String[] nc = line.split(":");
                properties.put(nc[0].toLowerCase(), nc[1]);
                System.out.println(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        String registryPort = getProperty("registryPort");
        String serverPort   = getProperty("serverPort");
        String clientSocket = getProperty("clientSocket");

        if (registryPort != null) {
            setRegistryPort(Integer.parseInt(registryPort));
        }

        if (serverPort != null) {
            setServerPort(Integer.parseInt(serverPort));
        }

        if (clientSocket != null) {
            setClientSocket(Integer.parseInt(clientSocket));
        }

        System.out.println(stringify());
    }

    private static String getProperty(String property) {
        if (property == null) return null;
        return properties.get(property.toLowerCase());
    }

	public static int getRegistryPort() {
		return registryPort;
	}
	private static void setRegistryPort(int registrySocket) {
		Config.registryPort = registrySocket;
	}
	public static int getServerPort() {
		return serverPort;
	}
	private static void setServerPort(int serverSocket) {
		Config.serverPort = serverSocket;
	}
	public static int getClientSocket() {
		return clientSocket;
	}
	private static void setClientSocket(int clientSocket) {
		Config.clientSocket = clientSocket;
	}

    public static String stringify() {
        return "Config{" +
                "registryPort=" + registryPort +
                ", serverPort=" + serverPort +
                ", clientSocket=" + clientSocket +
                '}';
    }
}
