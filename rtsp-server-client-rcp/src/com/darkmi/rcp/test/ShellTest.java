package com.darkmi.rcp.test;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

public class ShellTest extends Shell {
	private Text text;
	private Text text_1;

	public static void main(String args[]) {
		try {
			Display display = Display.getDefault();
			ShellTest shell = new ShellTest(display);
			shell.open();
			shell.layout();
			while (!shell.isDisposed()) {
				if (!display.readAndDispatch()) {
					display.sleep();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the shell.
	 * 
	 * @param display
	 */
	public ShellTest(Display display) {
		super(display, SWT.SHELL_TRIM);

		Label label = new Label(this, SWT.NONE);
		label.setBounds(10, 24, 54, 12);
		label.setText("用户名");

		text = new Text(this, SWT.BORDER);
		text.setBounds(70, 21, 70, 18);

		Label lblNewLabel = new Label(this, SWT.NONE);
		lblNewLabel.setBounds(10, 58, 54, 12);
		lblNewLabel.setText("密码");

		text_1 = new Text(this, SWT.BORDER);
		text_1.setBounds(70, 52, 70, 18);

		Button button = new Button(this, SWT.NONE);
		button.setBounds(8, 76, 72, 22);
		button.setText("登陆");

		Button button_1 = new Button(this, SWT.NONE);
		button_1.setBounds(98, 76, 72, 22);
		button_1.setText("取消");
		createContents();
	}

	/**
	 * Create contents of the shell.
	 */
	protected void createContents() {
		setText("SWT Application");
		setSize(200, 140);

	}

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}
}
