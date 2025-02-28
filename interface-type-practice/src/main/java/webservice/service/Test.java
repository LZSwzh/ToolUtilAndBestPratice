package webservice.service;

import org.apache.cxf.jaxws.endpoint.dynamic.JaxWsDynamicClientFactory;

import java.util.ArrayList;
import java.util.List;

public class Test {
    public static void main(String[] args) {
        try {
            JaxWsDynamicClientFactory dcf = JaxWsDynamicClientFactory.newInstance();
            org.apache.cxf.endpoint.Client client = dcf.createClient("http://172.16.80.14:8060/services/WorkflowServiceXml");
            Object[] objects = new Object[0];

            List<String> mtData = new ArrayList<>();
            mtData.add("12121123232");

            objects = client.invoke("doCreateWorkflowRequest", mtData);
            // 输出调用结果
            System.out.println("接口调用结果: " + objects[0].toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
