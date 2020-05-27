package tamagolem;

import java.util.ArrayList;

/**
 * @author Simone
 *
 */
public class TamaGolem {
	/***/
	public final static int P = 5;// QUESTO PARAMETRO E' DA DECIDERE ASSIEME
	/***/
	public final static int V = 10;// QUESTO PARAMETRO E' DA DECIDERE ASSIEME
	/** <b>Attribute</B> The {@linkplain TamaGolem} healt */
	private int healt;

	/**
	 * <b>Attribute</B> It is an {@linkplain ArrayList} containing the
	 * {@code stones } that the {@linkplain TamaGolem} will swallow
	 */
	private ArrayList<Element> stones;

	public TamaGolem(ArrayList<Element> stones) {
		this.healt = V;
		setStones(stones);
	}

	public TamaGolem() {
		this.healt = V;
		this.stones = new ArrayList<>(P);
	}

	/**
	 * @return the healt
	 */
	public int getHealt() {
		return healt;
	}

	/**
	 * @param healt
	 *            the healt to set
	 */
	public void setHealt(int healt) {
		this.healt = healt;
	}

	/**
	 * @return the rock
	 */
	public ArrayList<Element> getStones() {
		return stones;
	}

	/**
	 * @param stones
	 *            the rock to set
	 * @throws IllegalArgumentException
	 */
	public void setStones(ArrayList<Element> stones) {
		if (stones.size() > P)
			throw new IllegalArgumentException("There are too much Stone in this Array");
		this.stones = stones;
	}

	public boolean addStone(Element stone) {
		if (stones.size() > P)
			throw new IllegalArgumentException("The belly of the Tamagolem is full");
		return stones.add(stone);
	}
}
