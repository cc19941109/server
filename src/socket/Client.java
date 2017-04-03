package socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.management.ImmutableDescriptor;
import javax.print.attribute.standard.OutputDeviceAssigned;

import org.omg.CORBA.portable.UnknownException;

public class Client {
	public static void main(String[] args) {
		try {		
			//create client  socket   port address
			Socket socket =new Socket("localhost", 8888);//127.0.0.1
			//get outputstream 向服务器发送信息
			OutputStream oStream = socket.getOutputStream();
			PrintWriter pWriter =new PrintWriter(oStream);
			pWriter.write("用户名:admin;密码:123");
			pWriter.flush();
			socket.shutdownInput();//close outputstream
			
			//get inputstream
			InputStream inputStream = socket.getInputStream();
			BufferedReader bReader= new BufferedReader(new InputStreamReader(inputStream));
			String info=null;
			while((info= bReader.readLine())!=null){
				System.out.println(" i am client ,server say"+info);
				
			}
			
			//close
			bReader.close();
			inputStream.close();
			pWriter.close();
			oStream.close();
			socket.close();
			
			
		} catch (Exception e){
			e.printStackTrace();
		}
		
	}
}
