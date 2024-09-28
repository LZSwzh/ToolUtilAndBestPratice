package com.best.pratice.stream.bisic;

import com.best.pratice.stream.entity.User;

import static com.best.pratice.stream.nullIdex.NullTest.userArr;

public class parallerTest {
    static String suffix = "aiushgui";
    User[] UserArr = new User[10];
    static {
        User user1 = new User("wzh", 232, 5222.2);
        userArr[0] = user1;
        User user2 = new User("yrt", 74, 70512985.0);
        userArr[1] = user2;
        User user3 = new User("qws", 722, 22342.2);
        userArr[2] = user3;
        User user4 = new User("uhg", 224, 2272.2);
        userArr[3] = user4;
        for (int i = 4; i < 11; i++) {
            userArr[i] = new User(suffix+i,382549,123542.3);
        }
    }
    /**
     * 当某个需求是对一个超大的集合遍历然后进行重复的操作，适合使用并行流对利用多核CPU提升程序性能
     * 但是可能导致：
     *          1.线程安全问题，最好使用安全的集合类型
     *          2.ThreadLocal
     *          3.
     * @param args
     */
    public static void main(String[] args) {

    }
}
