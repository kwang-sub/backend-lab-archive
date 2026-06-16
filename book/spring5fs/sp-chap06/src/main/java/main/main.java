package main;

import config.AppCtx;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import spring.Client;
import spring.Client2;

public class main {

    public static void main(String[] args) {

        AbstractApplicationContext ctx = new AnnotationConfigApplicationContext(AppCtx.class);

        Client client = ctx.getBean(Client.class);
        Client2 client2 = ctx.getBean(Client2.class);

        try {
            Thread.sleep(1000);
            client.send();
            client2.send();
            Thread.sleep(1000);
        }catch (Exception e){
            e.printStackTrace();
        }

        ctx.close();

    }
}
