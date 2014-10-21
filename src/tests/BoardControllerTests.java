package tests;

import static org.junit.Assert.*;
import model.TokenModel.State;

import org.junit.Test;

import controller.BoardController;

public class BoardControllerTests {

	@Test
	public void addTokenTest() 
	{
		BoardController controller = new BoardController();
		
		controller.addToken(0);
		
		assertEquals("Token did not change color. ", State.RED, controller.getBoardModel().getBoard()[5][0].getState());
	}
	
	@Test
	public void findColumnHeightTest() 
	{
		BoardController controller = new BoardController();
		
		controller.addToken(0);
		
		assertEquals("Controller did not find the right height. ", 4, controller.findColumnHeight(0));
		
		controller.addToken(0);
	
		
		assertEquals("Controller did not find the right height. ", 3, controller.findColumnHeight(0));
		assertEquals("Controller did not find the right height. ", 5, controller.findColumnHeight(1));
	}
	
	@Test
	public void resetGameTest() 
	{
		BoardController controller = new BoardController();
		
		controller.addToken(0);
		controller.reset();
		
		assertEquals("The player was not reset. ", State.RED, controller.GetCurrentPlayer());
		
		int count = 0;
		for (int i = 0; i < 6; i++)
		{
			for (int j = 0; j < 7; j++)
			{
				if (controller.getBoardModel().getBoard()[i][j].getState() != State.EMPTY)
				{
					count++;
				}
			}
		}
		
		assertEquals("The Board was not reset. ", 0, count);
	}
	
	@Test
	public void changePlayerTest()
	{
		BoardController controller = new BoardController();
		controller.changePlayer();
		assertEquals("The player was not changed. ", State.YELLOW, controller.GetCurrentPlayer());
	}
	
	@Test
	public void isGameFinishedTest()
	{
		BoardController controller = new BoardController();
		
		controller.addToken(0);
		controller.addToken(0);
		controller.addToken(1);
		controller.addToken(1);
		controller.addToken(2);
		controller.addToken(2);
		controller.addToken(3);
		
		assertEquals("The game was not set to finished. ", true, controller.isGameFinished(5, 3));
	}
	
	@Test
	public void isColumnFullTest()
	{
		BoardController controller = new BoardController();
		
		controller.addToken(0);
		controller.addToken(0);
		controller.addToken(0);
		controller.addToken(0);
		controller.addToken(0);
		controller.addToken(0);
		
		assertEquals("The column was not set to full ", true, controller.IsColumnFull(0));
	}
	
	@Test
	public void isBoardFullTest()
	{
		BoardController controller = new BoardController();
		
		controller.addToken(0);
		controller.addToken(0);
		controller.addToken(0);
		controller.addToken(0);
		controller.addToken(0);
		controller.addToken(0);
		
		controller.addToken(1);
		controller.addToken(1);
		controller.addToken(1);
		controller.addToken(1);
		controller.addToken(1);
		controller.addToken(1);
		
		controller.addToken(2);
		controller.addToken(2);
		controller.addToken(2);
		controller.addToken(2);
		controller.addToken(2);
		controller.addToken(2);
		
		controller.addToken(3);
		controller.addToken(3);
		controller.addToken(3);
		controller.addToken(3);
		controller.addToken(3);
		controller.addToken(3);
		
		controller.addToken(4);
		controller.addToken(4);
		controller.addToken(4);
		controller.addToken(4);
		controller.addToken(4);
		controller.addToken(4);
		
		controller.addToken(5);
		controller.addToken(5);
		controller.addToken(5);
		controller.addToken(5);
		controller.addToken(5);
		controller.addToken(5);
		
		controller.addToken(6);
		controller.addToken(6);
		controller.addToken(6);
		controller.addToken(6);
		controller.addToken(6);
		controller.addToken(6);
		
		assertEquals("The board was not set to full ", true, controller.IsBoardFull());
	}
}
