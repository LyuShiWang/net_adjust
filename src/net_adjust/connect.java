package net_adjust;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;

public class connect {

	public static void main(String[] args) throws IOException {
		String name="";
		
		// 1.����һ����������Socket����ServerSocket��ָ���󶨵Ķ˿ڣ��������˶˿�
		ServerSocket serverSocket = new ServerSocket(12345);
		InetAddress address = InetAddress.getLocalHost();
		String ip = address.getHostAddress();
		Socket socket = null;

		// 2.����accept()�ȴ��ͻ�������
		System.out.println("������Ѿ������ȴ��ͻ��˽��룬�����ip��ַ: " + ip + "���ȴ�����in2�ļ�");
		// System.out.println("������Ѿ������ȴ��ͻ��˽��룬�����ip��ַ: ���ȴ�����in2�ļ�");
		socket = serverSocket.accept();

		// 3.���Ӻ��ȡ����������ȡ�ͻ�����Ϣ
		InputStream is = socket.getInputStream(); // ��ȡ������
		InputStreamReader isr = new InputStreamReader(is, "UTF-8");
		BufferedReader br = new BufferedReader(isr);

		name = br.readLine();

		String content = "";
		String info = null;
		while ((info = br.readLine()) != null) {// ѭ����ȡ�ͻ��˵���Ϣ
			System.out.println("�ͻ��˷��͹�������Ϣ��" + info);
			content = content + info + "\r\n";
		}
		System.out.println("��ȡ�ͻ��˷��͹�������Ϣ�ɹ�");

		// 4.����Ϣд�뵽�ļ���
		File file = new File("C:/" + name + "/" + name + ".in2");
		if (!file.exists()) {
			file.getParentFile().mkdirs();
			try {
				file.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		try {
			FileWriter fw = new FileWriter(file.getAbsoluteFile());
			BufferedWriter bw = new BufferedWriter(fw);
			bw.write(content);
			System.out.println("д��ɹ�");
			bw.close();
		} catch (IOException e1) {
		}

		socket.shutdownInput();// �ر�������
		socket.close();
		serverSocket.close();

		// 5.��exe�ļ�������ƽ��
		Runtime rn = Runtime.getRuntime();
		Process proc1, proc2;
		try {
			proc1 = rn.exec("C:\\��ɵƽ�����\\Cosawin.exe");
			// proc2 = rn.exec("C:\\AutoClick.exe");
			System.out.println("�򿪳ɹ�");
		} catch (Exception e) {
			System.out.println("Error exec!");
		}
		
		try {
			Robot robot = new Robot();
			MKEvent mKEvent=new MKEvent(robot);
            robot.setAutoDelay(200);//����Robot����һ�������������ʱ��,����ִ�й���
            mKEvent.open_in2(name);
		} catch (AWTException e) {
			e.printStackTrace();
		}

		// 6.���ؽ���ļ�
		ServerSocket serverSocket2 = new ServerSocket(54321);
		Socket socket2 = serverSocket2.accept();
		OutputStream os2 = socket2.getOutputStream();// �ֽ������
		OutputStreamWriter osw2 = new OutputStreamWriter(os2, "UTF-8");
		BufferedWriter bw2 = new BufferedWriter(osw2);
		PrintWriter pw2 = new PrintWriter(bw2, true);// ���������װΪ��ӡ��
		File file_rt2 = new File("C:/" + name + "/" + name + ".ou2");
		try {
			String line;
			BufferedReader bf2 = new BufferedReader(new FileReader(file_rt2));
			while (((line = bf2.readLine()) != null)) {
				System.out.println("��ȡ����Ϊ" + line);
				pw2.write(line + "\n");
			}
			bf2.close();
		} catch (Exception e) {
		}
		pw2.flush();

		pw2.close();
		socket2.shutdownOutput();// �ر������
		socket2.close();
		serverSocket2.close();
	}
}
