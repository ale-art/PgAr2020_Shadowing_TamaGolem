package tamagolem.player;

public class Player {

	private int currentGolem;

	private String name;

	public final static int G = 5;// PARAMETRO DA DECIDERE ASSIEME


	public Player( String name) {
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
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	public boolean hasLost() {
		return getCurrentGolem() == 0;
	}
}
