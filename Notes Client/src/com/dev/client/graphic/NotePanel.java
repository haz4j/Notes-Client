/*
 * 
 */
package com.dev.client.graphic;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;

/**
 * The NotePanel create for each Note.
 */
public class NotePanel extends JPanel {

	/** The text panel. */
	private JPanel textPanel;

	/** The text panel layout. */
	private FlowLayout textPanelLayout;

	/** The text area. */
	private JTextArea textArea;

	/** The action panel. */
	private JPanel actionPanel;

	/** The action panel layout. */
	private FlowLayout actionPanelLayout;

	/** The edit button. */
	private JButton editButton;

	/** The delete button. */
	private JButton deleteButton;

	/** The frame. */
	private JFrame frame;

	/** The note id. */
	private int noteId;

	/** The main panel. */
	private MainPanel mainPanel;

	/**
	 * Instantiates a new note panel.
	 *
	 * @param noteId
	 *            the note id
	 * @param mainPanel
	 *            the main panel
	 * @param text
	 *            the text
	 */
	public NotePanel(final int noteId, final MainPanel mainPanel, String text) {
		super(MyConsts.getNotePanelLayout());
		this.mainPanel = mainPanel;
		this.noteId = noteId;
		setMaximumSize(new Dimension(MyConsts.NOTEWIDTH, MyConsts.NOTEWHEIGHT));
		textPanel = new JPanel();
		textPanelLayout = new FlowLayout();
		textPanelLayout.setAlignment(FlowLayout.LEFT);
		textPanel.setLayout(textPanelLayout);
		textPanel.setPreferredSize(new Dimension(MyConsts.TEXTPANELWIDTH, MyConsts.TEXTPANELHEIGHT));
		add(textPanel);

		text = text.length() > 150 ? text.substring(0, 150) + "..." : text;

		textArea = new JTextArea(text);
		textArea.setPreferredSize(new Dimension(MyConsts.TEXTAREAWIDTH, MyConsts.TEXTAREAHEIGHT));
		textArea.setLineWrap(true);

		textPanel.add(textArea);

		actionPanel = new JPanel();
		actionPanelLayout = new FlowLayout();
		actionPanelLayout.setAlignment(FlowLayout.RIGHT);
		actionPanel.setLayout(actionPanelLayout);
		add(actionPanel);

		editButton = new JButton(MyConsts.getEditIcon());
		editButton.setPreferredSize(new Dimension(25, 25));

		editButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String newText = EditDialog.showDialog(frame, editButton, "Submit new note", textArea.getText());
				mainPanel.editNote(noteId, newText);
			}
		});

		actionPanel.add(editButton);

		deleteButton = new JButton(MyConsts.getDeleteIcon());
		deleteButton.setPreferredSize(new Dimension(25, 25));
		deleteButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				int response = JOptionPane.showConfirmDialog(null, "Do you want to continue?", "Confirm",
						JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, MyConsts.getDeleteIcon());
				if (response == JOptionPane.NO_OPTION) {
				} else if (response == JOptionPane.YES_OPTION) {
					mainPanel.deleteNote(noteId);

				} else if (response == JOptionPane.CLOSED_OPTION) {
				}
			}

		});
		actionPanel.add(deleteButton);

		Border raisedetched = BorderFactory.createEtchedBorder(EtchedBorder.RAISED);
		this.setBorder(raisedetched);
	}

	/**
	 * Sets the top level frame.
	 *
	 * @param frame
	 *            the new frame
	 */
	public void setFrame(JFrame frame) {
		this.frame = frame;
	}
}
