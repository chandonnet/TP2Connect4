package model;

public class PositionReverse 
{
	private int x;
	private int y;
	private boolean last;
	
	public PositionReverse(int x, int y)
	{
		this.x = x;
		this.y = y;
		this.last = false;
	}
	
	public int GetX()
	{
		return this.x;
	}
	
	public int GetY()
	{
		return this.y;
	}
	
	public boolean IsLastInStack()
	{
		return this.last;
	}
	
	public void LastInStack()
	{
		this.last = true;
	}
}
