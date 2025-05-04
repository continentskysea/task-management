package com.example.domain.value_object.user.name;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;



@SpringBootTest
public class FirstNameTest {

    @BeforeAll
    static void beforeAll() {
        System.out.println("FirstNameTest テスト開始");
    }

    @Test
    public void testAddFirstName() throws Error {
        FirstName firstName = new FirstName("");
        firstName.getFirstNameValue();
    }

    @AfterAll
    static void afterAll() {
        System.out.println("FirstNameTest テスト終了");
    }
    
}
