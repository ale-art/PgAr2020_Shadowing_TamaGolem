package tamagolem.battle;


import tamagolem.Element;
import util.mylib.InputDati;
import util.mylib.MyMenu;

public class BattleUtils {
	/**
	 * <b>Static Method</B> <br>
	 * 
	 * @return the element chosen
	 */
	public static Element readElement() {
		return useMenu();
	}

	/**
	 * <b>Static Method</b> {@linkplain MyMenu} without the chance to exit<br>
	 * the {@linkplain Player} must choose between these five Elements<br>
	 * 
	 * @return
	 */
	private static Element useMenu() {
		MyMenu<Element> battleMenu = new MyMenu<Element>("Avaible Element", Element.values());
		int choice;
		battleMenu.stampaMenu();
		choice = (InputDati.leggiIntero("Choose an element > ", 1, Element.N));
		return Element.values()[choice - 1];

	}
}
