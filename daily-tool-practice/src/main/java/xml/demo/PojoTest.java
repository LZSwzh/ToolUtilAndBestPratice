package xml.demo;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.security.AnyTypePermission;
import xml.pojo.Dept;
import xml.pojo.School;
import xml.pojo.User;

public class PojoTest {
    public static void main(String[] args) {
        /** 构造嵌套对象 */
        User user = new User();
        user.setName("zhangsan");
        user.setAge(30);

        Dept dept = new Dept("it",1,30);
        user.setDept(dept);

        School[] schools = new School[3];
        schools[0] = new School("陕西省实验中学","高中");
        schools[1] = new School("西安理工大学","本科");
        schools[2] = new School("西安科技大学","硕士");
        user.setSchools(schools);

        System.out.println("user对象信息 = " + user);

        /** obj序列化为xml */
        XStream xstream = new XStream();

        String xml = xstream.toXML(user);
        System.out.println(xml);


        /** xml反序列化为obj */
        user = (User) xstream.fromXML(xml);
        System.out.println("user2 = " + user);
    }
}
