import scripts.WeatherResponse

class WeatherResponseSpec {

    static void validate(WeatherResponse  json){
        try {
            validateInternal(json)
        } catch (AssertionError e) {
            throw new AssertionError(e.message)
        }
    }

    static void validateInternal(WeatherResponse  json) {
        List<String> errors = []

        if (json.currentWeather.temperature == null) {
            errors << "Temperature is missing"
        }

        if (json.currentWeather.temperature <= 0) {
            errors << "Temperature must be positive"
        }

        if (json.currentWeather.windspeed <= 0) {
            errors << "Windspeed must be >= 0"
        }

        // Throw combined error if any
        if (!errors.isEmpty()) {
            throw new AssertionError("Validation failed:\n - " + errors.join("\n - "))
        }
    }
}