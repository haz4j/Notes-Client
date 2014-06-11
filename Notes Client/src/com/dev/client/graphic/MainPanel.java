package com.dev.client.graphic;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.CountDownLatch;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;

import com.dev.client.data.model.DataCommunicationModel;
import com.dev.node.Note;
import com.dev.node.NoteModel;

class MainPanel extends JPanel {

	private DataCommunicationModel dataCommunicationModel;
	private ArrayList<NoteModel> notesArraylist;
	private JFrame frame;
	private JPanel notesPanel;
	private MainWindow mainWindow;
	private JScrollPane jScrollPane;
	private JPanel topPanel;
	private JButton btnNewNote;
	private Component horizontalStrut;
	private JLabel lblHelloUsername;
	private JButton btnSynchronize;
	private Border raisedetched;
	private JPanel bottomPanel;
	private JLabel lblProgressToSynchronization;
	private FlowLayout flowLayout;
	private JProgressBar progressBar;

	/**
	 * Create the panel.
	 */

	public MainPanel() {

	}

	public void setMainWindow(MainWindow mainWindow) {
		this.mainWindow = mainWindow;
	}

	public void setDataCommunicationModel(DataCommunicationModel dataCommunicationModel) {
		this.dataCommunicationModel = dataCommunicationModel;
	}

	public void setFrame(JFrame frame) {
		this.frame = frame;
	}

	public void initialize() {
		notesArraylist = dataCommunicationModel.getNotesArraylist();
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		topPanel = new JPanel();
		topPanel.setMaximumSize(new Dimension(MyConsts.TOPPANELWIDTH, MyConsts.TOPPANELHEIGHT));
		add(topPanel);

		lblHelloUsername = new JLabel("Hello, username");
		topPanel.add(lblHelloUsername);

		horizontalStrut = Box.createHorizontalStrut(20);
		topPanel.add(horizontalStrut);

		btnNewNote = new JButton(MyConsts.getAddIcon());

		btnNewNote.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String selectedName = AddDialog.showDialog(frame, frame, "Submit new note", "New text");
				if (!(selectedName == null)) {
					addNote(selectedName);
				}
			}
		});

		topPanel.add(btnNewNote);

		btnSynchronize = new JButton(MyConsts.getSyncronizeIcon());
		topPanel.add(btnSynchronize);
		btnSynchronize.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				synchronizeNotes();
			}
		});

		raisedetched = BorderFactory.createEtchedBorder(EtchedBorder.RAISED);

		this.setBorder(raisedetched);
		notesPanel = new JPanel();
		jScrollPane = new JScrollPane(notesPanel);
		jScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		jScrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

		jScrollPane.setMaximumSize(new Dimension(MyConsts.WINDOWWIDTH, 592));
		add(jScrollPane);

		notesPanel.setLayout(new BoxLayout(notesPanel, BoxLayout.Y_AXIS));

		notesPanel.setBackground(Color.red);

		refreshNotesPanel();

		bottomPanel = new JPanel();
		flowLayout = (FlowLayout) bottomPanel.getLayout();
		flowLayout.setAlignment(FlowLayout.TRAILING);
		bottomPanel.setAlignmentY(Component.BOTTOM_ALIGNMENT);
		bottomPanel.setMaximumSize(new Dimension(MyConsts.BOTTOMPANELWIDTH, MyConsts.BOTTOMPANELHEIGHT));
		add(bottomPanel);

		lblProgressToSynchronization = new JLabel("Progress to synchronization: ");
		bottomPanel.add(lblProgressToSynchronization);

		progressBar = new JProgressBar();
		progressBar.setToolTipText("Progress to sychronization:");
		bottomPanel.add(progressBar);

	}

	private int calcNotesPanelSize() {
		int i = 0;
		for (Iterator<NoteModel> iterator = notesArraylist.iterator(); iterator.hasNext();) {
			NoteModel note = iterator.next();
			if (!note.isDeleted()) {
				i++;
			}
		}
		return i;
	}

	private void refreshNotesPanel() {
		notesPanel.removeAll();
		notesPanel.setPreferredSize(new Dimension(MyConsts.WINDOWWIDTH, (calcNotesPanelSize()) * MyConsts.NOTEWHEIGHT));
		for (int i = 0; i < notesArraylist.size(); i++) {
			NoteModel currentNote = notesArraylist.get(i);
			if (!currentNote.isDeleted()) {
				NotePanel notePanel = new NotePanel(i, this, currentNote.getText());
				notePanel.setFrame(frame);
				notesPanel.add(notePanel);
			}
		}
	}

	protected void addNote(String text) {
		final Note note = new Note(notesArraylist.size() + 1, true, text, false);
		CountDownLatch countDownLatch = new CountDownLatch(1);
		AddThread addThread = new AddThread(notesArraylist, note, countDownLatch);
		try {
			countDownLatch.await();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		refreshNotesPanel();
		this.revalidate();
		this.repaint();
		jScrollPane.getVerticalScrollBar().setValue(0);
	}

	protected void deleteNote(int noteId) {
		CountDownLatch countDownLatch = new CountDownLatch(1);
		DeleteThread deleteThread = new DeleteThread(notesArraylist, noteId, countDownLatch);
		try {
			countDownLatch.await();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		refreshNotesPanel();
		this.revalidate();
		this.repaint();
		jScrollPane.getVerticalScrollBar().setValue(0);
	}

	protected void editNote(int noteId, String newText) {
		CountDownLatch countDownLatch = new CountDownLatch(1);
		EditThread editThread = new EditThread(notesArraylist, noteId, newText, countDownLatch);
		try {
			countDownLatch.await();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		refreshNotesPanel();
		this.revalidate();
		this.repaint();
		jScrollPane.getVerticalScrollBar().setValue(0);
	}

	protected void synchronizeNotes() {

		CountDownLatch countDownLatch = new CountDownLatch(1);
		SynchronizeThread synchronizeThread = new SynchronizeThread(dataCommunicationModel, countDownLatch);
		try {
			countDownLatch.await();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		refreshNotesPanel();
		this.revalidate();
		this.repaint();
		jScrollPane.getVerticalScrollBar().setValue(0);
	}

}

class AddThread implements Runnable {

	private CountDownLatch countDownLatch;
	private ArrayList<NoteModel> notesArraylist;
	private NoteModel note;

	public AddThread(ArrayList<NoteModel> notesArraylist, Note note, CountDownLatch countDownLatch) {
		this.countDownLatch = countDownLatch;
		this.notesArraylist = notesArraylist;
		this.note = note;
		new Thread(this).start();
	}

	public void run() {
		notesArraylist.add(note);
		countDownLatch.countDown();
	}
}

class EditThread implements Runnable {

	private CountDownLatch countDownLatch;
	private ArrayList<NoteModel> notesArraylist;
	private int noteId;
	private String newText;

	public EditThread(ArrayList<NoteModel> notesArraylist, int noteId, String newText, CountDownLatch countDownLatch) {
		this.notesArraylist = notesArraylist;
		this.noteId = noteId;
		this.countDownLatch = countDownLatch;
		this.newText = newText;
		new Thread(this).start();
	}

	public void run() {
		notesArraylist.get(noteId).setText(newText);
		notesArraylist.get(noteId).setChanged(true);
		countDownLatch.countDown();
	}
}

class DeleteThread implements Runnable {

	private CountDownLatch countDownLatch;
	private ArrayList<NoteModel> notesArraylist;
	private int noteId;

	public DeleteThread(ArrayList<NoteModel> notesArraylist, int noteId, CountDownLatch countDownLatch) {
		this.countDownLatch = countDownLatch;
		this.notesArraylist = notesArraylist;
		this.noteId = noteId;
		new Thread(this).start();
	}

	public void run() {
		notesArraylist.get(noteId).setDeleted(true);
		notesArraylist.get(noteId).setChanged(true);
		countDownLatch.countDown();
	}
}

class SynchronizeThread implements Runnable {

	private CountDownLatch countDownLatch;
	private DataCommunicationModel dataCommunicationModel;

	public SynchronizeThread(DataCommunicationModel dataCommunicationModel, CountDownLatch countDownLatch) {
		this.dataCommunicationModel = dataCommunicationModel;
		this.countDownLatch = countDownLatch;
		new Thread(this).start();
	}

	public void run() {
		dataCommunicationModel.syncronize();
		countDownLatch.countDown();
	}
}