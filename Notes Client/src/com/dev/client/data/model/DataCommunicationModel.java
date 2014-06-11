/**
 * 
 */
package com.dev.client.data.model;

import java.util.ArrayList;

import com.dev.node.NoteModel;

/**
 * @author hazard
 * 
 */
public interface DataCommunicationModel {

	public void syncronize();

	public ArrayList<NoteModel> getNotesArraylist();

	public void setDataSourceModel(DataSourceModel dataSourceModel);
}
