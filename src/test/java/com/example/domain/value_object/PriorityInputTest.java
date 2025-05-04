package com.example.domain.value_object;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.domain.value_object.task.priority.Priority;


@SpringBootTest
public class PriorityInputTest {

    @Test
    public void  優先順位が未入力() {
        try {
            Priority priority = new Priority(0);
            priority.getTasKPriority();
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }
}
