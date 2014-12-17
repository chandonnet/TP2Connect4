package model;

/**
 * La classe PositionReverse est une classe pour garder la position d'un élément
 * tout en sachant si cette élément est le dernier élément de la stack.
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
	 * Fonction qui retourne cette position est la dernière de la stack
	 * @return last
	 */
	public boolean IsLastInStack()
	{
		return this.last;
	}
	
	/**
	 * Fonction qui sert seulement à Set le last a true pour dire qu'il est le dernier
	 * élément dans la stack.
	 */
	public void LastInStack()
	{
		this.last = true;
	}
}
