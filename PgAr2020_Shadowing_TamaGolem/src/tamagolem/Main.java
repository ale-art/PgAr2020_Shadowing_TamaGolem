package tamagolem;

import tamagolem.battle.Battle;
import util.mylib.BelleStringhe;
import util.mylib.MyMenu;

public class Main {

	private static final String DEVELOPERS = BelleStringhe
			.stampaStringaCorniceCentrato(String.format("Cesara Gabriele%nFrosi Alessandra%nGiacomini Simone"));

	public static void main(String args[]) {
		MyMenu<String> menu = new MyMenu<String>("TAMAGOLEM",
				new String[] { "Start a new battle", "Rules", "Story", "Credits" });
		int choose;
		do {
			choose = menu.scegli();

			switch (choose) {
			case 1:
				Battle.startMatch();
				break;
			case 2:
				rules();
				break;
			case 3:
				story();
				break;
			case 4:
				System.out.println(DEVELOPERS);
				break;

			}

		} while (choose != 0);
	}

	public static final void story() {

		System.out.println(STORY);
	}

	public final static void rules() {
		System.out.println(RULES);
	}

	public final static String RULES = BelleStringhe.stampaStringaCorniceCentrato(
			String.format("The game starts by asking the names of the players (they must be different).%n"
					+ "We begin to create the first tamagolem of the first player, inserting \"P\" stones into his stomach.%n"
					+ "Each stone has an internal power of an element (FIRE, WATER, EARTH ...).%n"
					+ "Each element is different from another. It is weaker or stronger than another (interaction between itself is zero).%n"
					+ "These laws of weakness or superiority over another element is called Balance, which is generated randomly at every game.%n"
					+ "When choosing stones to give to a tamagolem, they are extracted from a bag common to both players.%n"
					+ "This means that if the stones of a certain element end up inside the bag, the players will no longer be able to use them.%n"
					+ "Once the second player has also created his first tamagolem, the clash between the two begins.%n"
					+ "Only one tamagolem survives, the other dies forever and in addition his stones vanish with him (each tamagolem has V of life)%n"
					+ "At this point the player who lost the tamagolem is called to summon another one (each player has G tamagolem available).%n"
					+ "when one of the two players has exhausted the tamagolem to be deployed, then he has lost, so the other is the winner"),
			BelleStringhe.GRADO);

	public final static String STORY = BelleStringhe.stampaStringaCorniceCentrato(String.format(
			"The delicate balance of the world has always been based on the interaction between the different natural forces,%n"
					+ "from the mildest to the most destructive.%n"
					+ "Each element in nature has its strengths and its own weaknesses, characteristics that keep our Universe stable and safe.%n"
					+ "For thousands of years, the Academy has been studying techniques for governing these elements: using%n"
					+ "some particular stones and feeding them to strange creatures called TamaGolem, in fact, is%n"
					+ "it is possible to retain the power of the elements to free him as needed.%n"
					+ "For this reason, the students of the Academy usually challenge each other in clandestine Tamagolem fights%n"
					+ "The skill of the fighters, in this case, lies in choosing the right stones%n"
					+ "of the Elements so that the clash has the desired result.%n"
					+ "This choice is not obvious, for the World Balances are changeable,%n"
					+ "and can radically change from one battle to the other.%n"
					+ "Only the TamaGolem that resists until the end decrees the victory of its fighter."),
			'*');

}
