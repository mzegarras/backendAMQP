package services.client.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.ws.client.core.WebServiceTemplate;
import org.springframework.ws.soap.client.core.SoapActionCallback;


import services.client.CampaniasClient;

public class CampaniasClientImpl implements CampaniasClient {

	
	@Autowired
	@Qualifier("campaniasServiceTemplate")
	WebServiceTemplate webServiceTemplate;
	
	public void doAlgo() {
		// TODO Auto-generated method stub
		/*
		 		
		GetCityForecastByZIP request = new GetCityForecastByZIP();
		request.setZIP(zipCode);
		
		GetCityForecastByZIPResponse response = (GetCityForecastByZIPResponse) webServiceTemplate
				.marshalSendAndReceive(
						"http://wsf.cdyne.com/WeatherWS/Weather.asmx",
						request,
						new SoapActionCallback("http://ws.cdyne.com/WeatherWS/GetCityForecastByZIP"));

		return response;
		 * 
		 * */
	}

}
