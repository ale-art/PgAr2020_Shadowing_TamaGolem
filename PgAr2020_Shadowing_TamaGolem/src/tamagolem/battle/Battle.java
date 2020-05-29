package tamagolem.battle;

import java.util.ArrayList;
import java.util.Collection;

import it.unibs.fp.mylib.MyMenu;
import tamagolem.Element;
import tamagolem.TamaGolem;
import tamagolem.equilibrium.EquilibriumManager;
import tamagolem.player.Player;

/**
 * 
 * @author Alessandra
 *
 */
public class Battle {
	

	private static final String SET_STONES = "  set the stones in your TamaGolem";
	private static final String NO_MORE_TAMAGOLEM = "No more Tamagolem";
	private static final String WON = "has won";
	private static final String LOST = "has lost";
	private static final String FRAME_NAME="vvvvvvvvvvvv";
	/**
	 * <b> Attribute</B> <br>
	 * the number of stones in the common supply shared between the first
	 * {@linkplain Player} and the second {@linkplain Player}
	 */
	private int S;
	/**
	 * <b>Attribute</B> <br>
	 * the common supply which contains {@link #S} stones
	 */
	private ArrayList<Element> sack;
	/**
	 * <b>Attribute</b> <br>
	 * the number of stones per {@linkplain Element} in the common {@link #sack}
	 * 
	 */
	private int numElement;
	/**
	 * <b>Attribute</B> <br>
	 * the first {@linkplain Player}
	 */
	private Player player1;
	/**
	 * <b>Attribute</B> the second {@linkplain Player}
	 */
	private Player player2;

	/**
	 * </b> Constructor</B> <br>
	 * request the two {@linkplain Player}
	 * 
	 * @param player1
	 * @param player2
	 */
	public Battle(Player player1, Player player2) {
		S = this.setS();
		numElement = this.setNumElement();
		this.sack = initialSack();
		this.player1 = player1;
		this.player2 = player2;
	}

	/**
	 * setting the {@link #numElement} following the given formula <br>
	 * {@link #numElement} ={@link #S}/N
	 * 
	 * @return the number of elements pro type in the {@link #sack}
	 */
	private int setNumElement() {
		int num = this.S / Element.N;
		return num;
	}

	/**
	 * set the common supply at the beginning of the {@linkplain Battle}
	 * 
	 * @return an arraylist of elements
	 */
	private ArrayList<Element> initialSack() {
		ArrayList<Element> elements = new ArrayList<Element>();
		for (int i = 0; i < 3; i++) {
			elements.add(Element.WATER);
			elements.add(Element.FIRE);
			elements.add(Element.GRASS);
			elements.add(Element.STEEL);
			elements.add(Element.ROCK);
		}
		return elements;
	}

	@Deprecated
	public int getS() {
		return S;
	}

	/**
	 * <b>Method</B> <br>
	 * set {@link #S} calculated by the following formula <br>
	 * {@link #S} =|(G*P*2)/N|*N <br>
	 * 
	 * set N = {@link #NUMBER_OF_ELEMENTS}<br>
	 * {@link G} is calculated in {@link Player}<br>
	 * {@link P} is calculated in {@link TamaGolem} <br>
	 * 
	 *
	 * @return numbers of stone in the sack
	 */
	public int setS() {

		return ((int) Math.ceil(Double.valueOf((2 * Element.N * TamaGolem.P)) / (Element.N))) * Element.N;
	}

	/**
	 * <b>Method</B> starting the battle with the first evocation<br>
	 * 
	 * @throws {@code NullPointerException } when one of the two players has no more
	 *                {@linkplain TamaGolem} <br>
	 *
	 */
	public void startBattle() {
		TamaGolem t1 = this.evocation(this.player1);
		TamaGolem t2 = this.evocation(this.player2);
		try {
			do {
				this.singleBattle(t1, t2);
			} while (t1.isDie() || t2.isDie());
			if (t1.isDie()) {
				this.removeTamaGolem(player1);
			} else {
				this.removeTamaGolem(player2);
			}
		} catch (NullPointerException e) {
			throw new NullPointerException(NO_MORE_TAMAGOLEM);
		}
	}

	/**
	 * <b>Method</B> evoking of a {@linkplain TamaGolem} <br>
	 * it is asked to the player to choose 3 stones <br>
	 * 
	 * @param player
	 * @return the TamaGolem
	 */
	public TamaGolem evocation(Player player) {
		TamaGolem t = new TamaGolem();
		System.out.println(FRAME_NAME);
		System.out.println(player.getName().toUpperCase() + SET_STONES);
		System.out.println(FRAME_NAME);
		this.setStones(t);
		System.out.println(t.toString());
		return t;
	}

	/**
	 * <b> Method</B> <br>
	 * begins to set the stones in a {@linkplain TamaGolem} belly <br>
	 * 
	 * @param tamagolem
	 */
	private void setStones(TamaGolem tamagolem) {
		for (int i = 0; i < TamaGolem.P; i++) {
			addSingleStone(tamagolem);
		}
	}

	/**
	 * <b>Method</B> a single stone is added<br>
	 * once as been set the stone is removed by the {@link #sack} <br>
	 * 
	 * @param tamagolem
	 */

	public void addSingleStone(TamaGolem tamagolem) {

		Element element = BattleUtils.readElement();
		try {
			this.checkSack(element);
		} catch (NullPointerException exception) {
			throw new NullPointerException("No avaible element");
		} finally {
			tamagolem.addStone(element);
			this.removeElement(element);
		}
	}

	/**
	 * <b>Method</B> <br>
	 * the battle has started between two {@linkplain TamaGolem}
	 * 
	 * @param t1
	 * @param t2
	 */
	public void singleBattle(TamaGolem t1, TamaGolem t2) {
		//mi serve un metodo per memorizzare la posizione su un dato elemento!!
		//da sostituire i
		Element element1 = t1.getElement(i);
		Element element2 = t2.getElement(i);
		int damage = EquilibriumManager.getDamage(element1, element2);
		if (damage < 0) {
			t1.lowerTheLife(damage);
		} else {
			t2.lowerTheLife(damage);
		}
	}

	public static void main(String args[]) {
		Player player1 = new Player("gino");
		Player player2 = new Player("pino");
		Battle b = new Battle(player1, player2);
		b.startBattle();
	}

	/**
	 * <b> Method</B> <br>
	 * remove the{@linkplain TamaGolem} when it is dead <br>
	 * 
	 * @param player
	 * @return player without a TamaGolem otherwise null
	 */
	public Player removeTamaGolem(Player player) {
		player.aTamaDie();
		if (!player.hasLost()) {
			return player;
		} else {
			return null;
		}

	}

	/**
	 * <b>Method</B> remove the element given to the {@linkplain TamaGolem} <br>
	 * 
	 * @param element
	 */
	private void removeElement(Element element) {
		this.sack.remove(element);
	}

	/**
	 * 
	 * @param element
	 * @return the element chosen from the {@link #sack} null otherwise
	 * 
	 */
	public Element checkSack(Element element) {
		for (Element elements : this.sack) {
			if (elements.equals(element)) {
				return element;
			}
		}
		return null;
	}

	/**
	 * declare the winning {@linkplain Player} and the loser {@linkplain Player}
	 * 
	 */
	public void declareWinner() {
		if (this.player1.hasLost()) {
			System.out.println(this.player1.getName() + LOST);
			System.out.println(this.player2.getName() + WON);
		} else {
			System.out.println(this.player2.getName() + LOST);
			System.out.println(this.player1.getName() + WON);
		}
	}
}
