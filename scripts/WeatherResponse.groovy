
// WeatherResponse.groovy
package scripts  // Здесь указана папка, в которой находится класс

import groovy.json.JsonSlurper

// Define the Groovy class to represent the structure of the JSON response
class WeatherResponse {
    double latitude
    double longitude
    String timezone
    double elevation
    CurrentWeather currentWeather
    CurrentWeatherUnits currentWeatherUnits

    static WeatherResponse fromJson(String json) {
        def jsonSlurper = new JsonSlurper()
        def parsedJson = jsonSlurper.parseText(json)

        // Create the WeatherResponse object and populate it with values
        def response = new WeatherResponse()
        response.latitude = parsedJson.latitude
        response.longitude = parsedJson.longitude
        response.timezone = parsedJson.timezone
        response.elevation = parsedJson.elevation
        response.currentWeather = new CurrentWeather(
                time: parsedJson.current_weather.time,
                temperature: parsedJson.current_weather.temperature,
                windspeed: parsedJson.current_weather.windspeed,
                winddirection: parsedJson.current_weather.winddirection,
                isDay: parsedJson.current_weather.is_day,
                weathercode: parsedJson.current_weather.weathercode
        )
        response.currentWeatherUnits = new CurrentWeatherUnits(
                time: parsedJson.current_weather_units.time,
                interval: parsedJson.current_weather_units.interval,
                temperature: parsedJson.current_weather_units.temperature,
                windspeed: parsedJson.current_weather_units.windspeed,
                winddirection: parsedJson.current_weather_units.winddirection,
                isDay: parsedJson.current_weather_units.is_day,
                weathercode: parsedJson.current_weather_units.weathercode
        )

        return response
    }
}

// Nested class to represent current weather data
class CurrentWeather {
    String time
    double temperature
    double windspeed
    int winddirection
    int isDay
    int weathercode
}

// Nested class to represent current weather units
class CurrentWeatherUnits {
    String time
    String interval
    String temperature
    String windspeed
    String winddirection
    String isDay
    String weathercode

    String toString() {
        return """CurrentWeatherUnits {
            time: '${time}',
            interval: '${interval}',
            temperature: '${temperature}',
            windspeed: '${windspeed}',
            winddirection: '${winddirection}',
            isDay: '${isDay}',
            weathercode: '${weathercode}'
        }"""
    }

    void log(log) {
        log.info(this.toString())
    }
}
