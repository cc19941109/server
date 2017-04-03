package socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.rmi.UnknownHostException;

import org.omg.CORBA.portable.UnknownException;


public class Server {
	public static void main(String[] args) {
		try {
			//创建一个服务器端socket  指定绑定的端口  监听此端口
			ServerSocket serverSocket = new ServerSocket(8888);
			//调用accept方法开始监听，等待客户端的链接
			System.out.println("服务器即将开始");
			Socket socket= serverSocket.accept();
			//获取输入流，并读取客户端数据
			InputStream iStream =socket.getInputStream();//字节输入流
			InputStreamReader inputStreamReader =new InputStreamReader(iStream);
			BufferedReader bReader =new BufferedReader(inputStreamReader);
			String info =null;
			while((info = bReader.readLine())!=null){
				System.out.println("i am server ,client say:"+info);
			}
			socket.shutdownInput();//close outputstream
			
			//get outputstream
			OutputStream oStream =socket.getOutputStream();
			PrintWriter pWriter=new PrintWriter(oStream);
			pWriter.println("welcome");
			pWriter.flush();
			
			//close 资源
			pWriter.close();
			oStream.close();
			bReader.close();
			iStream.close();
			inputStreamReader.close();
		
			socket.close();
			serverSocket.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
