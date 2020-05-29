package util.mylib;

/**
 * Classe che permette l'utilizzo di un menu' in Console, ma al posto dei
 * numeri, per la scelta si usano le <tt>lettere </tt>e lo <tt>0</tt>
 * 
 * @author Simone
 * @param <T>
 */
public class CharMenu<T> extends MyMenu<T> {
	/** Ci sono {@value} lettere nell'alfabeto */
	private static final int VOCI_DISPONIBILI = 26;
	/**{@linkplain String} di avviso che non sara' possibile usare un {@linkplain CharMenu}*/
	private static final String IMPOSSIBILE_CREARE_CHARMENU = "Impossibile creare un CharMenu con piu' di "
			+ VOCI_DISPONIBILI + " voci. Verra' usato MyMenu";
	final private static String RICHIESTA_INSERIMENTO = "Digita il carattere dell'opzione desiderata > ";
	/**
	 * <b>attributo </b>che capisce se e' possibile creare un {@linkplain CharMenu}. In caso non sia
	 * possibile, esso crea un normale {@link MyMenu}
	 */
	final private boolean isPossibile;
	/**<b>Attiributo</b> Sono le lettere che si possono inserire*/
	final private String ammissibili = creaAmmissibili();

	private CharMenu(String titolo, T[] voci, boolean isPossibile) {
		super(titolo, voci);
		this.isPossibile = isPossibile;
	}

	/**
	 * <b>Costrutture </b> per l'istanziamento di un oggetto {@linkplain CharMenu},
	 * <br>
	 * ATTENZIONE, SE LE VOCI SONO PIU' DI {@value #VOCI_DISPONIBILI}, non verra'
	 * istanziato un {@linkplain CharMenu}, ma un {@linkplain MyMenu}
	 * 
	 * @param titolo,
	 *            e' il titolo da dare al menu'
	 * @param voci,
	 *            sono le varie voci del menu'
	 * @author Simone
	 */
	public CharMenu(String titolo, T[] voci) {

		this(titolo, voci, !(voci.length > VOCI_DISPONIBILI));
	}

	@Override
	public void stampaMenu() {
		if (!isPossibile) {
			System.out.println(IMPOSSIBILE_CREARE_CHARMENU);
			super.stampaMenu();
		} else {
			System.out.println(BelleStringhe.incorniciaCentrato(getTitolo(), LUNGHEZZA_RICHIESTA));
			for (char a = 'A', i = 0; i < getVoci().length; a++, i++) {
				System.out.println((a) + ")\t" + getVoci()[i]);
			}
		}
	}

	@Override
	public int scegli() {
		if (!isPossibile)
			return super.scegli();
		else {
			stampaMenu();
			System.out.println('\n' + VOCE_USCITA + '\n');
			return InputDati.leggiUpperChar(RICHIESTA_INSERIMENTO, ammissibili);

		}

	}
/**<b>Metodo</b> che crea una {@linkplain String} con all'interno tutte le lettere disponibili la scelta
 * @see #scegli() 
 * @author Simone*/
	private String creaAmmissibili() {
		StringBuilder ammissibili = new StringBuilder();
		for (char i = 'A'; i < 'A' + getVoci().length; i++) {
			ammissibili.append(i);
		}
		ammissibili.append(0);
		return ammissibili.toString();
	}
}
