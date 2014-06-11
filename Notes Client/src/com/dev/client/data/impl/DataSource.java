package com.dev.client.data.impl;

import java.util.ArrayList;

import com.dev.client.data.model.DataSourceModel;
import com.dev.client.sockets.DataExchangeClient;
import com.dev.node.NoteModel;

public class DataSource implements DataSourceModel {

	private ArrayList<NoteModel> storedNodes;
	private DataExchangeClient dataExchangeClient;

	static {
		System.err.println("DataSource is created");
	}

	public DataSource() {
	}

	
	
	public void setDataExchangeClient(DataExchangeClient dataExchangeClient) {
		this.dataExchangeClient = dataExchangeClient;
	}



	public ArrayList<NoteModel> getStoredNodes() {
		storedNodes = dataExchangeClient.getNodes();
		return storedNodes;
	}

	public void pushNodes(ArrayList<NoteModel> notesArraylist) {
		dataExchangeClient = new DataExchangeClient();
		dataExchangeClient.pushNodes(notesArraylist);
	}
}
