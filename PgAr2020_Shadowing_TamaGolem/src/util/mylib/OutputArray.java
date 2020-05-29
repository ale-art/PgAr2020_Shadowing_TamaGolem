package util.mylib;

import java.util.Arrays;

/**Classe <tt>Util<tt> con metodi utili alla scrittura in {@linkplain Console}
 * @author Simone Giacomini*/
public class OutputArray {
	
	private static final String PROSSIMO_ELEMENTO = "< Per visualizzare il prossimo elemento, premi invio >";
/**Impossibile creare un istanza di questa classe*/
	private OutputArray() {
	}
	/**<b>Metodo statico</b> che permette di mostrare ogni sigolo elemento dell'<tt>array</tt> dopo che si preme invio 
	 * @param array, e' un array di {@linkplain String}
	 * @author Simone
	 * @param <T>*/
	public static <T> void arrayStringConsoleConAttesa(T[] array) {
		for (int i = 0; i < array.length; i++) {
			InputDati.isInvioPremuto(array[i],PROSSIMO_ELEMENTO);
			
		}
	}

	/**<b>Metodo statico</b> che permette di mostrare ogni sigolo elemento dell'<tt>array</tt> 
	 * @param array, e' un array di {@linkplain String}
	 * @author Simone
	 * @param <T>*/
	public static <T> void arrayStringConsole(T[] array) {
		System.out.println(	Arrays.toString(array));
		
	}
}
