package tamagolem.player;

import tamagolem.Element;
import tamagolem.TamaGolem;

public class Player {

	private int currentGolem;

	private String name;

	/**
	 * using the provided formula to calculate <b>{@code G}</B>=
	 * ⎡({@linkplain Element#N} - 1)*({@linkplain Element#N} - 2) / (2 *
	 * {@linkplain TamaGolem#P})⎤.
	 * 
	 * 
	 */
	public final static byte G = (byte) Math.ceil((Element.N-1)*(Element.N-2)/(2*TamaGolem.P));

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
