package model;

import java.util.Stack;

/**
 * La classe PreviousPlaysModel représente une stack de jeu remplie des actions précédentes
 * qui peuvent être remises à jeu.
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
	 * Fonction qui retourne la dernière action qui a été faite
	 * @return lastPlays.pop() dernière action
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
	 * Fonction qui ajoute la dernière action dans la stack.
	 * @param position  -La position de la nouvelle action
	 */
	public void AddPlay(PositionReverse position)
	{
		this.lastPlays.push(position);
	}
}
