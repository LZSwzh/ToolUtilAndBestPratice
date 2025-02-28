package webservice.service;


import javax.xml.soap.*;

public class AxisSoapService {
    public static void main(String[] args) {
        try {
            // 创建SOAP连接
            SOAPConnectionFactory soapConnectionFactory = SOAPConnectionFactory.newInstance();
            SOAPConnection soapConnection = soapConnectionFactory.createConnection();

            // 创建SOAP消息
            MessageFactory messageFactory = MessageFactory.newInstance();
            SOAPMessage soapMessage = messageFactory.createMessage();

            // 设置SOAP消息的内容
            SOAPPart soapPart = soapMessage.getSOAPPart();
            SOAPEnvelope envelope = soapPart.getEnvelope();
            envelope.addNamespaceDeclaration("ns", "http://WebXml.com.cn/");
            SOAPBody body = envelope.getBody();

            // 创建SOAP请求体
            SOAPElement request = body.addChildElement("qqCheckOnline", "ns", "http://WebXml.com.cn/");
            // 替换为你想要检查的QQ号码
            request.addChildElement("qqCode", "ns").addTextNode("2352186607");

            // 设置SOAP消息的目标地址
            String endpointUrl = "http://www.webxml.com.cn/webservices/qqOnlineWebService.asmx";
            SOAPMessage soapResponse = soapConnection.call(soapMessage, endpointUrl);

            // 获取响应
            SOAPBody responseBody = soapResponse.getSOAPBody();
            System.out.println(responseBody.getTextContent());

            // 关闭连接
            soapConnection.close();
        } catch (Exception e) {
            System.err.println("Error occurred while sending SOAP request: " + e.getMessage());
        }
    }
}
