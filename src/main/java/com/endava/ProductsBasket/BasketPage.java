package com.endava.ProductsBasket;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Created by draicu on 8/3/2016.
 */
public class BasketPage {
    private WebDriver webDriver;

    public BasketPage(WebDriver webDriver){
        this.webDriver = webDriver;
    }

    @FindBy(xpath = "//span[@class='k-icon k-i-arrow-n']")
    private WebElement increaseQuantityArrow;

    @FindBy(xpath = "(//span[@class='price']/span)[position()=1]")
    private WebElement productPrice;

    @FindBy(xpath = "(//span[@class='price']/span)[position()=2]")
    private WebElement totalPrice;

    @FindBy(xpath = "//*[@id='1']")
    private WebElement productQuantity;

    @FindBy(xpath = "//li[@class='bp bp-1 mobile-on']/span")
    private WebElement nrOfPointsForOneItem;

    @FindBy(xpath = "//li[@class='bp bp-1 mobile-on']")
    private WebElement totalNrOfPoints;

    @FindBy(xpath = "//a[@data-category='men']")
    private WebElement pentruElTab;

    public void increaseProductQuantity(){
        increaseQuantityArrow.click();
    }

    public Float getProductPrice(){
        String prodPriceString = productPrice.getText(); // the string will look something like "12,99 LEI"
        String[] parts = prodPriceString.split(" "); // i want to split the string in "12,99" and "LEI",  using the " " as a delimiter
        String prodPriceWithDot = parts[0].replace(',','.');
        Float prodPrice = Float.parseFloat(prodPriceWithDot); // now we take the first part "12.99" and transform it into an integer
        return prodPrice;
    }

    public Float getTotalPrice(){
        String totalPriceString = totalPrice.getText(); // the string will look something like "38,97 LEI"
        String[] partsTotal = totalPriceString.split(" "); // i want to split the string in "38,97" and "LEI",  using the " " as a delimiter
        String totalPriceWithDot = partsTotal[0].replace(',','.');
        Float totPrice = Float.parseFloat(totalPriceWithDot); // now we take the first part "38.97" and transform it into an integer
        return totPrice;
    }

    public Integer getProductQuantity(){
        String attribute = "aria-valuenow";
        Integer prodQuantity = Integer.parseInt(productQuantity.getAttribute(attribute));
        return prodQuantity;
    }

    public Float getNrOfPointsForOneItem(){
        String nrOfPointsString = nrOfPointsForOneItem.getText();
        String nrOfPointsWithDot = nrOfPointsString.replace(',','.');
        Float pointsEarned = Float.parseFloat(nrOfPointsWithDot);
        return pointsEarned;
    }

    public Float getTotalNrOfPoints(){
        String nrOfPointsString = totalNrOfPoints.getText();
        String totalNrOfPointsWithDot = nrOfPointsString.replace(',','.');
        Float pointsEarned = Float.parseFloat(totalNrOfPointsWithDot);
        return pointsEarned;
    }

    public PentruElPage openPentruElPage(){
        pentruElTab.click();

        PentruElPage pentruElPage = PageFactory.initElements(webDriver, PentruElPage.class );
        pentruElPage.waitForPage();
        return pentruElPage;
    }

    public void waitForPage() {
        WebDriverWait wait = new WebDriverWait(webDriver,10);
        //wait.until(ExpectedConditions.visibilityOfAllElements(sameCategoryList));
        //wait.until(ExpectedConditions.textToBePresentInElement(increaseQuantityArrow,""));
    }
}
