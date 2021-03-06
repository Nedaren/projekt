package org.kk.projekt;

public class CurrentWeather {
    private final MeteoApiResponse apiResponse;

    public CurrentWeather (MeteoApiResponse apiResponse) {
        this.apiResponse = apiResponse;
    }

    public static class Value {
        public int temp;
        public String windDirection;
        public int windSpeed;
    }

    public Value getValue (int hour) {
        Value val = new Value();
        for (int i = 0; i < this.apiResponse.dataseries.length; i++) {
            if (this.apiResponse.dataseries[i].timepoint > hour){
                int n = i - 1;
                val.temp = this.apiResponse.dataseries[n].temp2m;
                val.windDirection = this.apiResponse.dataseries[n].wind10m.direction;
                val.windSpeed = this.apiResponse.dataseries[n].wind10m.speed;
                break;
            }
        }
        return val;
    }
}

