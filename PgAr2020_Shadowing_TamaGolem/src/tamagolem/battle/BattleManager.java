package tamagolem.battle;

import tamagolem.Element;
import tamagolem.player.Player;
import util.mylib.InputDati;
import util.mylib.MyMenu;

/**
 * 
 * @author Alessandra
 *
 */
public class BattleManager {

	private static final String NEW_PLAY = "Start a new play";
	private static final String WELCOME = "Welcome to TamaGolemPlay";
	private static final String ENTER_NAME = "Enter your name...";
	private MyMenu mainMenu;

	/**
	 * <b>Constructor</B> <br>
	 * 
	 * 
	 */
	public BattleManager() {
		this.mainMenu = new MyMenu(WELCOME, new String[] { NEW_PLAY });
	}

	/**
	 * <b>Method</B> <br>
	 * manage to start a new {@link #battle}
	 */
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

	/**
	 * <b>Method</B> <br>
	 * to start the battle inserting <br>
	 * {@link player.name}<br>
	 * 
	 * @return the {@code String} name <br>
	 * 
	 */
	private String setNames() {
		return InputDati.leggiStringaNonVuota(ENTER_NAME);
	}

	/**
	 * <b>Method</B> starting a new {@linkplain Battle} until an exception is caught
	 * the battle goes on
	 * 
	 */
	public void startBattle() {
		Battle b = new Battle(new Player(this.setNames()), new Player(this.setNames()));
		try {
			b.startBattle();

		} catch (NullPointerException e) {
			e.getMessage();
			b.declareWinner();
			this.useMainMenu();
		}
	}
}
