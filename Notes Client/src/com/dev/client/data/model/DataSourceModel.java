package com.dev.client.data.model;

import java.util.ArrayList;

import com.dev.node.NoteModel;

public interface DataSourceModel {

	ArrayList<NoteModel> getStoredNodes();

	void pushNodes(ArrayList<NoteModel> notesArraylist);

}
