package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ManageUserPage {

    private WebDriver driver;
    private WebDriverWait wait;

    public ManageUserPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    // Click the "Add New" button to open the user form
    public void clickAddNewButton() {
        WebElement addNewButton = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//button[contains(@class, 'ant-btn-primary') and span[text()=' Add New']]")
        ));
        addNewButton.click();
    }

    // Verify if the user form is displayed
    public boolean isUserFormDisplayed() {
        WebElement userForm = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//form[@class='ant-form ant-form-horizontal ant-form-middle']")
        ));
        return userForm.isDisplayed();
    }

    // Fill the user form with the provided details
    public void fillUserForm(String username, String email, String employee, String userLevel, String language, String defaultModule) {
        clearAndEnterText(By.id("username"), username);
        clearAndEnterText(By.id("email"), email);

        // Handle dropdown selections
        selectDropdownOption("rc_select_0", employee); // Employee
        selectDropdownOption("rc_select_1", userLevel); // User Level
        selectDropdownOption("rc_select_3", language); // Language
        selectDropdownOption("rc_select_4", defaultModule); // Default Module
    }

    // Helper method to clear text by using the delete key and then enter text
    private void clearAndEnterText(By locator, String text) {
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(locator));
        element.click();

        // Simulate delete key presses to clear old text
        String existingText = element.getAttribute("value");
        for (int i = 0; i < existingText.length(); i++) {
            element.sendKeys(Keys.BACK_SPACE);
        }

        // Enter new text
        element.sendKeys(text);
    }

    // Helper method to select an option from a dropdown
    private void selectDropdownOption(String dropdownId, String optionText) {
        WebElement dropdown = driver.findElement(By.id(dropdownId));
        dropdown.click();

        // Wait for the dropdown list to be visible and select the option
        WebElement option = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//div[contains(@class, 'ant-select-item') and text()='" + optionText + "']")
        ));
        option.click();
    }

    // Click the "Save" button to submit the form
    public void clickSaveButton() {
        WebElement saveButton = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//div[contains(@class, 'ant-modal-footer')]//button[contains(@class, 'ant-btn-primary') and span[text()='Save']]")
        ));
        saveButton.click();
    }

    // Click the "OK" button on the modal dialog
    public void clickOkButtonOnModal() {
        WebElement okButton = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//div[@class='modal-footer']//button[contains(text(), 'Ok')]")
        ));
        okButton.click();
    }

    // Verify if the newly added user is displayed in the user list
    public boolean isUserDisplayed(String username) {
        WebElement newUser = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//td[contains(text(), '" + username + "')]")
        ));
        return newUser.isDisplayed();
    }

    // Edit an existing user with new details
    public void editUser(String existingUsername, String newUsername, String newEmail) {
        // Locate the edit button for the specific user
        WebElement editButton = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//td[contains(text(), '" + existingUsername + "')]/following-sibling::td//span[contains(text(), 'Edit')]")
        ));
        editButton.click();

        // Wait for the user form to appear
        WebElement userForm = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//form[@class='ant-form ant-form-horizontal ant-form-middle']")
        ));

        // Clear and update user details
        clearAndEnterText(By.id("username"), newUsername);
        clearAndEnterText(By.id("email"), newEmail);

        // Clear and update dropdown selections
//        selectDropdownOption("rc_select_1", newUserLevel); // User Level
//        selectDropdownOption("rc_select_3", newLanguage); // Language
//        selectDropdownOption("rc_select_4", newDefaultModule); // Default Module

        // Click Save button
        clickSaveButton();
    }
}
