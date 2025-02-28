package webservice.service;

import javax.xml.soap.*;

public class OaSoapService {
    public static void main(String[] args) {
        try {
            SOAPConnectionFactory soapConnectionFactory = SOAPConnectionFactory.newInstance();
            SOAPConnection soapConnection = soapConnectionFactory.createConnection();

            MessageFactory messageFactory = MessageFactory.newInstance();
            SOAPMessage soapMessage = messageFactory.createMessage();

            SOAPPart soapPart = soapMessage.getSOAPPart();
            SOAPEnvelope envelope = soapPart.getEnvelope();
            envelope.addNamespaceDeclaration("tns", "webservices.services.weaver.com.cn");

            SOAPBody soapBody = envelope.getBody();
            SOAPElement doCreateWorkflowRequest = soapBody.addChildElement("doCreateWorkflowRequest", "tns");
            doCreateWorkflowRequest.addChildElement("in0", "tns").addTextNode("Example Input");
            doCreateWorkflowRequest.addChildElement("in1", "tns").addTextNode("1");

            String endpointUrl = "http://172.16.80.14:8060/services/WorkflowService";
            SOAPMessage response = soapConnection.call(soapMessage, endpointUrl);

            // 检查响应消息
            SOAPBody responseBody = response.getSOAPBody();
            if (responseBody.hasFault()) {
                // 处理错误情况
                SOAPFault fault = responseBody.getFault();
                System.err.println("SOAP Fault Code: " + fault.getFaultCode());
                System.err.println("SOAP Fault String: " + fault.getFaultString());
            } else {
                // 处理正常响应
                System.out.println("Response: " + responseBody.getTextContent());
            }

            soapConnection.close();
        } catch (Exception e) {
            // 捕获并处理异常
            e.printStackTrace();
        }
    }
}
