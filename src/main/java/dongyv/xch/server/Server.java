package dongyv.xch.server;

import dongyv.xch.util.Pub;

public class Server {
	private static ServerHandle serverHandle;
	public static void start() {
		start(Pub.DEFAULT_PORT);
	}
	
	public static synchronized void start(int port) {
		if(serverHandle != null) {
			serverHandle.stop();
		}
		serverHandle = new ServerHandle(port);
		new Thread(serverHandle,"server").start();
	}
	
	public static void main(String args[]){
		start();
	}
}
