package br.com.localize.util;

import java.io.InputStream;
import java.io.Serializable;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

import br.com.localize.dto.FornecedorDTO;
import br.com.localize.dto.TipoServicoDTO;

public class WsHelper implements Serializable {

	private static final long serialVersionUID = -5142940435503125048L;

	/** Atributo URL. */
	//private static final String URL = "http://172.16.4.211:8080/";
	//private static final String URL = "http://10.0.0.101:8080/";
	//private static final String URL = "http://www.localize.kinghost.net/";
	//private static final String URL = "http://192.168.2.131:8080/";
	private static final String URL = "http://192.168.43.10:8080/";

	/** Atributo URL_OBTER_DADOS. */
	private static final String URL_OBTER_DADOS = URL+"localize/servico/wsmobile/obterdados/{0}/{1}/{2}";

	/** Atributo URL_LISTAR_TIPO_SERVICO. */
	private static final String URL_LISTAR_TIPO_SERVICO = URL+"localize/servico/wsmobile/listarTipoServico";

	public static List<FornecedorDTO> obterDados(final Long idTipoServico, final double longitude, final double latitude, final int distancia) {

		try {
			final String latitudeStr = String.valueOf(latitude);

			final String longitudeStr = String.valueOf(longitude);

			final String distanciaStr = String.valueOf(distancia);
			
			final String idTipoServiceStr = String.valueOf(idTipoServico);

			final InputStream instream = UtilHTTP.GET(MessageFormat.format(WsHelper.URL_OBTER_DADOS, idTipoServiceStr, longitudeStr, latitudeStr+"/"+distanciaStr));

			final String result = UtilHTTP.convertStreamToString(instream);

			instream.close();

			return FornecedorDTO.buildList(result);

		} catch (final Exception e) {

			e.printStackTrace();	

			return new ArrayList<FornecedorDTO>();

		}

	}

	public static List<TipoServicoDTO> listarTipoServico() {

		try {

			final InputStream instream = UtilHTTP.GET(WsHelper.URL_LISTAR_TIPO_SERVICO);

			final String result = UtilHTTP.convertStreamToString(instream);

			instream.close();

			return TipoServicoDTO.buildList(result);

		} catch (final Exception e) {

			e.printStackTrace();

			return new ArrayList<TipoServicoDTO>();

		}
	}

}
