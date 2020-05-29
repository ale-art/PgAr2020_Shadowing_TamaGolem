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
	public final static byte G = (byte) Math.ceil((Element.N - 1) * (Element.N - 2) / (2 * TamaGolem.P));

	/**
	 * <b>Constructor</B> <br>
	 * set {@link #currentGolem} = {@linkplain #G} <br>
	 * set {@link #name} = {@code name passed}
	 * 
	 * @throws IllegalArgumentException
	 *             if the {@code name} passed, is empty
	 */
	public Player(String name) {
		super();
		setCurrentGolem(G);
		setName(name);
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

	private void setCurrentGolem(int currentGolem) {
		if (currentGolem < 0 || currentGolem > G)
			throw new IllegalArgumentException(
					String.format("The Player's TamaGolems number cannot be set >%d or <%d", G, 0));
		this.currentGolem = currentGolem;
	}

	/**
	 * @param name
	 *            the name to set
	 * @throws IllegalArgumentException
	 *             if the name is an empty {@linkplain String}
	 */
	public void setName(String name) {
		name = name.trim();
		if (name.length() > 0)
			this.name = name;
		else
		throw new IllegalArgumentException("The name cannot be empty");
	}

	/**
	 * <b>Methods</b> that say if the {@linkplain Player} has <b>{@code lost}</b>
	 * 
	 * @return true if the {@link #currentGolem} equals 0, false otherwise
	 */
	public boolean hasLost() {
		return getCurrentGolem() == 0;
	}

	/**
	 * <b>Method</B> to use when a {@linkplain TamaGolem} die<br>
	 * this method low the {@link #currentGolem} -1
	 * 
	 * @return the attribute {@link #currentGolem} update
	 */
	public int aTamaDie() {

		try {
			setCurrentGolem(getCurrentGolem() - 1);
		} catch (IllegalArgumentException e) {
			setCurrentGolem(0);
		}
		return getCurrentGolem();
	}
}
