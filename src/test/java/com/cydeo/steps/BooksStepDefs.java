package com.cydeo.steps;

import com.cydeo.pages.BookPage;
import com.cydeo.pages.DashBoardPage;
import com.cydeo.utility.BrowserUtil;
import com.cydeo.utility.DB_Util;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;

import java.util.List;
import java.util.Map;

public class BooksStepDefs {
    BookPage bookPage=new BookPage();
    List<String> actualCategoryList;


    @When("the user navigates to {string} page")
    public void the_user_navigates_to_page(String moduleName) {
        new DashBoardPage().navigateModule(moduleName);
    }

    @When("the user gets all book categories in webpage")
    public void the_user_gets_all_book_categories_in_webpage() {
        actualCategoryList=BrowserUtil.getAllSelectOptions(bookPage.mainCategoryElement);
        actualCategoryList.remove(0);
        System.out.println("expectedCategoryList = " + actualCategoryList);
    }

    @Then("user should be able to see following categories")
    public void user_should_be_able_to_see_following_categories(List<String> expectedCategoryList) {

        Assert.assertEquals(expectedCategoryList, actualCategoryList);

    }

    @When("I open book {string}")
    public void i_open_book(String bookName) {

        System.out.println("bookName = " + bookName);
        BrowserUtil.waitForClickablility(bookPage.search, 5).sendKeys(bookName);
        BrowserUtil.waitForClickablility(bookPage.editBook(bookName), 5).click();

    }

    @Then("verify book categories must match book categories table from db")
    public void verifyBookCategoriesMustMatchBookCategoriesTableFromDb() {

            String query="select name from book_categories";
            DB_Util.runQuery(query);

            // get Data
            List<String> expectedCategoryList = DB_Util.getColumnDataAsList(1);

            // Assertions
            Assert.assertEquals(expectedCategoryList, actualCategoryList);


        }

    @Then("book information must match the database for {string}")
    public void bookInformationMustMatchTheDatabaseFor(String bookName) {

        BrowserUtil.waitFor(4);


        String actualBookName = bookPage.bookName.getAttribute("value");
        String actualAuthor = bookPage.author.getAttribute("value");
        String actualISBN = bookPage.isbn.getAttribute("value");
        String actualYear = bookPage.year.getAttribute("value");
        String actualDesc = bookPage.description.getAttribute("value");

        System.out.println("actualAuthor = " + actualAuthor);

        // get DB book INFO
        String query="select name,isbn,year,author,description from books\n" +
                "where name='"+bookName+"'";

        DB_Util.runQuery(query);

        Map<String, String> dbData = DB_Util.getRowMap(1);

        System.out.println("dbData = " + dbData);

        String expectedBookName = dbData.get("name");
        String expectedAuthor = dbData.get("author");
        String expectedISBN = dbData.get("isbn");
        String expectedYear = dbData.get("year");
        String expectedDesc = dbData.get("description");


        // Compare
        Assert.assertEquals(expectedBookName, actualBookName);
        Assert.assertEquals(expectedAuthor, actualAuthor);
        Assert.assertEquals(expectedISBN, actualISBN);
        Assert.assertEquals(expectedYear, actualYear);
        Assert.assertEquals(expectedDesc, actualDesc);


    }

    @Given("Display book name and related bookCategory name from library")
    public void displayBookNameAndRelatedBookCategoryNameFromLibrary() {
        String query="select  b.name, bc.name\n" +
                "from books b inner join book_categories bc on b.book_category_id = bc.id ";

        DB_Util.runQuery(query);
        // get Data

        List<String> expNameBNameBC = DB_Util.getAllColumnNamesAsList();
        System.out.println( " INFO " + expNameBNameBC);

        // Assertions
     //   Assert.assertEquals(expectedCategoryNameList, actualCategoryList);

    }

    @And("Find me how many book we have in each category")
    public void findMeHowManyBookWeHaveInEachCategory() {
        String query = "select book_category_id,count(*) from books\n" +
                "\n" +
                "group by book_category_id\n" +
                "order by  2 desc\n" +
                "limit 1";
        DB_Util.runQuery(query);
    }

    @And("Display most popular book category \\( this book needs to borrow to be popular.)")
    public void displayMostPopularBookCategoryThisBookNeedsToBorrowToBePopular() {
        String query ="select  bc.name,count(*)\n" +
                "from books b\n" +
                " inner join book_categories bc on b.book_category_id = bc.id\n" +
                "group by bc.name\n" +
                "order by  2 desc\n" +
                "limit 1";
        DB_Util.runQuery(query);

    }

    @Then("Display how many books are borrowed not turn back yet")
    public void displayHowManyBooksAreBorrowedNotTurnBackYet() {
        String query = "select bc.name, count(*)\n" +
                "from book_borrow bb\n" +
                "inner join books b on bb.book_id = b.id\n" +
                "inner join book_categories bc on b.book_category_id = bc.id\n" +
                "group by  bc.name\n" +
                "order by 2 desc\n" +
                "limit 1";
        DB_Util.runQuery(query);

    }
}
