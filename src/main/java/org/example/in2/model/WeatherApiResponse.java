package org.example.in2.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.sun.tools.javac.Main;

@JsonIgnoreProperties(ignoreUnknown = true)
public class WeatherApiResponse {

    //main object contains temp, pressure, humidity...
    private Main main;
    private long dt;
    private int timezone;

    public static class Main {
        private Double temp;

        public Double getTemp() { return temp; }
        public void setTemp(Double temp) { this.temp = temp; }
    }

    public Main getMain() { return main; }
    public void setMain(Main main) { this.main = main; }


    public long getDt() { return dt; }
    public void setDt(long dt) { this.dt = dt; }

    public int getTimezone() { return timezone; }
    public void setTimezone(int timezone) { this.timezone = timezone; }
}
