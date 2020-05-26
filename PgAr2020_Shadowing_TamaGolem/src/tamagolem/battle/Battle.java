package tamagolem.battle;

import java.util.ArrayList;

import it.unibs.fp.mylib.MyMenu;
import tamagolem.Element;
import tamagolem.TamaGolem;
import tamagolem.equilibrium.EquilibriumManager;

public class Battle {
	private static final int DEAD = 0;

	private static final int NUMBER_OF_ELEMENTS = Element.values().length;

	// P
	private static final int ROCKS = 3; // TamaGolem.getP();
	private int G; 
	private int S;
	private ArrayList<Element> sack;
	private ArrayList<TamaGolem> player1;
	private ArrayList<TamaGolem> player2;
	private MyMenu battleMenu;

	// constructor class battle
	public Battle(/*
					 * ArrayList<Element> sack, ArrayList<TamaGolem> player1, ArrayList<TamaGolem>
					 * player2
					 */) {
		G = this.setG();
		S = this.setS();
		this.sack = sack;
		this.player1 = player1;
		this.player2 = player2;
	}

	public int getG() {
		return G;
	}

	public int setG() {
		int g;
		return g = (int) Math.ceil((Double.valueOf((NUMBER_OF_ELEMENTS - 1) * (NUMBER_OF_ELEMENTS - 2))) / (ROCKS * 2));
	}

	public int getS() {
		return S;
	}

	public int setS() {
		int s;
		return s = ((int) Math.ceil(Double.valueOf((2 * this.getG() * this.ROCKS)) / (NUMBER_OF_ELEMENTS)))
				* NUMBER_OF_ELEMENTS;
	}

	public void startBattle() {
		try {
			boolean exit=false;
			do {
				TamaGolem t1 = this.evocation(this.player1);
				TamaGolem t2 = this.evocation(this.player2);
				do {
					for (int i = 0; i < ROCKS; i++) {
						Element element1 = t1.elements.get(i);
						Element element2 = t2.elements.get(i);
						int winner = EquilibriumManager.whoWin(element1, element2);
						if (winner == 1) {
							t2.setHealth(t2.getHealth() - element1.getDamage(element2));
						} else {
							t1.setHealth(t1.getHealth() - element2.getDamage(element1));
						}
					}
				} while (t1.getHealth() <= DEAD || t2.getHealth() <= DEAD);
				if(t1.getHealth()<=DEAD) {
					this.removeTamaGolem(this.player1);
				}
				else {
					this.removeTamaGolem(this.player2);
				}
			} while (exit);
		} catch (NullPointerException e) {
			throw new NullPointerException("No more Tamagolem");
		}
	}

	public TamaGolem evocation(ArrayList<TamaGolem> player) {
		TamaGolem t = player.get(0);
		return t;
	}
	
	public ArrayList<TamaGolem> removeTamaGolem(ArrayList<TamaGolem> loser) {
		loser.remove(0);
		try {
			return loser;
		} catch (NullPointerException e) {
			//non succede
			throw new NullPointerException("No more TamaGolem");
		}
	}

	public static void main(String args[]) {
		Battle b = new Battle();
		System.out.println(b.getG());
		System.out.println(b.getS());
		System.out.println(Math.ceil(2.1));

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
		}catch(NullPointerException e){
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
}
