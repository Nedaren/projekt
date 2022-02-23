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
        int n;
        for (int i = 0; i < this.apiResponse.dataseries.length; i++) {
            if (this.apiResponse.dataseries[i].timepoint > hour){
                if (hour < 3) {
                    n = 3;
                }
                // Jeżeli godzina jest mniejsza niż 3, wychodzi błąd ponieważ API nie posiada żadnych danych dla godziny wcześniejszej niż 3:00.
                else {
                    n = i - 1;
                }
//              API używany do wyznaczenia pogody nie posiada danych na okres
                val.temp = this.apiResponse.dataseries[n].temp2m;
                val.windDirection = this.apiResponse.dataseries[n].wind10m.direction;
                val.windSpeed = this.apiResponse.dataseries[n].wind10m.speed;
                break;
            }
        }
        return val;
    }
}

