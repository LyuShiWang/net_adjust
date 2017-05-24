package net_adjust;

import java.awt.Robot;
import java.awt.event.KeyEvent;

public class MKEvent {
	private Robot robot;
	
	public MKEvent(Robot robot1) {
		robot=robot1;
	}

	public void open_in2(String name) {
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

		input_ProjectName(name);
		
		 robot.keyPress(KeyEvent.VK_BACK_SLASH);
		 robot.keyRelease(KeyEvent.VK_BACK_SLASH);// \
		
		input_ProjectName(name);
		
		 robot.keyPress(KeyEvent.VK_PERIOD);
		 robot.keyRelease(KeyEvent.VK_PERIOD);// .
		 robot.keyPress(KeyEvent.VK_I);
		 robot.keyRelease(KeyEvent.VK_I);// i
		 robot.keyPress(KeyEvent.VK_N);
		 robot.keyRelease(KeyEvent.VK_N);// n
		 robot.keyPress(KeyEvent.VK_2);
		 robot.keyRelease(KeyEvent.VK_2);// 2
		 
		 robot.keyPress(KeyEvent.VK_ENTER);
		 robot.keyRelease(KeyEvent.VK_ENTER);// 回车
	}
	
	public void input_ProjectName(String name){
		char[] alphas = name.toCharArray();
		for (char item : alphas) {
			int value = (int) item;
			
			if (value > 96 && value < 123) {// 是小写字母的话
				value = value - 32;
				System.out.println("字符为：" + item + "。其ACSII码为：" + value);
				robot.keyPress(value);
				robot.keyRelease(value);// 输入工程名
				continue;
			}
			if (value > 64 && value < 91) { // 是大写字母的话
				System.out.println("字符为：" + item + "。其ACSII码为：" + value);
				robot.keyPress(KeyEvent.VK_SHIFT);
				robot.keyPress(value);
				robot.keyRelease(value);// 输入工程名
				robot.keyRelease(KeyEvent.VK_SHIFT);
				continue;
			}
			if (value > 47 && value < 58) { // 是数字的话
				System.out.println("字符为：" + item + "。其ACSII码为：" + value);
				robot.keyPress(value);
				robot.keyRelease(value);
				continue;
			}
			System.out.println("工程名输入出错！工程名仅能由大小写字母和数字组成");
		}
	}
}
