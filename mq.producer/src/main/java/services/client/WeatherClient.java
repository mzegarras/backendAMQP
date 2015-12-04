package services.client;

import hello.wsdl.GetCityForecastByZIPResponse;

public interface WeatherClient {
	
	GetCityForecastByZIPResponse getCityForecastByZip(String zipCode);

}
