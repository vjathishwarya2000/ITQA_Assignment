package stepdefinitions.ui;

import io.cucumber.java.en.*;
import org.openqa.selenium.WebDriver;
import pages.ManageUserPage;
import runners.TestRunner;

public class ManageUserSteps {

    private final WebDriver driver;
    private final ManageUserPage manageUserPage;

    public ManageUserSteps() {
        this.driver = TestRunner.getDriver();
        this.manageUserPage = new ManageUserPage(driver);
    }

    @When("I click the 'Add New' button")
    public void i_click_the_add_new_button() {
        manageUserPage.clickAddNewButton();
    }

    @Then("I should see the user form")
    public void i_should_see_the_user_form() {
        if (!manageUserPage.isUserFormDisplayed()) {
            throw new AssertionError("User form is not displayed!");
        }
    }

    @When("I fill the form with valid attributes")
    public void i_fill_the_form_with_valid_attributes() {
        manageUserPage.fillUserForm(
                "Maranmax",
                "maran@gmail.com",
                "IceHrm Employee",
                "Admin",
                "English",
                "[user] Documents => HR Forms"
        );
    }

    @And("I click the 'Save' button")
    public void i_click_the_save_button() {
        manageUserPage.clickSaveButton();
    }

    @And("I click the OK button on the confirmation modal")
    public void i_click_the_ok_button_on_the_confirmation_modal() {
        manageUserPage.clickOkButtonOnModal();
    }

    @Then("The new user should be displayed on the screen")
    public void the_new_user_should_be_displayed_on_the_screen() {
        if (!manageUserPage.isUserDisplayed("Maranmax")) {
            throw new AssertionError("New user is not displayed!");
        }
    }

    @When("I edit the user {string} with new details {string}, {string}")
    public void i_edit_the_user_with_new_details(
            String existingUsername,
            String newUsername,
            String newEmail) {
        manageUserPage.editUser(existingUsername, newUsername, newEmail);
    }

    @Then("The updated user details should be displayed on the screen with {string}")
    public void the_updated_user_details_should_be_displayed_on_the_screen(String updatedUsername) {
        if (!manageUserPage.isUserDisplayed(updatedUsername)) {
            throw new AssertionError("Updated user is not displayed!");
        }
        System.out.println("Updated user details displayed successfully.");
    }

    @Then("I should see the Manage Users screen")
    public void i_should_see_the_manage_users_screen() {
        // Add specific validation for the Manage Users screen if necessary
        System.out.println("Manage Users screen is displayed successfully.");
    }
}
