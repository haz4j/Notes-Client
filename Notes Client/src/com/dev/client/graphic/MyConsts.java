/*
 * 
 */
package com.dev.client.graphic;

import java.awt.FlowLayout;

import javax.swing.ImageIcon;

/**
 * The Class MyConsts. Sizes of windows and other elements.
 */
public class MyConsts {

	/** The Constant WINDOWWIDTH. */
	public static final int WINDOWWIDTH = 600;

	/** The Constant WINDOWHEIGHT. */
	public static final int WINDOWHEIGHT = 700;

	/** The Constant TOPPANELWIDTH. */
	public static final int TOPPANELWIDTH = WINDOWWIDTH;

	/** The Constant TOPPANELHEIGHT. */
	public static final int TOPPANELHEIGHT = 45;

	/** The Constant NOTEWIDTH. */
	public static final int NOTEWIDTH = WINDOWWIDTH;

	/** The Constant NOTEWHEIGHT. */
	public static final int NOTEWHEIGHT = 65;

	/** The Constant TEXTPANELWIDTH. */
	public static final int TEXTPANELWIDTH = 480;

	/** The Constant TEXTPANELHEIGHT. */
	public static final int TEXTPANELHEIGHT = 55;

	/** The Constant TEXTAREAWIDTH. */
	public static final int TEXTAREAWIDTH = TEXTPANELWIDTH - 10;

	/** The Constant TEXTAREAHEIGHT. */
	public static final int TEXTAREAHEIGHT = TEXTPANELHEIGHT - 15;

	/** The Constant BOTTOMPANELWIDTH. */
	public static final int BOTTOMPANELWIDTH = WINDOWWIDTH;

	/** The Constant BOTTOMPANELHEIGHT. */
	public static final int BOTTOMPANELHEIGHT = 30;

	// private static Dimension topPanelDimension;
	// private static Dimension bottomPanelDimension;
	// private static Dimension noteDimension;
	// private static Dimension textPanelDimension;

	/** The note panel layout. */
	private static FlowLayout notePanelLayout;

	/** The add icon. */
	private static ImageIcon addIcon;

	/** The view icon. */
	private static ImageIcon viewIcon;

	/** The edit icon. */
	private static ImageIcon editIcon;

	/** The delete icon. */
	private static ImageIcon deleteIcon;

	/** The syncronize icon. */
	private static ImageIcon syncronizeIcon;

	static {
		notePanelLayout = new FlowLayout();
		notePanelLayout.setAlignment(FlowLayout.LEFT);
		// noteDimension = new Dimension(HOTEWIDTH, HOTEWHEIGHT);
		// textPanelDimension = new Dimension(TEXTPANELWIDTH, TEXTPANELHEIGHT);
		// topPanelDimension = new Dimension(TOPPANELWIDTH, TOPPANELHEIGHT);
		// bottomPanelDimension = new Dimension(BOTTOMPANELWIDTH,
		// BOTTOMPANELHEIGHT);
		addIcon = new ImageIcon("img/add-icon.png");
		viewIcon = new ImageIcon("img/view-icon.png");
		editIcon = new ImageIcon("img/edit-icon.png");
		deleteIcon = new ImageIcon("img/delete-icon.png");
		syncronizeIcon = new ImageIcon("img/synchronize-icon.png");
	}

	/**
	 * Gets the note panel layout.
	 *
	 * @return the note panel layout
	 */
	public static FlowLayout getNotePanelLayout() {
		return notePanelLayout;
	}

	// public static Dimension getNoteDimention() {
	// return noteDimension;
	// }
	//
	// public static Dimension getTextPanelDimension() {
	// return textPanelDimension;
	// }
	//
	// public static Dimension getTopPanelDimension() {
	// return topPanelDimension;
	// }
	//
	// public static Dimension getBottomPanelDimension() {
	// return bottomPanelDimension;
	// }

	/**
	 * Gets the adds the icon.
	 *
	 * @return the adds the icon
	 */
	public static ImageIcon getAddIcon() {
		return addIcon;
	}

	/**
	 * Gets the view icon.
	 *
	 * @return the view icon
	 */
	public static ImageIcon getViewIcon() {
		return viewIcon;
	}

	/**
	 * Gets the edits the icon.
	 *
	 * @return the edits the icon
	 */
	public static ImageIcon getEditIcon() {
		return editIcon;
	}

	/**
	 * Gets the delete icon.
	 *
	 * @return the delete icon
	 */
	public static ImageIcon getDeleteIcon() {
		return deleteIcon;
	}

	/**
	 * Gets the syncronize icon.
	 *
	 * @return the syncronize icon
	 */
	public static ImageIcon getSyncronizeIcon() {
		return syncronizeIcon;
	}

}
