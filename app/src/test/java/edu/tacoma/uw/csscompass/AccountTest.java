package edu.tacoma.uw.csscompass;

import org.junit.Test;

import static org.junit.Assert.*;

import edu.tacoma.uw.csscompass.authentication.Account;

public class AccountTest {

    @Test
    public void testConstructorValid() {
        Account account = new Account("testuser@test.com", "test12!", "", "", "", "", "");
        assertNotNull(account);
    }

    @Test
    public void testConstructorInvalidEmail() {
        assertThrows(IllegalArgumentException.class, () ->
                new Account("testuser@test.com", "test12!", "", "", "", "", ""));
    }

    @Test
    public void testConstructorInvalidPassword() {
        assertThrows(IllegalArgumentException.class, () ->
                new Account("testuser@test.com", "test12!", "", "", "", "", ""));
    }

    @Test
    public void testGetEmail() {
        Account account = new Account("testuser@test.com", "test12!", "", "", "", "", "");
        String expected = "testuser@test.com";
        String actual = account.getEmail();
        assertEquals(expected, actual);
    }

    @Test
    public void testSetEmailInvalid() {
        Account validAccount = new Account("testuser@test.com", "test12!", "", "", "", "", "");
        assertThrows(IllegalArgumentException.class, () -> validAccount.setEmail("badEmail"));
    }

    @Test
    public void testSetEmailValid() {
        Account validAccount = new Account("testuser@test.com", "test12!", "", "", "", "", "");
        validAccount.setEmail("validemail@test.com");
        String expected = "validemail@test.com";
        String actual = validAccount.getEmail();
        assertEquals(expected, actual);
    }

    @Test
    public void getPassword() {
        Account validAccount =new Account("testuser@test.com", "test12!", "", "", "", "", "");
        String expected = "test12!";
        String actual = validAccount.getPassword();
        assertEquals(expected, actual);
    }

    @Test
    public void testSetPasswordInvalid() {
        Account validAccount = new Account("testuser@test.com", "test12!", "", "", "", "", "");
        assertThrows(IllegalArgumentException.class, () -> validAccount.setPassword("pw"));
    }

    @Test
    public void testSetPasswordValid() {
        Account validAccount = new Account("testuser@test.com", "test12!", "", "", "", "", "");
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
}