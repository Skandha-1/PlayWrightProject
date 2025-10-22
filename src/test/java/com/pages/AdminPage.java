package com.pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

public class AdminPage {
    private Page page;
    private Locator adminTab;
    private Locator addButton;
    private Locator userRoleDropdown;
    private Locator employeeNameInput;
    private Locator usernameInput;
    private Locator statusDropdown;
    private Locator passwordInput;
    private Locator confirmPasswordInput;
    private Locator saveButton;

    public AdminPage(Page page) {
        this.page = page;

        adminTab = page.locator("//span[text()='Admin']");
        addButton = page.locator("//button[normalize-space()='Add']");
        userRoleDropdown = page.locator("//label[text()='User Role']/following::div[1]//div[contains(@class,'oxd-select-text')]").first();
        employeeNameInput = page.locator("//input[@placeholder='Type for hints...']");
        usernameInput = page.locator("//label[text()='Username']/following::input[1]");
        statusDropdown = page.locator("//label[text()='Status']/following::div[1]//div[contains(@class,'oxd-select-text')]").first();
        passwordInput = page.locator("//label[text()='Password']/following::input[1]");
        confirmPasswordInput = page.locator("//label[text()='Confirm Password']/following::input[1]");
        saveButton = page.locator("//button[normalize-space()='Save']");
    }

    public void openAdminSection() {
        adminTab.click();
    }

    public void clickAddButton() {
        addButton.click();
    }

    public void fillUserDetails(String empName, String username, String password, String confirmPassword) {
        userRoleDropdown.click();
        page.locator("//div[@role='listbox']//span[text()='Admin']").click();

        employeeNameInput.fill(empName);
        page.locator("//div[@role='listbox']//span[contains(text(),'" + empName + "')]").click();

        usernameInput.fill(username);

        statusDropdown.click();
        page.locator("//div[@role='listbox']//span[text()='Enabled']").click();

        passwordInput.fill(password);
        confirmPasswordInput.fill(confirmPassword);
    }

    public void saveUser() {
        saveButton.click();
    }
}