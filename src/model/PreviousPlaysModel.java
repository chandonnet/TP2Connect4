package model;

import java.util.Stack;

public class PreviousPlaysModel 
{
	private Stack<PositionReverse> lastPlays;
	
	public PreviousPlaysModel()
	{
		this.lastPlays = new Stack<PositionReverse>();
	}
	
	public PositionReverse ReversePlay()
	{
		if (this.lastPlays.size() == 1)
		{
			this.lastPlays.peek().LastInStack();
		}
		
		return this.lastPlays.pop();
	}
	
	public void AddPlay(PositionReverse position)
	{
		this.lastPlays.push(position);
	}
}
