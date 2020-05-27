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

	/** The number of stones the {@linkplain TamaGolem} must have {@value} */
	public final static byte P = 5;// QUESTO PARAMETRO E' DA DECIDERE ASSIEME

	/** Initial healt number {@value} */
	public final static byte V = 10;// QUESTO PARAMETRO E' DA DECIDERE ASSIEME

	public final static byte MIN_HEALT = 0;

	/** <b>Attribute</B> The {@linkplain TamaGolem} healt */
	private int healt;

	/**
	 * <b>Attribute</B> It is an {@linkplain ArrayList} containing the
	 * {@code stones } that the {@linkplain TamaGolem} will swallow
	 */
	private ArrayList<Element> stones;

	/**
	 * <b>Constructor</B> <br>
	 * set {@link #healt} = {@value #V} <br>
	 * set {@link #stones} {@code array attribute} = {@code stones array passed}
	 * 
	 * @throws IllegalArgumentException
	 *             if the {@code array stones} passed, doesn't have the right number
	 *             of elements({@value #P})
	 */
	public TamaGolem(Collection<Element> stones) {
		setHealt(V);
		setStones(stones);
	}

	/**
	 * <b>DEFAULT Constructor</B> <br>
	 * set {@link #healt}={@value #V} & the {@link #stones} array is set
	 * {@code Empty}
	 */
	public TamaGolem() {
		setHealt(V);
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
	private void setHealt(int healt) {
		if (healt < TamaGolem.MIN_HEALT || healt > TamaGolem.V)
			throw new IllegalArgumentException(
					String.format("Life cannot be set >%d or <%d", TamaGolem.V, TamaGolem.MIN_HEALT));
		this.healt = healt;
	}

	/**
	 * @return the stones
	 */
	public ArrayList<Element> getStones() {
		return stones;
	}

	/**
	 * <b>Metod</B> that set {@link #stones} attribute to the {@code stones} passed
	 * 
	 * @param stones
	 *            the stone to set
	 * @throws IllegalArgumentException
	 *             if the {@code array stones} passed, doesn't have the right number
	 *             of elements({@value #P})
	 */
	private void setStones(Collection<Element> stones) {
		if (stones.size() != P)
			throw new IllegalArgumentException("There are too many / too few Elements in this Array");
		this.stones = (ArrayList<Element>) stones;
	}

	/**
	 * <b>Metod</B> Add an {@linkplain Element} to the {@link #stones} attribute
	 * 
	 * @param stone
	 *            the {@linkplain Element} to add
	 * @throws IllegalArgumentException
	 *             if the {@link #stones} are already full (<b>MAX SIZE</B> is
	 *             {@value #P})
	 * @return true if the Element is add succesfully to the {@link #stones} array,
	 *         false otherwise
	 */
	public boolean addStone(Element stone) {
		if (stones.size() >= P)
			throw new IllegalArgumentException(THE_BELLY_OF_THE_TAMAGOLEM_IS_FULL);
		return stones.add(stone);
	}

	/**
	 * <b>Metod</B> Add more {@linkplain Element}s to the {@link #stones} attribute
	 * 
	 * @param stones
	 *            the {@linkplain Element}s to add
	 * @throws IllegalArgumentException
	 *             if the {@link #stones} are already full (<b>MAX SIZE</B> is
	 *             {@value #P})
	 * @throws IllegalArgumentException
	 *             if the number of elements passed and the numbers of elements
	 *             present in {@link #stones} is greater than {@value #P}
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
	 *             if the index is out of range(index < 0 || index >= {@value #P})
	 * 
	 */
	public Element getElement(int index) {
		return stones.get(index);
	}

	/**
	 * <b>Method </B>that lowers the {@link #healt} of a {@linkplain TamaGolem}.<br>
	 * If {@code howMuchLowerIt} is a positive number, it will subtract it from the
	 * current <b>{@code healt}</b>;<br>
	 * else if {@code howMuchLowerIt} is a negative number, it will add up to the
	 * current <b>{@code healt}</b>
	 * 
	 * @param howMuchLowerIt
	 *            is how much to lower the {@link #healt}
	 * @return the new <b>{@code healt}</b>
	 * @author Simone
	 */
	public int lowerTheLife(int howMuchLowerIt) {
		if (howMuchLowerIt < 0)
			howMuchLowerIt = Math.abs(howMuchLowerIt);
		try {
			setHealt(getHealt() - howMuchLowerIt);
		} catch (IllegalArgumentException e) {
			setHealt(TamaGolem.MIN_HEALT);
		}
		return getHealt();
	}

	@Override
	public int hashCode() {

		return stones.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof TamaGolem) {
			TamaGolem t = (TamaGolem) obj;
			return getHealt() == t.getHealt() && getStones().containsAll(t.getStones());
		}
		return false;
	}

	
	/**
	 * @return a <tt>String </TT>like
	 *         <ul>
	 *         This Tamagolem have these Stones in his belly<br>
	 *         1 FIRE<br>
	 *         2 WATER...<bR>
	 *         ...<br>
	 *         else if it empty, it will return "EMPTY"
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
	 * <b>Metods</b> that say if the {@linkplain TamaGolem} is <b>{@code DIE}</b>
	 * 
	 * @return true if the {@link #healt} equals {@value #MIN_HEALT}, false
	 *         otherwise
	 */
	public boolean isDie() {
		return getHealt() == MIN_HEALT;
	}

}
