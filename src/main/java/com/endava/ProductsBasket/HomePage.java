package com.endava.ProductsBasket;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * Created by draicu on 8/1/2016.
 */
public class HomePage {
    @FindBy(xpath = "//a[@data-category='bath-body']")
    private WebElement ingrijireCorpTab;

    @FindBy(xpath = "//div[@class=\"ui-cookie-tooltip\"]/button")
    private WebElement closeCookieButton;

    private WebDriver webDriver;

    public HomePage(WebDriver webDriver){
        this.webDriver = webDriver;
    }

    public void closeCookies(){
        closeCookieButton.click();
    }

    public IngrijireCorpPage openIngrijireaCorpuluiTab(){
        ingrijireCorpTab.click();

        IngrijireCorpPage ingrijireCorpPage = PageFactory.initElements(webDriver, IngrijireCorpPage.class );
        ingrijireCorpPage.waitForPage();
        return ingrijireCorpPage;
    }
}
