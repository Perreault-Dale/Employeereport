package byui.cit360.employeereport.control;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import javax.net.ssl.HttpsURLConnection;

public class Weather {

    private String cityZip;
    private JSONObject weatherInfo;
    private String weatherString = "HQ Weather\n";

    public Weather() {
        this.cityZip = "Rexburg,us";
    }

    public Weather(String cityString) { this.cityZip = cityString; }

    private void getWeatherInfo() {
        String response = null;
        String appId = "04ec42bc385dcb48b6d325cf720ffcb4";
        try {
            URL url = new URL(String.format("https://api.openweathermap.org/data/2.5/weather?q=%s&appid=%s", cityZip, appId));
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

    private void addTemperature() {
        try {
            JSONObject tempJson = (JSONObject) weatherInfo.get("main");
            Double temp = (Double) tempJson.get("temp");
            Double imp_temp = (temp - 270) * 9 / 5 + 32;
            weatherString += "Temp: " + imp_temp + "\n";
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void addHumidity() {
        try {
            JSONObject tempJson = (JSONObject) weatherInfo.get("main");
            Integer humid = (Integer) tempJson.get("humidity");
            weatherString += "Humidity: " + humid + "%\n";
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void addWind() {
        try {
            JSONObject tempJson = (JSONObject) weatherInfo.get("wind");
            Double windSpeed = (Double) tempJson.get("speed");
            Integer windDeg = (Integer) tempJson.get("deg");
            int windInt = (int) ((windDeg - 22.5) / 45.0);
            String direction = "";
            switch (windInt) {
                case 0:
                    direction = "N";
                    break;
                case 1:
                    direction = "NE";
                    break;
                case 2:
                    direction = "E";
                    break;
                case 3:
                    direction = "SE";
                    break;
                case 4:
                    direction = "S";
                    break;
                case 5:
                    direction = "SW";
                    break;
                case 6:
                    direction = "W";
                    break;
                case 7:
                    direction = "NW";
                    break;
            }
            weatherString += "Wind: " + windSpeed + " mph " + direction;
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public String getWeatherString() {
        return weatherString;
    }

    public void execute() {
        getWeatherInfo();
        addTemperature();
        addHumidity();
        addWind();
    }
}