package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import controller.BoardController;

public class MenuView extends JDialog
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JDialog menu;
	private JTextField txtNbJetonLong;
	private JTextField txtNbJetonHaut;
	private JTextField txtNbJetonPourGagner;
	private int[] settings = new int[3];
	private BoardController controller;
	
	/**
	 * Constructeur par défaut de la classe MenuView. Initialise le menu qui sera
	 * affiché lorsque l'utilisateur voudra changer les settings du jeu.
	 */
	public MenuView(BoardController controller)
	{
		this.controller = controller;
		this.initializeMenu();
		this.settings = new int[]{6, 7, 4};
	}
	
	/**
	 * Méthode qui initialise le menu.
	 */
	public void initializeMenu()
	{
		JPanel buttomPane = new JPanel();
		JPanel middlePane = new JPanel();
		
		this.menu = new JDialog();
		this.txtNbJetonHaut = new JTextField();
		this.txtNbJetonLong = new JTextField();
		this.txtNbJetonPourGagner = new JTextField();
		
		this.menu.setSize(600, 400);
		this.menu.setLocationRelativeTo(null);
		
		JLabel lblNbJetonHaut = new JLabel("Nombre de jeton de haut :");
		JLabel lblNbJetonLong = new JLabel("Nombre de jeton de long :");
		JLabel lblNbJetonPourGagner = new JLabel("Nombre de jeton pour gagner :");
		
		JButton bouton = new JButton("Commencer");
		bouton.addActionListener(new SaveNewSettings());
		
		//set the middle of the pop-up window
		middlePane.setLayout(new BoxLayout(middlePane, BoxLayout.PAGE_AXIS));
		middlePane.add(Box.createRigidArea(new Dimension(0,5)));
		//this.SetMenu(nbJetonLong, nbJetonLarge, nbJetonCollerGagne);
		middlePane.add(lblNbJetonHaut);
		middlePane.add(this.txtNbJetonHaut);
		middlePane.add(lblNbJetonLong);
		middlePane.add(this.txtNbJetonLong);
		middlePane.add(lblNbJetonPourGagner);
		middlePane.add(this.txtNbJetonPourGagner);
		middlePane.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
		
		//set the bottum of the pop-up window
		buttomPane.setLayout(new BoxLayout(buttomPane, BoxLayout.LINE_AXIS));
		buttomPane.setBorder(BorderFactory.createEmptyBorder(0, 10, 10, 10));
		buttomPane.add(Box.createHorizontalGlue());
		buttomPane.add(bouton);
		
		this.menu.add(middlePane, BorderLayout.CENTER);
		this.menu.add(buttomPane, BorderLayout.PAGE_END);

		
	}
	
	/**
	 * Méthode qui modifie les champs textes pour que les settings concordent avec le jeu.
	 * @param nbJettonLong -int, nombre maximum de jeton sur le long
	 * @param nbJettonLarge -int, nombre maximum de jeton sur le long
	 * @param nbJettonColler -int, nombre de jeton pour gagner la partie
	 */
	public void SetMenu(int[] settings)
	{
		this.txtNbJetonHaut.setText(Integer.toString(settings[0]));
		this.txtNbJetonLong.setText(Integer.toString(settings[1]));
		this.txtNbJetonPourGagner.setText(Integer.toString(settings[2]));	
	}
	
	/**
	 * Méthode que retourne le menu.
	 * @return menu
	 */
	public JDialog GetMenu()
	{
		return this.menu;
	}
	
	/**
	 * Class privé qui est un ActionListener pour le bouton Commencer
	 */
	private class SaveNewSettings implements ActionListener
	{
		
		@Override
		public void actionPerformed(ActionEvent arg0)
		{
			int[] setting = new int[3];
			setting[0] = Integer.parseInt(txtNbJetonHaut.getText());
			setting[1] = Integer.parseInt(txtNbJetonLong.getText());
			setting[2] = Integer.parseInt(txtNbJetonPourGagner.getText());
			controller.SetSettings(setting);
			controller.reset();
			menu.setVisible(false);
		}
	}	
	
	public int[] GetSettings()
	{
		return this.settings;
	}
}
