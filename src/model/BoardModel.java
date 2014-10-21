package model;

/**
 * La classe BoardModel représente un tableau de jeu remplie de jetons vides
 *  avec le hauteur et la largeur voulue.
 */
public class BoardModel 
{
	/**
	 * Le tableau de jeu qui sera rempli de jetons.
	 */
	private TokenModel[][] board = null;
	
	public BoardModel(int height, int width)
	{
		this.board = new TokenModel[height][width];
		for (int i = 0; i < height; i++)
		{
			for (int j = 0; j < width; j++)
			{
				TokenModel newToken = new TokenModel();
				this.board[i][j] = newToken;
			}
		}
	}
	
	/**
	 * Retourne le tableau de jeu.
	 * @return Le tableau de jeu.
	 */
	public TokenModel[][] getBoard()
	{
		return this.board;
	}
	
}
