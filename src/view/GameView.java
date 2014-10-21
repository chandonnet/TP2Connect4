package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import model.TokenModel.State;
import controller.BoardController;

public class GameView extends JFrame
{
	private static final long serialVersionUID = 1L;

	private JButton[] controlButtons;

	private MyImageContainer[][] placeHolders;

	private final JTextField message = new JTextField(20);
	private final JPanel centerPane = new JPanel();
	
	private BoardController controller;

	public GameView(BoardController controller)
	{
		this.setTitle("Connect4");

		this.controller = controller;
		this.configureWindow();

		this.setLayout(new BorderLayout());
		JPanel panelNorth = new JPanel();
		panelNorth.setLayout(new FlowLayout());
		panelNorth.add(this.message);
		this.message.setEditable(false);
		this.message.setText("Joueur " + controller.GetCurrentPlayer());
		this.add(panelNorth, BorderLayout.NORTH);
		this.createMenu();
		this.setVisible(true);
		
	}

	private void createMenu()
	{
		JMenuBar menuBar = new JMenuBar();
		JMenu gameMenu = new JMenu("Game");
		JMenuItem resignMenuItem = new JMenuItem("Resign");
		resignMenuItem.addActionListener(new ResignActionHandler());
		JMenuItem settingMenu = new JMenuItem("Setting");
		settingMenu.addActionListener(new SettingActionHandler());
		gameMenu.add(settingMenu);
		gameMenu.add(resignMenuItem);
		menuBar.add(gameMenu);

		JMenu helpMenu = new JMenu("Help");
		JMenuItem aboutMenuItem = new JMenuItem("About");
		aboutMenuItem.addActionListener(new AboutActionHandler());
		helpMenu.add(aboutMenuItem);
		menuBar.add(helpMenu);

		this.setJMenuBar(menuBar);
	}

	public void initBoard(int nbRows, int nbColumns)
	{
		this.message.setText("Joueur " + controller.GetCurrentPlayer());
		this.centerPane.removeAll();
		this.placeHolders = new MyImageContainer[nbRows][nbColumns];
		this.controlButtons = new JButton[nbColumns];

		centerPane.setLayout(new GridLayout(nbRows + 1, nbColumns));

		for (int i = 0; i < nbColumns; i++)
		{
			JButton button = new JButton("T");
			this.controlButtons[i] = button;
			button.addActionListener(new ButtonHandler(i));
			centerPane.add(button);
		}

		for (int row = 0; row < nbRows; row++) 
		{
			for (int column = 0; column < nbColumns; column++)
			{
				MyImageContainer imageContainer = new MyImageContainer();
				ImageIcon i = new ImageIcon(State.EMPTY.getValue());
				imageContainer.setImageIcon(i);
				imageContainer.setOpaque(true);
				placeHolders[row][column] = imageContainer;
				centerPane.add(imageContainer);
			}
		}
		this.add(centerPane, BorderLayout.CENTER);
		this.revalidate();
	}

	private void endGame(String message)
	{
		int reply = JOptionPane.showConfirmDialog(null, message, "Rejouer?", JOptionPane.YES_NO_OPTION);
	    if (reply == JOptionPane.NO_OPTION)
	    {
	      System.exit(0);
	    }
	    if (reply == JOptionPane.YES_OPTION)
	    {
	    	controller.reset();
	    }
	}
	
	private void refreshView(int rowIndex, int columnIndex)
	{
		this.placeHolders[rowIndex][columnIndex].setImageIcon(new ImageIcon(this.controller.getBoardModel().getBoard()[rowIndex][columnIndex].getState().getValue()));
	}
	
	private void configureWindow()
	{
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		Dimension screenSize = toolkit.getScreenSize();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(((screenSize.width * 3) / 8), ((screenSize.height * 4) / 6));
		setLocation(((screenSize.width - getWidth()) / 2), ((screenSize.height - getHeight()) / 2));
	}

	private class ButtonHandler implements ActionListener
	{
		private final int columnIndex;

		private ButtonHandler(int columnIndex)
		{
			this.columnIndex = columnIndex;
		}

		@Override
		public void actionPerformed(ActionEvent arg0)
		{
			if (!controller.IsColumnFull(columnIndex))
			{
				int rowIndex = controller.addToken(columnIndex);
				if (controller.isGameFinished(rowIndex, columnIndex))
				{
					refreshView(rowIndex, columnIndex);
					endGame(controller.GetCurrentPlayer() + " a gagn�!");
				}
				else
				{
					controller.changePlayer();
					message.setText("Joueur " + controller.GetCurrentPlayer());
				}
				refreshView(rowIndex, columnIndex);
				
				if (controller.IsBoardFull())
				{
					endGame("Partie nulle");
				}
			}
		}
	}

	private class ResignActionHandler implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent arg0)
		{
			controller.reset();
			System.out.println("Action on menu");
		}
	}
	
	private class SettingActionHandler implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent arg0)
		{
			MenuView menuView = controller.GetMenuView();
			menuView.SetMenu(controller.GetSettings());
			menuView.GetMenu().setVisible(true);
		}
	}

	private class AboutActionHandler implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent arg0)
		{
			JOptionPane.showMessageDialog(GameView.this, "GUI for Connect4\n420-520-SF TP1\n\nAuthor: Fran�ois Gagnon", "About", JOptionPane.INFORMATION_MESSAGE);
		}
	}

}
