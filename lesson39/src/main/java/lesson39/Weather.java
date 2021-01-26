package lesson39;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.net.URL;
import java.net.URLConnection;
import java.util.Map;

import org.json.JSONObject;

public class Weather {
	private static final String API_KEY = "a0e0636dcd1bb8b330a292b6c053c0b9";

	private static final String REQUEST_URL = "http://api.openweathermap.org/data/2.5/weather?" + "appid=%s&" + "q=%s&"
			+ "lang=ru&" + "units=metric";

	public static String getWeather(String city) throws IOException {

		String requestStr = String.format(REQUEST_URL, API_KEY, city);

		// выполнение запроса
		URL url = new URL(requestStr);
		URLConnection conn = url.openConnection();
		System.out.println("Connection to openweathermap done!!");

		InputStream in = conn.getInputStream();
		BufferedReader reader = new BufferedReader(new InputStreamReader(in));

		StringBuffer buffer = new StringBuffer();
		reader.lines().forEach(s -> buffer.append(s));

		// десериализация из JSON
		JSONObject json = new JSONObject(buffer.toString());

		Map<String, Object> map = json.toMap();

		Map<String, Object> mainMap = (Map<String, Object>) map.get("main");

		Object temp = mainMap.get("temp");

		if (temp instanceof BigDecimal) {
			temp = ((BigDecimal) temp).intValue();
		}

		String result = String.format("Температура в городе %s %d", city, temp);

		return result;
	}

}
