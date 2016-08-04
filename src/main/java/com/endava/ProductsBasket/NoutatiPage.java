package com.endava.ProductsBasket;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Created by draicu on 8/2/2016.
 */
public class NoutatiPage {

    private WebDriver webDriver;

    public NoutatiPage(WebDriver webDriver){
        this.webDriver = webDriver;
    }

    @FindBy(xpath = "//span[@class='total-count']")
    private WebElement totalNewProductsNumber;

    @FindBy(xpath = "(//a[@class='ui-product-box g5 isotope-item'])[position()=2]")
    private WebElement secondNewProductLink;

    public Integer getTotalNewProductsNumber(){
        String totalProductsNumber = totalNewProductsNumber.getText();
        return Integer.parseInt(totalProductsNumber);
    }

    public SecondNewProductPage openSecondNewProductPage(){
        secondNewProductLink.click();
        SecondNewProductPage secondNewProductPage = PageFactory.initElements(webDriver, SecondNewProductPage.class );
        secondNewProductPage.waitForPage();
        return secondNewProductPage;

    }


    public void waitForPage() {
        WebDriverWait wait = new WebDriverWait(webDriver,10);
        //wait.until(ExpectedConditions.visibilityOfAllElements(sameCategoryList));
        wait.until(ExpectedConditions.textToBePresentInElement(totalNewProductsNumber,""));
    }

}
