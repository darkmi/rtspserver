package com.darkmi.rcp.test;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;

public class GroupExamples {

	Display display = new Display();
	Shell shell = new Shell(display);

	public GroupExamples() {

		Group group0 = new Group(shell, SWT.NULL);
		group0.setLayout(new FillLayout());
		Label label = new Label(group0, SWT.NULL);
		label.setAlignment(SWT.CENTER);
		label.setText("a group without title.");

		Group group1 = new Group(shell, SWT.NULL);
		group1.setText("SWT.NULL");

		Group group2 = new Group(shell, SWT.SHADOW_ETCHED_IN);
		group2.setText("SWT.SHADOW_ETCHED_IN");
		Group group3 = new Group(shell, SWT.SHADOW_ETCHED_OUT);
		group3.setText("SWT.SHADOW_ETCHED_OUT");

		Group group4 = new Group(shell, SWT.SHADOW_IN);
		group4.setText("SWT.SHADOW_IN");

		Group group5 = new Group(shell, SWT.SHADOW_OUT);
		group5.setText("SWT.SHADOW_OUT");

		Group[] groups = new Group[] { group0, group1, group2, group3, group4, group5 };

		for (int i = 0; i < groups.length; i++) {
			groups[i].setBounds(10, 10 + i * 50, 300, 40);
		}
		shell.pack();
		shell.open();

		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
		display.dispose();
	}

	public static void main(String[] args) {
		new GroupExamples();
	}
}
