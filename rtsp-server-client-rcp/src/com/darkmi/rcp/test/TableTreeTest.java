package com.darkmi.rcp.test;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.*;
import org.eclipse.swt.layout.*;
import org.eclipse.swt.widgets.*;

/**
 * This class demonstrates TableTree
 */
public class TableTreeTest {
	// The number of rows and columns
	private static final int NUM = 3;

	/**
	 * Runs the application
	 */
	public void run() {
		Display display = new Display();
		Shell shell = new Shell(display);
		shell.setText("TableTree Test");
		createContents(shell);
		shell.pack();
		shell.open();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
		display.dispose();
	}

	/**
	 * Creates the main window's contents
	 *
	 * @param shell the main window
	 */
	@SuppressWarnings("deprecation")
	private void createContents(final Shell shell) {
		shell.setLayout(new FillLayout());

		// Create the TableTree and set some attributes on the underlying table
		TableTree tableTree = new TableTree(shell, SWT.NONE);
		Table table = tableTree.getTable();
		table.setHeaderVisible(true);
		table.setLinesVisible(false);
		// Create the columns, passing the underlying table
		for (int i = 0; i < NUM; i++) {
			new TableColumn(table, SWT.LEFT).setText("Column " + (i + 1));
		}

		// Create the data
		for (int i = 0; i < NUM; i++) {
			// Create a parent item and add data to the columns
			TableTreeItem parent = new TableTreeItem(tableTree, SWT.NONE);
			parent.setText(0, "Parent " + (i + 1));
			parent.setText(1, "Data");
			parent.setText(2, "More data");

			// Add children items
			for (int j = 0; j < NUM; j++) {
				// Create a child item and add data to the columns
				TableTreeItem child = new TableTreeItem(parent, SWT.NONE);
				child.setText(0, "Child " + (j + 1));
				child.setText(1, "Some child data");
				child.setText(2, "More child data");
			}
			// Expand the parent item
			parent.setExpanded(true);
		}

		// Pack the columns
		TableColumn[] columns = table.getColumns();
		for (int i = 0, n = columns.length; i < n; i++) {
			columns[i].pack();
		}
	}

	/**
	 * The application entry point
	 *
	 * @param args the command line arguments
	 */
	public static void main(String[] args) {
		new TableTreeTest().run();
	}
}
