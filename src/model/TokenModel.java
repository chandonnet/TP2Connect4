package model;

/**
 * Cette classe mod�le repr�sente un jeton dans le jeu
 * @author Robin
 */
public class TokenModel 
{
	/**
	 * �num�ration des �tats possibles d'un jeton
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
	 * �tat du jeton
	 */
	private State state;
	
	/**
	 * Constructeur par d�faut d'un jeton
	 */
	public TokenModel()
	{
		state = State.EMPTY;
	}
	
	/**
	 * Constructeur qui initialise les propri�t�s du jeton selon les valeurs pass�es en param�tres
	 * @param state
	 */
	public TokenModel(State state)
	{
		this.state = state;
	}

	/**
	 * Retourne l'�tat du jeton
	 * @return state
	 */
	public State getState() 
	{
		return state;
	}

	/**
	 * Change l'�tat du jeton
	 * @param state
	 */
	public void setState(State state) 
	{
		this.state = state;
	}
}