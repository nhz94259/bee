package com.nhz.mycode.spring.xml;

import org.springframework.context.support.ClassPathXmlApplicationContext;


public class xmlSpringApplication {

    public static void main(String[] args) throws Exception {
        ClassPathXmlApplicationContext context =
                new ClassPathXmlApplicationContext("classpath:/*.xml");
        Knight knight = context.getBean(Knight.class);
        knight.embarkOnQuest();
        context.close();
    }

    public interface Quest {
        void embark();
    }

    public interface Knight {
        void embarkOnQuest();
    }


}
