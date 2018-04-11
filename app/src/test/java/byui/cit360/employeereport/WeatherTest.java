package byui.cit360.employeereport;

import org.junit.Test;
import org.junit.Assert.*;
import byui.cit360.employeereport.control.Weather;

import static junit.framework.Assert.*;

/**
 * Created by Dale on 4/11/2018.
 */

public class WeatherTest {
    @Test
    public void TestDownload() throws Exception {
        Weather w1 = new Weather();
        Weather w2 = new Weather("Rexburg,us");

        w1.execute();
        w2.execute();

        assertNotNull(w1);
        assertNotNull(w2);
        assertEquals(w1.getWeatherString(),w2.getWeatherString());
    }
}
