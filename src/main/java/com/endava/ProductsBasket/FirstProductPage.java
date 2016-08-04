package com.endava.ProductsBasket;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

/**
 * Created by draicu on 8/2/2016.
 */
public class FirstProductPage {

    @FindBy(xpath = "//a[@class='ui-product-box g5']")
    private List<WebElement> sameCategoryList;

    @FindBy(xpath = "//h1[@class='name']")
    private WebElement productNameTitle;

    @FindBy(xpath = "//span[@itemprop='price']")
    private WebElement productPrice;

    @FindBy(xpath = "//span[@class='v-icon-side-menu-arrow-left']")
    private WebElement goBackToIngrijireCorpPageLink;



    private WebDriver webDriver;

    public FirstProductPage(WebDriver webDriver){
        this.webDriver = webDriver;
    }

    public String getProductNameTitle(){
        String productTitle = productNameTitle.getText();
        return  productTitle;
    }

    public String getProductPrice(){
        String productPriceOnPage = productPrice.getText();
        return productPriceOnPage;
    }

    public IngrijireCorpPage openIngrijireCorpPage(){
        goBackToIngrijireCorpPageLink.click();

        IngrijireCorpPage ingrijireCorpPage = PageFactory.initElements(webDriver, IngrijireCorpPage.class );
        ingrijireCorpPage.waitForPage();
        return ingrijireCorpPage;
    }

    public void waitForPage() {
        WebDriverWait wait = new WebDriverWait(webDriver,10);
        //wait.until(ExpectedConditions.visibilityOfAllElements(sameCategoryList));
        wait.until(ExpectedConditions.textToBePresentInElement(productNameTitle,""));
    }
}
