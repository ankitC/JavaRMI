package util;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

/* Config variables for the system */
public class Config {
	private static int registryPort= 15440;
	private static int serverPort = 15441;
	private static int clientPort = 15442;

    private static String registryIPAddress = "localhost";

    private static Map<String, String> properties;

    static {
        properties = new HashMap<String, String>();

        System.out.println("Reading properties file...");

        BufferedReader brn;
        try {
            brn = new BufferedReader(new InputStreamReader(
                    new FileInputStream("properties.txt")));

            String line;

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
        String clientPort = getProperty("clientPort");
        String registryIPAddress = getProperty("registryIPAddress");

        if (registryPort != null) {
            setRegistryPort(Integer.parseInt(registryPort));
        }

        if (serverPort != null) {
            setServerPort(Integer.parseInt(serverPort));
        }

        if (clientPort != null) {
            setClientPort(Integer.parseInt(clientPort));
        }

        if (registryIPAddress != null) {
            setRegistryIPAddress(registryIPAddress);
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
	public static int getClientPort() {
		return clientPort;
	}
	private static void setClientPort(int clientPort) {
		Config.clientPort = clientPort;
	}
    public static String getRegistryIPAddress() {
        return registryIPAddress;
    }
    private static void setRegistryIPAddress(String registryIPAddress) {
        Config.registryIPAddress = registryIPAddress;
    }

    public static String stringify() {
        return "Config{" +
                "registryPort=" + registryPort +
                ", serverPort=" + serverPort +
                ", clientPort=" + clientPort +
                ", registryIPAddress=" + registryIPAddress +
                '}';
    }
}
