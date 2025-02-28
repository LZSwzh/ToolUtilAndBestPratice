package xml.pojo;

import lombok.Data;

/**
 * @author wangzh
 */
@Data
public class User {
    private String name;

    private Integer age;

    private Dept dept;

    private School[] schools;
}
