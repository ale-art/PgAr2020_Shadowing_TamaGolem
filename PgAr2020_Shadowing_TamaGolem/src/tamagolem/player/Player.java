package tamagolem.player;

public class Player {

	private int currentGolem;

	private String name;

	/**
	 * using the provided formula to calculate G: |(N-1)*(N-2)/P*2| N is given: 5 P
	 * is calculated in TamaGolem Class
	 * 
	 * 
	 */
	public final static int G = 2;

	public Player(String name) {
		super();
		this.currentGolem = G;
		this.name = name;
	}

	/**
	 * @return the currentGolem
	 */
	public int getCurrentGolem() {
		return currentGolem;
	}

	/**
	 * set the number of TamaGolem
	 */
	public void setCurrentGolem(int currentGolem) {
		this.currentGolem = currentGolem;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	public boolean hasLost() {
		return getCurrentGolem() == 0;
	}
}
