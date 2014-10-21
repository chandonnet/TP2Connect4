package model;

/**
 * Cette classe modèle représente un jeton dans le jeu
 */
public class TokenModel 
{
	/**
	 * Énumération des états possibles d'un jeton
	 */
	public enum State 
	{ 
		EMPTY("./img/emptySlot.png"), YELLOW("./img/yellowSlot.png"), RED("./img/redSlot.png");
		private String value;
		private State(String value)
		{
			this.value = value;
		}
		public String getValue()
		{
			return this.value;
		}
	};
	
	/**
	 * État du jeton
	 */
	private State state;
	
	/**
	 * Constructeur par défaut d'un jeton
	 */
	public TokenModel()
	{
		state = State.EMPTY;
	}
	
	/**
	 * Constructeur qui initialise les propriétés du jeton selon les valeurs passées en paramètres
	 * @param state État par défaut voulu
	 */
	public TokenModel(State state)
	{
		this.state = state;
	}

	/**
	 * Retourne l'état du jeton
	 * @return state Retourne l'état du jeton voulu
	 */
	public State getState() 
	{
		return state;
	}

	/**
	 * Change l'état du jeton
	 * @param state État voulu
	 */
	public void setState(State state) 
	{
		this.state = state;
	}
}
