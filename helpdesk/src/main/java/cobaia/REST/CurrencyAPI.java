package cobaia.REST;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import javax.sound.sampled.AudioFormat.Encoding;

import org.apache.commons.io.IOUtils;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;



public class CurrencyAPI {
	private final String ACCESS_KEY= "89815dc3ef587e9915fa106b30121a35";
	private final String API_URL= "http://apilayer.net/api/live";
	public double getCurrencyDifference(String currency) {
		URL url;
		InputStream content = null;
		String resp= "";
		try {
			url = new URL(API_URL+"?access_key="+ACCESS_KEY+"&currencies="+currency);
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			content = connection.getInputStream();
			resp = IOUtils.toString(content, "UTF-8"); ;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		JsonParser parser = new JsonParser();
		JsonObject json = (JsonObject)(parser.parse(resp));
		JsonObject currencyJSON = json.getAsJsonObject("quotes");
		Double value = Double.parseDouble(currencyJSON.get("USD"+currency).toString());
		return value;
	}
}
