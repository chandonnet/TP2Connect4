package tests;

import model.TokenModel;
import model.TokenModel.State;

import org.junit.Test;

public class TokenModelTest {

	/**
	 * Fonction qui sert tester les get/set du Token fonctionne bien avec State
	 * @see State
	 */ 
	@Test
	public void getSetStateTest() 
	{
		TokenModel tokenModelTest = new TokenModel(State.EMPTY);
		
		tokenModelTest.setState(State.RED);
		
		assert(State.RED == tokenModelTest.getState());
	}
	
	/**
	 * Fonction qui sert tester la constructeur par défaut.
	 * @see TokenModel
	 */ 
	@Test
	public void defaultConstructeurTest() 
	{
		TokenModel tokenModelTest = new TokenModel();
		
		assert(State.EMPTY == tokenModelTest.getState());
	}
	
	/**
	 * Fonction qui sert tester le constructeur avec paramètre
	 * @see TokenModel
	 */ 
	@Test
	public void constructeurTest() 
	{
		TokenModel tokenModelTest = new TokenModel(State.YELLOW);
		
		assert(State.YELLOW == tokenModelTest.getState());
	}

}
