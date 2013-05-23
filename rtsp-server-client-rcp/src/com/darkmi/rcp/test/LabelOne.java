package com.darkmi.rcp.test;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;

public class LabelOne {
	public static void main(String[] args) {
		//display是SWT与OS交互的通道
		Display display = new Display();
		//shell代表主窗口
		Shell shell = new Shell(display);
		shell.setBackground(new Color(display, 181, 240, 228));
		//使用网格布局
		GridLayout layout = new GridLayout();
		layout.numColumns = 2;
		layout.makeColumnsEqualWidth = true;
		shell.setLayout(layout);
		//标签(1,1) 左对齐
		GridData data = new GridData(GridData.FILL_BOTH);
		Label lblOne = new Label(shell, SWT.PUSH);
		lblOne.setText("青龙");
		lblOne.setAlignment(SWT.LEFT);
		lblOne.setBounds(0, 0, 45, 100);
		lblOne.setLayoutData(data);
		//标签(1,2)右对齐
		data = new GridData(GridData.FILL_BOTH);
		Label lblTwo = new Label(shell, SWT.PUSH);
		lblTwo.setText("白虎");
		lblTwo.setAlignment(SWT.RIGHT);
		lblTwo.setLayoutData(data);
		//标签(2,1)中间对齐
		data = new GridData(GridData.FILL_BOTH);
		Label lblThree = new Label(shell, SWT.PUSH);
		lblThree.setText("朱雀");
		lblThree.setAlignment(SWT.CENTER);
		lblThree.setLayoutData(data);
		//标签(2,2)
		data = new GridData(GridData.FILL_BOTH);
		Label lblFour = new Label(shell, SWT.PUSH);
		lblFour.setText("玄武");
		lblFour.setAlignment(SWT.LEFT_TO_RIGHT);
		lblFour.setLayoutData(data);
		shell.pack();
		shell.open();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
		display.dispose();
	}
}
