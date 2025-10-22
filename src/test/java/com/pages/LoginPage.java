package com.pages;

import com.microsoft.playwright.Page;

public class LoginPage {
    private Page page;

    private String usernameField = "input[name='username']";
    private String passwordField = "input[name='password']";
    private String loginButton = "button[type='submit']";

    public LoginPage(Page page) {
        this.page = page;
    }

    public void setUsername(String username) {
        page.locator(usernameField).fill(username);
    }

    public void setPassword(String password) {
        page.locator(passwordField).fill(password);
    }

    public void clickLogin() {
        page.locator(loginButton).click();
    }

    public void login(String username, String password) {
        setUsername(username);
        setPassword(password);
        clickLogin();
    }
}