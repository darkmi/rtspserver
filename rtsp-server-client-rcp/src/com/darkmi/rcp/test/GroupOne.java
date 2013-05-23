package com.darkmi.rcp.test;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Shell;

public class GroupOne {
	public static void main(String[] args) {
		Display display = new Display();
		Shell shell = new Shell(display);
		shell.setLayout(new FillLayout());
		//添加分组框
		Group group1 = new Group(shell, SWT.SHADOW_IN);
		//设置分组框的显示标题
		group1.setText("Who's your favorite?");
		group1.setLayout(new RowLayout(SWT.VERTICAL));
		new Button(group1, SWT.RADIO).setText("John");
		new Button(group1, SWT.RADIO).setText("Paul");
		new Button(group1, SWT.RADIO).setText("George");
		new Button(group1, SWT.RADIO).setText("Ringo");
		
		Group group2 = new Group(shell, SWT.NO_RADIO_GROUP);
		group2.setText("Who's your favorite?");
		group2.setLayout(new RowLayout(SWT.VERTICAL));
		new Button(group2, SWT.CHECK).setText("Barry");
		new Button(group2, SWT.CHECK).setText("Robin");
		new Button(group2, SWT.CHECK).setText("Maurice");
		
		shell.setSize(300, 200);
		shell.open();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
		display.dispose();
	}
}
