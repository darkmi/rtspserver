package com.darkmi.rcp.test;

import org.eclipse.swt.*;
import org.eclipse.swt.custom.*;
import org.eclipse.swt.events.*;
import org.eclipse.swt.graphics.*;
import org.eclipse.swt.layout.*;
import org.eclipse.swt.widgets.*;

/**
 * This class demonstrates CTabFolder
 */
public class ShowCTabFolder {
	// Because CTabFolder doesn't have a getInsertMark() method,
	// store the value so you can keep track of it
	private int insertMark = -1;
	private CTabFolder tabFolder;

	/**
	 * Runs the application
	 */
	public void run() {
		Display display = new Display();
		Shell shell = new Shell(display);
		shell.setText("Show CTabFolder");
		createContents(shell);
		shell.open();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
		display.dispose();
	}

	/**
	 * Creates the window's contents
	 *
	 * @param shell the parent shell
	 */
	@SuppressWarnings("deprecation")
	private void createContents(Shell shell) {
		shell.setLayout(new GridLayout(1, true));

		// Create the buttons to create tabs
		Composite composite = new Composite(shell, SWT.NONE);
		composite.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		composite.setLayout(new RowLayout());
		createButtons(composite);

		// Create the tabs
		tabFolder = new CTabFolder(shell, SWT.TOP);
		tabFolder.setBorderVisible(true);
		tabFolder.setLayoutData(new GridData(GridData.FILL_BOTH));
		Display display = shell.getDisplay();

		// Set up a gradient background for the selected tab
		tabFolder.setSelectionBackground(
				new Color[] { display.getSystemColor(SWT.COLOR_WIDGET_DARK_SHADOW),
						display.getSystemColor(SWT.COLOR_WIDGET_NORMAL_SHADOW),
						display.getSystemColor(SWT.COLOR_WIDGET_LIGHT_SHADOW) }, new int[] { 50, 100 });
		// Add a listener to get the close button on each tab
		tabFolder.addCTabFolderListener(new CTabFolderAdapter() {
			public void itemClosed(CTabFolderEvent event) {
			}
		});
	}

	/**
	 * Creates the buttons for moving the insert mark and adding a tab
	 *
	 * @param composite the parent composite
	 */
	private void createButtons(Composite composite) {
		// Move mark left
		Button button = new Button(composite, SWT.PUSH);
		button.setText("<<");
		button.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent event) {
				if (insertMark > -1) {
					--insertMark;
					resetInsertMark();
				}
			}
		});

		// Move mark right
		button = new Button(composite, SWT.PUSH);
		button.setText(">>");
		button.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent event) {
				if (insertMark < tabFolder.getItemCount()) {
					++insertMark;
					resetInsertMark();
				}
			}
		});

		// Add a tab
		button = new Button(composite, SWT.PUSH);
		button.setText("Add Tab");
		button.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent event) {
				new CTabItem(tabFolder, SWT.NONE, insertMark + 1).setText("Tab (" + (insertMark + 1) + ")");
			}
		});
	}

	/**
	 * Moves the insert mark to the new location
	 */
	private void resetInsertMark() {
		tabFolder.setInsertMark(insertMark, true);

		// Workaround for bug #32846
		if (insertMark == -1) {
			tabFolder.redraw();
		}
	}

	/**
	 * The application entry point
	 *
	 * @param args the command line arguments
	 */
	public static void main(String[] args) {
		new ShowCTabFolder().run();
	}
}
