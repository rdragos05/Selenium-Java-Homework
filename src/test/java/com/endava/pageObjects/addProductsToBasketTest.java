package com.endava.pageObjects;

import com.endava.ProductsBasket.*;
import com.sun.deploy.net.URLEncoder;
import org.junit.Assert;
import org.junit.Test;

import java.io.UnsupportedEncodingException;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by draicu on 8/1/2016.
 */
public class addProductsToBasketTest extends TestBaseClass{

    @Test
    public void openIngrijireaCorpuluiTab() throws UnsupportedEncodingException, InterruptedException {
        homePage.closeCookies();
        IngrijireCorpPage ingrijireCorpPage = homePage.openIngrijireaCorpuluiTab();

        Integer totalProductsNumber = ingrijireCorpPage.getTotalProducts();
        System.out.println("Total products " + totalProductsNumber);
        assertTrue(totalProductsNumber>0);

        String expectedPageName="Îngrijire Corp".toUpperCase();
        String pageName = ingrijireCorpPage.getPageName();
        System.out.println("Page name: " + pageName);
        assertEquals(pageName, expectedPageName);

        ingrijireCorpPage.accessFilterByList();
        ingrijireCorpPage.filterByHigher();

        String productName = ingrijireCorpPage.getMostExpensiveProductName();
        System.out.println("Most expensive product: " + productName);

        String productHighestPrice = ingrijireCorpPage.getHighestPrice();
        System.out.println("Highest product price: " + productHighestPrice);

        FirstProductPage firstProductPage = ingrijireCorpPage.openFirstProductPage();

        String productNameOnProductPage = firstProductPage.getProductNameTitle();
        assertEquals(productName, productNameOnProductPage );

        String productPriceOnProductPage = firstProductPage.getProductPrice();
        assertEquals(productHighestPrice, productPriceOnProductPage );

        firstProductPage.openIngrijireCorpPage();

        ingrijireCorpPage.openNoutatiPAge();

        NoutatiPage noutatiPage = ingrijireCorpPage.openNoutatiPAge();
        Integer totalNewProducts = noutatiPage.getTotalNewProductsNumber();
        System.out.println("Total new products: " + totalNewProducts);
        assertTrue(totalNewProducts>0);
        String numberOfNewProducts= ingrijireCorpPage.getNumberOfNewProducts();
        assertTrue(numberOfNewProducts.contains(totalNewProducts.toString()));

        SecondNewProductPage secondNewProductPage = noutatiPage.openSecondNewProductPage();

        boolean addItemButtonExists = secondNewProductPage.addItemToCartButtonExists();
        assertTrue(addItemButtonExists);

        secondNewProductPage.addItemToCart();
        String productAdded = secondNewProductPage.getProductAddedToBasket();
        String expectedProductAdded = secondNewProductPage.getProductNameTitle();
        assertEquals(productAdded, expectedProductAdded);

        BasketPage basketPage = secondNewProductPage.openBasketPage();

        Float nrOfPointsPerOneProduct = basketPage.getNrOfPointsForOneItem(); //will use later, after adding more products
        System.out.println("Nr of points per 1 product is : " + nrOfPointsPerOneProduct);


        basketPage.increaseProductQuantity();
        basketPage.increaseProductQuantity();

        System.out.println("Product Quantity: " + basketPage.getProductQuantity());

        Float priceOfProduct = basketPage.getProductPrice();
        System.out.println("Price per one product is: " + priceOfProduct);

        Float totalPriceofBasket = basketPage.getTotalPrice();

        Integer prodQuantity = basketPage.getProductQuantity();

        Float totalCalculatedPrice = priceOfProduct * prodQuantity;
        assertEquals(totalPriceofBasket, totalCalculatedPrice);

        Float nrOfPointsPerAllProducts = basketPage.getTotalNrOfPoints();
        Float exepectedNrOfTotalPoints = nrOfPointsPerOneProduct * prodQuantity;

        assertEquals(nrOfPointsPerAllProducts, exepectedNrOfTotalPoints);


        PentruElPage pentruElPage = basketPage.openPentruElPage();


        pentruElPage.pressAccesoriiDropDown();
        pentruElPage.openCeasuriTab();

        String productRating = pentruElPage.getLastItemRating();
        String lastProductName = pentruElPage.getLastItemName();
        System.out.println("Last watch name: " + lastProductName);
        System.out.println("Last watch rating " + productRating);

        LastWatchPage lastWatchPage = pentruElPage.openLastWatchPage();
        assertTrue(lastWatchPage.isEmailButtonPresent());


        EmailSharePage emailSharePage = lastWatchPage.openEmailSharePage();
        String newURL = emailSharePage.getPageUrl();
        System.out.println(newURL);
        String productNameEncoded = lastProductName.replace(" ","%20");
        System.out.println(productNameEncoded);
        assertTrue(newURL.contains(productNameEncoded)); //this assert won;t work (For more info -> EmailSharePage class)

    }
}
