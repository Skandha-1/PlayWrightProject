package com.pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

public class SearchUserPage {
    private Page page;

    private Locator usernameInput;
    private Locator userRoleDropdown;
    private Locator statusDropdown;
    private Locator searchButton;

    public SearchUserPage(Page page) {
        this.page = page;

        usernameInput = page.locator("//label[text()='Username']/following::input[1]");
        userRoleDropdown = page.locator("//label[text()='User Role']/following::div[1]//div[contains(@class,'oxd-select-text')]").first();
        statusDropdown = page.locator("//label[text()='Status']/following::div[1]//div[contains(@class,'oxd-select-text')]").first();
        searchButton = page.locator("//button[normalize-space()='Search']");
    }

    public void searchUser(String username, String role, String status) {
        usernameInput.fill("");
        usernameInput.fill(username);

        userRoleDropdown.click();
        page.locator("//div[@role='listbox']//span[text()='" + role + "']").click();

        statusDropdown.click();
        page.locator("//div[@role='listbox']//span[text()='" + status + "']").click();

        searchButton.click();
    }
}