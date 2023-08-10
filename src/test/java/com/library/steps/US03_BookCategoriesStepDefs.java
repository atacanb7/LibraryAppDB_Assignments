package com.library.steps;

import com.library.pages.BookPage;
import com.library.pages.LoginPage;
import com.library.utility.BrowserUtil;
import com.library.utility.DB_Util;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;

import java.util.List;

public class US03_BookCategoriesStepDefs {

    LoginPage loginPage = new LoginPage();
    BookPage bookPage = new BookPage();

    @Given("the {string} on the home page")
    public void the_on_the_home_page(String userType) {
        loginPage.login(userType);
    }

    @When("the user navigates to {string} page")
    public void the_user_navigates_to_page(String moduleName) {
        bookPage.navigateModule(moduleName);
    }

    List<String> actualCategories;

    @When("the user clicks book categories")
    public void the_user_clicks_book_categories() {
        //bookPage.mainCategoryElement.click();

        actualCategories = BrowserUtil.getAllSelectOptions(bookPage.mainCategoryElement);
        System.out.println("actualCategories = " + actualCategories);

        actualCategories.remove(0);

        System.out.println("actualCategories = " + actualCategories);
    }

    @Then("verify book categories must match book_categories table from db")
    public void verify_book_categories_must_match_book_categories_table_from_db() {

        DB_Util.runQuery("select name from book_categories");

        List<String> expectedCategories = DB_Util.getColumnDataAsList(1);

        System.out.println("expectedCategories = " + expectedCategories);

        Assert.assertEquals(expectedCategories, actualCategories);

        //Assert.assertTrue(actualCategories.containsAll(expectedCategories));


    }
}
