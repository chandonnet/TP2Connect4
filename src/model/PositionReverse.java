package model;

/**
 * La classe PositionReverse est une classe pour garder la position d'un �l�ment
 * tout en sachant si cette �l�ment est le dernier �l�ment de la stack.
 * @author Gabriel
 */
public class PositionReverse 
{
	private int x;
	private int y;
	private boolean last;
	
	/**
	 * Constructeur de PositionReverse
	 * @param x  -position en x
	 * @param y  -position en y
	 */
	public PositionReverse(int x, int y)
	{
		this.x = x;
		this.y = y;
		this.last = false;
	}
	
	/**
	 * Fonction qui retourne la valeur de x
	 * @return x
	 */
	public int GetX()
	{
		return this.x;
	}
	
	/**
	 * Fonction qui retourne la valeur de y
	 * @return y
	 */
	public int GetY()
	{
		return this.y;
	}
	
	/**
	 * Fonction qui retourne cette position est la derni�re de la stack
	 * @return last
	 */
	public boolean IsLastInStack()
	{
		return this.last;
	}
	
	/**
	 * Fonction qui sert seulement � Set le last a true pour dire qu'il est le dernier
	 * �l�ment dans la stack.
	 */
	public void LastInStack()
	{
		this.last = true;
	}
}
