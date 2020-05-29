package tamagolem;

import tamagolem.battle.Battle;
import util.mylib.MyMenu;

public class Main {

	public static void main(String args[]) {
		MyMenu menu = new MyMenu("TAMAGOLEM", new String[] { "Start a new battle", "Rules", "Story", "Credits" });

		int choose;
		do {
			choose = menu.scegli();

			switch (choose) {
			case 1:
				Battle.startMatch();
				break;
			case 2:
				break;
			case 3:
				story();
				break;
			case 4:
				break;
			}

		} while (choose != 0);
	}

	public static final void story() {
		System.out.println(String.format(
				"The delicate balance of the world has always been based on the interaction between the different natural forces,%n"
						+ "from the mildest to the most destructive. Each element in nature has its strengths and its own%n"
						+ "weaknesses, characteristics that keep our Universe stable and safe.%n"
						+ "For thousands of years, the Academy has been studying techniques for governing these elements: using%n"
						+ "some particular stones and feeding them to strange creatures called TamaGolem, in fact, is%n"
						+ "It is possible to retain the power of the elements to free him as needed%n"
						+ "For this reason, the students of the Academy usually challenge each other in clandestine fights%n"
						+ "fra TamaGolem. The skill of the fighters, in this case, lies in choosing the right stones%n"
						+ "of the Elements so that the clash has the desired result. This choice is not obvious,%n"
						+ "for the World Balances are changeable, and can radically change from one%n"
						+ "battle to the other.%n"
						+ "Only the TamaGolem that resists until the end decrees the victory of its fighter."));
	}
}
