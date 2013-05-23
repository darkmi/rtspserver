package com.darkmi.rcp.test;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.DisposeEvent;
import org.eclipse.swt.events.DisposeListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;

/**
 * This class demonstrates SelectionListener and DisposeListener
 */
public class DisposeListenerExample {
	/**
	 * The application entry point
	 *
	 * @param args the command line arguments
	 */
	public static void main(String[] args) {
		Display display = new Display();

		// Create the main window
		Shell mainShell = new Shell(display);
		mainShell.setLayout(new FillLayout());
		mainShell.setText("Big Brother");
		final Label mainMessage = new Label(mainShell, SWT.LEFT);
		mainMessage.setText("Don't even think about it");

		// Create the child shell and the dispose listener
		final Shell childShell = new Shell(mainShell);
		childShell.addDisposeListener(new DisposeListener() {
			public void widgetDisposed(DisposeEvent event) {
				// When the child shell is disposed, change the message on the main shell
				mainMessage.setText("Gotcha");
			}
		});
		childShell.setLayout(new FillLayout());
		childShell.setText("little brother");

		// Put a message on the child shell
		new Label(childShell, SWT.LEFT).setText("If you dispose me, my big brother's gonna get you!");
		// Add a button and a listener to the child shell
		Button button = new Button(childShell, SWT.PUSH);
		button.setText("Close Me!");
		button.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent event) {
				// When the button is clicked, close the child shell
				childShell.close();
			}
		});

		// Open the shells
		mainShell.open();
		childShell.open();

		while (!mainShell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
		display.dispose();
	}
}
