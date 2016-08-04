package com.endava.ProductsBasket;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

/**
 * Created by draicu on 8/1/2016.
 */
public class IngrijireCorpPage {

    @FindBy(xpath = "//span[@class='total-count']")
    private WebElement totalResultsField;

    @FindBy(xpath= "//h1[@class='ui-category-header-title']")
    private WebElement pageTitle;

    @FindBy(xpath = "//span[@class='k-input']")
    private WebElement showFilterOptions;

    @FindBy(xpath = "//ul[@id='top-filter_listbox']/li[@data-offset-index='2']")
    private WebElement filterByHigherPrice;

    @FindBy(xpath = "//a[@class='ui-product-box g5 isotope-item']")
    private List<WebElement> productList;

    @FindBy(xpath = "//section/a[position()<2]/div[@class='w-info']/span[@class='name']")
    private WebElement mostExpensiveProductName;

    @FindBy(xpath = "//section/a[position()<2]/div[@class='w-info']/div[@class='offer']/span")
    private WebElement highestPrice;


    @FindBy(xpath = "(//span[@class='item-count'])[position()<2]")
    private WebElement noutatiLink;

    private WebDriver webDriver;

    public IngrijireCorpPage(WebDriver webDriver){
        this.webDriver = webDriver;
    }


    public Integer getTotalProducts(){
        String totalResults = totalResultsField.getText();
        return Integer.parseInt(totalResults);
    }

    public String getPageName(){
        String pageName = pageTitle.getText();
        return pageName;
    }

    public void accessFilterByList(){
        showFilterOptions.click();
    }

    public void filterByHigher(){
        filterByHigherPrice.click();
    }

    public String getMostExpensiveProductName(){
        String firstPoductName = mostExpensiveProductName.getText();
        return firstPoductName;
    }

    public String getHighestPrice() {
        String highestProductPrice = highestPrice.getText();
        String errorMessage = "unspecified price";
        if (highestProductPrice != null)
            return highestProductPrice;
        else
            return errorMessage;
    }

    public FirstProductPage openFirstProductPage(){
        mostExpensiveProductName.click();

        FirstProductPage firstProductPage = PageFactory.initElements(webDriver, FirstProductPage.class );
        firstProductPage.waitForPage();
        return firstProductPage;
    }

    public NoutatiPage openNoutatiPAge(){
        noutatiLink.click();

        NoutatiPage noutatiPage = PageFactory.initElements(webDriver, NoutatiPage.class );
        noutatiPage.waitForPage();
        return noutatiPage;
    }

    public String getNumberOfNewProducts(){
        String nrOfNewProducts = noutatiLink.getText();
        return nrOfNewProducts;
    }

    public void waitForPage() {
        WebDriverWait wait = new WebDriverWait(webDriver,10);
        wait.until(ExpectedConditions.visibilityOfAllElements(productList));
        wait.until(ExpectedConditions.textToBePresentInElement(totalResultsField,""));
    }
}
