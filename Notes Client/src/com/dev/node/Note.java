package com.dev.node;

import java.io.Serializable;

public class Note implements NoteModel, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int id;
	private boolean changed = false;
	private String text;
	private boolean deleted = false;

	public Note(int id, boolean changed, String text, boolean deleted) {
		this.setId(id);
		this.setChanged(changed);
		this.setText(text);
		this.setDeleted(deleted);
	}

	public Note(NoteModel note) {
		this.setId(note.getId());
		this.setChanged(note.isChanged());
		this.setText(note.getText());
		this.setDeleted(note.isDeleted());
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public boolean isChanged() {
		return changed;
	}

	public void setChanged(boolean changed) {
		this.changed = changed;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public boolean isDeleted() {
		return deleted;
	}

	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}

	@Override
	public String toString() {
		return "Note [id=" + id + ", changed=" + changed + ", text=" + text + ", deleted=" + deleted + "]";
	}

}
