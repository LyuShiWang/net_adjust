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
		
		// 1.创建一个服务器端Socket，即ServerSocket，指定绑定的端口，并监听此端口
		ServerSocket serverSocket = new ServerSocket(12345);
		InetAddress address = InetAddress.getLocalHost();
		String ip = address.getHostAddress();
		Socket socket = null;

		// 2.调用accept()等待客户端连接
		System.out.println("服务端已就绪，等待客户端接入，服务端ip地址: " + ip + "。等待接收in2文件");
		// System.out.println("服务端已就绪，等待客户端接入，服务端ip地址: 。等待接收in2文件");
		socket = serverSocket.accept();

		// 3.连接后获取输入流，读取客户端信息
		InputStream is = socket.getInputStream(); // 获取输入流
		InputStreamReader isr = new InputStreamReader(is, "UTF-8");
		BufferedReader br = new BufferedReader(isr);

		name = br.readLine();

		String content = "";
		String info = null;
		while ((info = br.readLine()) != null) {// 循环读取客户端的信息
			System.out.println("客户端发送过来的信息：" + info);
			content = content + info + "\r\n";
		}
		System.out.println("读取客户端发送过来的信息成功");

		// 4.将信息写入到文件中
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
			System.out.println("写入成功");
			bw.close();
		} catch (IOException e1) {
		}

		socket.shutdownInput();// 关闭输入流
		socket.close();
		serverSocket.close();

		// 5.打开exe文件，进行平差
		Runtime rn = Runtime.getRuntime();
		Process proc1, proc2;
		try {
			proc1 = rn.exec("C:\\科傻平差软件\\Cosawin.exe");
			// proc2 = rn.exec("C:\\AutoClick.exe");
			System.out.println("打开成功");
		} catch (Exception e) {
			System.out.println("Error exec!");
		}
		
		try {
			Robot robot = new Robot();
			MKEvent mKEvent=new MKEvent(robot);
            robot.setAutoDelay(200);//设置Robot产生一个动作后的休眠时间,否则执行过快
            mKEvent.open_in2(name);
		} catch (AWTException e) {
			e.printStackTrace();
		}

		// 6.传回结果文件
		ServerSocket serverSocket2 = new ServerSocket(54321);
		Socket socket2 = serverSocket2.accept();
		OutputStream os2 = socket2.getOutputStream();// 字节输出流
		OutputStreamWriter osw2 = new OutputStreamWriter(os2, "UTF-8");
		BufferedWriter bw2 = new BufferedWriter(osw2);
		PrintWriter pw2 = new PrintWriter(bw2, true);// 将输出流包装为打印流
		File file_rt2 = new File("C:/" + name + "/" + name + ".ou2");
		try {
			String line;
			BufferedReader bf2 = new BufferedReader(new FileReader(file_rt2));
			while (((line = bf2.readLine()) != null)) {
				System.out.println("读取内容为" + line);
				pw2.write(line + "\n");
			}
			bf2.close();
		} catch (Exception e) {
		}
		pw2.flush();

		pw2.close();
		socket2.shutdownOutput();// 关闭输出流
		socket2.close();
		serverSocket2.close();
	}
}
