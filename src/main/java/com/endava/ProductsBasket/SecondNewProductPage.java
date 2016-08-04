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
public class SecondNewProductPage {
    private WebDriver webDriver;

    public SecondNewProductPage(WebDriver webDriver){
        this.webDriver = webDriver;
    }

    @FindBy(xpath = "//a[@class='ui-product-box g5']")
    private List<WebElement> sameCategoryList;

    @FindBy(xpath = "//h1[@class='name']")
    private WebElement productNameTitle;

    @FindBy(xpath = "//span[@itemprop='price']")
    private WebElement productPrice;

    @FindBy(xpath = "//button[@id='addToBasketButton']")
    private WebElement adaugaInCosButton;

    @FindBy(xpath = "//span[@class='basket-items']")
    private WebElement nrOfProductsInBasket;

    @FindBy(xpath = "//div[@class='info']/div[@class='name']")
    private WebElement productAddedToBasket;

    @FindBy(xpath = "//span[@class='v-icon-mini-bag']")
    private WebElement linkToBasketPage;


    public String getProductNameTitle(){
        String productTitle = productNameTitle.getText();
        return  productTitle;
    }

    public String getProductPrice(){
        String productPriceOnPage = productPrice.getText();
        return productPriceOnPage;
    }

    public void addItemToCart(){
        adaugaInCosButton.click();
    }

    public boolean addItemToCartButtonExists(){
            if (adaugaInCosButton.isDisplayed())
                return  true;
            else
                return false;
    }

    public Integer getNumberOfProductsInBasket(){
        Integer productsInBasket = Integer.parseInt(nrOfProductsInBasket.getText());
        return productsInBasket;
    }

    public String getProductAddedToBasket(){
        WebDriverWait waitelem = new WebDriverWait(webDriver,10);
        waitelem.until(ExpectedConditions.textToBePresentInElement(productAddedToBasket,""));
        String productAddedName = productAddedToBasket.getText();

        return productAddedName;
    }

    public BasketPage openBasketPage(){
        linkToBasketPage.click();

        BasketPage basketPage = PageFactory.initElements(webDriver, BasketPage.class );
        basketPage.waitForPage();
        return basketPage;
    }


    public void waitForPage() {
        WebDriverWait wait = new WebDriverWait(webDriver,10);
        //wait.until(ExpectedConditions.visibilityOfAllElements(sameCategoryList));
        wait.until(ExpectedConditions.textToBePresentInElement(productNameTitle,""));
    }
}
