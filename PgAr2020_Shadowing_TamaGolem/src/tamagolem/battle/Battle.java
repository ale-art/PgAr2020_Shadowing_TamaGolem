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

	private static final int NUMBER_OF_ELEMENTS = Element.N;
	private static final int ROCKS = TamaGolem.P;
	private static final int NUMBERS_OF_TAMAGOLEM = Player.G;
	private int S;
	private ArrayList<Element> sack;
	private Player player1;
	private Player player2;
	private MyMenu battleMenu;

	/**
	 * Constructor request the two players
	 * 
	 * @param player1
	 * @param player2
	 */
	public Battle(Player player1, Player player2) {
		S = this.setS();
		this.sack = sack;
		this.player1 = player1;
		this.player2 = player2;
	}

	/**
	 * number of stones you can find in the sack
	 * 
	 * @return
	 */
	public int getS() {
		return S;
	}

	/**
	 * <b>Method</B> <br>
	 * set {@link #S} calculated by the following formula <br>
	 * {@link #S} =|(G*P*2)/N|*N <br>
	 * 
	 * set N = {@link #NUMBER_OF_ELEMENTS}<br>
	 * {@link #G} is calculated in {@link #Player}<br>
	 * {@link #P} is calculated in {@link #TamaGolem} <br>
	 * 
	 *
	 * @return numbers of stone in the sack
	 */
	public int setS() {
	
		return ((int) Math.ceil(Double.valueOf((2 * NUMBERS_OF_TAMAGOLEM * ROCKS)) / (NUMBER_OF_ELEMENTS)))
				* NUMBER_OF_ELEMENTS;
	}

	/**
	 *
	 * start the battle
	 *
	 */
	public void startBattle() {
		try {
			TamaGolem t1 = new TamaGolem(this.setStones());
			TamaGolem t2 = new TamaGolem(this.setStones());
			do {
				singleBattle(t1, t2);
			} while (t1.isDie() || t2.isDie());
			if (t1.isDie()) {
				this.removeTamaGolem(this.player1);
			} else {
				this.removeTamaGolem(this.player2);
			}

		} catch (NullPointerException e) {
			throw new NullPointerException("No more Tamagolem");
		}
	}

	/**
	 * <b>Method</B> <br>
	 * choose between the elements remained in the
	 * 
	 * @return the collection of Elements
	 */
	public Collection<Element> setStones() {

		return null;
	}

	/**
	 * single battle has started between two TamaGolem
	 * 
	 * @param t1
	 * @param t2
	 */
	public void singleBattle(TamaGolem t1, TamaGolem t2) {
		for (int i = 0; i < ROCKS; i++) {
			Element element1 = t1.getElement(i);
			Element element2 = t2.getElement(i);
			int damage = EquilibriumManager.getDamage(element1, element2);
			if (damage < 0) {
				t1.lowerTheLife(damage);
			} else {
				t2.lowerTheLife(damage);
			}
		}
	}

	/**
	 * remove the TamaGolem when it is dead
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

	public void addWaterElement() {
		try {
			this.checkSack(Element.WATER);
		} catch (NullPointerException e) {
			throw new NullPointerException("No avaible Element");
		}
	}

	public void addFireElement() {
		try {
			this.checkSack(Element.FIRE);
			this.removeElement(Element.FIRE);
		} catch (NullPointerException e) {
			throw new NullPointerException("No avaible Element");
		}
	}

	private void removeElement(Element element) {
		this.sack.remove(element);
	}

	public Element checkSack(Element element) {
		for (Element elements : this.sack) {
			if (elements.equals(element)) {
				return element;
			}
		}
		return null;
	}

	public void addGrassElement() {
		// TODO Auto-generated method stub

	}

	public void addRockElement() {
		// TODO Auto-generated method stub

	}

	public void declareWinner() {

	}
}
