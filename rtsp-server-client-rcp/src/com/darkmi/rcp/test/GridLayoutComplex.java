package com.darkmi.rcp.test;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

public class GridLayoutComplex {
	public static void main(String[] args) {
		Display display = new Display();
		Shell shell = new Shell(display);
		GridLayout layout = new GridLayout();
		layout.numColumns = 3;
		layout.makeColumnsEqualWidth = true;
		shell.setLayout(layout);

		// Create the big button in the upper left
		GridData data = new GridData(GridData.FILL_BOTH);
		data.widthHint = 200;
		Button one = new Button(shell, SWT.PUSH);
		one.setText("one");
		one.setLayoutData(data);

		// Create a composite to hold the three buttons in the upper right
		Composite composite = new Composite(shell, SWT.NONE);
		data = new GridData(GridData.FILL_BOTH);
		data.horizontalSpan = 2;
		composite.setLayoutData(data);
		layout = new GridLayout();
		layout.numColumns = 1;
		layout.marginHeight = 15;
		composite.setLayout(layout);

		// Create button "two"
		data = new GridData(GridData.FILL_BOTH);
		Button two = new Button(composite, SWT.PUSH);
		two.setText("two");
		two.setLayoutData(data);

		// Create button "three"
		data = new GridData(GridData.HORIZONTAL_ALIGN_CENTER);
		Button three = new Button(composite, SWT.PUSH);
		three.setText("three");
		three.setLayoutData(data);

		// Create button "four"
		data = new GridData(GridData.HORIZONTAL_ALIGN_BEGINNING);
		Button four = new Button(composite, SWT.PUSH);
		four.setText("four");
		four.setLayoutData(data);

		// Create the long button across the bottom
		data = new GridData();
		data.horizontalAlignment = GridData.FILL;
		data.grabExcessHorizontalSpace = true;
		data.horizontalSpan = 3;
		data.heightHint = 150;
		Button five = new Button(shell, SWT.PUSH);
		five.setText("five");
		five.setLayoutData(data);

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
