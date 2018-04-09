package dongyv.xch.client;

import dongyv.xch.util.Pub;

public class Client {
	private static ClientHandle clientHandle;
	public static void start() {
		start(Pub.DEFAULT_HOST,Pub.DEFAULT_PORT);
	}
	private static synchronized void start(String host,int port) {
		if(clientHandle !=null) {
			clientHandle.stop();
		}
		clientHandle = new ClientHandle(host,port);
		new Thread(clientHandle,"Server").start();
	}
	
	public static String sendMsg(String msg) throws Exception{
		start();
		Thread.sleep(1000);
		clientHandle.sendMsg(msg);
		return "";
	}
	
	public static void main(String args[]) throws Exception {
		start();
		Thread.sleep(1000);
		sendMsg("夏东宇");
	}
}
