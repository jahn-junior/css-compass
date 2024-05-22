package edu.tacoma.uw.csscompass;

import org.junit.Test;

import static org.junit.Assert.*;

import edu.tacoma.uw.csscompass.authentication.Account;

public class AccountTest {

    @Test
    public void testConstructorValid() {
        Account account = new Account("testuser@test.com",
                "test12!",
                "John",
                "Von Neumann",
                "1234567",
                "2016",
                "2020");
        assertNotNull(account);
    }

    @Test
    public void testConstructorInvalidEmail() {
        assertThrows(IllegalArgumentException.class, () ->
                new Account("bad",
                        "test12!",
                        "John",
                        "Von Neumann",
                        "1234567",
                        "2016",
                        "2020"));
    }

    @Test
    public void testConstructorInvalidPassword() {
        assertThrows(IllegalArgumentException.class, () ->
                new Account("testuser@test.com",
                        "bad",
                        "John",
                        "Von Neumann",
                        "1234567",
                        "2016",
                        "2020"));
    }

    @Test
    public void testConstructorInvalidEnrollYear() {
        assertThrows(NumberFormatException.class, () ->
                new Account("testuser@test.com",
                        "test12!",
                        "John",
                        "Von Neumann",
                        "1234567",
                        "twentysixteen",
                        "2020"));
    }

    @Test
    public void testConstructorInvalidGradYear() {
        assertThrows(NumberFormatException.class, () ->
                new Account("testuser@test.com",
                        "test12!",
                        "John",
                        "Von Neumann",
                        "1234567",
                        "2016",
                        "twentytwenty"));
    }

    @Test
    public void testConstructorInvalidStudentNo() {
        assertThrows(NumberFormatException.class, () ->
                new Account("testuser@test.com",
                        "test12!",
                        "John",
                        "Von Neumann",
                        "student_number",
                        "2016",
                        "2020"));
    }

    @Test
    public void testGetEmail() {
        Account account = new Account("testuser@test.com",
                "test12!",
                "John",
                "Von Neumann",
                "1234567",
                "2016",
                "2020");
        String expected = "testuser@test.com";
        String actual = account.getEmail();
        assertEquals(expected, actual);
    }

    @Test
    public void testSetEmailInvalid() {
        Account account = new Account("testuser@test.com",
                "test12!",
                "John",
                "Von Neumann",
                "1234567",
                "2016",
                "2020");
        assertThrows(IllegalArgumentException.class, () -> account.setEmail("badEmail"));
    }

    @Test
    public void testSetEmailValid() {
        Account validAccount = new Account("testuser@test.com",
                "test12!",
                "John",
                "Von Neumann",
                "1234567",
                "2016",
                "2020");
        validAccount.setEmail("validemail@test.com");
        String expected = "validemail@test.com";
        String actual = validAccount.getEmail();
        assertEquals(expected, actual);
    }

    @Test
    public void getPassword() {
        Account validAccount = new Account("testuser@test.com",
                "test12!",
                "John",
                "Von Neumann",
                "1234567",
                "2016",
                "2020");
        String expected = "test12!";
        String actual = validAccount.getPassword();
        assertEquals(expected, actual);
    }

    @Test
    public void testSetPasswordInvalid() {
        Account validAccount = new Account("testuser@test.com",
                "test12!",
                "John",
                "Von Neumann",
                "1234567",
                "2016",
                "2020");
        assertThrows(IllegalArgumentException.class, () -> validAccount.setPassword("pw"));
    }

    @Test
    public void testSetPasswordValid() {
        Account validAccount = new Account("testuser@test.com",
                "test12!",
                "John",
                "Von Neumann",
                "1234567",
                "2016",
                "2020");
        validAccount.setPassword("greatpassword-649");
        String actual = "greatpassword-649";
        String expected = validAccount.getPassword();
        assertEquals(expected, actual);
    }

    @Test
    public void testIsValidEmailNull() {
        assertFalse(Account.isValidEmail(null));
    }

    @Test
    public void testIsValidEmailPatternMismatch() {
        assertFalse(Account.isValidEmail("patternmismatch"));
    }

    @Test
    public void testIsValidEmail() {
        assertTrue(Account.isValidEmail("validemail@test.com"));
    }

    @Test
    public void testIsValidPasswordNull() {
        assertFalse(Account.isValidPassword(null));
    }

    @Test
    public void testIsValidPasswordTooShort() {
        assertFalse(Account.isValidPassword("12!"));
    }

    @Test
    public void testIsValidPasswordNoDigit() {
        assertFalse(Account.isValidPassword("almostvalid!"));
    }

    @Test
    public void testIsValidPasswordNoSymbol() {
        assertFalse(Account.isValidPassword("almostvalid12"));
    }

    @Test
    public void testSetStudentNumberInvalid() {
        Account validAccount = new Account("testuser@test.com",
                "test12!",
                "John",
                "Von Neumann",
                "1234567",
                "2016",
                "2020");
        assertThrows(NumberFormatException.class, () -> validAccount.setStudentNumber("num"));
    }

    @Test
    public void testSetEnrollYearInvalid() {
        Account validAccount = new Account("testuser@test.com",
                "test12!",
                "John",
                "Von Neumann",
                "1234567",
                "2016",
                "2020");
        assertThrows(NumberFormatException.class, () -> validAccount.setEnrollmentYear("year"));
    }

    @Test
    public void testSetGradYearInvalid() {
        Account validAccount = new Account("testuser@test.com",
                "test12!",
                "John",
                "Von Neumann",
                "1234567",
                "2016",
                "2020");
        assertThrows(NumberFormatException.class, () -> validAccount.setGraduationYear("year"));
    }

}