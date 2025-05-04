package com.example.domain.value_object;

import org.junit.jupiter.api.Test;

import com.example.domain.shared.ValidationDomainException;
import com.example.domain.value_object.user.name.FirstName;

public class FirstNameTest {
    @Test
    public void testAddFirstName() {
        try {
            FirstName firstName = new FirstName(null);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }

        try {
            FirstName firstName2 = new FirstName("1");
            
        } catch (ValidationDomainException e) {
            // TODO: handle exception
            System.err.println(e.getMessage());
        }
    }
}
