package fsm;

import fsm.byspring.IOrderService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class Test {
    public static void main(String[] args) {
        Thread.currentThread().setName("主线程");

        ConfigurableApplicationContext context = SpringApplication.run(Test.class,args);

        IOrderService orderService = (IOrderService)context.getBean("orderService");

        orderService.createOrder();
        orderService.createOrder();

        orderService.pay(1L);

        new Thread("客户线程"){
            @Override
            public void run() {
                orderService.ship(1L);
                orderService.receive(1L);
            }
        }.start();

        orderService.pay(2L);
        orderService.ship(2L);
        orderService.receive(2L);
    }
}
