package tamagolem;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

/**
 * @author Simone
 *
 */
public class TamaGolem implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -2110932066828491892L;

	private static final String THE_BELLY_OF_THE_TAMAGOLEM_IS_FULL = "The belly of the Tamagolem is full";

	/**
	 * The number of stones the {@linkplain TamaGolem} must have <br>
	 * calculated by the following formula <b>{@code P}</B>=⎡
	 * ({@linkplain Element#N}+1)/3⎤+1
	 * 
	 */
	public final static byte P =  (byte) (Math.ceil((Element.N+1)/3)+1);

	/** Initial health number {@value} */
	
	public final static byte V = 10;// QUESTO PARAMETRO E' DA DECIDERE ASSIEME

	public final static byte MIN_HEALTH = 0;

	/** <b>Attribute</B> The {@linkplain TamaGolem} health */
	private int health;

	/**
	 * <b>Attribute</B> It is an {@linkplain ArrayList} containing the
	 * {@code stones } that the {@linkplain TamaGolem} will swallow
	 */
	private ArrayList<Element> stones;

	/**
	 * <b>Constructor</B> <br>
	 * set {@link #health} = {@value #V} <br>
	 * set {@link #stones} {@code array attribute} = {@code stones array passed}
	 * 
	 * @throws IllegalArgumentException
	 *             if the {@code array stones} passed, doesn't have the right number
	 *             of elements({@link #P})
	 */
	public TamaGolem(Collection<Element> stones) {
		setHealth(V);
		setStones(stones);
	}

	/**
	 * <b>DEFAULT Constructor</B> <br>
	 * set {@link #health}={@value #V} & the {@link #stones} array is set
	 * {@code Empty}
	 */
	public TamaGolem() {
		setHealth(V);
		this.stones = new ArrayList<>(P);
	}

	/**
	 * @return the health
	 */
	public int getHealth() {
		return health;
	}

	/**
	 * @param healt
	 *            the health to set
	 */
	public void setHealth(int health) {
		if (health < TamaGolem.MIN_HEALTH || health > TamaGolem.V)
			throw new IllegalArgumentException(
					String.format("Life cannot be set >%d or <%d", TamaGolem.V, TamaGolem.MIN_HEALTH));
		this.health = health;
	}

	/**
	 * @return the stones
	 */
	public ArrayList<Element> getStones() {
		return stones;
	}

	/**
	 * <b>Method</B> that set {@link #stones} attribute to the {@code stones} passed
	 * 
	 * @param stones
	 *            the stone to set
	 * @throws IllegalArgumentException
	 *             if the {@code array stones} passed, doesn't have the right number
	 *             of elements({@link #P})
	 */
	private void setStones(Collection<Element> stones) {
		if (stones.size() != P)
			throw new IllegalArgumentException("There are too many / too few Elements in this Array");
		this.stones = (ArrayList<Element>) stones;
	}

	/**
	 * <b>Method</B> Add an {@linkplain Element} to the {@link #stones} attribute
	 * 
	 * @param stone
	 *            the {@linkplain Element} to add
	 * @throws IllegalArgumentException
	 *             if the {@link #stones} are already full (<b>MAX SIZE</B> is
	 *             {@link  #P})
	 * @return true if the Element is add successfully to the {@link #stones} array,
	 *         false otherwise
	 */
	public boolean addStone(Element stone) {
		if (stones.size() >= P)
			throw new IllegalArgumentException(THE_BELLY_OF_THE_TAMAGOLEM_IS_FULL);
		return stones.add(stone);
	}

	/**
	 * <b>Method</B> Add more {@linkplain Element}s to the {@link #stones} attribute
	 * 
	 * @param stones
	 *            the {@linkplain Element}s to add
	 * @throws IllegalArgumentException
	 *             if the {@link #stones} are already full (<b>MAX SIZE</B> is
	 *             {@link  #P})
	 * @throws IllegalArgumentException
	 *             if the number of elements passed and the numbers of elements
	 *             present in {@link #stones} is greater than {@link  #P}
	 * @return true if the Element is add succesfully to the {@link #stones} array,
	 *         false otherwise
	 */

	public boolean addStones(Collection<Element> stones) {

		if (this.stones.size() >= P)
			throw new IllegalArgumentException(THE_BELLY_OF_THE_TAMAGOLEM_IS_FULL);

		else if (stones.size() + this.stones.size() > TamaGolem.P)
			throw new IllegalArgumentException("Too many stones passed, free slots = " + (P - this.stones.size()));

		return this.stones.addAll(stones);
	}

	/**
	 * @return the {@linkplain Element} of the {@link #stones} in the specified
	 *         position <br>
	 *         (remember, the <tt>first Element</tt> is at {@code index}=<b>0</B>)
	 * @param index
	 *            index of the element to return
	 * @throws IndexOutOfBoundsException
	 *             if the index is out of range(index < 0 || index >= {@link #P})
	 * 
	 */
	public Element getElement(int index) {
		return stones.get(index);
	}

	/**
	 * <b>Method </B>that lowers the {@link #health} of a
	 * {@linkplain TamaGolem}.<br>
	 * If {@code howMuchLowerIt} is a positive number, it will subtract it from the
	 * current <b>{@code health}</b>;<br>
	 * else if {@code howMuchLowerIt} is a negative number, it will add up to the
	 * current <b>{@code health}</b>
	 * 
	 * @param howMuchLowerIt
	 *            is how much to lower the {@link #health}
	 * @return the new <b>{@code health}</b>
	 * @author Simone
	 */
	public int lowerTheLife(int howMuchLowerIt) {
		if (howMuchLowerIt < 0)
			howMuchLowerIt = Math.abs(howMuchLowerIt);
		try {
			setHealth(getHealth() - howMuchLowerIt);
		} catch (IllegalArgumentException e) {
			setHealth(TamaGolem.MIN_HEALTH);
		}
		return getHealth();
	}

	@Override
	public int hashCode() {

		return stones.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof TamaGolem) {
			TamaGolem t = (TamaGolem) obj;
			return getHealth() == t.getHealth() && getStones().containsAll(t.getStones());
		}
		return false;
	}

	/**<b>toString</B>
	 * @return a <tt>String </TT>such as
	 *         <ul>
	 *         This Tamagolem have these Stones in his belly<br>
	 *         1 FIRE<br>
	 *         2 WATER...<bR>
	 *         ...<br>
	 *         else if it is empty, it will return "EMPTY"
	 *         </ul>
	 */
	public String toString() {
		StringBuilder elements = new StringBuilder();
		int i = 1;
		for (Element element : stones) {
			elements.append(i);
			elements.append('\t');
			elements.append(element.toString());
			i++;
		}
		if (elements.length() == 0)
			elements.append("EMPTY");
		return String.format("This Tamagolem have these Stones in his belly%n%s", elements.toString());
	}

	/**
	 * <b>Methods</b> that say if the {@linkplain TamaGolem} is <b>{@code DIE}</b>
	 * 
	 * @return true if the {@link #health} equals {@value #MIN_HEALTH}, false
	 *         otherwise
	 */
	public boolean isDie() {
		return getHealth() == MIN_HEALTH;
	}

}
