package util.mylib;

import java.util.*;

/** Classe utile per ricevere dati input da tastiera in console */
public class InputDati {
	private static Scanner lettore = creaScanner();

	private final static String ERRORE_FORMATO = "Attenzione: il dato inserito non e' nel formato corretto";
	private final static String ERRORE_MINIMO = "Attenzione: e' richiesto un valore maggiore o uguale a ";
	private final static String ERRORE_STRINGA_VUOTA = "Attenzione: non hai inserito alcun carattere";
	private final static String ERRORE_MASSIMO = "Attenzione: e' richiesto un valore minore o uguale a ";
	private final static String MESSAGGIO_AMMISSIBILI = "Attenzione: i caratteri ammissibili sono: ";

	public final static char RISPOSTA_SI = 'S';
	public final static char RISPOSTA_NO = 'N';

	private static Scanner creaScanner() {
		Scanner creato = new Scanner(System.in);
		creato.useDelimiter(System.getProperty("line.separator"));
		return creato;
	}

	/**
	 * Legge una {@linkplain String} da input senza nessun controllo
	 * 
	 * @param messaggio
	 *            da visualizzare in {@code Console}
	 */
	public static String leggiStringa(String messaggio) {
		System.out.print(messaggio);
		return lettore.next();
	}

	/**
	 * Legge una {@linkplain String} da input, controllando che non sia vuota
	 * 
	 * @param messaggio
	 *            da visualizzare in {@code Console}
	 */
	public static String leggiStringaNonVuota(String messaggio) {
		boolean finito = false;
		String lettura = null;
		do {
			lettura = leggiStringa(messaggio);
			lettura = lettura.trim();
			if (lettura.length() > 0)
				finito = true;
			else
				System.out.println(ERRORE_STRINGA_VUOTA);
		} while (!finito);

		return lettura;
	}

	/**
	 * Legge un carattere da input senza nessun controllo
	 * 
	 * @param messaggio
	 *            da visualizzare in {@code Console}
	 */
	public static char leggiChar(String messaggio) {
		boolean finito = false;
		char valoreLetto = '\0';
		do {
			String lettura = leggiStringaNonVuota(messaggio);
			valoreLetto = lettura.charAt(0);
			finito = true;

		} while (!finito);
		return valoreLetto;
	}

	/**
	 * Legge un carattere da input, controllando che sia all'interno della
	 * {@linkplain String} {@code ammissibili}
	 * 
	 * @param messaggio
	 *            da visualizzare in {@code Console}
	 * @param ammissibili
	 *            e' la {@linkplain String} che contiene tutti i char disponibili
	 */
	public static char leggiUpperChar(String messaggio, String ammissibili) {
		boolean finito = false;
		char valoreLetto = '\0';
		do {
			valoreLetto = leggiChar(messaggio);
			valoreLetto = Character.toUpperCase(valoreLetto);
			if (ammissibili.indexOf(valoreLetto) != -1)
				finito = true;
			else
				System.out.println(MESSAGGIO_AMMISSIBILI + ammissibili);
		} while (!finito);
		return valoreLetto;
	}

	/**
	 * Legge un intero da input, controllando che ciò che si inserisce sia testo
	 * 
	 * @param messaggio
	 *            da visualizzare in {@code Console}
	 */
	public static Integer leggiIntero(String messaggio) {
		boolean finito = false;
		int valoreLetto = 0;
		do {
			try {
				valoreLetto = Integer.parseInt(leggiStringaNonVuota(messaggio));
				finito = true;
			} catch (InputMismatchException | NumberFormatException e) {
				System.out.println(ERRORE_FORMATO);
				// String daButtare = lettore.next();
			}
		} while (!finito);
		return valoreLetto;
	}

	/**
	 * Legge un intero da input Positivo {@code >=1}
	 * 
	 * @param messaggio
	 *            da visualizzare in {@code Console}
	 */
	public static int leggiInteroPositivo(String messaggio) {
		return leggiInteroConMinimo(messaggio, 1);
	}

	/**
	 * Legge un intero da input che non sia negativo {@code >=0}
	 * 
	 * @param messaggio
	 *            da visualizzare in {@code Console}
	 */
	public static int leggiInteroNonNegativo(String messaggio) {
		return leggiInteroConMinimo(messaggio, 0);
	}

	/**
	 * Legge un intero da input, assicurandosi che sia inserito un valore
	 * {@code >=minimo}
	 * 
	 * @param messaggio
	 *            da visualizzare in {@code Console}
	 */
	public static int leggiInteroConMinimo(String messaggio, int minimo) {
		boolean finito = false;
		int valoreLetto = 0;
		do {
			valoreLetto = leggiIntero(messaggio);
			if (valoreLetto >= minimo)
				finito = true;
			else
				System.out.println(ERRORE_MINIMO + minimo);
		} while (!finito);

		return valoreLetto;
	}

	/**
	 * Legge un intero da input, assicurandosi che
	 * 
	 * @param messaggio
	 *            da visualizzare in {@code Console}
	 */
	public static int leggiIntero(String messaggio, int minimo, int massimo) {
		boolean finito = false;
		int valoreLetto = 0;
		do {
			valoreLetto = leggiIntero(messaggio);
			if (valoreLetto >= minimo && valoreLetto <= massimo)
				finito = true;
			else if (valoreLetto < minimo)
				System.out.println(ERRORE_MINIMO + minimo);
			else
				System.out.println(ERRORE_MASSIMO + massimo);
		} while (!finito);

		return valoreLetto;
	}

	public static double leggiDouble(String messaggio) {
		boolean finito = false;
		double valoreLetto = 0;
		do {
			try {
				valoreLetto = Double.parseDouble(leggiStringaNonVuota(messaggio));
				finito = true;
			} catch (InputMismatchException | NumberFormatException e) {
				System.out.println(ERRORE_FORMATO);
				// String daButtare = lettore.next();
			}
		} while (!finito);
		return valoreLetto;
	}

	public static float leggiFloat(String messaggio, int minimo, int massimo) {
		return (float) leggiDouble(messaggio, minimo, massimo);
	}

	public static double leggiDoubleConMinimo(String messaggio, double minimo) {
		boolean finito = false;
		double valoreLetto = 0;
		do {
			valoreLetto = leggiDouble(messaggio);
			if (valoreLetto >= minimo)
				finito = true;
			else
				System.out.println(ERRORE_MINIMO + minimo);
		} while (!finito);

		return valoreLetto;
	}

	public static double leggiDouble(String messaggio, int minimo, int massimo) {
		boolean finito = false;
		double valoreLetto = 0;
		do {
			valoreLetto = leggiDouble(messaggio);
			if (valoreLetto >= minimo && valoreLetto <= massimo)
				finito = true;
			else if (valoreLetto < minimo)
				System.out.println(ERRORE_MINIMO + minimo);
			else
				System.out.println(ERRORE_MASSIMO + massimo);
		} while (!finito);

		return valoreLetto;
	}

	public static boolean yesOrNo(String messaggio) {
		String mioMessaggio = messaggio + "(" + RISPOSTA_SI + "/" + RISPOSTA_NO + ")";
		char valoreLetto = leggiUpperChar(mioMessaggio, String.valueOf(RISPOSTA_SI) + String.valueOf(RISPOSTA_NO));

		if (valoreLetto == RISPOSTA_SI)
			return true;
		else
			return false;
	}

	/**
	 * <b>Metodo</B> che capisce se il tasto <b>{@code INVIO}</B> e' stato premuto
	 * 
	 * @param daOutputtare
	 *            e' la {@linkplain String} con il contenuto da mandare in output
	 * @param messaggio
	 *            e' il messaggio di 'attesa' finche' non si preme invio
	 * @author Simone
	 * @param <T>
	 */
	public static <T> void isInvioPremuto(T daOutputtare, String messaggio) {
		System.out.println(daOutputtare);
		System.out.print(messaggio);
		lettore.next();
		return;
	}
}
