package com.best.practice.version.jdk8.optional;

import java.util.Optional;
import java.util.stream.Stream;

public class OptionalApiTest {
    public static void main(String[] args) {
        /**  ====================================一:介绍构造Optional的方法  ====================================*/
        /** 1.1.构建方法1:of方法,如果传入图null会报错控制针*/
        String str1 = "ABB";
        String str2 = null;
        Optional<String> opt1 = Optional.of(str1);
//        Optional<String> opt2 = Optional.of(str2);
        /** 1.2.构建方法2：ofNullable方法,如果传入图null不会报错空指针*/
        Optional<Object> opt3 = Optional.ofNullable(null);
        /** 1.3.构造方法3：empty相当于ofNullable(null) ，注意对empty返回的使用get会抛出异常*/
        Optional<Object> opt4 = Optional.empty();
        /** ==================================== 二：介绍ifPresent API  ==================================== */
        if (opt1.isPresent()) System.out.println("------------------------2.1.boolean ifPresent():"+opt1.get());
        //  void ifPresent(Consumer<? super T> action)
        opt1.ifPresent(s ->   System.out.println("------------------------2.2.void ifPresent(SupplierFunction):"+s.toUpperCase()));
        /** ==================================== 三:介绍orElse API ====================================*/
        //Optional有值则返回,无值则返回orElse传入的参数，注意传入的是T  类型
        String orElseStr = Optional.ofNullable("ABB").orElse("BBB");
        System.out.println("------------------------3.1.T orElseStr(T):"+orElseStr);

        //orElseGet()同orElse()，区别在于orElseGet()的参数是一个Supplier接口，
        String ss = null;
        String orElseGetStr = Optional.ofNullable(ss).orElseGet(() -> "BBB");
        System.out.println("------------------------3.2.T orElseGetStr(Supplier<? extend T>):"+orElseGetStr);

        //orElseThrow()如果存在则返回，不存在抛出自定义异常;不传参默认抛NoSuchElementException:No value present
//        String s = Optional.ofNullable(ss).orElseThrow();
//        String s2 = Optional.ofNullable(ss).orElseThrow(()->new RuntimeException("自定义异常"));


        /** ==================================== 四:介绍map API ====================================*/
        /*
        map()方法:当Optional有值时，执行map方法，并返回一个Optional对象，否则返回一个空的Optional对象。
         */
        User userA = new User("张三", 18);
        Optional<Integer> optA = Optional.ofNullable(userA).map(user -> user.getAge());
        System.out.println("------------------------4.1.map有值:"+optA.orElseGet(()->-1));
        User userB = new User("李四", null);
        Optional<Integer> optB = Optional.ofNullable(userB).map(User::getAge);
        System.out.println("------------------------4.2.map无值:"+optB.orElseGet(()->-1));
        /** ==================================== 五:介绍filter API ====================================*/
        User userC = new User("王五", 18);
        Optional<User> filterOpt = Optional.ofNullable(userC)
                .filter(user -> user.getAge() > 18);
        if (filterOpt.isPresent()){
            System.out.println("------------------------5.1.存在返回原Opt:"+filterOpt.get());
        }else {
            System.out.println("------------------------5.2.不存在返回empty的Opt:"+filterOpt.orElse(new User("null",null)));
        }
        /** ==================================== 六:介绍or API(JDK11) ====================================*/
        //和orElse、orElseGet类似，区别在于传入一个supplier,返回的是自定义的Optional.整体也返回Opt
        User userD = null;
        Optional<User> orOpt = Optional.ofNullable(userD).or(() -> Optional.of(new User("张三", 18)));
        System.out.println("------------------------6.1.orOpt:"+orOpt.orElse(new User("null",null)));
        /** ==================================== 七:介绍stream API ====================================*/
        //stream()方法:如果Optional有值，则返回一个包含该值的Stream，否则返回一个空的Stream。Stream。empty()
        User userE = new User("张三", 18);
        Stream<User> streamS = Optional.ofNullable(userE).stream();
    }

    static class User{
        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Integer getAge() {
            return age;
        }

        public void setAge(Integer age) {
            this.age = age;
        }

        public User(String name, Integer age) {
            this.name = name;
            this.age = age;
        }

        @Override
        public String toString() {
            return "User{" +
                    "name='" + name + '\'' +
                    ", age=" + age +
                    '}';
        }

        private String name;
        private Integer age;

    }
}
