package tamagolem.battle;

import it.unibs.fp.mylib.MyMenu;
import tamagolem.Element;

public class BattleUtils {
/**
 * <b>Static Method</B> <br>
 * 
 * @return the element chosen
 */
	public static Element readElement() {
		Element element;
		element=useMenu();
		return element;
		
	}
/**
 * <b>Static Method</b>
 * {@linkplain MyMenu} without the chance to exit<br>
 * the {@linkplain Player} must choose between these five Elements<br>
 * @return
 */
	private static Element useMenu() {
		MyMenu battleMenu = new MyMenu("Chose between avaible rocks",
				new String[] { "Water", "Fire", "Grass", "Rock", "Steel" });
		int choice;
		do {
			choice=battleMenu.scegliSenzaUscita();
			switch (choice) {
			case 1:
				return Element.WATER;
			case 2:
				return Element.FIRE;
			case 3:
				return Element.GRASS;
			case 4:
				return Element.ROCK;
			case 5:
				return Element.STEEL;
			default:
				System.out.println("You must choose an Element");
				break;
			}
		}while(choice!=0);
		return null;
	}

}
