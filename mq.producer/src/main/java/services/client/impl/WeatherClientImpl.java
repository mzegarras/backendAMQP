package services.client.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.ws.client.core.WebServiceTemplate;
import org.springframework.ws.soap.client.core.SoapActionCallback;

import hello.wsdl.GetCityForecastByZIP;
import hello.wsdl.GetCityForecastByZIPResponse;
import services.client.WeatherClient;


public class WeatherClientImpl implements WeatherClient {
	
	@Autowired
	@Qualifier("weatherClientTemplate")
	WebServiceTemplate webServiceTemplate;
	
	public GetCityForecastByZIPResponse getCityForecastByZip(String zipCode) {
		// TODO Auto-generated method stub
		
		GetCityForecastByZIP request = new GetCityForecastByZIP();
		request.setZIP(zipCode);
		
		GetCityForecastByZIPResponse response = (GetCityForecastByZIPResponse) webServiceTemplate
				.marshalSendAndReceive(
						"http://wsf.cdyne.com/WeatherWS/Weather.asmx",
						request,
						new SoapActionCallback("http://ws.cdyne.com/WeatherWS/GetCityForecastByZIP"));

		return response;
		
	
	}

}
