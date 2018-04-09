package dongyv.xch.server;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

import dongyv.xch.service.GoodsService;

/**
 * NIO服务器
 * @author aaa
 *
 */
public class ServerHandle implements Runnable{
	private Selector selector;
	private ServerSocketChannel serverChannel;
	private volatile boolean started;

	public ServerHandle(int port) {
		try {
			selector = Selector.open();
			serverChannel = ServerSocketChannel.open();
			serverChannel.configureBlocking(false);//开启非阻塞模式
			serverChannel.socket().bind(new InetSocketAddress(port),1024);
			serverChannel.register(selector, SelectionKey.OP_ACCEPT);
			//标记服务器开
			started = true;
			System.out.println("服务器已开，端口号:"+port);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.exit(1);;
		}
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		//遍历selector
		while(started) {
			try {
				selector.select(1000);
				Set<SelectionKey> keys = selector.selectedKeys();
				Iterator<SelectionKey> it = keys.iterator();
				SelectionKey key = null;
				while(it.hasNext()) {
					key = it.next();
					it.remove();//当前标志使用后 从列表删除
					handleInput(key);
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	private void handleInput(SelectionKey key) throws IOException{
		if(key.isValid()) {
			if(key.isAcceptable()) {
				ServerSocketChannel ssc = (ServerSocketChannel)key.channel();//获得服务器selectionkey通道
				SocketChannel sc = ssc.accept();
				sc.configureBlocking(false);
				sc.register(selector, SelectionKey.OP_READ);
			}
			if(key.isReadable()) {
				SocketChannel sc = (SocketChannel)key.channel();//获得客户端通道
				ByteBuffer buffer = ByteBuffer.allocate(1024);
				int readBytes = sc.read(buffer);
				if(readBytes>0) {//读取到字节进行解码
					buffer.flip();//把光标移到第一位
					byte[] bytes = new byte[buffer.remaining()];
					buffer.get(bytes);//将缓冲区的数据复制到新建数据中
					String id = new String(bytes,"utf-8");
					System.out.println("服务器接收到消息为:"+id);
					GoodsService gs = new GoodsService();
					String result = gs.goodById(Integer.parseInt(id));
					
					//转发给服务器
					doWriter(sc,result);
				}else if(readBytes<0){
					key.cancel();
					sc.close();
				}

			}
		}
	}
	
	private void doWriter(SocketChannel channel,String response) throws IOException {
		byte[] bytes = response.getBytes();
		ByteBuffer writerBuffer = ByteBuffer.allocate(bytes.length);
		writerBuffer.put(bytes);//将字节复制到缓冲区
		writerBuffer.flip();
		channel.write(writerBuffer);//发送给客户端
	}
	
	public void stop(){
		started = false;
	}
}
