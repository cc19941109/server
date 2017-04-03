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
			//����һ����������socket  ָ���󶨵Ķ˿�  �����˶˿�
			ServerSocket serverSocket = new ServerSocket(8888);
			//����accept������ʼ�������ȴ��ͻ��˵�����
			System.out.println("������������ʼ");
			Socket socket= serverSocket.accept();
			//��ȡ������������ȡ�ͻ�������
			InputStream iStream =socket.getInputStream();//�ֽ�������
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
			
			//close ��Դ
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
