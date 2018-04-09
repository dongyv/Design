package dongyv.xch.client;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

public class ClientHandle implements Runnable{
	private String host;
	private int port;
	private Selector selector;
	private SocketChannel socketChannel;
	private volatile boolean started;
	
	public ClientHandle(String host,int port) {
		this.host = host;
		this.port = port;
		try {
			selector = Selector.open();
			socketChannel = SocketChannel.open();
			socketChannel.configureBlocking(false);
			started = true;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.exit(1);
		}
	}
	
	public void stop() {
		started = false;
	}
	
	@Override
	public void run() {
		try {
			doConnect();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		// TODO Auto-generated method stub
		while(started) {
			try {
				selector.select(1000);
				Set<SelectionKey> keys = selector.selectedKeys();
				Iterator<SelectionKey> it = keys.iterator();
				SelectionKey key = null;
				while(it.hasNext()) {
					key = it.next();
					it.remove();
					handleInput(key);
					
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}//无论是否有读写事件发生，selector每隔1s被唤醒一次
		}
	}
	
	private static void handleInput(SelectionKey key) throws IOException {
		if(key.isValid()) {
			SocketChannel sc = (SocketChannel)key.channel();
			if(key.isConnectable()) {
				if(sc.finishConnect());
				else System.exit(1);
			}
			if(key.isReadable()) {
				ByteBuffer buffer = ByteBuffer.allocate(1024);
				int readBytes = sc.read(buffer);//返回从服务器读取到的字节
				if(readBytes>0) {
					buffer.flip();
					byte[] bytes = new byte[buffer.remaining()];
					buffer.get(bytes);
					String result = new String(bytes,"UTF-8");
					System.out.println("客户端收到消息：" + result);
				}else if(readBytes<0){
					key.cancel();
					sc.close();
				}
			}
		}
	}
	
	private void doConnect() throws IOException {
		if(socketChannel.connect(new InetSocketAddress(host, port)));
		else
			socketChannel.register(selector, SelectionKey.OP_CONNECT);
	}
	
	public void sendMsg(String msg) throws Exception{
		socketChannel.register(selector,SelectionKey.OP_READ);
		doWriter(socketChannel,msg);
	}
	
	private void doWriter(SocketChannel channel,String request) throws IOException {
		byte[] bytes = request.getBytes();
		//根据数组容量创建ByteBuffer
		ByteBuffer writeBuffer = ByteBuffer.allocate(bytes.length);
		//将字节数组复制到缓冲区
		writeBuffer.put(bytes);
		//flip操作
		writeBuffer.flip();
		//发送缓冲区的字节数组
		channel.write(writeBuffer);
	}
}
