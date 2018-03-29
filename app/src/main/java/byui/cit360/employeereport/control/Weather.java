package byui.cit360.employeereport.control;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import javax.net.ssl.HttpsURLConnection;

/**
 * Created by Dale on 3/28/2018.
 */

public class Weather {

    String cityZip;
    JSONObject weatherInfo;

    public Weather() {
        this.cityZip = "Rexburg,us";
    }

    public void getWeatherInfo() {
        String response = null;
        try {
            URL url = new URL("https://api.openweathermap.org/data/2.5/weather?q=" + cityZip + "&appid=04ec42bc385dcb48b6d325cf720ffcb4");
            HttpsURLConnection connect;
            connect = (HttpsURLConnection) url.openConnection();
            BufferedReader br = new BufferedReader(new InputStreamReader(connect.getInputStream()));
            response = br.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }

        JSONTokener tokener = new JSONTokener(response);
        try {
            weatherInfo = new JSONObject(tokener);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }


}
