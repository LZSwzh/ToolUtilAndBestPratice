package webservice.code.oa.entity;

import com.thoughtworks.xstream.XStream;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class XmlUtil {
    private static XStream xs = null;
    private static XmlUtil xmlUtil = null;

    private static final String OA_SUFFIX = "weaver.workflow.webservices";

    private static final String ENEITY_SUFFIX = "webservice.code.oa.entity";


    private XmlUtil() {
        try {
            // TODO 这里修改WorkflowRequestInfo的地址
            ArrayList var1 = new ArrayList();
            var1.add(ENEITY_SUFFIX + ".WorkflowRequestInfo");
            xs = this.alias(var1);
        } catch (ClassNotFoundException var2) {
            var2.printStackTrace();
        }

    }

    public static synchronized XmlUtil getInstance() {
        if (null == xmlUtil) {
            xmlUtil = new XmlUtil();
        }

        return xmlUtil;
    }

    /**
     * 为
     * @param var1
     * @return
     * @throws ClassNotFoundException
     */
    public XStream alias(List var1) throws ClassNotFoundException {
        XStream var2 = new XStream();

        for(int var3 = 0; var3 < var1.size(); ++var3) {
            try {
                Object var4 = Class.forName((String)var1.get(var3)).newInstance();
                Class var5 = var4.getClass();
                this.aliasAtt(var2, var5);
            } catch (InstantiationException var6) {
                var6.printStackTrace();
            } catch (IllegalAccessException var7) {
                var7.printStackTrace();
            }
        }

        return var2;
    }

    /**
     * 为var类和其字段设置别名
     * @param var1
     * @param var2
     */
    public void aliasAtt(XStream var1, Class var2) {
        if (null != var2) {
            var1.alias(var2.getSimpleName(), var2);
            Field[] var3 = var2.getDeclaredFields();

            for(int var4 = 0; var4 < var3.length; ++var4) {
                Field field = var3[var4];
                String tmpName = field.getName();
//                if (field.getType().equals(WorkflowRequestTableRecord.class)){
//                    tmpName = OA_SUFFIX
//                }
                var1.aliasField(tmpName, var2, tmpName);
            }

        }
    }

    public Object xmlToObject(String var1) {
        var1 = var1.replaceAll(OA_SUFFIX, ENEITY_SUFFIX);
//        System.out.println("xml = " + var1);
        return xs.fromXML(var1);
    }

    public String objToXml(Object var1) {
        String xml = xs.toXML(var1);
        return xml.replaceAll(ENEITY_SUFFIX,OA_SUFFIX);
    }

}
