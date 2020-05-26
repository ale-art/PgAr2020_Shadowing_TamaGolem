package tamagolem.battle;

import it.unibs.fp.mylib.MyMenu;
import tamagolem.Element;

public class BattleManager {

	private MyMenu mainMenu;
	private static MyMenu battleMenu;
	private Battle battle;

	public BattleManager() {
		this.mainMenu = new MyMenu("Welcome to TamaGolemPlay", new String[] { "Start a new play" });
		this.battleMenu = new MyMenu("Chose between avaible rocks",
				new String[] { "Water", "Fire", "Grass", "Rock", "Steel" });
	}
//
//	private void startProgram() {
//		this.useMainMenu();
//	}

	private void useMainMenu() {
		int choice;
		do {
			choice = this.mainMenu.scegli();
			switch (choice) {
			case 1:
				this.startBattle();
				break;
			default:
				break;
			}
		} while (choice != 0);

	}

	public void menuElement() {
		int choice;
		do {
			choice = this.battleMenu.scegli();
			switch (choice) {
			case 1:
				this.addWater();
				break;
			case 2:
				this.addFire();
				break;
			case 3:
				this.addGrass();
				break;
			case 4:
				this.addRock();
				break;
			case 5:
				return Element.STEEL;
			case 0:
				System.out.println("You must choose an element");
				choice = 6;
			}
		} while (choice != 0);
	}

	private void addRock() {
		try {
			this.battle.addRockElement();
		} catch (NullPointerException e) {
			e.getMessage();
		}
		
	}
	private void addGrass() {
		try {
			this.battle.addGrassElement();
		} catch (NullPointerException e) {
			e.getMessage();
		}
	}
	private void addFire() {

		try {
			this.battle.addFireElement();
		} catch (NullPointerException e) {
			e.getMessage();
		}
	}

	private void addWater() {
		try {
			this.battle.addWaterElement();
		} catch (NullPointerException e) {
			e.getMessage();
		}

	}

	public void startBattle() {
		try {
			this.battle.startBattle();
		} catch (NullPointerException e) {
			e.getMessage();
			this.battle.declareWinner();
			this.useMainMenu();
		}
	}
}
