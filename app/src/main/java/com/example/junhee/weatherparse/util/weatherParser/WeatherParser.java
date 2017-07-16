package com.example.junhee.weatherparse.util.weatherParser;

/**
 * Created by JunHee on 2017. 7. 11..
 */

public class WeatherParser {

    final static String PRE = "http://newsky2.kma.go.kr/service/SecndSrtpdFrcstInfoService2/ForecastSpaceData";
    final static String KEY = "?ServiceKey=8KzfBV6CNXa%2FhZqIGN%2BylJzvH2h2TBPVkZUJ1qLXL8KHXS8egAxsRamjNH%2BesADLwB%2BeetDJNLE2ZY3tqT0G3g%3D%3D";
    final static String BASE_DATE = "&base_date=";
    final static String BASE_TIME = "&base_time=";
    final static String NX = "&nx=";
    final static String NY = "&ny=";
    final static String type = "&pageNo=1&numOfRows=1000&_type=json";

    public static String setKmaUri(String yyyyMMdd, String nx, String ny) {
        return PRE + KEY + BASE_DATE + yyyyMMdd + BASE_TIME + "0500" + NX + nx + NY + ny + type;
    }

    public static String setDiscomfortUri(String yyyyMMddHH) {
        String uri = "http://newsky2.kma.go.kr/iros/RetrieveLifeIndexService2/getDsplsLifeList?serviceKey=8KzfBV6CNXa%2FhZqIGN%2BylJzvH2h2TBPVkZUJ1qLXL8KHXS8egAxsRamjNH%2BesADLwB%2BeetDJNLE2ZY3tqT0G3g%3D%3D&areaNo=1100000000&time="
                + yyyyMMddHH + "&_type=json";
        return uri;
    }

    public static String setRadiUri(String yyyyMMddHH) {
        String uri = "http://newsky2.kma.go.kr/iros/RetrieveLifeIndexService2/getUltrvLifeList?serviceKey=8KzfBV6CNXa%2FhZqIGN%2BylJzvH2h2TBPVkZUJ1qLXL8KHXS8egAxsRamjNH%2BesADLwB%2BeetDJNLE2ZY3tqT0G3g%3D%3D&areaNo=1100000000" +
                "&time=" + yyyyMMddHH + "&type=json";
        return uri;
    }

    public static String setSkCurrentUri(String lat, String lon) {
        String uri = "http://apis.skplanetx.com/weather/current/hourly?version=1&lat=" + lat + "&lon=" + lon + "&city=&county=&village=";
        return uri;
    }

    public static String setSk6daysUri(String lat, String lon) {
        String uri = "http://apis.skplanetx.com/weather/forecast/6days?version=1&lat=" + lat + "&lon=" + lon + "&city=&county=&village=&foretxt=";
        return uri;
    }

    public static String setSkDustUrl(String lat, String lon){
        String uri = "http://apis.skplanetx.com/weather/dust?version=1&lat=" + lat + "&lon=" + lon;
        return uri;
    }
}
