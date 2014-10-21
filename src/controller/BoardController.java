package controller;

import view.GameView;
import view.MenuView;
import model.BoardModel;
import model.TokenModel.State;

public class BoardController 
{
	private GameView gameView;
	private MenuView menuView;
	private BoardModel boardModel;
	private State currentPlayer = State.RED;
	private int[] settings;
	private int nbTokens;
	
	public BoardController()
	{
		this.gameView = new GameView(this);
		this.menuView = new MenuView(this);
		
		this.settings = this.menuView.GetSettings();
		this.nbTokens = 0;
		
		this.boardModel = new BoardModel(this.settings[0], this.settings[1]);
		this.gameView.initBoard(this.settings[0], this.settings[1]);
	}
	
	public int addToken(int columnIndex)
	{
		int x = findColumnHeight(columnIndex);
		this.boardModel.getBoard()[x][columnIndex].setState(this.currentPlayer);
		return x;
	}
	
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
	
	public void reset()
	{
		this.currentPlayer = State.RED;
		gameView.initBoard(this.settings[0], this.settings[1]);
		boardModel = new BoardModel(this.settings[0], this.settings[1]);
	}
	
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
	
	public boolean isGameFinished(int x, int y)
	{
		this.nbTokens = 0;
		
		this.LookUpperLeft(x, y);
		this.LookLowerRight(x, y);
		
		if (this.nbTokens + 1 >= this.settings[2])
		{
			return true;
		}
		
		this.nbTokens = 0;

		this.LookUpperRight(x, y);
		this.LookLowerLeft(x, y);
		
		if (this.nbTokens + 1 >= this.settings[2])
		{
			return true;
		}
		
		this.nbTokens = 0;
		
		this.LookRight(x, y);
		this.LookLeft(x, y);
		
		if (this.nbTokens + 1 >= this.settings[2])
		{
			return true;
		}
		
		this.nbTokens = 0;
		
		this.LookDown(x, y);
		
		if (this.nbTokens + 1 >= this.settings[2])
		{
			return true;
		}
		
		return false;
	}
	
	public BoardModel getBoardModel()
	{
		return this.boardModel;
	}
	
	public boolean IsColumnFull(int nbColumn)
	{
		if (this.boardModel.getBoard()[0][nbColumn].getState() != State.EMPTY)
		{
			return true;
		}
		
		return false;
	}
	
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
	
	public void LookUpperLeft(int positionX, int positionY)
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
			
		}
	}
	
	public void LookUpperRight(int positionX, int positionY)
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
			
		}
	}
	
	public void LookLowerLeft(int positionX, int positionY)
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
			
		}
	}
	
	public void LookLowerRight(int positionX, int positionY)
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
			
		}
	}
	
	public void LookDown(int positionX, int positionY)
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
			
		}
	}
	
	public void LookLeft(int positionX, int positionY)
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
			
		}
	}
	
	public void LookRight(int positionX, int positionY)
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
			
		}
	}
	
	public State GetCurrentPlayer()
	{
		return this.currentPlayer;
	}
	
	public MenuView GetMenuView() 
	{
		return this.menuView;
	}

	public int[] GetSettings()
	{
		return this.settings;
	}
	
	public void SetSettings(int[] settings)
	{
		this.settings = settings;
	}

	
	
	public static void main(String[] args)
	{
		@SuppressWarnings("unused")
		BoardController controller = new BoardController();
	}
}
