package com.darkmi.rcp.test;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseListener;
import org.eclipse.swt.events.MouseMoveListener;
import org.eclipse.swt.events.MouseTrackListener;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;

/**
 * This class demonstrates mouse events
 */
public class MouseEventExample implements MouseListener, MouseMoveListener, MouseTrackListener {

	// The label to hold the messages from mouse events
	Label myLabel = null;

	/**
	 * MouseEventExample constructor
	 *
	 * @param shell the shell
	 */
	public MouseEventExample(Shell shell) {
		myLabel = new Label(shell, SWT.BORDER);
		myLabel.setText("I ain't afraid of any old mouse");
		shell.addMouseListener(this);
		shell.addMouseMoveListener(this);
		shell.addMouseTrackListener(this);
	}

	/**
	 * The application entry point
	 *
	 * @param args the command line arguments
	 */
	public static void main(String[] args) {
		// Create the window
		Display display = new Display();
		Shell shell = new Shell(display);
		shell.setLayout(new GridLayout());
		shell.setSize(450, 200);
		shell.setText("Mouse Event Example");

		// Create the listener
		@SuppressWarnings("unused")
		MouseEventExample myMouseEventExample = new MouseEventExample(shell);

		// Display the window
		shell.open();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
		display.dispose();
	}

	/**
	 * Called when user double-clicks the mouse
	 */
	public void mouseDoubleClick(MouseEvent e) {
		myLabel.setText("Double Click " + e.button + " at: " + e.x + "," + e.y);
	}

	/**
	 * Called when user clicks the mouse
	 */
	public void mouseDown(MouseEvent e) {
		myLabel.setText("Button " + e.button + " Down at: " + e.x + "," + e.y);
	}

	/**
	 * Called when user releases the mouse after clicking
	 */
	public void mouseUp(MouseEvent e) {
		myLabel.setText("Button " + e.button + " Up at: " + e.x + "," + e.y);
	}

	/**
	 * Called when user moves the mouse
	 */
	public void mouseMove(MouseEvent e) {
		myLabel.setText("Mouse Move at: " + e.x + "," + e.y);
	}

	/**
	 * Called when user enters the shell with the mouse
	 */
	public void mouseEnter(MouseEvent e) {
		myLabel.setText("Mouse Enter at: " + e.x + "," + e.y);
	}

	/**
	 * Called when user exits the shell with the mouse
	 */
	public void mouseExit(MouseEvent e) {
		myLabel.setText("Mouse Exit at: " + e.x + "," + e.y);
	}

	/**
	 * Called when user hovers the mouse
	 */
	public void mouseHover(MouseEvent e) {
		myLabel.setText("Mouse Hover at: " + e.x + "," + e.y);
	}
}
