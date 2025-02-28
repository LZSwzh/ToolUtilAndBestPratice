package xml.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author wangzh
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Dept {
    private String deptName;

    private Integer level;

    private Integer employeeCount;
}
