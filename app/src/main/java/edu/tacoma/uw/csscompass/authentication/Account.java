/*
 * TCSS 450 - Mobile Application Development
 * Programming Project Sprint 2
 */

package edu.tacoma.uw.csscompass.authentication;

import java.util.regex.Pattern;

/**
 * Description //FIXME
 *
 * @author JJ Coldiron
 * @author Danie Oum
 * @author Derek Ruiz-Garcia
 * @version 1.0
 */
public class Account {

    /** Description //FIXME */
    private String mEmail;

    /** Description //FIXME */
    private String mPassword;

    /** Description //FIXME */
    private final static int PASSWORD_LEN = 6;

    /**
     * Description //FIXME
     *
     * @param mEmail
     * @param mPassword
     */
    public Account(String mEmail, String mPassword) {
        if(!isValidEmail(mEmail)){
            throw new IllegalArgumentException("Invalid email");
        }
        if(!isValidPassword(mPassword)){
            throw new IllegalArgumentException("Invalid password");
        }
        this.mEmail = mEmail;
        this.mPassword = mPassword;
    }

    /**
     * Description //FIXME
     *
     * @return
     */
    public String getEmail() {
        return mEmail;
    }

    /**
     * Description //FIXME
     *
     * @param mEmail
     */
    public void setEmail(String mEmail) {
        if(!isValidEmail(mEmail)){
            throw new IllegalArgumentException("Invalid email");
        }
        this.mEmail = mEmail;
    }

    /**
     * Description //FIXME
     *
     * @return
     */
    public String getPassword() {
        return mPassword;
    }

    /**
     * Description //FIXME
     *
     * @param mPassword
     */
    public void setPassword(String mPassword) {
        if(!isValidPassword(mPassword)){
            throw new IllegalArgumentException("Invalid password");
        }
        this.mPassword = mPassword;
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
