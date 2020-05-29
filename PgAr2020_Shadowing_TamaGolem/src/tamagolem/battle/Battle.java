package tamagolem.battle;

import java.util.ArrayList;

import tamagolem.Element;
import tamagolem.TamaGolem;
import tamagolem.equilibrium.EquilibriumManager;
import tamagolem.player.Player;
import util.mylib.BelleStringhe;
import util.mylib.InputDati;

import util.mylib.OutputArray;

/**
 * 
 * @author Alessandra
 *
 */
public class Battle {

	private static final String NONE_HURT = "The element are equals, none has been hurt";
	private static final String DEAD_MESSAGE = "Your TamaGolem is dead";
	private static final String CHANGE = "You must change TamaGolem";
	private static final String DAMAGED_TAMAGOLEM = "%s!  Your TamaGolem has been hurt, the damage is: %d";
	private static final String SET_STONES = " set the stones in your TamaGolem";
	private static final String NO_MORE_TAMAGOLEM = "No more Tamagolem";
	private static final String WON = "has won";
	private static final String LOST = "has lost";

	/**
	 * <b> Attribute</B> <br>
	 * the number of stones in the common supply shared between the first
	 * {@linkplain Player} and the second {@linkplain Player}
	 */
	private static final int S = setS();

	/**
	 * <b>Attribute</b> <br>
	 * the number of stones per {@linkplain Element} in the common {@link #sack}
	 * 
	 */
	private static final int numElement = setNumElement();
	/**
	 * <b>Attribute</B> <br>
	 * the common supply which contains {@link #S} stones
	 */
	private static ArrayList<Element> sack = initialSack();

	/**
	 * <b>Attribute</B> <br>
	 * the first {@linkplain Player}
	 */
	private static Player player1;
	/**
	 * <b>Attribute</B> the second {@linkplain Player}
	 */
	private static Player player2;

	/**
	 * </b> Constructor</B> <br>
	 * request the two {@linkplain Player}
	 * 
	 * @param player1
	 * @param player2
	 */
	public Battle(Player player1, Player player2) {
		Battle.player1 = player1;
		Battle.player2 = player2;
	}

	/**
	 * setting the {@link #numElement} following the given formula <br>
	 * {@link #numElement} ={@link #S}/N
	 * 
	 * @return the number of elements pro type in the {@link #sack}
	 */
	private static final int setNumElement() {
		return S / Element.N;

	}

	/**
	 * set the common supply at the beginning of the {@linkplain Battle}
	 * 
	 * @return
	 * 
	 * @return an {@code ArrayList} of elements
	 */
	private static ArrayList<Element> initialSack() {
		ArrayList<Element> elements = new ArrayList<Element>();
		for (int i = 0; i < numElement; i++) {
			elements.add(Element.WATER);
			elements.add(Element.FIRE);
			elements.add(Element.GRASS);
			elements.add(Element.STEEL);
			elements.add(Element.ROCK);
		}
		return (elements);
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
	private static int setS() {

		return ((int) Math.ceil(Double.valueOf((2 * Player.G * TamaGolem.P)) / (Element.N))) * Element.N;
	}

	/**
	 * <b>Method</B> starting the battle with the first evocation<br>
	 * 
	 * @throws {@code
	 *             NullPointerException } when one of the two players has no more
	 *             {@linkplain TamaGolem} <br>
	 *
	 */
	public void startBattle() {

		TamaGolem t1 = this.evocation(Battle.player1, null);
		TamaGolem t2 = this.evocation(Battle.player2, t1.getStones());
		boolean exit = false;
		try {
			do {
				do {
					this.singleBattle(t1, t2);
				} while (!t1.isDie() && !t2.isDie());
				if (t1.isDie()) {
					// remove and evocation of a new tamagolem
					if (this.removeTamaGolem(Battle.player1) == null) {
						exit = true;
					} else {
						System.out.println(CHANGE);
						t1 = this.evocation(Battle.player1, t2.getStones());
					}

				} else {
					// remove and evocation of a new tamagolem
					if (this.removeTamaGolem(player2) == null) {
						exit = true;
					} else {
						System.out.println(CHANGE);
						t2 = this.evocation(Battle.player2, t1.getStones());
					}
				}
			} while (!exit);
		} catch (NullPointerException e) {
			throw new NullPointerException(NO_MORE_TAMAGOLEM);
		}

		Battle.declareWinner();
	}

	/**
	 * <b>Method</B> evoking of a {@linkplain TamaGolem} <br>
	 * it is asked to the player to choose 3 stones <br>
	 * 
	 * @param player
	 * @return the TamaGolem
	 */
	public TamaGolem evocation(Player player, ArrayList<Element> foeElements) {
		TamaGolem t = new TamaGolem();

		boolean error = false;

		do {
			error = false;

			System.out.println(
					BelleStringhe.stampaStringaCorniceCentrato(player.getName() + SET_STONES, BelleStringhe.GRADO));

			this.setStones(t);

			if (foeElements != null && foeElements.equals(t.getStones())) {
				error = true;
				Battle.sack.addAll(t.getStones());
				t.resetStones();
				System.out.println("WARNING! You cannot insert the same opponent's stone!");
			}

		} while (error);

		System.out.println((t.toString()));
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
			try {
				this.addSingleStone(tamagolem);
			} catch (NullPointerException exception) {
				System.out.println(exception.getMessage());
				i--;
			}
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
		if (this.checkSack(element) == null) {
			throw new NullPointerException("No avaible element,please reinsert");
		}
		tamagolem.addStone(element);
		Battle.removeElement(element);
	}

	/**
	 * <b>Method</B> <br>
	 * the battle has started between two {@linkplain TamaGolem}
	 * 
	 * @param t1
	 * @param t2
	 */
	public void singleBattle(TamaGolem t1, TamaGolem t2) {
		for (int i = 0; i < 3; i++) {
			Element element1 = t1.getElement(i);
			Element element2 = t2.getElement(i);
			StringBuilder outPut = new StringBuilder();
			int damage = EquilibriumManager.getDamage(element1, element2);
			if (damage == 0) {
				outPut.append(String.format("%s VS %s%n%n", element1.toString(), element2.toString()));
				outPut.append(String.format(NONE_HURT + "%n"));

			} else if (damage < 0) {
				t1.lowerTheLife(damage);
				outPut.append(String.format("%s VS %s%n%n", element1.toString(), element2.toString()));
				outPut.append(String.format(DAMAGED_TAMAGOLEM + "%n", player1.getName(), damage));

			} else {

				t2.lowerTheLife(damage);
				outPut.append(String.format("%s VS %s%n%n", element2.toString(), element1.toString()));

				outPut.append(String.format(DAMAGED_TAMAGOLEM + "%n", player2.getName(), -damage));
			}
			InputDati.isInvioPremuto(BelleStringhe.stampaStringaCorniceCentrato(outPut.toString()),
					OutputArray.PROSSIMO_ELEMENTO);
			if (t1.isDie() || t2.isDie())
				return;
		}
	}

	/**
	 * <b> Method</B> <br>
	 * remove the{@linkplain TamaGolem} when it is dead <br>
	 * 
	 * @param player
	 * @return player without a TamaGolem otherwise null
	 */
	public Player removeTamaGolem(Player player) {
		System.out.println(
				BelleStringhe.stampaStringaCorniceCentrato(String.format("%s, %s", player.getName(), DEAD_MESSAGE)));
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
	private static void removeElement(Element element) {
		Battle.sack.remove(element);
	}

	/**
	 * 
	 * @param element
	 * @return the element chosen from the {@link #sack} null otherwise
	 * 
	 */
	public Element checkSack(Element element) {
		for (Element elements : Battle.sack) {
			if (elements.equals(element)) {
				return element;
			}
		}
		return null;
	}

	public static void startMatch() {
		System.out.println("Welcome on a new match!");
	
		Player player1 = new Player(InputDati.leggiStringaNonVuota("Insert the name of the first player: "));
		Player player2 = new Player(InputDati.leggiStringaNonVuota("Insert the name of the second player: "));
	
		Battle battle = new Battle(player1, player2);
	
		EquilibriumManager.calcEquilibrium();
		battle.startBattle();
	}

	/**
	 * declare the winning {@linkplain Player} and the loser {@linkplain Player}
	 * 
	 */
	public static void declareWinner() {
		if (player1.hasLost()) {
			String output = (String.format("%s %s%n", player1.getName(), LOST))
					+ (String.format("%s %s", player2.getName(), WON));
			System.out.println(BelleStringhe.stampaStringaCorniceCentrato(output, '§'));
		} else {
			String output = (String.format("%s %s%n", player2.getName(), LOST))
					+ (String.format("%s %s", player1.getName(), WON));
			System.out.println(BelleStringhe.stampaStringaCorniceCentrato(output, '§'));
		}
		EquilibriumManager.showMatrix();
	}

}
