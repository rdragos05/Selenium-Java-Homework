package com.endava.ProductsBasket;

import java.lang.String;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

/**
 * Created by draicu on 8/4/2016.
 */
public class EmailSharePage {
    private WebDriver webDriver;

    public EmailSharePage(WebDriver webDriver){
        this.webDriver = webDriver;
    }

    @FindBy(xpath = "//button[@class=\"at-expanded-menu-close\"]")
    private WebElement closeButton;

    @FindBy(xpath = "//input[@id='at-expanded-menu-email-to']")
    private WebElement clickInsideTextBox;

    public String getPageUrl() throws InterruptedException {
        //return webDriver.getWindowHandles(); // used this function to get window name/ id
        //webDriver.switchTo().window("{5d2d70a2-f9fa-4f17-911d-0455abe36176}/ {1c217e24-04ed-4d69-b924-6efce712c58a}");// used this function to switch to the new window
        return webDriver.getCurrentUrl(); //without switching to the window, the returned url is still the old one; even tried using Thread.sleep()
    }

    public void waitForPage() {
        WebDriverWait wait = new WebDriverWait(webDriver,10);
        //wait.until(ExpectedConditions.visibilityOfAllElements(sameCategoryList));
        wait.until(ExpectedConditions.textToBePresentInElement(closeButton,""));
    }
}
