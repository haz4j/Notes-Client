/*
 * 
 */
package com.dev.client.graphic;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;

/**
 * The Class AddDialog manages add dialog of hew note. Sounds logically.
 */
public class AddDialog extends JDialog implements ActionListener {

	/** The dialog. */
	private static AddDialog dialog;

	/** The value from dialog. */
	private static String value = "";

	/** The initial value. */
	private String initialValue;

	/** Text pane */
	JTextPane jTextPane;

	/**
	 * Show dialog.
	 *
	 * @param frameComp
	 *            Link to main frame. It's needed to place dialog to the center
	 *            of frame.
	 * @param locationComp
	 *            Parent component
	 * @param title
	 *            the title of dialog
	 * @param initialValue
	 *            the initial value of text
	 * @return the string
	 */
	public static String showDialog(Component frameComp, Component locationComp, String title, String initialValue) {
		Frame frame = JOptionPane.getFrameForComponent(frameComp);
		dialog = new AddDialog(frame, locationComp, title, initialValue);
		dialog.setVisible(true);
		return value;
	}

	/**
	 * Instantiates a new add dialog.
	 *
	 * @param frame
	 *            the frame
	 * @param locationComp
	 *            the location comp
	 * @param title
	 *            the title
	 * @param initialValue
	 *            the initial value
	 */
	private AddDialog(Frame frame, Component locationComp, String title, String initialValue) {
		super(frame, title, true);

		this.initialValue = initialValue;

		// Create and initialize the buttons.
		JButton cancelButton = new JButton("Cancel");
		cancelButton.addActionListener(this);
		//
		final JButton setButton = new JButton("Set");
		setButton.setActionCommand("Set");
		setButton.addActionListener(this);
		getRootPane().setDefaultButton(setButton);

		// main part of the dialog

		jTextPane = new JTextPane();
		jTextPane.setText(initialValue);

		JScrollPane textScroller = new JScrollPane(jTextPane);
		textScroller.setPreferredSize(new Dimension(250, 80));
		textScroller.setAlignmentX(LEFT_ALIGNMENT);

		JPanel textPane = new JPanel();
		textPane.setLayout(new BoxLayout(textPane, BoxLayout.PAGE_AXIS));
		textPane.add(Box.createRigidArea(new Dimension(0, 5)));
		textPane.add(textScroller);
		textPane.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

		// Lay out the buttons from left to right.
		JPanel buttonPane = new JPanel();
		buttonPane.setLayout(new BoxLayout(buttonPane, BoxLayout.LINE_AXIS));
		buttonPane.setBorder(BorderFactory.createEmptyBorder(0, 10, 10, 10));
		buttonPane.add(Box.createHorizontalGlue());
		buttonPane.add(cancelButton);
		buttonPane.add(Box.createRigidArea(new Dimension(10, 0)));
		buttonPane.add(setButton);

		// Put everything together, using the content pane's BorderLayout.
		Container contentPane = getContentPane();
		contentPane.add(textPane, BorderLayout.CENTER);
		contentPane.add(buttonPane, BorderLayout.PAGE_END);

		// Initialize values.
		pack();
		setLocationRelativeTo(locationComp);
	}

	// Handle clicks on the Set and Cancel buttons.
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	public void actionPerformed(ActionEvent e) {
		if ("Set".equals(e.getActionCommand())) {
			AddDialog.value = (String) (jTextPane.getText());
		}

		if ("Cancel".equals(e.getActionCommand())) {
			AddDialog.value = null;
		}

		AddDialog.dialog.setVisible(false);
	}
}
