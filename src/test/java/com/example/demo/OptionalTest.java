package com.example.demo;

import com.example.demo.service.Factory.OperatorFactory;
import com.example.demo.service.interf.Operation;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

/**
 * @program: demo
 * @description:
 * @author: Irving
 * @create: 2020-06-17
 **/
@SpringBootTest
public class OptionalTest {

    @Test
    public void OptionalTest() {

        User user = new User();
        user.setUsername("zbangsan").setPassword("123456");

        Optional.ofNullable(user).ifPresent(u -> {
            u.setUsername("lisi");
            System.out.println(u.password);
            System.out.println(u.username);
        });
    }

    @Test
    public void OptionalTest1() {

        User user = null;

        Optional.ofNullable(user);

    }

    public void OptionalTest2() {
        Province province = new Province().setCity("").setDistrict("");
    }


    public String getStreetName(Province province) {
        return Optional.ofNullable(province)
                .map(Province::getCity)
                .orElse("未找到该道路名");
    }
    @Test
    public void factoryTest(){
        int add = calculateUsingFactory(5, 2, "add");
        System.out.println(add);
    }

    public int calculateUsingFactory(int a, int b, String operator) {
        Operation targetOperation = OperatorFactory
                .getOperation(operator)
                .orElseThrow(() -> new IllegalArgumentException("Invalid Operator"));
        return targetOperation.apply(a, b);
    }


    class User {
        String username;
        String password;

        public User setUsername(String username) {
            this.username = username;
            return this;
        }

        public User setPassword(String password) {
            this.password = password;
            return this;
        }
    }

    class Province {

        private String city;
        private String district;
        private String street;
        private String name;

        public String getCity() {
            return city;
        }

        public String getDistrict() {
            return district;
        }

        public String getStreet() {
            return street;
        }

        public String getName() {
            return name;
        }

        public Province setCity(String city) {
            this.city = city;
            return this;
        }

        public Province setDistrict(String district) {
            this.district = district;
            return this;
        }

        public Province setStreet(String street) {
            this.street = street;
            return this;
        }

        public Province setName(String name) {
            this.name = name;
            return this;
        }
    }
}
