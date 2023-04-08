package com.nacu.project.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@ActiveProfiles("main")
public class TestPasswordService
{
    @Autowired
    PasswordService service;

    /*
    Equivalence partitioning

    Input p (password):
    P1 = {p | p.length < 8}
    P2 = {p | p.length > 20}
    P3 = {p | p.length >=8 && p.length <= 20 && p doesn't contain lowercase}
    P4 = {p | p.length >=8 && p.length <= 20 && p doesn't contain uppercase}
    P5 = {p | p.length >=8 && p.length <= 20 && p doesn't contain digit}
    P6 = {p | p.length >=8 && p.length <= 20 && p doesn't contain special char}
    P7 = {p | p.length >=8 && p.length <= 20 && p contains lowercase, uppercase, digit, special char}

    Output:
    O1 = {"The password is valid!"}
    O2 = {"The password must be at least 8 characters and at most 20"}
    O3 = {"The password must have at least one lower case character"}
    O4 = {"The password must have at least one upper case character"}
    O5 = {"The password must have at least one digit"}
    O6 = {"The password must have at least one special char"}

    Classes:
    C12 = {p | p in P1 and output O2} -----> p = "pw"
    C22 = {p | p in P2 and output O2} -----> p = "pwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwww"
    C33 = {p | p in P3 and output O3} -----> p = "PASSWORD123"
    C44 = {p | p in P4 and output O4} -----> p = "password123"
    C55 = {p | p in P5 and output O5} -----> p = "Password++"
    C66 = {p | p in P6 and output O6} -----> p = "Password123"
    C71 = {p | p in P7 and output O1} -----> p = "Great7+Password3-"
     */

    @Test
    public void testEquivalence()
    {
        // 100% coverage
        assertEquals("The password must be at least 8 characters and at most 20", service.validatePassword("pw"));
        assertEquals("The password must be at least 8 characters and at most 20", service.validatePassword("pwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwww"));
        assertEquals("The password must have at least one lower case character", service.validatePassword("PASSWORD123"));
        assertEquals("The password must have at least one upper case character", service.validatePassword("password123"));
        assertEquals("The password must have at least one digit", service.validatePassword("Password++"));
        assertEquals("The password must have at least one special char", service.validatePassword("Password123"));
        assertEquals("The password is valid!", service.validatePassword("Great7+Password3-"));
    }

    /*
    Boundary value analysis

    Boundaries:
    for P1 -> "abcdefg" (length = 7)
    for P2 -> "pwwwwwwwwwwwwwwwwwwww" (length = 21)
    for P3 -> "PASSWORD" (length = 8 && no lowercase), "PWWWWWWWWWWWWWWWWWWW" (length = 20 && no lowercase)
    for P4 -> "password" (length = 8 && no uppercase), "pwwwwwwwwwwwwwwwwwww" (length = 20 && no uppercase)
    for P5 -> "Password" (length = 8 && no digit), "Pwwwwwwwwwwwwwwwwwww" (length = 20 && no digit)
    for P6 -> "Pass1234" (length = 8 && no special), "Pwwwwwwwwwwwwwww1234" (length = 20 && no special)
    for P7 -> "Pass12++" (length = 8), "Pwwwwwwwwwwwwwww12++" (length = 20)
     */

    @Test
    public void testBoundaries()
    {
        // 100% coverage
        assertEquals("The password must be at least 8 characters and at most 20", service.validatePassword("abcdefg"));

        assertEquals("The password must be at least 8 characters and at most 20", service.validatePassword("pwwwwwwwwwwwwwwwwwwww"));

        assertEquals("The password must have at least one lower case character", service.validatePassword("PASSWORD"));
        assertEquals("The password must have at least one lower case character", service.validatePassword("PWWWWWWWWWWWWWWWWWWW"));

        assertEquals("The password must have at least one upper case character", service.validatePassword("password"));
        assertEquals("The password must have at least one upper case character", service.validatePassword("pwwwwwwwwwwwwwwwwwww"));

        assertEquals("The password must have at least one digit", service.validatePassword("Password"));
        assertEquals("The password must have at least one digit", service.validatePassword("Pwwwwwwwwwwwwwwwwwww"));

        assertEquals("The password must have at least one special char", service.validatePassword("Pass1234"));
        assertEquals("The password must have at least one special char", service.validatePassword("Pwwwwwwwwwwwwwww1234"));

        assertEquals("The password is valid!", service.validatePassword("Pass12++"));
        assertEquals("The password is valid!", service.validatePassword("Pwwwwwwwwwwwwwww12++"));
    }

    /*
    Case-effect graphing
     */

    @Test
    public void testGce()
    {
        // 100% coverage
        assertEquals("The password must be at least 8 characters and at most 20", service.validatePassword("pass"));
        assertEquals("The password must have at least one lower case character", service.validatePassword("PASSPASSPASS"));
        assertEquals("The password must have at least one upper case character", service.validatePassword("passpasspass"));
        assertEquals("The password must have at least one digit", service.validatePassword("PassPass++"));
        assertEquals("The password must have at least one special char", service.validatePassword("PassPass123"));
        assertEquals("The password is valid!", service.validatePassword("Pass17@Word3^"));
    }
}
