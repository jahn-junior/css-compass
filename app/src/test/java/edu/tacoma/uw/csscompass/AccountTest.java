package edu.tacoma.uw.csscompass;

import org.junit.Test;

import static org.junit.Assert.*;

import edu.tacoma.uw.csscompass.authentication.Account;

/**
 * JUnit test case for the Account class.
 */
public class AccountTest {

    /**
     * Tests constructor with valid parameters.
     */
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

    /**
     * Tests constructor with an invalid email provided.
     */
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

    /**
     * Tests constructor with invalid password provided.
     */
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

    /**
     * Tests constructor with invalid enrollment year provided.
     */
    @Test
    public void testConstructorInvalidEnrollYear() {
        assertThrows(IllegalArgumentException.class, () ->
                new Account("testuser@test.com",
                        "test12!",
                        "John",
                        "Von Neumann",
                        "1234567",
                        "twentysixteen",
                        "2020"));
    }

    /**
     * Tests constructor with invalid graduation year provided.
     */
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

    /**
     * Tests constructor with invalid student number provided.
     */
    @Test
    public void testConstructorInvalidStudentNo() {
        assertThrows(IllegalArgumentException.class, () ->
                new Account("testuser@test.com",
                        "test12!",
                        "John",
                        "Von Neumann",
                        "student_number",
                        "2016",
                        "2020"));
    }

    /**
     * Tests getEmail.
     */
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

    /**
     * Tests setEmail when an invalid email is provided.
     */
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

    /**
     * Tests setEmail when a valid email is provided.
     */
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

    /**
     * Tests getPassword.
     */
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

    /**
     * Tests setPassword when an invalid password is provided.
     */
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

    /**
     * Tests setPassword when a valid password is provided.
     */
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

    /**
     * Tests isValidEmail when a null reference is passed.
     */
    @Test
    public void testIsValidEmailNull() {
        assertFalse(Account.isValidEmail(null));
    }

    /**
     * Tests isValidEmail on an improperly formatted email.
     */
    @Test
    public void testIsValidEmailPatternMismatch() {
        assertFalse(Account.isValidEmail("patternmismatch"));
    }

    /**
     * Tests isValidEmail on a valid email.
     */
    @Test
    public void testIsValidEmail() {
        assertTrue(Account.isValidEmail("validemail@test.com"));
    }

    /**
     * Tests isValidEmail when a null reference is passed.
     */
    @Test
    public void testIsValidPasswordNull() {
        assertFalse(Account.isValidPassword(null));
    }

    /**
     * Tests isValidPassword with an invalid length.
     */
    @Test
    public void testIsValidPasswordTooShort() {
        assertFalse(Account.isValidPassword("12!"));
    }

    /**
     * Tests isValidPassword with a password containing no digits.
     */
    @Test
    public void testIsValidPasswordNoDigit() {
        assertFalse(Account.isValidPassword("almostvalid!"));
    }

    /**
     * Tests isValidPassword with a password containing no symbols.
     */
    @Test
    public void testIsValidPasswordNoSymbol() {
        assertFalse(Account.isValidPassword("almostvalid12"));
    }

    /**
     * Tests setStudentNumber when an invalid student number is provided.
     */
    @Test
    public void testSetStudentNumberIAException() {
        Account validAccount = new Account("testuser@test.com",
                "test12!",
                "John",
                "Von Neumann",
                "1234567",
                "2016",
                "2020");
        assertThrows(IllegalArgumentException.class, () -> validAccount.setStudentNumber("12345678"));
    }

    /**
     * Tests setEnrollYear when an invalid enrollment year is provided.
     */
    @Test
    public void testSetEnrollYearException() {
        Account validAccount = new Account("testuser@test.com",
                "test12!",
                "John",
                "Von Neumann",
                "1234567",
                "2016",
                "2020");
        assertThrows(IllegalArgumentException.class, () -> validAccount.setEnrollmentYear("year"));
    }

    /**
     * Tests setGradYear when an invalid graduation year is provided.
     */
    @Test
    public void testSetGradYearException() {
        Account validAccount = new Account("testuser@test.com",
                "test12!",
                "John",
                "Von Neumann",
                "1234567",
                "2016",
                "2020");
        assertThrows(IllegalArgumentException.class, () -> validAccount.setGraduationYear("year"));
    }

    /**
     * Tests isValidGradYear when a valid graduation year is provided.
     */
    @Test
    public void testIsValidGradYearTrue() {
        assertTrue(Account.isValidGradYear("2024", "2020"));
    }

    /**
     * Tests isValidGradYear with an improperly formatted year.
     */
    @Test
    public void testIsValidGradYearPatternMismatch() {
        assertFalse(Account.isValidGradYear("02024", "2020"));
    }

    /**
     * Tests isValidGradYear when a null reference is passed.
     */
    @Test
    public void testIsValidGradYearNull() {
        assertFalse(Account.isValidGradYear(null, "2020"));
    }

    /**
     * Tests isValidGradYear when the year is too far in the future.
     */
    @Test
    public void testIsValidGradYearFuture() {
        assertFalse(Account.isValidGradYear("3000", "2020"));
    }

    /**
     * Tests isValidGradYear when the grad year is before the enrollment year.
     */
    @Test
    public void testIsValidGradYearTimeTravel() {
        assertFalse(Account.isValidGradYear("2020", "2024"));
    }

    /**
     * Tests isValidEnrollYear when a valid year is passed.
     */
    @Test
    public void testIsValidEnrollYearTrue() {
        assertTrue(Account.isValidEnrollYear("2020", "2024"));
    }

    /**
     * tests isValidEnrollYear when a null reference is passed.
     */
    @Test
    public void testIsValidEnrollYearNull() {
        assertFalse(Account.isValidEnrollYear(null, "2024"));
    }

    /**
     * Tests isValidEnrollYear when an improperly formatted year is passed.
     */
    @Test
    public void testIsValidEnrollYearPatternMismatch() {
        assertFalse(Account.isValidEnrollYear("02020", "2024"));
    }

    /**
     * Tests isValidEnrollYear when the enrollment year is too far in the past.
     */
    @Test
    public void testIsValidEnrollYearTooOld() {
        assertFalse(Account.isValidEnrollYear("1989", "1993"));
    }

    /**
     * Tests isValidEnrollYear when the enrollment year is after the graduation year.
     */
    @Test
    public void testIsValidEnrollYearTimeTravel() {
        assertFalse(Account.isValidEnrollYear("2024", "2020"));
    }

    /**
     * Tests isValidStudentNumber with a proper student number.
     */
    @Test
    public void testIsValidStudentNumberTrue() {
        assertTrue(Account.isValidStudentNo("1234567"));
    }

    /**
     * Tests isValidStudentNumber with an improper number of digits.
     */
    @Test
    public void testIsValidStudentNumberFalse() {
        assertFalse(Account.isValidStudentNo("12345678"));
    }

}