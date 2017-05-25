package net_adjust;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;

import com.sun.jna.platform.win32.User32;
import com.sun.jna.platform.win32.WinDef.HWND;

public class auto_test {

	  
	 
	public static void main(String[] args) throws AWTException {
		// TODO Auto-generated method stub
		Runtime rn = Runtime.getRuntime();
		Process proc1;
		try {
			proc1 = rn.exec("C:\\科傻平差软件\\Cosawin.exe");
			// proc2 = rn.exec("C:\\AutoClick.exe");
		} catch (Exception e) {
			System.out.println("Error exec!");
		}
		
		Robot robot=new Robot();
        robot.setAutoDelay(100);//设置Robot产生一个动作后的休眠时间,否则执行过快
		
		robot.keyPress(KeyEvent.VK_CONTROL);
		robot.keyPress(KeyEvent.VK_O);
		robot.keyRelease(KeyEvent.VK_CONTROL);
		robot.keyRelease(KeyEvent.VK_O);// 打开打开文件窗口

		robot.keyPress(KeyEvent.VK_SHIFT);
		robot.keyPress(KeyEvent.VK_C);
		robot.keyRelease(KeyEvent.VK_C);
		robot.keyRelease(KeyEvent.VK_SHIFT);// C

		robot.keyPress(KeyEvent.VK_SHIFT);
		robot.keyPress(KeyEvent.VK_SEMICOLON);
		robot.keyRelease(KeyEvent.VK_SEMICOLON);
		robot.keyRelease(KeyEvent.VK_SHIFT);// :

		robot.keyPress(KeyEvent.VK_BACK_SLASH);
		robot.keyRelease(KeyEvent.VK_BACK_SLASH);// \

		input_ProjectName(robot,"trytry");

		robot.keyPress(KeyEvent.VK_BACK_SLASH);
		robot.keyRelease(KeyEvent.VK_BACK_SLASH);// \

		input_ProjectName(robot,"trytry");

		robot.keyPress(KeyEvent.VK_PERIOD);
		robot.keyRelease(KeyEvent.VK_PERIOD);// .
		robot.keyPress(KeyEvent.VK_I);
		robot.keyRelease(KeyEvent.VK_I);// i
		robot.keyPress(KeyEvent.VK_N);
		robot.keyRelease(KeyEvent.VK_N);// n
		robot.keyPress(KeyEvent.VK_2);
		robot.keyRelease(KeyEvent.VK_2);// 2

		robot.keyPress(KeyEvent.VK_ENTER);
		robot.keyRelease(KeyEvent.VK_ENTER);// 回车，此时.in2文件已经打开了
		
		
	}
	
	public static void input_ProjectName(Robot robot,String name) {
		char[] alphas = name.toCharArray();
		for (char item : alphas) {
			int value = (int) item;

			if (value > 96 && value < 123) {// 是小写字母的话
				value = value - 32;
				System.out.println("字符为：" + item + "。其KeyCode码为：" + value);
				robot.keyPress(value);
				robot.keyRelease(value);// 输入工程名
				continue;
			}
			if (value > 64 && value < 91) { // 是大写字母的话
				System.out.println("字符为：" + item + "。其KeyCode码为：" + value);
				robot.keyPress(KeyEvent.VK_SHIFT);
				robot.keyPress(value);
				robot.keyRelease(value);// 输入工程名
				robot.keyRelease(KeyEvent.VK_SHIFT);
				continue;
			}
			if (value > 47 && value < 58) { // 是数字的话
				System.out.println("字符为：" + item + "。其KeyCode码为：" + value);
				robot.keyPress(value);
				robot.keyRelease(value);
				continue;
			}
			System.out.println("工程名输入出错！工程名仅能由大小写字母和数字组成");
		}
	}

}
