package com.dev.client.graphic;

import java.awt.FlowLayout;

import javax.swing.ImageIcon;

public class MyConsts {

	public static final int WINDOWWIDTH = 600;
	public static final int WINDOWHEIGHT = 700;
	public static final int TOPPANELWIDTH = WINDOWWIDTH;
	public static final int TOPPANELHEIGHT = 45;
	public static final int NOTEWIDTH = WINDOWWIDTH;
	public static final int NOTEWHEIGHT = 65;
	public static final int TEXTPANELWIDTH = 480;
	public static final int TEXTPANELHEIGHT = 55;
	public static final int TEXTAREAWIDTH = TEXTPANELWIDTH - 10;
	public static final int TEXTAREAHEIGHT = TEXTPANELHEIGHT - 15;
	public static final int BOTTOMPANELWIDTH = WINDOWWIDTH;
	public static final int BOTTOMPANELHEIGHT = 30;

	// private static Dimension topPanelDimension;
	// private static Dimension bottomPanelDimension;
	// private static Dimension noteDimension;
	// private static Dimension textPanelDimension;

	private static FlowLayout notePanelLayout;
	private static ImageIcon addIcon;
	private static ImageIcon viewIcon;
	private static ImageIcon editIcon;
	private static ImageIcon deleteIcon;
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

	public static ImageIcon getAddIcon() {
		return addIcon;
	}

	public static ImageIcon getViewIcon() {
		return viewIcon;
	}

	public static ImageIcon getEditIcon() {
		return editIcon;
	}

	public static ImageIcon getDeleteIcon() {
		return deleteIcon;
	}

	public static ImageIcon getSyncronizeIcon() {
		return syncronizeIcon;
	}

}
