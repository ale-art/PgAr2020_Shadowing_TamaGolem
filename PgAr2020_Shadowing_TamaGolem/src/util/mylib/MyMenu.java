package util.mylib;

/**
 * Questa classe rappresenta un menu testuale generico a piu' voci Si suppone
 * che la voce per uscire sia sempre associata alla scelta 0 e sia presentata in
 * fondo al menu
 * 
 * @param <T>
 * 
 */

public class MyMenu<T> {

	final protected static String VOCE_USCITA = "0)\tExit";
	final private static String RICHIESTA_INSERIMENTO = "Enter the number of the required option: ";
	protected final static int LUNGHEZZA_RICHIESTA = RICHIESTA_INSERIMENTO.length();
	private String titolo;
	private T[] voci;

	public MyMenu(String titolo, T[] voci) {
		this.titolo = titolo;
		this.voci = voci;
	}

	public int scegli() {
		stampaMenu();
		System.out.println('\n' + VOCE_USCITA + '\n');
		return InputDati.leggiIntero(RICHIESTA_INSERIMENTO, 0, voci.length);
	}

	public void stampaMenu() {
		System.out.println(BelleStringhe.incorniciaCentrato(getTitolo(), LUNGHEZZA_RICHIESTA));
		for (int i = 0; i < voci.length; i++) {
			System.out.println((i + 1) + ")\t" + voci[i]);
		}

	}

	protected String getTitolo() {
		return titolo;
	}

	protected T[] getVoci() {
		return voci;
	}

}
