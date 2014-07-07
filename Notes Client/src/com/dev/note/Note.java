package com.dev.note;

import java.io.Serializable;

public class Note implements NoteModel, Serializable {

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (changed ? 1231 : 1237);
		result = prime * result + (deleted ? 1231 : 1237);
		result = prime * result + id;
		result = prime * result + ((text == null) ? 0 : text.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Note other = (Note) obj;
		if (changed != other.changed)
			return false;
		if (deleted != other.deleted)
			return false;
		if (id != other.id)
			return false;
		if (text == null) {
			if (other.text != null)
				return false;
		} else if (!text.equals(other.text))
			return false;
		return true;
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private int id;

	private boolean changed = false;

	private String text;

	private boolean deleted = false;

	public Note() {

	}

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
