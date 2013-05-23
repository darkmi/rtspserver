package com.darkmi.rcp.test;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;

public class TestMain {
	private Display display;
	protected Shell shell;
	private Label label;
	private TestThread thread;

	public static void main(String[] args) {
		try {
			TestMain window = new TestMain();
			window.open();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	   * Open the window
	   */
	public void open() {
		display = Display.getDefault();
		createContents();
		shell.open();
		shell.layout();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch())
				display.sleep();
		}
	}

	/**
	   * Create contents of the window
	   */
	protected void createContents() {
		thread = new TestThread(this);
		shell = new Shell();
		shell.setSize(390, 187);
		shell.setText("SWT Application");

		label = new Label(shell, SWT.NONE);
		label.setText("Label");
		label.setBounds(139, 52, 79, 13);

		final Button button = new Button(shell, SWT.NONE);
		button.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				display.asyncExec(new Runnable() {
					public void run() {
						new Thread() {
							public void run() {
								thread.process();
							}
						}.start();
					}

				});

			}
		});
		button.setText("button");
		button.setBounds(117, 97, 129, 23);
		//

	}

	public void setLabel(String str) {
		label.setText(str);
	}

}
