package com.dev.node;

public interface NoteModel {

	public abstract int getId();

	public abstract void setId(int id);

	public abstract boolean isChanged();

	public abstract void setChanged(boolean changed);

	public abstract String getText();

	public abstract void setText(String text);

	public abstract boolean isDeleted();

	public abstract void setDeleted(boolean deleted);

	@Override
	public String toString();

}