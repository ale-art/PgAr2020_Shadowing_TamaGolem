package tamagolem;

import tamagolem.battle.Battle;
import util.mylib.MyMenu;

public class Main {

	public static void main(String args[]) {
		MyMenu menu = new MyMenu("TAMAGOLEM", new String[] { "Start a new battle" });

		int choose;
		do {
			choose = menu.scegli();

			switch (choose) {
			case 1:
				Battle.startMatch();
				break;
			}

		} while (choose != 0);
	}
}
