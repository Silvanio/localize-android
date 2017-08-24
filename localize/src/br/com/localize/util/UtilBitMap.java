package br.com.localize.util;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;


/**
 * <p>
 * <b>Title:</b> UtilBitMap.java
 * </p>
 * 
 * <p>
 * <b>Description:</b> Classe utilitaria para bitmap.
 * </p>	
 * 	
 * <p>	
 * <b>Company: </b> Fábrica de Software - Localize
 * </p>	
 * 	
 * @author Silvânio Júnior
 * 
 * @version 1.0.0
 */
public class UtilBitMap {
	
	/**
	 * Método responsável por retornar o bitmap.
	 *
	 * @author silvanioSilvânio Júnior
	 *
	 * @param valor
	 * 
	 * @return <code>Bitmap</code>
	 */
	public static Bitmap getBitmap(String valor){
		
		final byte[] bytesPanel = Base64.decode(valor, Base64.DEFAULT);
		
		return BitmapFactory.decodeByteArray(bytesPanel, 0, bytesPanel.length);
		
	}

	/**
	 * Método responsável por retornar o bitmap.
	 *
	 * @author silvanioSilvânio Júnior
	 *
	 * @param valor
	 * 
	 * @return <code>Bitmap</code>
	 */
	public static Bitmap getBitmap(byte[] bytesPanel){
		
		return BitmapFactory.decodeByteArray(bytesPanel, 0, bytesPanel.length);
		
	}

	/**
	 * Método responsável por retornar o bitmap.
	 *
	 * @author silvanioSilvânio Júnior
	 *
	 * @param valor
	 * 
	 * @return <code>Bitmap</code>
	 */
	public static byte[] getArray(String valor){
		
		final byte[] bytesPanel = Base64.decode(valor, Base64.DEFAULT);
		
		return bytesPanel;
	}

}
