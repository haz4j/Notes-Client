package com.dev.note;

/**
 * NoteModel is a interface that provides an opportunity to create, store, edit
 * and delete notes.
 */

public interface NoteModel {

	/**
	 * Provides flag to check if note changes after the last synchronization.
	 *
	 * @return true, if is changed
	 */
	public abstract boolean isChanged();

	/**
	 * Allows to mention that note has changed after the last synchronization.
	 *
	 * @param changed
	 *            true if changed, false if not
	 */
	public abstract void setChanged(boolean changed);

	/**
	 * Provides text of note.
	 *
	 * @return text of note
	 */
	public abstract String getText();

	/**
	 * Allows to set text of note.
	 *
	 * @param text
	 *            the new text
	 */
	public abstract void setText(String text);

	/**
	 * Provides flag to check if note deleted after the last synchronization.
	 *
	 * @return true, if is deleted
	 */
	public abstract boolean isDeleted();

	/**
	 * Allows to mention that note has deleted after the last synchronization.
	 *
	 * @param deleted
	 *            the new deleted
	 */
	public abstract void setDeleted(boolean deleted);

	/**
	 * Provides a string representation of note.
	 *
	 * @return the string
	 */
	public String toString();

	/**
	 * Provides note id.
	 *
	 * @return the id
	 */
	public abstract int getId();

	/**
	 * Allows to set note id. Please be aware that some DBs set notes while
	 * saving due to autoincremental.
	 *
	 * @param id
	 *            the new id
	 */
	public abstract void setId(int id);

}