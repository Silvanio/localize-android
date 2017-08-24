package br.com.localize.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;


/**
 * <p>
 * <b>Title:</b> UtilHTTP
 * </p>
 * 
 * <p>
 * <b>Description:</b> Classe utilitaria para requisições HTTP
 * </p>
 * 
 * <p>
 * <b>Company: </b> ITSS Soluções em Tecnologia
 * </p>
 * 
 * @author silvanio
 * 
 * @version 1.0.0
 */
public class UtilHTTP implements Serializable{

	private static final long serialVersionUID = -5694983977688708461L;
	
	private static final String	UTF_8	= "UTF-8";

	/**
	 * Método responsável por executar a chamada ao web service da solução.
	 * 
	 * @author Bruno Zafalão
	 * 
	 * @param url
	 * 
	 * @return <code>InputStream</code>
	 */
	public static InputStream GET(final String url) {

		InputStream content = null;

		try {

			final HttpClient httpclient = new DefaultHttpClient();

			final HttpResponse response = httpclient.execute(new HttpGet(url.replaceAll(" ", "+")));

			content = response.getEntity().getContent();

		} catch (final Exception e) {

			e.printStackTrace();
		}

		return content;
	}

	/**
	 * Método responsável por realizar HTTP Post.
	 *
	 * @author Silvânio Júnior
	 *
	 * @param url
	 * 
	 * @param nameJson
	 * 
	 * @param json
	 * 
	 * @return
	 * 
	 * @throws Exception
	 */
	public static String POST(final String url, String nameJson, String json) throws Exception {

		final DefaultHttpClient httpClient = new DefaultHttpClient();

		final HttpPost post = new HttpPost(url);

		final List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(1);

		nameValuePairs.add(new BasicNameValuePair(nameJson, json));

		post.setEntity(new UrlEncodedFormEntity(nameValuePairs));

		post.setEntity(new UrlEncodedFormEntity(nameValuePairs, UTF_8));

		final HttpResponse httpresponse = httpClient.execute(post);

		final HttpEntity entity = httpresponse.getEntity();

		final InputStream stream = entity.getContent();

		final String result = convertStreamToString(stream);

		stream.close();

		return result;
	}
	
	/**
	 * M�todo respons�vel por converter o stream entregue para o formato <code>JSON</code>
	 * 
	 * @author Bruno Zafal�o
	 * 
	 * @param is
	 * 
	 * @return <code>String</code>
	 */
	public static String convertStreamToString(final InputStream is) {

		final BufferedReader reader = new BufferedReader(new InputStreamReader(is));

		final StringBuilder sb = new StringBuilder();

		String line = null;

		try {

			while (( line = reader.readLine() ) != null) {

				sb.append(line + "\n");
			}

		} catch (final IOException e) {

			e.printStackTrace();

		} finally {

			try {

				is.close();

			} catch (final IOException e) {

				e.printStackTrace();
			}
		}

		return sb.toString();
	}
}
