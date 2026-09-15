package net.engineeringdigest.journalApp.service;

import net.engineeringdigest.journalApp.Cache.AppCache;
import org.springframework.beans.factory.annotation.Value;
import net.engineeringdigest.journalApp.entity.weather.WeatherResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.util.*;

@Service
public class WeatherService {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private AppCache appCache;

    @Value("${weather.api.key}")
    private String apiKey;

    public WeatherResponse getWeather(String city) {
        String url = appCache.APP_CACHE.get("WEATHER_API").replace("<city>",city).replace("<YOUR_KEY>",apiKey);
        return restTemplate.getForObject(url, WeatherResponse.class);
    }
}
