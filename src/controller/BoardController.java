package controller;

import view.GameView;
import view.MenuView;
import model.BoardModel;
import model.TokenModel.State;

/**
 * Classe BoardController qui utilise les modèles et gère les événements envoyés par les vues
 * GameView et MenuView.
 */
public class BoardController 
{
	private GameView gameView;
	private MenuView menuView;
	private BoardModel boardModel;
	private State currentPlayer = State.RED;
	private int[] settings;
	private int nbTokens;
	
	/**
	 * Constructeur du BoardController. Initialise les vues GameView et MenuView, reçoit les
	 * settings et initialise le tableau de jeu.
	 */
	public BoardController()
	{
		this.gameView = new GameView(this);
		this.menuView = new MenuView(this);
		
		this.settings = this.menuView.GetSettings();
		this.nbTokens = 0;
		
		this.boardModel = new BoardModel(this.settings[0], this.settings[1]);
		this.gameView.initBoard(this.settings[0], this.settings[1]);
	}
	
	/**
	 * Classe permettant d'ajouter logiquement un jeton (changer l'état de celui-ci) dans 
	 * le tableau de jeu.
	 * @param columnIndex La colonne où le jeton doit être ajouté
	 * @return La rangée à laquelle le jeton a été ajouté
	 */
	public int addToken(int columnIndex)
	{
		int x = findColumnHeight(columnIndex);
		this.boardModel.getBoard()[x][columnIndex].setState(this.currentPlayer);
		return x;
	}
	
	/**
	 * Trouve à quelle rangée un jeton doit être ajouter selon la colonne
	 * @param columnIndex La colonne à vérifier
	 * @return La rangée où le jeton doit être ajouté
	 */
	public int findColumnHeight(int columnIndex)
	{
		for (int i = this.settings[0] - 1; i >= 0; i--)
		{
			if (this.boardModel.getBoard()[i][columnIndex].getState() == State.EMPTY)
			{
				return i;
			}
		}
		return -1;
	}
	
	/**
	 * Remet le tableau de jeu à zéro.
	 */
	public void reset()
	{
		this.currentPlayer = State.RED;
		gameView.initBoard(this.settings[0], this.settings[1]);
		boardModel = new BoardModel(this.settings[0], this.settings[1]);
	}
	
	/**
	 * Permet d'alterner entre les joueurs
	 */
	public void changePlayer()
	{
		if (this.currentPlayer == State.RED)
		{
			this.currentPlayer = State.YELLOW;
		}
		else
		{
			this.currentPlayer = State.RED;
		}
	}
	
	/**
	 * Détermine si la partie est terminée ou non
	 * @param x La position en X du dernier jeton placé
	 * @param yLa position en Y du dernier jeton placé
	 * @return Un booléen, true si la partie est terminée, false si elle ne l'est pas.
	 */
	public boolean isGameFinished(int x, int y)
	{
		return this.LookLeftToRight(x, y) || 
			   this.LookLeftToRightDiagonal(x, y) || 
			   this.LookRightToLeftDiagonal(x, y) ||
			   this.LookUpToDown(x, y);

	}
	
	
	/**
	 * Vérifie si la partie a été gagné en diagonale de gauche à droite
	 * @param x La position du jeton en X
	 * @param y La position du jeton en Y
	 * @return Un booléen, true si la partie a été gagné, false si non.
	 */
	private boolean LookLeftToRightDiagonal(int x, int y)
	{
		this.nbTokens = 0;
		
		this.LookUpperLeft(x, y);
		this.LookLowerRight(x, y);
		
		if (this.nbTokens + 1 >= this.settings[2])
		{
			return true;
		}
		
		return false;
	}
	
	/**
	 * Vérifie si la partie a été gagné en diagonale de droite à gauche
	 * @param x La position du jeton en X
	 * @param y La position du jeton en Y
	 * @return Un booléen, true si la partie a été gagné, false si non.
	 */
	private boolean LookRightToLeftDiagonal(int x, int y)
	{
		this.nbTokens = 0;

		this.LookUpperRight(x, y);
		this.LookLowerLeft(x, y);
		
		if (this.nbTokens + 1 >= this.settings[2])
		{
			return true;
		}
		
		return false;
	}
	
	/**
	 * Vérifie si la partie a été gagné en ligne droite horizontale
	 * @param x La position du jeton en X
	 * @param y La position du jeton en Y
	 * @return Un booléen, true si la partie a été gagné, false si non.
	 */
	private boolean LookLeftToRight(int x, int y)
	{
		this.nbTokens = 0;
		
		this.LookRight(x, y);
		this.LookLeft(x, y);
		
		if (this.nbTokens + 1 >= this.settings[2])
		{
			return true;
		}
		
		return false;
	}
	
	/**
	 * Vérifie si la partie a été gagné en ligne droite verticale
	 * @param x La position du jeton en X
	 * @param y La position du jeton en Y
	 * @return Un booléen, true si la partie a été gagné, false si non.
	 */
	private boolean LookUpToDown(int x, int y)
	{
		this.nbTokens = 0;
		
		this.LookDown(x, y);
		
		if (this.nbTokens + 1 >= this.settings[2])
		{
			return true;
		}
		
		return false;
	}
	
	
	/**
	 * Retourne le modèle du tableau
	 * @return Le modèle du tableau
	 */
	public BoardModel getBoardModel()
	{
		return this.boardModel;
	}
	
	/**
	 * Vérifie si une colonne est pleine
	 * @param nbColumn La colonne à vérifier
	 * @return Un booléen, true si la colonne est pleine, false si elle ne l'est pas
	 */
	public boolean IsColumnFull(int nbColumn)
	{
		if (this.boardModel.getBoard()[0][nbColumn].getState() != State.EMPTY)
		{
			return true;
		}
		
		return false;
	}
	
	/**
	 * Vérifie si le tableau de jeu est plein, signifiant une partie nulle
	 * @return Un booléen, true si le tableau est plein, false s'il ne l'est pas.
	 */
	public boolean IsBoardFull()
	{
		int nbFullColumns = 0;
		for (int i = 0; i < this.settings[1]; i++)
		{
			if (IsColumnFull(i))
			{
				nbFullColumns++;
			}
		}
		return nbFullColumns == this.settings[1];
	}
	
	/**
	 * Vérifie en haut à gauche du jeton à la position spécifiée.
	 * @param positionX La position du jeton en X
	 * @param positionY La position du jeton en Y
	 */
	private void LookUpperLeft(int positionX, int positionY)
	{
		try
		{
			if (this.currentPlayer == this.boardModel.getBoard()[positionX + 1][positionY - 1].getState())
			{
				this.nbTokens++;
				this.LookUpperLeft(positionX + 1, positionY - 1);
			}
		}
		catch(ArrayIndexOutOfBoundsException e)
		{
			 e.printStackTrace();
		}
	}
	
	/**
	 * Vérifie en haut à droite du jeton à la position spécifiée.
	 * @param positionX La position du jeton en X
	 * @param positionY La position du jeton en Y
	 */
	private void LookUpperRight(int positionX, int positionY)
	{
		try
		{
			if (this.currentPlayer == this.boardModel.getBoard()[positionX + 1][positionY + 1].getState())
			{
				this.nbTokens++;
				this.LookUpperRight(positionX + 1, positionY + 1);
			}
		}
		catch(ArrayIndexOutOfBoundsException e)
		{
			e.printStackTrace();
		}
	}
	
	/**
	 * Vérifie en bat à gauche du jeton à la position spécifiée.
	 * @param positionX La position du jeton en X
	 * @param positionY La position du jeton en Y
	 */
	private void LookLowerLeft(int positionX, int positionY)
	{
		try
		{
			if (this.currentPlayer == this.boardModel.getBoard()[positionX - 1][positionY - 1].getState())
			{
				this.nbTokens++;
				this.LookLowerLeft(positionX - 1, positionY - 1);
			}
		}
		catch(ArrayIndexOutOfBoundsException e)
		{
			e.printStackTrace();
		}
	}
	
	/**
	 * Vérifie en bas à droite du jeton à la position spécifiée.
	 * @param positionX La position du jeton en X
	 * @param positionY La position du jeton en Y
	 */
	private void LookLowerRight(int positionX, int positionY)
	{
		try
		{
			if (this.currentPlayer == this.boardModel.getBoard()[positionX - 1][positionY + 1].getState())
			{
				this.nbTokens++;
				this.LookLowerRight(positionX - 1, positionY + 1);
			}
		}
		catch(ArrayIndexOutOfBoundsException e)
		{
			e.printStackTrace();
		}
	}
	
	/**
	 * Vérifie en bas du jeton à la position spécifiée.
	 * @param positionX La position du jeton en X
	 * @param positionY La position du jeton en Y
	 */
	private void LookDown(int positionX, int positionY)
	{
		try
		{
			if (this.currentPlayer == this.boardModel.getBoard()[positionX + 1][positionY].getState())
			{
				this.nbTokens++;
				this.LookDown(positionX + 1, positionY);
			}
		}
		catch(ArrayIndexOutOfBoundsException e)
		{
			e.printStackTrace();
		}
	}
	
	/**
	 * Vérifie à gauche du jeton à la position spécifiée.
	 * @param positionX La position du jeton en X
	 * @param positionY La position du jeton en Y
	 */
	private void LookLeft(int positionX, int positionY)
	{
		try
		{
			if (this.currentPlayer == this.boardModel.getBoard()[positionX][positionY - 1].getState())
			{
				this.nbTokens++;
				this.LookLeft(positionX, positionY - 1);
			}
		}
		catch(ArrayIndexOutOfBoundsException e)
		{
			e.printStackTrace();
		}
	}
	
	/**
	 * Vérifie à droite du jeton à la position spécifiée.
	 * @param positionX La position du jeton en X
	 * @param positionY La position du jeton en Y
	 */
	private void LookRight(int positionX, int positionY)
	{
		try
		{
			if (this.currentPlayer == this.boardModel.getBoard()[positionX][positionY + 1].getState())
			{
				this.nbTokens++;
				this.LookRight(positionX, positionY + 1);
			}
		}
		catch(ArrayIndexOutOfBoundsException e)
		{
			e.printStackTrace();
		}
	}
	
	/**
	 * Retourne le joueur actif
	 * @return Le joueur actif
	 */
	public State GetCurrentPlayer()
	{
		return this.currentPlayer;
	}
	
	/**
	 * Retourne la vue Menu
	 * @return La vue Menu
	 */
	public MenuView GetMenuView() 
	{
		return this.menuView;
	}

	/**
	 * Retourne les settings de l'application
	 * @return Un tableau de settings
	 */
	public int[] GetSettings()
	{
		return this.settings;
	}
	
	/**
	 * Permet de modifier les settings de l'application
	 * @param settings Un tableau de nouveaux settings
	 */
	public void SetSettings(int[] settings)
	{
		this.settings = settings;
	}

	/**
	 * Point de départ de l'application
	 * @param args
	 */
	public static void main(String[] args)
	{
		@SuppressWarnings("unused")
		BoardController controller = new BoardController();
	}
}
