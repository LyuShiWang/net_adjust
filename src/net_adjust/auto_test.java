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
			proc1 = rn.exec("C:\\��ɵƽ�����\\Cosawin.exe");
			// proc2 = rn.exec("C:\\AutoClick.exe");
		} catch (Exception e) {
			System.out.println("Error exec!");
		}
		
		Robot robot=new Robot();
        robot.setAutoDelay(100);//����Robot����һ�������������ʱ��,����ִ�й���
		
		robot.keyPress(KeyEvent.VK_CONTROL);
		robot.keyPress(KeyEvent.VK_O);
		robot.keyRelease(KeyEvent.VK_CONTROL);
		robot.keyRelease(KeyEvent.VK_O);// �򿪴��ļ�����

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
		robot.keyRelease(KeyEvent.VK_ENTER);// �س�����ʱ.in2�ļ��Ѿ�����
		
		
	}
	
	public static void input_ProjectName(Robot robot,String name) {
		char[] alphas = name.toCharArray();
		for (char item : alphas) {
			int value = (int) item;

			if (value > 96 && value < 123) {// ��Сд��ĸ�Ļ�
				value = value - 32;
				System.out.println("�ַ�Ϊ��" + item + "����KeyCode��Ϊ��" + value);
				robot.keyPress(value);
				robot.keyRelease(value);// ���빤����
				continue;
			}
			if (value > 64 && value < 91) { // �Ǵ�д��ĸ�Ļ�
				System.out.println("�ַ�Ϊ��" + item + "����KeyCode��Ϊ��" + value);
				robot.keyPress(KeyEvent.VK_SHIFT);
				robot.keyPress(value);
				robot.keyRelease(value);// ���빤����
				robot.keyRelease(KeyEvent.VK_SHIFT);
				continue;
			}
			if (value > 47 && value < 58) { // �����ֵĻ�
				System.out.println("�ַ�Ϊ��" + item + "����KeyCode��Ϊ��" + value);
				robot.keyPress(value);
				robot.keyRelease(value);
				continue;
			}
			System.out.println("������������������������ɴ�Сд��ĸ���������");
		}
	}

}
