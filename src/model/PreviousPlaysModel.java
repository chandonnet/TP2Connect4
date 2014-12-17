package model;

import java.util.Stack;

/**
 * La classe PreviousPlaysModel repr�sente une stack de jeu remplie des actions pr�c�dentes
 * qui peuvent �tre remises � jeu.
 * @author Gabriel
 */
public class PreviousPlaysModel 
{
	private Stack<PositionReverse> lastPlays;
	
	/**
	 * Constructeur de PreviousPlaysModel
	 */
	public PreviousPlaysModel()
	{
		this.lastPlays = new Stack<PositionReverse>();
	}
	
	/**
	 * Fonction qui retourne la derni�re action qui a �t� faite
	 * @return lastPlays.pop() derni�re action
	 */
	public PositionReverse ReversePlay()
	{
		if (this.lastPlays.size() == 1)
		{
			this.lastPlays.peek().LastInStack();
		}
		
		return this.lastPlays.pop();
	}
	
	/**
	 * Fonction qui ajoute la derni�re action dans la stack.
	 * @param position  -La position de la nouvelle action
	 */
	public void AddPlay(PositionReverse position)
	{
		this.lastPlays.push(position);
	}
}
