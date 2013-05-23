package com.darkmi.rcp.test;

import org.eclipse.swt.widgets.Display;

public class TestThread {

	private TestMain tsmain;
	private Display display;

	public TestThread(TestMain tsmain) {
		this.tsmain = tsmain;
		display = Display.getDefault();
	}

	public void process() {
		///通过按钮button来设置GUI中的setLabel的值
		//tsmain.setLabel("hello world");
		setSelection();
	}

	public void setSelection() {
		display.syncExec(new Runnable() {
			public void run() {
				tsmain.setLabel("hello world");
			}
		});
	}
}
