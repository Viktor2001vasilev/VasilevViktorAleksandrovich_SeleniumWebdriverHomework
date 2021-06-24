package ru.avito;

import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class TestingPrintersSearch {
    @BeforeClass
    public static void driverSetUp() {
        System.setProperty("webdriver.chrome.driver", "src/resources/chromedriver.exe");
    }
    @Test
    public void test() {
        ChromeDriver driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get("https://www.avito.ru/");
        driver.manage().window().maximize();

        Select category = new Select(driver.findElement(By.xpath("//select[@name = 'category_id']")));
        category.selectByVisibleText("Оргтехника и расходники");

        WebElement input = driver.findElement(By.xpath("//input[@id = 'search']"));
        input.sendKeys("Принтер");

        WebElement city = driver.findElement(By.xpath("//div[@class = 'main-text-2PaZG']"));
        city.click();

        WebElement cityInput = driver.findElement(By.xpath("//input[@class = 'suggest-input-3p8yi']"));
        cityInput.sendKeys("Владивосток");

        WebElement suggestedCity = driver.findElement(By.xpath("//li[@data-marker = 'suggest(0)']//strong[text() = 'Владивосток']"));
        suggestedCity.click();
        WebElement suggestedCityButton = driver.findElement(By.xpath("//button[@data-marker = 'popup-location/save-button']"));
        suggestedCityButton.click();

        WebElement checkBox = driver.findElement(By.xpath("//span[@data-marker = 'delivery-filter/text']"));
        if (!checkBox.isSelected())
            checkBox.click();

        WebElement showResults = driver.findElement(By.xpath("//button[@data-marker = 'search-filters/submit-button']"));
        showResults.click();

        Select showSelect = new Select(driver.findElement(By.xpath("//div[@class = 'sort-select-3QxXG select-select-box-3LBfK select-size-s-2gvAy']//select[@class = 'select-select-3CHiM']")));
        showSelect.selectByValue("2");

        List <WebElement> itemsList = driver.findElements(By.xpath("//div[@class = 'items-items-38oUm'][1]/div[@data-marker = 'item']"));
        for (int count = 0; count < 3; count++) {
            System.out.println(itemsList.get(count).findElement(By.xpath(".//div[@class = 'iva-item-titleStep-2bjuh']")).getText() + " стоит " + itemsList.get(count).findElement(By.xpath(".//div[@class = 'iva-item-priceStep-2qRpg']")).getText());
        }

    }
}
