/*
 * TCSS 450 - Mobile Application Development
 * Programming Project Sprint 2
 */

package edu.tacoma.uw.csscompass.authentication;

import java.util.regex.Pattern;

/**
 * An account class used to store the user information
 *
 * @author JJ Coldiron
 * @author Danie Oum
 * @author Derek Ruiz-Garcia
 * @version 1.0
 */
public class Account {

    /** The email of this account. */
    private String mEmail;

    /** The password of this account. */
    private String mPassword;

    /** The first name of the account user. */
    private String mFirstName;

    /** The last name of the account user. */
    private String mLastName;

    /** The student number of the account user. */
    private String mStudentNumber;

    /** The enrollment year of the account user. */
    private String mEnrollmentYear;

    /** The graduation year of the account user. */
    private String mGraduationYear;

    /** The minimum length required for a password to be considered valid. */
    private final static int PASSWORD_LEN = 6;

    /**
     * Creates an account using the passed email, password, first name, last name, student number,
     * enrollment year and graduation year. Throws errors if: the email is not valid, the
     * password is not valid, the student number is not a valid number, the enrollment year is
     * not a valid number or if the graduation is not a valid number.
     *
     * @param email the email to be saved in this account as a String.
     * @param password the password to be saved in this account as a String.
     * @param firstName the first name to saved in this account as a String.
     * @param lastName the last name to saved in this account as a String.
     * @param studentNumber the student number to save in this account as a String.
     * @param enrollmentYear the enrollment year to save in this account as a String.
     * @param graduationYear the graduation year to save in this account as a String.
     */
    public Account(String email, String password, String firstName, String lastName,
                   String studentNumber, String enrollmentYear, String graduationYear) {

        if(!isValidEmail(email)) throw new IllegalArgumentException("Invalid email");

        if(!isValidPassword(password)) throw new IllegalArgumentException("Invalid password");

        try {
            int studNum = Integer.parseInt(studentNumber);
        } catch (NumberFormatException nf) {
            throw new NumberFormatException("Invalid student number");
        }

        try {
            int enrollYear = Integer.parseInt(enrollmentYear);
        } catch (NumberFormatException nf) {
            throw new NumberFormatException("Invalid enrollment year");
        }

        try {
            int gradYear = Integer.parseInt(graduationYear);
        } catch (NumberFormatException nf) {
            throw new NumberFormatException("Invalid graduation year");
        }

        this.mEmail = email;
        this.mPassword = password;
        this.mFirstName = firstName;
        this.mLastName = lastName;
        this.mStudentNumber = studentNumber;
        this.mEnrollmentYear = enrollmentYear;
        this.mGraduationYear = graduationYear;
    }

    /**
     * Returns the email in this account.
     *
     * @return the email stored in this account as a String.
     */
    public String getEmail() {
        return mEmail;
    }

    /**
     * Assigns the passed email to this account.
     *
     * @param email the email to be assigned to this account as a String.
     */
    public void setEmail(String email) {
        if(!isValidEmail(email)) throw new IllegalArgumentException("Invalid email");
        this.mEmail = email;
    }

    /**
     * Returns the password stored in this account.
     *
     * @return the paswword stored in this account as a String.
     */
    public String getPassword() {
        return mPassword;
    }

    /**
     * Assigns the password in this account to the passed password as long as it is valid.
     *
     * @param password the password to be assigned to this account as a String.
     */
    public void setPassword(String password) {
        if (!isValidPassword(password)) throw new IllegalArgumentException("Invalid password");
        this.mPassword = password;
    }

    /**
     * Returns the first name stored in this account.
     *
     * @return the first name stored in this account as a string.
     */
    public String getFirstName() {
        return mFirstName;
    }

    /**
     * Sets the first name in the account to the passed first name.
     *
     * @param firstName the first name to store in this account.
     */
    public void setFirstName(String firstName) {
        this.mFirstName = firstName;
    }

    /**
     * Returns the last name stored in this account.
     *
     * @return the last name stored in this account as a String.
     */
    public String getLastName() {
        return mLastName;
    }

    /**
     * Sets the last name in this account to the passed last name.
     *
     * @param lastName the last name to store in this account as a String.
     */
    public void setLastName(String lastName) {
        this.mLastName = lastName;
    }

    /**
     * Returns the student number stored in this account.
     *
     * @return the student number stored in this account as a String.
     */
    public String getStudentNumber() {
        return mStudentNumber;
    }

    /**
     * Sets the student number in this account to the passed student number
     *
     * @param studentNumber the student number to store in this account as a String.
     */
    public void setStudentNumber(String studentNumber) {
        try {
            int value = Integer.parseInt(studentNumber);
            this.mStudentNumber = studentNumber;
        } catch (NumberFormatException nf) {
            throw new NumberFormatException("Invalid student number");
        }

    }

    /**
     * Returns the enrollment year stored in this account.
     *
     * @return the enrollment year stored in this account as an String.
     */
    public String getEnrollmentYear() {
        return mEnrollmentYear;
    }

    /**
     * Sets the enrollment year in this account to the passed enrollment year.
     *
     * @param enrollmentYear the enrollment year to store in this account as a String.
     */
    public void setEnrollmentYear(String enrollmentYear) {
        try {
            int value = Integer.parseInt(enrollmentYear);
            this.mEnrollmentYear = enrollmentYear;
        } catch (NumberFormatException nf) {
            throw new NumberFormatException("Invalid enrollment year");
        }
    }

    /**
     * Returns the graduation year stored in this account.
     *
     * @return the graduation year stored in this account as a String.
     */
    public String getGraduationYear() {
        return mGraduationYear;
    }

    /**
     * Sets the graduation year in this account to the passed enrollment year.
     *
     * @param graduationYear the graduation year to store in this account as a String.
     */
    public void setGraduationYear(String graduationYear) {
        try {
            int value = Integer.parseInt(graduationYear);
            this.mGraduationYear = graduationYear;
        } catch (NumberFormatException nf) {
            throw new NumberFormatException("Invalid graduation year");
        }
    }

    /** Email validation pattern. */
    public static final Pattern EMAIL_PATTERN = Pattern.compile(
            "[a-zA-Z0-9\\+\\.\\_\\%\\-\\+]{1,256}" +
                    "\\@" +
                    "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}" +
                    "(" +
                    "\\." +
                    "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25}" +
                    ")+"
    );

    /**
     * Validates if the given input is a valid email address.
     *
     * @param email The email to validate.
     * @return {@code true} if the input is a valid email. {@code false} otherwise.
     */
    public static boolean isValidEmail(String email) {
        return email != null && EMAIL_PATTERN.matcher(email).matches();
    }

    /**
     * Validates if the given password is valid.
     * Valid password must be at last 6 characters long
     * with at least one digit and one symbol.
     *
     * @param password The password to validate.
     * @return {@code true} if the input is a valid password. {@code false} otherwise.
     */
    public static boolean isValidPassword(String password) {
        boolean foundDigit = false, foundSymbol = false;
        if (password == null ||
                password.length() < PASSWORD_LEN)
            return false;
        for (int i = 0; i < password.length(); i++) {
            if (Character.isDigit(password.charAt(i)))
                foundDigit = true;
            if (!Character.isLetterOrDigit(password.charAt(i)))
                foundSymbol = true;
        }
        return foundDigit && foundSymbol;
    }
}
