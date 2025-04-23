package com.best.practice.intertype.webservice.service;

import cn.hutool.core.map.MapUtil;
import cn.hutool.core.util.StrUtil;
import com.best.practice.intertype.webservice.code.oa.entity.*;

import javax.xml.rpc.ServiceException;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class OaSoapService2 {
    public static void main(String[] args) throws ServiceException, RemoteException {
        WorkflowServiceXmlLocator locator = new WorkflowServiceXmlLocator();
        WorkflowServiceXmlPortType pt = locator.getWorkflowServiceXmlHttpPort();

        boolean flag = true;
        if (flag){
            //创建工作流信息
            HashMap<String, Object> params = new HashMap<>();
            String workflowInfo = createWorkflowInfo(params,7731);
            System.out.println("生成的工作流xml:\n"+workflowInfo);
            /** 向OA发起对应工作流 */
            String requestId = pt.doCreateWorkflowRequest(workflowInfo, 7731);
            System.out.println("requestId = " + requestId);
        }else {
            /** 根据id查询OA流程 入参:请求id、当前用户、原流程id；出参：流程信息 */
            String response = pt.getWorkflowRequest(1138751, 7731, -1);

            XmlUtil instance = XmlUtil.getInstance();
            WorkflowRequestInfo workflowRequestInfo = (WorkflowRequestInfo) instance.xmlToObject(response);
            System.out.println(workflowRequestInfo.toString());
        }

//        System.out.println(getTestWorkflowInfo());

    }

    private static String getTestWorkflowInfo(){
        return "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?>\n" +
                "<WorkflowRequestInfo>\n" +
                "      \n" +
                "    <requestName/>\n" +
                "      \n" +
                "    <requestLevel>0</requestLevel>\n" +
                "      \n" +
                "    <workflowBaseInfo>\n" +
                "            \n" +
                "        <workflowId>1399</workflowId>\n" +
                "            \n" +
                "        <workflowName/>\n" +
                "            \n" +
                "        <workflowTypeName>webservice</workflowTypeName>\n" +
                "          \n" +
                "    </workflowBaseInfo>\n" +
                "      \n" +
                "    <creatorId>7731</creatorId>\n" +
                "      \n" +
                "    <canView>true</canView>\n" +
                "      \n" +
                "    <canEdit>true</canEdit>\n" +
                "      \n" +
                "    <mustInputRemark>false</mustInputRemark>\n" +
                "      \n" +
                "    <needAffirmance>false</needAffirmance>\n" +
                "      \n" +
                "    <workflowMainTableInfo>\n" +
                "            \n" +
                "        <requestRecords>\n" +
                "                  \n" +
                "            <weaver.workflow.webservices.WorkflowRequestTableRecord>\n" +
                "                        \n" +
                "                <recordOrder>0</recordOrder>\n" +
                "                        \n" +
                "                <workflowRequestTableFields>\n" +
                "                              \n" +
                "                    <weaver.workflow.webservices.WorkflowRequestTableField>\n" +
                "                                    \n" +
                "                        <fieldName>ycpydh1024</fieldName>\n" +
                "                                    \n" +
                "                        <fieldValue/>\n" +
                "                                    \n" +
                "                        <fieldOrder>0</fieldOrder>\n" +
                "                                    \n" +
                "                        <isView>true</isView>\n" +
                "                                    \n" +
                "                        <isEdit>true</isEdit>\n" +
                "                                    \n" +
                "                        <isMand>false</isMand>\n" +
                "                                  \n" +
                "                    </weaver.workflow.webservices.WorkflowRequestTableField>\n" +
                "                              \n" +
                "                    <null/>\n" +
                "                              \n" +
                "                    <null/>\n" +
                "                              \n" +
                "                    <null/>\n" +
                "                              \n" +
                "                    <weaver.workflow.webservices.WorkflowRequestTableField>\n" +
                "                                    \n" +
                "                        <fieldName>sqr</fieldName>\n" +
                "                                    \n" +
                "                        <fieldValue>7731</fieldValue>\n" +
                "                                    \n" +
                "                        <fieldOrder>0</fieldOrder>\n" +
                "                                    \n" +
                "                        <isView>true</isView>\n" +
                "                                    \n" +
                "                        <isEdit>true</isEdit>\n" +
                "                                    \n" +
                "                        <isMand>false</isMand>\n" +
                "                                  \n" +
                "                    </weaver.workflow.webservices.WorkflowRequestTableField>\n" +
                "                              \n" +
                "                    <weaver.workflow.webservices.WorkflowRequestTableField>\n" +
                "                                    \n" +
                "                        <fieldName>gw</fieldName>\n" +
                "                                    \n" +
                "                        <fieldValue>516</fieldValue>\n" +
                "                                    \n" +
                "                        <fieldOrder>0</fieldOrder>\n" +
                "                                    \n" +
                "                        <isView>true</isView>\n" +
                "                                    \n" +
                "                        <isEdit>true</isEdit>\n" +
                "                                    \n" +
                "                        <isMand>false</isMand>\n" +
                "                                  \n" +
                "                    </weaver.workflow.webservices.WorkflowRequestTableField>\n" +
                "                              \n" +
                "                    <weaver.workflow.webservices.WorkflowRequestTableField>\n" +
                "                                    \n" +
                "                        <fieldName>szbm</fieldName>\n" +
                "                                    \n" +
                "                        <fieldValue>8</fieldValue>\n" +
                "                                    \n" +
                "                        <fieldOrder>0</fieldOrder>\n" +
                "                                    \n" +
                "                        <isView>true</isView>\n" +
                "                                    \n" +
                "                        <isEdit>true</isEdit>\n" +
                "                                    \n" +
                "                        <isMand>false</isMand>\n" +
                "                                  \n" +
                "                    </weaver.workflow.webservices.WorkflowRequestTableField>\n" +
                "                              \n" +
                "                    <weaver.workflow.webservices.WorkflowRequestTableField>\n" +
                "                                    \n" +
                "                        <fieldName>dh</fieldName>\n" +
                "                                    \n" +
                "                        <fieldValue>18403909715</fieldValue>\n" +
                "                                    \n" +
                "                        <fieldOrder>0</fieldOrder>\n" +
                "                                    \n" +
                "                        <isView>true</isView>\n" +
                "                                    \n" +
                "                        <isEdit>true</isEdit>\n" +
                "                                    \n" +
                "                        <isMand>false</isMand>\n" +
                "                                  \n" +
                "                    </weaver.workflow.webservices.WorkflowRequestTableField>\n" +
                "                              \n" +
                "                    <weaver.workflow.webservices.WorkflowRequestTableField>\n" +
                "                                    \n" +
                "                        <fieldName>sfwjsgcs1024</fieldName>\n" +
                "                                    \n" +
                "                        <fieldValue/>\n" +
                "                                    \n" +
                "                        <fieldOrder>0</fieldOrder>\n" +
                "                                    \n" +
                "                        <isView>true</isView>\n" +
                "                                    \n" +
                "                        <isEdit>true</isEdit>\n" +
                "                                    \n" +
                "                        <isMand>false</isMand>\n" +
                "                                  \n" +
                "                    </weaver.workflow.webservices.WorkflowRequestTableField>\n" +
                "                              \n" +
                "                    <weaver.workflow.webservices.WorkflowRequestTableField>\n" +
                "                                    \n" +
                "                        <fieldName>fbmc</fieldName>\n" +
                "                                    \n" +
                "                        <fieldValue/>\n" +
                "                                    \n" +
                "                        <fieldOrder>0</fieldOrder>\n" +
                "                                    \n" +
                "                        <isView>true</isView>\n" +
                "                                    \n" +
                "                        <isEdit>true</isEdit>\n" +
                "                                    \n" +
                "                        <isMand>false</isMand>\n" +
                "                                  \n" +
                "                    </weaver.workflow.webservices.WorkflowRequestTableField>\n" +
                "                              \n" +
                "                    <weaver.workflow.webservices.WorkflowRequestTableField>\n" +
                "                                    \n" +
                "                        <fieldName>slgcs</fieldName>\n" +
                "                                    \n" +
                "                        <fieldValue/>\n" +
                "                                    \n" +
                "                        <fieldOrder>0</fieldOrder>\n" +
                "                                    \n" +
                "                        <isView>true</isView>\n" +
                "                                    \n" +
                "                        <isEdit>true</isEdit>\n" +
                "                                    \n" +
                "                        <isMand>false</isMand>\n" +
                "                                  \n" +
                "                    </weaver.workflow.webservices.WorkflowRequestTableField>\n" +
                "                              \n" +
                "                    <weaver.workflow.webservices.WorkflowRequestTableField>\n" +
                "                                    \n" +
                "                        <fieldName>xcgcsdh1024</fieldName>\n" +
                "                                    \n" +
                "                        <fieldValue/>\n" +
                "                                    \n" +
                "                        <fieldOrder>0</fieldOrder>\n" +
                "                                    \n" +
                "                        <isView>true</isView>\n" +
                "                                    \n" +
                "                        <isEdit>true</isEdit>\n" +
                "                                    \n" +
                "                        <isMand>false</isMand>\n" +
                "                                  \n" +
                "                    </weaver.workflow.webservices.WorkflowRequestTableField>\n" +
                "                              \n" +
                "                    <weaver.workflow.webservices.WorkflowRequestTableField>\n" +
                "                                    \n" +
                "                        <fieldName>KHQC</fieldName>\n" +
                "                                    \n" +
                "                        <fieldValue/>\n" +
                "                                    \n" +
                "                        <fieldOrder>0</fieldOrder>\n" +
                "                                    \n" +
                "                        <isView>true</isView>\n" +
                "                                    \n" +
                "                        <isEdit>true</isEdit>\n" +
                "                                    \n" +
                "                        <isMand>false</isMand>\n" +
                "                                  \n" +
                "                    </weaver.workflow.webservices.WorkflowRequestTableField>\n" +
                "                              \n" +
                "                    <weaver.workflow.webservices.WorkflowRequestTableField>\n" +
                "                                    \n" +
                "                        <fieldName>khlx</fieldName>\n" +
                "                                    \n" +
                "                        <fieldValue/>\n" +
                "                                    \n" +
                "                        <fieldOrder>0</fieldOrder>\n" +
                "                                    \n" +
                "                        <isView>true</isView>\n" +
                "                                    \n" +
                "                        <isEdit>true</isEdit>\n" +
                "                                    \n" +
                "                        <isMand>false</isMand>\n" +
                "                                  \n" +
                "                    </weaver.workflow.webservices.WorkflowRequestTableField>\n" +
                "                              \n" +
                "                    <weaver.workflow.webservices.WorkflowRequestTableField>\n" +
                "                                    \n" +
                "                        <fieldName>productMode</fieldName>\n" +
                "                                    \n" +
                "                        <fieldValue/>\n" +
                "                                    \n" +
                "                        <fieldOrder>0</fieldOrder>\n" +
                "                                    \n" +
                "                        <isView>true</isView>\n" +
                "                                    \n" +
                "                        <isEdit>true</isEdit>\n" +
                "                                    \n" +
                "                        <isMand>false</isMand>\n" +
                "                                  \n" +
                "                    </weaver.workflow.webservices.WorkflowRequestTableField>\n" +
                "                              \n" +
                "                    <weaver.workflow.webservices.WorkflowRequestTableField>\n" +
                "                                    \n" +
                "                        <fieldName>SL</fieldName>\n" +
                "                                    \n" +
                "                        <fieldValue/>\n" +
                "                                    \n" +
                "                        <fieldOrder>0</fieldOrder>\n" +
                "                                    \n" +
                "                        <isView>true</isView>\n" +
                "                                    \n" +
                "                        <isEdit>true</isEdit>\n" +
                "                                    \n" +
                "                        <isMand>false</isMand>\n" +
                "                                  \n" +
                "                    </weaver.workflow.webservices.WorkflowRequestTableField>\n" +
                "                              \n" +
                "                    <weaver.workflow.webservices.WorkflowRequestTableField>\n" +
                "                                    \n" +
                "                        <fieldName>blsl</fieldName>\n" +
                "                                    \n" +
                "                        <fieldValue/>\n" +
                "                                    \n" +
                "                        <fieldOrder>0</fieldOrder>\n" +
                "                                    \n" +
                "                        <isView>true</isView>\n" +
                "                                    \n" +
                "                        <isEdit>true</isEdit>\n" +
                "                                    \n" +
                "                        <isMand>false</isMand>\n" +
                "                                  \n" +
                "                    </weaver.workflow.webservices.WorkflowRequestTableField>\n" +
                "                              \n" +
                "                    <weaver.workflow.webservices.WorkflowRequestTableField>\n" +
                "                                    \n" +
                "                        <fieldName>yccshj1024</fieldName>\n" +
                "                                    \n" +
                "                        <fieldValue/>\n" +
                "                                    \n" +
                "                        <fieldOrder>0</fieldOrder>\n" +
                "                                    \n" +
                "                        <isView>true</isView>\n" +
                "                                    \n" +
                "                        <isEdit>true</isEdit>\n" +
                "                                    \n" +
                "                        <isMand>false</isMand>\n" +
                "                                  \n" +
                "                    </weaver.workflow.webservices.WorkflowRequestTableField>\n" +
                "                              \n" +
                "                    <null/>\n" +
                "                              \n" +
                "                    <weaver.workflow.webservices.WorkflowRequestTableField>\n" +
                "                                    \n" +
                "                        <fieldName>cplx</fieldName>\n" +
                "                                    \n" +
                "                        <fieldValue/>\n" +
                "                                    \n" +
                "                        <fieldOrder>0</fieldOrder>\n" +
                "                                    \n" +
                "                        <isView>true</isView>\n" +
                "                                    \n" +
                "                        <isEdit>true</isEdit>\n" +
                "                                    \n" +
                "                        <isMand>false</isMand>\n" +
                "                                  \n" +
                "                    </weaver.workflow.webservices.WorkflowRequestTableField>\n" +
                "                              \n" +
                "                    <weaver.workflow.webservices.WorkflowRequestTableField>\n" +
                "                                    \n" +
                "                        <fieldName>sfsqhh1024</fieldName>\n" +
                "                                    \n" +
                "                        <fieldValue/>\n" +
                "                                    \n" +
                "                        <fieldOrder>0</fieldOrder>\n" +
                "                                    \n" +
                "                        <isView>true</isView>\n" +
                "                                    \n" +
                "                        <isEdit>true</isEdit>\n" +
                "                                    \n" +
                "                        <isMand>false</isMand>\n" +
                "                                  \n" +
                "                    </weaver.workflow.webservices.WorkflowRequestTableField>\n" +
                "                              \n" +
                "                    <weaver.workflow.webservices.WorkflowRequestTableField>\n" +
                "                                    \n" +
                "                        <fieldName>sqhhsl1024</fieldName>\n" +
                "                                    \n" +
                "                        <fieldValue/>\n" +
                "                                    \n" +
                "                        <fieldOrder>0</fieldOrder>\n" +
                "                                    \n" +
                "                        <isView>true</isView>\n" +
                "                                    \n" +
                "                        <isEdit>true</isEdit>\n" +
                "                                    \n" +
                "                        <isMand>false</isMand>\n" +
                "                                  \n" +
                "                    </weaver.workflow.webservices.WorkflowRequestTableField>\n" +
                "                              \n" +
                "                    <weaver.workflow.webservices.WorkflowRequestTableField>\n" +
                "                                    \n" +
                "                        <fieldName>dkhdyx</fieldName>\n" +
                "                                    \n" +
                "                        <fieldValue/>\n" +
                "                                    \n" +
                "                        <fieldOrder>0</fieldOrder>\n" +
                "                                    \n" +
                "                        <isView>true</isView>\n" +
                "                                    \n" +
                "                        <isEdit>true</isEdit>\n" +
                "                                    \n" +
                "                        <isMand>false</isMand>\n" +
                "                                  \n" +
                "                    </weaver.workflow.webservices.WorkflowRequestTableField>\n" +
                "                              \n" +
                "                    <null/>\n" +
                "                              \n" +
                "                    <weaver.workflow.webservices.WorkflowRequestTableField>\n" +
                "                                    \n" +
                "                        <fieldName>wtfjsc</fieldName>\n" +
                "                                    \n" +
                "                        <fieldOrder>0</fieldOrder>\n" +
                "                                    \n" +
                "                        <isView>true</isView>\n" +
                "                                    \n" +
                "                        <isEdit>true</isEdit>\n" +
                "                                    \n" +
                "                        <isMand>false</isMand>\n" +
                "                                  \n" +
                "                    </weaver.workflow.webservices.WorkflowRequestTableField>\n" +
                "                              \n" +
                "                    <weaver.workflow.webservices.WorkflowRequestTableField>\n" +
                "                                    \n" +
                "                        <fieldName>WTXXMS</fieldName>\n" +
                "                                    \n" +
                "                        <fieldValue/>\n" +
                "                                    \n" +
                "                        <fieldOrder>0</fieldOrder>\n" +
                "                                    \n" +
                "                        <isView>true</isView>\n" +
                "                                    \n" +
                "                        <isEdit>true</isEdit>\n" +
                "                                    \n" +
                "                        <isMand>false</isMand>\n" +
                "                                  \n" +
                "                    </weaver.workflow.webservices.WorkflowRequestTableField>\n" +
                "                              \n" +
                "                    <weaver.workflow.webservices.WorkflowRequestTableField>\n" +
                "                                    \n" +
                "                        <fieldName>xccpybbms</fieldName>\n" +
                "                                    \n" +
                "                        <fieldValue/>\n" +
                "                                    \n" +
                "                        <fieldOrder>0</fieldOrder>\n" +
                "                                    \n" +
                "                        <isView>true</isView>\n" +
                "                                    \n" +
                "                        <isEdit>true</isEdit>\n" +
                "                                    \n" +
                "                        <isMand>false</isMand>\n" +
                "                                  \n" +
                "                    </weaver.workflow.webservices.WorkflowRequestTableField>\n" +
                "                              \n" +
                "                    <weaver.workflow.webservices.WorkflowRequestTableField>\n" +
                "                                    \n" +
                "                        <fieldName>bltpsp</fieldName>\n" +
                "                                    \n" +
                "                        <fieldOrder>0</fieldOrder>\n" +
                "                                    \n" +
                "                        <isView>false</isView>\n" +
                "                                    \n" +
                "                        <isEdit>false</isEdit>\n" +
                "                                    \n" +
                "                        <isMand>false</isMand>\n" +
                "                                  \n" +
                "                    </weaver.workflow.webservices.WorkflowRequestTableField>\n" +
                "                              \n" +
                "                    <weaver.workflow.webservices.WorkflowRequestTableField>\n" +
                "                                    \n" +
                "                        <fieldName>sfgb</fieldName>\n" +
                "                                    \n" +
                "                        <fieldValue/>\n" +
                "                                    \n" +
                "                        <fieldOrder>0</fieldOrder>\n" +
                "                                    \n" +
                "                        <isView>true</isView>\n" +
                "                                    \n" +
                "                        <isEdit>true</isEdit>\n" +
                "                                    \n" +
                "                        <isMand>false</isMand>\n" +
                "                                  \n" +
                "                    </weaver.workflow.webservices.WorkflowRequestTableField>\n" +
                "                              \n" +
                "                    <weaver.workflow.webservices.WorkflowRequestTableField>\n" +
                "                                    \n" +
                "                        <fieldName>LSFA</fieldName>\n" +
                "                                    \n" +
                "                        <fieldValue/>\n" +
                "                                    \n" +
                "                        <fieldOrder>0</fieldOrder>\n" +
                "                                    \n" +
                "                        <isView>true</isView>\n" +
                "                                    \n" +
                "                        <isEdit>true</isEdit>\n" +
                "                                    \n" +
                "                        <isMand>false</isMand>\n" +
                "                                  \n" +
                "                    </weaver.workflow.webservices.WorkflowRequestTableField>\n" +
                "                              \n" +
                "                    <weaver.workflow.webservices.WorkflowRequestTableField>\n" +
                "                                    \n" +
                "                        <fieldName>qwjjrq</fieldName>\n" +
                "                                    \n" +
                "                        <fieldValue/>\n" +
                "                                    \n" +
                "                        <fieldOrder>0</fieldOrder>\n" +
                "                                    \n" +
                "                        <isView>true</isView>\n" +
                "                                    \n" +
                "                        <isEdit>true</isEdit>\n" +
                "                                    \n" +
                "                        <isMand>false</isMand>\n" +
                "                                  \n" +
                "                    </weaver.workflow.webservices.WorkflowRequestTableField>\n" +
                "                              \n" +
                "                    <weaver.workflow.webservices.WorkflowRequestTableField>\n" +
                "                                    \n" +
                "                        <fieldName>BZ</fieldName>\n" +
                "                                    \n" +
                "                        <fieldValue/>\n" +
                "                                    \n" +
                "                        <fieldOrder>0</fieldOrder>\n" +
                "                                    \n" +
                "                        <isView>true</isView>\n" +
                "                                    \n" +
                "                        <isEdit>true</isEdit>\n" +
                "                                    \n" +
                "                        <isMand>false</isMand>\n" +
                "                                  \n" +
                "                    </weaver.workflow.webservices.WorkflowRequestTableField>\n" +
                "                              \n" +
                "                    <weaver.workflow.webservices.WorkflowRequestTableField>\n" +
                "                                    \n" +
                "                        <fieldName>FJ</fieldName>\n" +
                "                                    \n" +
                "                        <fieldOrder>0</fieldOrder>\n" +
                "                                    \n" +
                "                        <isView>false</isView>\n" +
                "                                    \n" +
                "                        <isEdit>false</isEdit>\n" +
                "                                    \n" +
                "                        <isMand>false</isMand>\n" +
                "                                  \n" +
                "                    </weaver.workflow.webservices.WorkflowRequestTableField>\n" +
                "                              \n" +
                "                    <weaver.workflow.webservices.WorkflowRequestTableField>\n" +
                "                                    \n" +
                "                        <fieldName>creationSource</fieldName>\n" +
                "                                    \n" +
                "                        <fieldValue>0</fieldValue>\n" +
                "                                    \n" +
                "                        <fieldOrder>0</fieldOrder>\n" +
                "                                    \n" +
                "                        <isView>true</isView>\n" +
                "                                    \n" +
                "                        <isEdit>true</isEdit>\n" +
                "                                    \n" +
                "                        <isMand>false</isMand>\n" +
                "                                  \n" +
                "                    </weaver.workflow.webservices.WorkflowRequestTableField>\n" +
                "                              \n" +
                "                    <weaver.workflow.webservices.WorkflowRequestTableField>\n" +
                "                                    \n" +
                "                        <fieldName>recordID</fieldName>\n" +
                "                                    \n" +
                "                        <fieldValue/>\n" +
                "                                    \n" +
                "                        <fieldOrder>0</fieldOrder>\n" +
                "                                    \n" +
                "                        <isView>true</isView>\n" +
                "                                    \n" +
                "                        <isEdit>true</isEdit>\n" +
                "                                    \n" +
                "                        <isMand>false</isMand>\n" +
                "                                  \n" +
                "                    </weaver.workflow.webservices.WorkflowRequestTableField>\n" +
                "                              \n" +
                "                    <weaver.workflow.webservices.WorkflowRequestTableField>\n" +
                "                                    \n" +
                "                        <fieldName>SQRQ</fieldName>\n" +
                "                                    \n" +
                "                        <fieldValue/>\n" +
                "                                    \n" +
                "                        <fieldOrder>0</fieldOrder>\n" +
                "                                    \n" +
                "                        <isView>true</isView>\n" +
                "                                    \n" +
                "                        <isEdit>true</isEdit>\n" +
                "                                    \n" +
                "                        <isMand>false</isMand>\n" +
                "                                  \n" +
                "                    </weaver.workflow.webservices.WorkflowRequestTableField>\n" +
                "                              \n" +
                "                    <weaver.workflow.webservices.WorkflowRequestTableField>\n" +
                "                                    \n" +
                "                        <fieldName>sn</fieldName>\n" +
                "                                    \n" +
                "                        <fieldValue/>\n" +
                "                                    \n" +
                "                        <fieldOrder>0</fieldOrder>\n" +
                "                                    \n" +
                "                        <isView>true</isView>\n" +
                "                                    \n" +
                "                        <isEdit>true</isEdit>\n" +
                "                                    \n" +
                "                        <isMand>false</isMand>\n" +
                "                                  \n" +
                "                    </weaver.workflow.webservices.WorkflowRequestTableField>\n" +
                "                              \n" +
                "                    <weaver.workflow.webservices.WorkflowRequestTableField>\n" +
                "                                    \n" +
                "                        <fieldName>recordID</fieldName>\n" +
                "                                    \n" +
                "                        <fieldValue/>\n" +
                "                                    \n" +
                "                        <fieldOrder>0</fieldOrder>\n" +
                "                                    \n" +
                "                        <isView>true</isView>\n" +
                "                                    \n" +
                "                        <isEdit>true</isEdit>\n" +
                "                                    \n" +
                "                        <isMand>false</isMand>\n" +
                "                                  \n" +
                "                    </weaver.workflow.webservices.WorkflowRequestTableField>\n" +
                "                              \n" +
                "                    <null/>\n" +
                "                              \n" +
                "                    <null/>\n" +
                "                            \n" +
                "                </workflowRequestTableFields>\n" +
                "                      \n" +
                "            </weaver.workflow.webservices.WorkflowRequestTableRecord>\n" +
                "                \n" +
                "        </requestRecords>\n" +
                "          \n" +
                "    </workflowMainTableInfo>\n" +
                "      \n" +
                "    <workflowDetailTableInfos>\n" +
                "            \n" +
                "        <weaver.workflow.webservices.WorkflowDetailTableInfo>\n" +
                "                  \n" +
                "            <workflowRequestTableRecords>\n" +
                "                        \n" +
                "                <weaver.workflow.webservices.WorkflowRequestTableRecord>\n" +
                "                              \n" +
                "                    <recordOrder>0</recordOrder>\n" +
                "                              \n" +
                "                    <workflowRequestTableFields>\n" +
                "                                    \n" +
                "                        <weaver.workflow.webservices.WorkflowRequestTableField>\n" +
                "                                          \n" +
                "                            <fieldName>ycsl</fieldName>\n" +
                "                                          \n" +
                "                            <fieldValue/>\n" +
                "                                          \n" +
                "                            <fieldOrder>0</fieldOrder>\n" +
                "                                          \n" +
                "                            <isView>true</isView>\n" +
                "                                          \n" +
                "                            <isEdit>true</isEdit>\n" +
                "                                          \n" +
                "                            <isMand>false</isMand>\n" +
                "                                        \n" +
                "                        </weaver.workflow.webservices.WorkflowRequestTableField>\n" +
                "                                  \n" +
                "                    </workflowRequestTableFields>\n" +
                "                            \n" +
                "                </weaver.workflow.webservices.WorkflowRequestTableRecord>\n" +
                "                      \n" +
                "            </workflowRequestTableRecords>\n" +
                "                \n" +
                "        </weaver.workflow.webservices.WorkflowDetailTableInfo>\n" +
                "            \n" +
                "        <weaver.workflow.webservices.WorkflowDetailTableInfo>\n" +
                "                  \n" +
                "            <workflowRequestTableRecords/>\n" +
                "                \n" +
                "        </weaver.workflow.webservices.WorkflowDetailTableInfo>\n" +
                "          \n" +
                "    </workflowDetailTableInfos>\n" +
                "    \n" +
                "</WorkflowRequestInfo>\n";
    }


    private static String createWorkflowInfo(HashMap<String, Object> params,Integer userId) {
        //生成工作流信息
        WorkflowRequestInfo workflowRequestInfo = new WorkflowRequestInfo();

        /*============================================ 1.标题 Begin ===================================================*/
        //显示
        workflowRequestInfo.setCanView(true);
        //可编辑
        workflowRequestInfo.setCanEdit(true);
        //请求标题
        workflowRequestInfo.setRequestName(MapUtil.getStr(params, "bt", ""));
        //请求重要级别 0：正常 1：重要 2：紧急
        workflowRequestInfo.setRequestLevel("0");
        //创建者id  通过 申请人 字段转化; 创建流程时为必输项
        workflowRequestInfo.setCreatorId(String.valueOf(userId));
        /*============================================ 1.标题 End ===================================================*/

        /*============================================ 2.工作流基本信息 Begin========================================= */
        WorkflowBaseInfo workflowBaseInfo = new WorkflowBaseInfo();
        //测试系统workflowId//1141
        workflowBaseInfo.setWorkflowId("1399");
        //流程名称
        workflowBaseInfo.setWorkflowName(MapUtil.getStr(params, "bt", ""));
        //流程类型名称
        workflowBaseInfo.setWorkflowTypeName("com/best/practice/intertype/webservice");
        workflowRequestInfo.setWorkflowBaseInfo(workflowBaseInfo);
        /*============================================ 2.工作流基本信息 End ========================================= */

        /*============================================ 3.填充主表  Begin   ========================================= */
        WorkflowMainTableInfo workflowMainTableInfo = new WorkflowMainTableInfo();
        WorkflowRequestTableRecord[] workflowTableRecords = new WorkflowRequestTableRecord[1];
        WorkflowRequestTableField[] workflowTableFields = new WorkflowRequestTableField[40];

        //异常品运单号 TODO 非必填项，跳过异常单号和收件人
        String ycNum = MapUtil.getStr(params, "ycpydh1024", "");
        workflowTableFields[0] = new TableFieldBuilder().fieldName("ycpydh1024").fieldValue(ycNum).build();
        //收件人(ID、电话、部门) TODO 非必填项，跳过异常单号和收件人
        String sjrgh = MapUtil.getStr(params,"sjrgh");
        if (StrUtil.isNotBlank(sjrgh)){
            workflowTableFields[1] = new TableFieldBuilder().fieldName("sjr1024").fieldValue("7731").build();
            workflowTableFields[2] = new TableFieldBuilder().fieldName("sjrdh1024").fieldValue("18403909715").build();
            workflowTableFields[3] = new TableFieldBuilder().fieldName("sjrbm1024").fieldValue("8").build();
        }

        // 申请人(ID)
        workflowTableFields[4] = new TableFieldBuilder().fieldName("sqr").fieldValue("7731").build();

        // 申请人岗位 TODO
        workflowTableFields[5] = new TableFieldBuilder().fieldName("gw").fieldValue("516").build();

        // 申请人所在部门 TODO
        workflowTableFields[6] = new TableFieldBuilder().fieldName("szbm").fieldValue("8").build();

        // 申请人电话
        workflowTableFields[7] = new TableFieldBuilder().fieldName("dh").fieldValue("18403909715").build();

        // 是否为技术工程师
        workflowTableFields[8] = new TableFieldBuilder().fieldName("sfwjsgcs1024").fieldValue("").build();

        // 分部名称 下拉框
        workflowTableFields[9] = new TableFieldBuilder().fieldName("fbmc").fieldValue("").build();

        // 现场支持工程师
        workflowTableFields[10] = new TableFieldBuilder().fieldName("slgcs").fieldValue("").build();

        // 现场支持工程师电话
        workflowTableFields[11] = new TableFieldBuilder().fieldName("xcgcsdh1024").fieldValue("19874214398").build();

        // 客户全称
        workflowTableFields[12] = new TableFieldBuilder().fieldName("KHQC").fieldValue("").build();

        // 客户类型
        workflowTableFields[13] = new TableFieldBuilder().fieldName("khlx").fieldValue("").build();

        // 产品型号
        workflowTableFields[14] = new TableFieldBuilder().fieldName("productMode").fieldValue("").build();

        // 现场使用数量
        workflowTableFields[15] = new TableFieldBuilder().fieldName("SL").fieldValue("").build();

        // 异常数量
        workflowTableFields[16] = new TableFieldBuilder().fieldName("blsl").fieldValue("").build();

        // 异常生产环节
        workflowTableFields[17] = new TableFieldBuilder().fieldName("yccshj1024").fieldValue("").build();

        // 产品线
        workflowTableFields[19] = new TableFieldBuilder().fieldName("cplx").fieldValue("").build();

        // 是否申请退换货
        workflowTableFields[20] = new TableFieldBuilder().fieldName("sfsqhh1024").fieldValue("").build();

        // 申请退换货数量
        workflowTableFields[21] = new TableFieldBuilder().fieldName("sqhhsl1024").fieldValue("").build();

        // 对客户的影响
        workflowTableFields[22] = new TableFieldBuilder().fieldName("dkhdyx").fieldValue("").build();

        // 抄送人员
//        workflowTableFields[23] = new TableFieldBuilder().fieldName("csry1ghs").fieldValue("nova002464").build();

        // 现场问题信息收集表上传
        workflowTableFields[24] = new TableFieldBuilder().fieldName("wtfjsc").fieldValue("0").build();

        // 问题描述、排查过程及结果
        workflowTableFields[25] = new TableFieldBuilder().fieldName("WTXXMS").fieldValue("").build();

        // 现场产品与版本描述
        workflowTableFields[26] = new TableFieldBuilder().fieldName("xccpybbms").fieldValue("").build();

        // 不良图片/视频
        workflowTableFields[27] = new TableFieldBuilder()
                .fieldName("bltpsp")
                .fieldValue("")
                .isView(false)
                .isEdit(false).build();

        // 现场问题是否临时解决
        workflowTableFields[28] = new TableFieldBuilder().fieldName("sfgb").fieldValue("").build();

        // 现场问题临时解决方案
        workflowTableFields[29] = new TableFieldBuilder().fieldName("LSFA").fieldValue("").build();

        // 现场问题期望解决日期
        workflowTableFields[30] = new TableFieldBuilder().fieldName("qwjjrq").fieldValue("").build();

        // 备注
        workflowTableFields[31] = new TableFieldBuilder().fieldName("BZ").fieldValue("").build();

        // 附件01
        workflowTableFields[32] = new TableFieldBuilder().fieldName("FJ").fieldValue("0").build();

        // 创建来源 0-工单   1-OA
        workflowTableFields[33] = new TableFieldBuilder().fieldName("creationSource").fieldValue("0").build();

        // 数据ID
        workflowTableFields[34] = new TableFieldBuilder().fieldName("recordID").fieldValue("").build();

        // 申请日期
        workflowTableFields[35] = new TableFieldBuilder().fieldName("SQRQ").fieldValue("").build();

        // 拼接明细表sn到主表sn字段中
        workflowTableFields[36] = new TableFieldBuilder().fieldName("sn").fieldValue("").build();

        // recordID 2023年11月2日 16:08:36
        workflowTableFields[37] = new TableFieldBuilder().fieldName("recordID").fieldValue("").build();


        // 将属性设置给记录
        workflowTableRecords[0] = new WorkflowRequestTableRecord();
        workflowTableRecords[0].setWorkflowRequestTableFields(workflowTableFields);
        // 将记录设置给主表
        workflowMainTableInfo.setRequestRecords(workflowTableRecords);
        // 将主表设置给请求信息
        workflowRequestInfo.setWorkflowMainTableInfo(workflowMainTableInfo);
        /*============================================ 3.填充主表  End   ========================================= */

        /*============================================ 4.填充明细表 Begin   ========================================= */
        // 两个明细表
        WorkflowDetailTableInfo[] workflowDetailTableInfo = new WorkflowDetailTableInfo[2];

        // ******************************************* 第一张明细表开始 *******************************************
        workflowTableRecords = new WorkflowRequestTableRecord[1];
        workflowTableFields = new WorkflowRequestTableField[1];

        workflowTableFields[0] = new TableFieldBuilder().fieldName("ycsl").fieldValue("").build();

        // 填充完毕，设置属性给记录
        workflowTableRecords[0] = new WorkflowRequestTableRecord();
        workflowTableRecords[0].setWorkflowRequestTableFields(workflowTableFields);

        // 设置记录给第一张表
        workflowDetailTableInfo[0] = new WorkflowDetailTableInfo();
        workflowDetailTableInfo[0].setWorkflowRequestTableRecords(workflowTableRecords);

        // ******************************************* 第二张明细表开始 *******************************************
        ArrayList<Map<String,String>> snList = MapUtil.get(params,"ks",ArrayList.class,new ArrayList());
        //TODO 待删除
        snList = new ArrayList<>();
        HashMap<String, String> toTest = new HashMap<>();
        toTest.put("snCodes","1");
        snList.add(toTest);
        workflowTableRecords = new WorkflowRequestTableRecord[snList.size()];

        for (int i = 0; i < snList.size(); i++) {
            workflowTableFields = new WorkflowRequestTableField[2];
            String code = snList.get(i).get("snCodes");
            String deliveryDate = snList.get(i).get("deliveryDates");
            workflowTableFields[0] = new TableFieldBuilder().fieldName("snCode").fieldValue("111").build();

            workflowTableFields[1] = new TableFieldBuilder().fieldName("deliveryDate").fieldValue("2024-10-24").build();

            // 填充完毕，设置属性给记录
            workflowTableRecords[i]  = new WorkflowRequestTableRecord();
            workflowTableRecords[i].setWorkflowRequestTableFields(workflowTableFields);
        }
        workflowDetailTableInfo[1] =new WorkflowDetailTableInfo();
        workflowDetailTableInfo[1].setWorkflowRequestTableRecords(workflowTableRecords);
        workflowRequestInfo.setWorkflowDetailTableInfos(workflowDetailTableInfo);
        /*============================================ 4.填充明细表 End   ========================================= */

        String requestInfo = "";

        XmlUtil xmlUtil = XmlUtil.getInstance();
        requestInfo = xmlUtil.objToXml(workflowRequestInfo);
        System.out.println("生成的requestInfo:\n"+requestInfo);
        return requestInfo;
    }

    static class TableFieldBuilder {

        private  WorkflowRequestTableField field = new WorkflowRequestTableField();

        {
            field.setEdit(true);
            field.setView(true);
        }


        public TableFieldBuilder fieldId(String fieldId) {
            field.setFieldId(fieldId);
            return this;
        }

        public TableFieldBuilder fieldName(String fieldName) {
            field.setFieldName(fieldName);
            return this;
        }

        public TableFieldBuilder fieldValue(String fieldValue) {
            field.setFieldValue(fieldValue);
            return this;
        }

        public TableFieldBuilder fieldHtmlType(String fieldHtmlType) {
            field.setFieldHtmlType(fieldHtmlType);
            return this;
        }

        public TableFieldBuilder fieldType(String fieldType) {
            field.setFieldType(fieldType);
            return this;
        }

        public TableFieldBuilder fieldDBType(String fieldDBType) {
            field.setFieldDBType(fieldDBType);
            return this;
        }

        public TableFieldBuilder fieldFormName(String fieldFormName) {
            field.setFieldFormName(fieldFormName);
            return this;
        }

        public TableFieldBuilder fieldOrder(int fieldOrder) {
            field.setFieldOrder(fieldOrder);
            return this;
        }

        public TableFieldBuilder isView(boolean isView) {
            field.setView(isView);
            return this;
        }

        public TableFieldBuilder isEdit(boolean isEdit) {
            field.setView(isEdit);
            return this;
        }

        public TableFieldBuilder isMand(boolean isMand) {
            field.setMand(isMand);
            return this;
        }

        public TableFieldBuilder fieldShowName(String fieldShowName) {
            field.setFieldShowName(fieldShowName);
            return this;
        }

        public TableFieldBuilder browserurl(String browserurl) {
            field.setBrowserurl(browserurl);
            return this;
        }

        public TableFieldBuilder selectnames(String[] selectnames) {
            field.setSelectnames(selectnames);
            return this;
        }

        public TableFieldBuilder selectvalues(String[] selectvalues) {
            field.setSelectvalues(selectvalues);
            return this;
        }

        public TableFieldBuilder fieldShowValue(String fieldShowValue) {
            field.setFieldShowValue(fieldShowValue);
            return this;
        }

        public TableFieldBuilder filedHtmlShow(String filedHtmlShow) {
            field.setFiledHtmlShow(filedHtmlShow);
            return this;
        }

        public WorkflowRequestTableField build() {
            return field;
        }
    }


}
