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

public class EditDialog extends JDialog implements ActionListener {
	private static EditDialog dialog;
	private static String value = "";
	private String initialValue;
	JTextPane jTextPane;

	public static String showDialog(Component frameComp,
			Component locationComp, String title, String initialValue) {
		Frame frame = JOptionPane.getFrameForComponent(frameComp);
		dialog = new EditDialog(frame, locationComp, title, initialValue);
		dialog.setVisible(true);
		return value;
	}

	private EditDialog(Frame frame, Component locationComp, String title,
			String initialValue) {
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
	public void actionPerformed(ActionEvent e) {
		if ("Set".equals(e.getActionCommand())) {
			EditDialog.value = (String) (jTextPane.getText());
		}

		if ("Cancel".equals(e.getActionCommand())) {
			EditDialog.value = initialValue;
		}

		EditDialog.dialog.setVisible(false);
	}
}
