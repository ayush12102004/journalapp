package net.engineeringdigest.journalApp.entity.weather;

import lombok.Getter;
import lombok.Setter;
import java.util.List;


@Getter
@Setter
public class WeatherResponse {
    private List<Weather> weather;
    private Main main;
    private String name;
}
