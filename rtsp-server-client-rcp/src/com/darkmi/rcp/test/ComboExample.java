package com.darkmi.rcp.test;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

/**
 * This class demonstrates Combo
 */
public class ComboExample {
	// Strings to use as list items
	private static final String[] ITEMS = { "Alpha", "Bravo", "Charlie", "Delta", "Echo", "Foxtrot", "Golf", "Hotel",
			"India", "Juliet", "Kilo", "Lima", "Mike", "November", "Oscar", "Papa", "Quebec", "Romeo", "Sierra",
			"Tango", "Uniform", "Victor", "Whiskey", "X-Ray", "Yankee", "Zulu" };

	public static void main(String[] args) {
		Display display = new Display();
		Shell shell = new Shell(display);
		shell.setLayout(new GridLayout(2, true));

		// Create a dropdown Combo
		Combo combo = new Combo(shell, SWT.DROP_DOWN);
		combo.setItems(ITEMS);

		// Create a read-only Combo
		Combo readOnly = new Combo(shell, SWT.DROP_DOWN | SWT.READ_ONLY);
		readOnly.setItems(ITEMS);

		// Create a "simple" Combo
		Combo simple = new Combo(shell, SWT.SIMPLE);
		simple.setItems(ITEMS);

		shell.open();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
		display.dispose();
	}
}
