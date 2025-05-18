package com.example.domain.value_object.user.name;

import java.nio.file.Path;

// import java.nio.file.Path;

// import org.junit.jupiter.api.AfterAll;
// import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.junit.jupiter.api.io.TempDir;


@SpringBootTest
public class FirstNameTest {


    // @TempDir static Path tempDir;
    

    // @BeforeAll
    // static void beforeAll() {
    //     System.out.println("FirstNameTest テスト開始");
    // }

    
    @Test
    public void testAddFirstName(@TempDir Path tempDir) throws Error {
        var file = tempDir.resolve("file.txt");
        FirstName firstName = new FirstName("");
        firstName.getFirstNameValue();
        System.out.println(file);
    }

    // @AfterAll
    // static void afterAll() {
    //     System.out.println("FirstNameTest テスト終了");
    // }
    
}
