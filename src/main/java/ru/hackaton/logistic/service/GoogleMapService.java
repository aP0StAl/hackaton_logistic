package ru.hackaton.logistic.service;

import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.springframework.stereotype.Service;
import ru.hackaton.logistic.domain.GeoPoint;
import ru.hackaton.logistic.domain.RouteMap;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.lang.Math.abs;
import static java.lang.Math.min;


@Slf4j
@Service
public class GoogleMapService {
    private WebDriver driver;

    public GoogleMapService() {
        try {
            ChromeOptions options = new ChromeOptions();
            options.setHeadless(true);
            //driver = new ChromeDriver(options);
            log.info("ChromeDriver успешно запущен");
        } catch (Exception e) {
            log.error("Не удалось запустить ChromeDriver");
            throw e;
        }
    }

    public RouteMap getRoute(String p1, String p2){
        driver.get("https://www.google.com/maps/dir/"+p1+"/"+p2+"/");
        String pageSource = driver.getPageSource();
        int start = pageSource.indexOf("wide_routes");
        int end = pageSource.indexOf("wide_routes", start + 1);
        end = min(end, pageSource.indexOf("пешком", start + 1));

        List<String> allMatches = new ArrayList<>();
        Matcher m = Pattern.compile("null,\\d+.\\d{3,},\\d+.\\d{3,}]")
                .matcher(pageSource.substring(start, end));
        while (m.find()) {
            allMatches.add(m.group());
        }

        List<GeoPoint> allPoints = new ArrayList<>();
        allMatches.forEach(s -> {
            String[] ss = s.split(",");
            double lat = Double.parseDouble(ss[1]);
            double lon = Double.parseDouble(ss[2].replace("]", ""));
            allPoints.add(new GeoPoint(lat, lon));
        });

        List<GeoPoint> forRemove = new ArrayList<>();
        for(int i=0; i<allPoints.size()-1; i++){
            double dist = manhattanDist(allPoints.get(i), allPoints.get(i+1));
            if(dist > 1e-5){
                forRemove.add(allPoints.get(i));
            }
        }
        allPoints.removeAll(forRemove);

        String dist = getDistance(pageSource.substring(start, end));
        String time = getTime(pageSource, dist);
        return new RouteMap(allPoints, dist, time);
    }

    private String getDistance(String source){
        int kmIndex = source.indexOf("м\\");
        int start = source.substring(0, kmIndex).lastIndexOf("\"");
        int end = source.substring(kmIndex).indexOf("\"");
        return source.substring(start+1, kmIndex+end-1);
    }

    private String getTime(String source, String distance){
        int start = source.indexOf(distance);
        start = source.indexOf("\"", start+1);
        start = source.indexOf("\"", start+1);
        int end = source.indexOf("\"", start+1);
        return source.substring(start+1, end-1);
    }

    private double manhattanDist(GeoPoint p1, GeoPoint p2){
        return abs(p1.getLat() - p2.getLat()) + abs(p1.getLon() - p2.getLon());
    }

}
