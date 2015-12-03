package campanias.proxy;

import java.io.IOException;

import javax.xml.namespace.QName;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.TransformerFactoryConfigurationError;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ws.WebServiceMessage;
import org.springframework.ws.client.core.WebServiceMessageCallback;
import org.springframework.ws.client.core.WebServiceTemplate;
import org.springframework.ws.soap.saaj.SaajSoapMessage;

import services.campanias.ConsultarLead;
import services.campanias.ConsultarLeadResponse;
import services.campanias.ObjectFactory;
import services.core.Identidad;
import services.core.RequestHeader;
import services.core.RequestId;
import services.core.UsernameToken;


@Service
public class CampaniasWebServiceClient implements CampaniasWebService {
	
	private static final services.campanias.ObjectFactory WS_CLIENT_FACTORY_1 = new services.campanias.ObjectFactory();
	 private static final services.core.ObjectFactory WS_CLIENT_FACTORY_2 = new services.core.ObjectFactory();
	 
	
	@Autowired
	private  WebServiceTemplate webServiceTemplate;

   /* public CampaniasWebServiceClient(WebServiceTemplate webServiceTemplate) {
        this.webServiceTemplate = webServiceTemplate;
    }*/

	public void doCall() {
		// TODO Auto-generated method stub
		
		
		final RequestHeader requestHeader = WS_CLIENT_FACTORY_2.createRequestHeader();
		
		RequestId requestId = new RequestId();
		requestId.setChannelId(2);
		requestId.setMessageId("2015120215585800286");
		requestId.setCountryId("PE");
		requestId.setCompanyId("01");
		
		requestHeader.setRequestId(requestId);
		
		Identidad identidad = WS_CLIENT_FACTORY_2.createIdentidad();
		identidad.setCodigoRed("BI");
		identidad.setCodigoUsuario("USRBUSBPI");
		requestHeader.setIdentidad(identidad);
		
		UsernameToken usernameToken = WS_CLIENT_FACTORY_2.createUsernameToken();
		usernameToken.setUserType((short) 1);
		usernameToken.setUserName("USRBPIBUSUAT");
		requestHeader.getUserId().add(usernameToken);
		
		
		ConsultarLead consultarLead = WS_CLIENT_FACTORY_1.createConsultarLead();
		consultarLead.setTipoBusqueda((short)2);
		consultarLead.setIdCliente("00000050001157");
		consultarLead.setCodigoCanal("BPI");
		consultarLead.setTipoCampania("C");
		
	
		
		
		
		ConsultarLeadResponse response = (ConsultarLeadResponse)webServiceTemplate.marshalSendAndReceive(consultarLead, new WebServiceMessageCallback() {
			
			public void doWithMessage(WebServiceMessage message) throws IOException, TransformerException {
				// TODO Auto-generated method stub
				SaajSoapMessage soapMessage = (SaajSoapMessage)message;
				
			
				
				
				soapMessage.getSoapHeader().addAttribute(new QName("", "requesHeader"),requestHeader.toString());
			}
		});
		
	}

            
    

}
