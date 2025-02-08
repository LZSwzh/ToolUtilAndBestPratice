package webservice.service;

import webservice.code.qq.QqOnlineWebService;
import webservice.code.qq.QqOnlineWebServiceSoap;

public class WsdlService {
    public static void main(String[] args) {
        // 创建QqOnlineWebService实例
        QqOnlineWebService service = new QqOnlineWebService();

        // 获取服务的Soap端口
        QqOnlineWebServiceSoap port = service.getQqOnlineWebServiceSoap();

        // 设置要检查的QQ号码
        String qqCode = "2352186607";

        // 调用qqCheckOnline方法并接收结果
        String result = port.qqCheckOnline(qqCode);

        // 打印结果
        System.out.println("QQ在线状态：" + result);
    }
}
