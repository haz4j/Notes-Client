/*
 * 
 */
package com.dev.client.graphic;

import java.awt.EventQueue;

import javax.swing.JFrame;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

/**
 * The Class MainWindow.
 */
@Component
public class MainWindow {

	/** The frame. */
	private JFrame frame;

	/** The main panel. */
	private MainPanel mainPanel;

	/** The Constant applicationContext. */
	private final static ApplicationContext applicationContext = new ClassPathXmlApplicationContext("beansConfFile.xml");

	/**
	 * Launch the application.
	 *
	 * @param args
	 *            the arguments
	 */
	public static void main(String[] args) {

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainWindow window = (MainWindow) applicationContext.getBean("mainWindow");
					window.initialize();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public MainWindow() {

	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, MyConsts.WINDOWWIDTH, MyConsts.WINDOWHEIGHT);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		mainPanel = (MainPanel) applicationContext.getBean("mainPanel");
		mainPanel.initialize();
		frame.setContentPane(mainPanel);
		frame.setVisible(true);
	}
}
