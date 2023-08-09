package com.library.steps;

import com.library.pages.DashBoardPage;
import com.library.utility.DB_Util;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;

public class US02_DashboardStepDefs {

    DashBoardPage dashBoardPage = new DashBoardPage();
    String actualBorrowedBook;

    @When("the librarian gets borrowed books number")
    public void the_librarian_gets_borrowed_books_number() {

        // OPT 1 --> WEBELEMENT
        actualBorrowedBook = dashBoardPage.borrowedBooksNumber.getText();
        System.out.println("actualBorrowedBook = " + actualBorrowedBook);

        // OPT 2 --> METHOD
        System.out.println("dashBoardPage.getModuleCount(\"Borrowed Books\") = " + dashBoardPage.getModuleCount("Borrowed Books"));

    }

    @Then("borrowed books number information must match with DB")
    public void borrowed_books_number_information_must_match_with_db() {

        String query = "SELECT COUNT(*) FROM book_borrow\n" +
                "WHERE is_returned=0";

        DB_Util.runQuery(query);

        String expectedBorrowedBook = DB_Util.getFirstRowFirstColumn();
        System.out.println("expectedBorrowedBook = " + expectedBorrowedBook);

        Assert.assertEquals(expectedBorrowedBook, actualBorrowedBook);
    }
}
