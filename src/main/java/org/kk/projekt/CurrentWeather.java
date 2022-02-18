package org.kk.projekt;

public class CurrentWeather {
    private final MeteoApiResponse apiResponse;

    public CurrentWeather (MeteoApiResponse apiResponse) {
        this.apiResponse = apiResponse;
    }

//    public static class Value {
//        public int temp;
//    }

    public int getValue (int hour) {
        int n = 0;
//        Value val = new Value();
        for (int i = 0; i < this.apiResponse.dataseries.length; i++) {
            if (this.apiResponse.dataseries[i].timepoint > hour){
                n = i-1;
//                val.temp = this.apiResponse.dataseries[n].temp2m;
                break;
            }
        }
        return n;
    }
}
