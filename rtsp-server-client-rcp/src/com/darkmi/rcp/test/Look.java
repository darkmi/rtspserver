package com.darkmi.rcp.test;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.*;
import org.eclipse.swt.events.*;
import org.eclipse.swt.graphics.*;
import org.eclipse.swt.layout.*;
import org.eclipse.swt.widgets.*;

/**
 * This class demonstrates ViewForm
 */
public class Look {
	// Images used in the ViewForm
	private Image lookImage;
	private Image menuImage;

	// Counter for titles of ViewForms
	private int count = 0;

	/**
	 * Runs the application
	 */
	public void run() {
		Display display = new Display();
		Shell shell = new Shell(display);
		shell.setText("Look");

		// Load the images
		lookImage = new Image(display, this.getClass().getResourceAsStream("/images/look.gif"));
		menuImage = new Image(display, this.getClass().getResourceAsStream("/images/down.gif"));

		createContents(shell);
		shell.open();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
		// You created the images, so you must dispose
		if (lookImage != null)
			lookImage.dispose();
		if (menuImage != null)
			menuImage.dispose();
		display.dispose();
	}

	/**
	 * Creates the main window's contents
	 *
	 * @param parent the main window
	 */
	public void createContents(Composite parent) {
		parent.setLayout(new GridLayout(1, false));

		// Clicking the New Document button will create a new ViewForm
		Button button = new Button(parent, SWT.PUSH);
		button.setText("New Document");

		// Create the composite that holds the ViewForms
		final Composite composite = new Composite(parent, SWT.NONE);
		composite.setLayoutData(new GridData(GridData.FILL_BOTH));
		composite.setLayout(new FillLayout());

		// Add the event handler to create the ViewForms
		button.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent event) {
				createViewFormHelper(composite, "Document " + (++count));
				composite.layout();
			}
		});
	}

	/**
	 * Helper function for creating the ViewForms
	 *
	 * @param parent the parent Composite
	 * @param text the title text
	 */
	private void createViewFormHelper(final Composite parent, String text) {
		// Create the ViewForm
		final ViewForm vf = new ViewForm(parent, SWT.BORDER);

		// Create the CLabel for the top left, which will have an image and text
		CLabel label = new CLabel(vf, SWT.NONE);
		label.setText(text);
		label.setImage(lookImage);
		label.setAlignment(SWT.LEFT);
		vf.setTopLeft(label);
		// Create the downward-pointing arrow to display the menu
		// and set it as the top center
		final ToolBar tbMenu = new ToolBar(vf, SWT.FLAT);
		final ToolItem itemMenu = new ToolItem(tbMenu, SWT.PUSH);
		itemMenu.setImage(menuImage);
		vf.setTopCenter(tbMenu);

		// Create the close button and set it as the top right
		ToolBar tbClose = new ToolBar(vf, SWT.FLAT);
		ToolItem itemClose = new ToolItem(tbClose, SWT.PUSH);
		itemClose.setText("X");
		itemClose.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent event) {
				vf.dispose();
				parent.layout();
			}
		});
		vf.setTopRight(tbClose);

		// Create the content--a multiline text box
		final Text textArea = new Text(vf, SWT.MULTI | SWT.WRAP | SWT.V_SCROLL);
		vf.setContent(textArea);

		// Create the menu to display when the down arrow is pressed
		final Menu menu = new Menu(tbMenu);
		MenuItem clear = new MenuItem(menu, SWT.NONE);
		clear.setText("Clear");
		clear.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent event) {
				textArea.setText("");
			}
		});

		// Add the handler to display the menu
		itemMenu.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent event) {
				// Place the menu right below the toolbar button
				Rectangle rect = itemMenu.getBounds();
				menu.setLocation(tbMenu.toDisplay(rect.x, rect.y + rect.height));
				menu.setVisible(true);
			}
		});
	}

	/**
	 * The application entry point
	 *
	 * @param args the command line arguments
	 */
	public static void main(String[] args) {
		new Look().run();
	}
}
