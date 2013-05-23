package com.darkmi;

import java.awt.Dimension;
import java.awt.Toolkit;

public class AA {

	public AA() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Dimension screen=Toolkit.getDefaultToolkit().getScreenSize();//得到屏幕的大小   
		System.out.println (screen.getWidth());//输出屏幕的宽度   
		System.out.println (screen.getHeight());//输出屏幕的高度 
		
		int ab = 1;
		switch (ab) {
		case 1:
			System.out.println(1);
			break;
		case 2:
			System.out.println(2);
			break;
		default:
			break;
		}
		
		int [] sess = new int[]{1,2,3,4,5};
		int[] aa = repair(sess,10);
		System.out.println(aa);
	}
	
	public static int[] repair(int[] sess,int count){
		int[] fresh = new int[count];
		int[] list = new int[count - sess.length];
		for(int i=0;i<list.length;i++){
			list[i] = 9;
		}
		
		System.arraycopy(sess, 0, fresh, 0, sess.length);
		System.arraycopy(list, 0, fresh, sess.length, list.length);
		
		return fresh;
	}

}
