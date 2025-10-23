package com.tests;
 
import com.pages.LoginPage;
import com.pages.SearchUserPage;
import com.microsoft.playwright.Locator;
import com.pages.AdminPage;
import com.pages.DeleteUserPage;
import com.pages.EditUserPage;
import com.utilites.ConfigReader;
import com.utilites.TestBase;
import org.testng.annotations.Test;
 
public class LoginTest extends TestBase {
 
    @Test
    public void testCreateAdminUser() {
        String username = ConfigReader.readPropertyFileData("userName", "config");
        String password = ConfigReader.readPropertyFileData("password", "config");
        String empName = ConfigReader.readPropertyFileData("empName", "config");
        String newUserName = ConfigReader.readPropertyFileData("newUserName", "config");
        String newUserPassword = ConfigReader.readPropertyFileData("newUserPassword", "config");
        String newUserConfirmPassword = ConfigReader.readPropertyFileData("newUserConfirmPassword", "config");
        LoginPage loginPage = new LoginPage(page);
        loginPage.login(username, password);
        
        page.waitForSelector("text=Dashboard");
        AdminPage adminPage = new AdminPage(page);
        adminPage.openAdminSection();
        adminPage.clickAddButton();
        adminPage.fillUserDetails(empName, newUserName, newUserPassword, newUserConfirmPassword);
        adminPage.saveUser();
        page.locator("text=Successfully Saved").waitFor(new Locator.WaitForOptions().setTimeout(20000)); 

        System.out.println("New admin user created successfully.");
 
        SearchUserPage searchUserPage = new SearchUserPage(page);
        String searchRole = ConfigReader.readPropertyFileData("searchRole", "config");
        String searchStatus = ConfigReader.readPropertyFileData("searchStatus", "config");
        searchUserPage.searchUser(newUserName, searchRole, searchStatus);
        page.locator("text=Successfully Saved").waitFor(new Locator.WaitForOptions().setTimeout(2000)); 
        EditUserPage editUserPage = new EditUserPage(page);
        String newStatus = ConfigReader.readPropertyFileData("newStatus", "config");
        editUserPage.editUserStatus(newUserName, newStatus);
        
        DeleteUserPage deleteUserPage = new DeleteUserPage(page);
        deleteUserPage.deleteUser();
    }
}