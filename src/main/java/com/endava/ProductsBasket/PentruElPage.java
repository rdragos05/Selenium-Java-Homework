package com.endava.ProductsBasket;

import com.google.gson.annotations.Until;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.internal.FindsByXPath;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

/**
 * Created by draicu on 8/3/2016.
 */
public class PentruElPage {
    private WebDriver webDriver;

    public PentruElPage(WebDriver webDriver){
        this.webDriver = webDriver;
    }

    @FindBy(xpath = "//a[@class='ui-product-box g5 isotope-item']")
    private List<WebElement> productList;

    @FindBy(xpath = "(//a[contains(@class,'first-level toggle right-arrow')])[last()]")
    private WebElement accesoriiDropDown;

    @FindBy(xpath = "((//ul[@data-facet-select='single']/li)[last()]/ul/li)[position()=3]/a")
    private WebElement ceasuriTab;

    @FindBy(xpath = "(//ul[@class='links-list']//a)[position()=9]")
    private WebElement goDirectlyToCeasuri; // MUST BE THE SOLUTION. TEST!!!!! this or refresh page

    @FindBy(xpath = "(//a[@class='ui-product-box g5 isotope-item'])[last()]//span[@class='name']")
    private WebElement lastItemName;

    @FindBy(xpath = "(//a[@class='ui-product-box g5 isotope-item'])[last()]//span[@class='stars']")
    private WebElement lastItemRating;

    @FindBy(xpath = "(//a[@class='ui-product-box g5 isotope-item'])[last()]")
    private WebElement lastItemBox;


    public String getLastItemName(){
        String lastItmName = lastItemName.getText();
        return lastItmName;
    }

    public String getLastItemRating(){
        String lastItmRating = lastItemRating.getText();
        return lastItmRating;
    }

    public void pressAccesoriiDropDown(){
        accesoriiDropDown.sendKeys(Keys.RETURN);
        WebDriverWait waitelem = new WebDriverWait(webDriver,10);
        waitelem.until(ExpectedConditions.textToBePresentInElement(ceasuriTab,""));
    }

    public void openCeasuriTab(){
        ceasuriTab.sendKeys(Keys.RETURN);
        WebDriverWait waitelem = new WebDriverWait(webDriver,10);
        waitelem.until(ExpectedConditions.textToBePresentInElement(lastItemBox,""));
    }

    public LastWatchPage openLastWatchPage(){
        lastItemBox.sendKeys(Keys.RETURN);

        LastWatchPage lastWatchPage = PageFactory.initElements(webDriver, LastWatchPage.class );
        lastWatchPage.waitForPage();
        return lastWatchPage;
    }


    public void waitForPage() {
        WebDriverWait wait = new WebDriverWait(webDriver,10);
        wait.until(ExpectedConditions.visibilityOfAllElements(productList));
        //wait.until(ExpectedConditions.textToBePresentInElement(increaseQuantityArrow,""));
    }
}
