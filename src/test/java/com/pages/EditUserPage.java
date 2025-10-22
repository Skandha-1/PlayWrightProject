package com.pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

public class EditUserPage {
    private Page page;

    private Locator usernameInput;
    private Locator searchButton;
    private Locator editIcon;
    private Locator statusDropdown;
    private Locator saveButton;

    public EditUserPage(Page page) {
        this.page = page;

        usernameInput = page.locator("//label[text()='Username']/following::input[1]");
        searchButton = page.locator("//button[normalize-space()='Search']");
        editIcon = page.locator("//i[@class='oxd-icon bi-pencil-fill']").first(); // Avoid strict mode error
        statusDropdown = page.locator("//label[text()='Status']/following::div[1]//div[contains(@class,'oxd-select-text')]").first();
        saveButton = page.locator("//button[normalize-space()='Save']");
    }

    public void editUserStatus(String username, String newStatus) {
            usernameInput.fill("");
            usernameInput.fill(username);
            searchButton.click();
            page.waitForSelector("//i[@class='oxd-icon bi-pencil-fill']");
            editIcon.click();
            statusDropdown.click();
            page.locator("//div[@role='listbox']//span[text()='" + newStatus + "']").click();
            saveButton.click();
            page.waitForSelector("text=Success");
            System.out.println("User status updated successfully.");
        }
    }
