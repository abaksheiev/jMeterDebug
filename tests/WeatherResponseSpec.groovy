// WeatherResponseSpec.groovy
import scripts.WeatherResponse  // Импорт с учетом пакета

class WeatherResponseSpec {
    static void validate(WeatherResponse  json) {
        assert json.currentWeather.temperature != null : "Temperature is missing"
        assert json.currentWeather.temperature < 0 : "Temperature must be positive"
        assert json.currentWeather.windspeed >= 0 : "Windspeed must be >= 0"
    }
}