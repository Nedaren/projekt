package org.kk.projekt;

public class MeteoApiResponse {
    public static class Dataseries {
        public int timepoint;
        public int temp2m;
        public Wind10m wind10m;
    }

    public static class Wind10m {
        public String direction;
        public int speed;
    }

    public Dataseries[] dataseries;
}
