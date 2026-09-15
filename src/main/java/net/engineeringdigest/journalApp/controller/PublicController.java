package net.engineeringdigest.journalApp.controller;

import net.engineeringdigest.journalApp.entity.User;
import net.engineeringdigest.journalApp.entity.weather.WeatherResponse;
import net.engineeringdigest.journalApp.service.UserEntryService;
import net.engineeringdigest.journalApp.service.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/public")
public class PublicController {

    @Autowired
    private UserEntryService userEntryService;

    @Autowired
    private WeatherService weatherService;

    @GetMapping("/health-check")
    public String healthcheck()
    {
        return "OK";
    }

    @PostMapping("/create-user")
    public void createUser(@RequestBody User user)
    {
        userEntryService.saveNewUser(user);
    }

    @GetMapping("/greeting")
    public String greeting() {
        String city = "Mumbai";
        WeatherResponse weather = weatherService.getWeather(city);
        String description = weather.getWeather().get(0).getDescription();
        double temp = weather.getMain().getTemp();
        return "Hello! It's " + temp + "°C and " + description + " in " + city;
    }

}
