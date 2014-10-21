package tests;

import org.junit.Test;

import view.MenuView;
import controller.BoardController;

public class MenuViewTest {

	/**
	 * Fonction qui sert tester le constructeur du menu
	 * @see MenuView
	 */ 
	@Test
	public void constructeurTest() 
	{
		BoardController controller = new BoardController();
		MenuView menu = new MenuView(controller);
		
		assert(menu.GetSettings()[0] == 6);
		assert(menu.GetSettings()[1] == 7);
		assert(menu.GetSettings()[2] == 4);
	}
	
	/**
	 * Fonction qui sert tester le set du menu
	 * @see SetMenu
	 */ 
	@Test
	public void setMenuTest() 
	{
		BoardController controller = new BoardController();
		MenuView menu = new MenuView(controller);
		
		int[] newSettings = new int[3];
		newSettings[0] = -80;
		newSettings[1] = 100;
		newSettings[2] = 999;
		
		assert(menu.GetSettings()[0] == -80);
		assert(menu.GetSettings()[1] == 100);
		assert(menu.GetSettings()[2] == 999);
	}

}
