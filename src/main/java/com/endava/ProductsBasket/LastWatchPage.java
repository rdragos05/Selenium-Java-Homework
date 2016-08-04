package com.endava.ProductsBasket;

import java.lang.String;
import org.omg.CORBA.BooleanHolder;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Created by draicu on 8/4/2016.
 */
public class LastWatchPage {

    private WebDriver webDriver;

    public LastWatchPage(WebDriver webDriver){
        this.webDriver = webDriver;
    }


    @FindBy(xpath = "//div[@class='w-share-icons']/a[@title='Email']")
    private WebElement emailButton;

    public Boolean isEmailButtonPresent(){
        if (emailButton.isDisplayed())
            return true;
        else
            return false;
    }

    public EmailSharePage openEmailSharePage(){
        emailButton.click();

        EmailSharePage emailSharePage = PageFactory.initElements(webDriver, EmailSharePage.class );
        emailSharePage.waitForPage();
        return emailSharePage;
    }


    public void waitForPage() {
        WebDriverWait wait = new WebDriverWait(webDriver,10);
        //wait.until(ExpectedConditions.visibilityOfAllElements(sameCategoryList));
        wait.until(ExpectedConditions.textToBePresentInElement(emailButton,""));
    }
}
