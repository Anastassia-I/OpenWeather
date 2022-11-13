import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;
import runner.BaseTest;

import java.time.Duration;

import static org.openqa.selenium.support.ui.ExpectedConditions.numberOfWindowsToBe;

@Ignore
public class ViacheslavAnisimovTest extends BaseTest {

    @Test
    public void testH2TagText_WhenSearchingCityCountry() throws InterruptedException {


        String url = "https://openweathermap.org/";
        String cityName = "Paris";
        String expectedResult = "Paris, FR";

        getDriver().get(url);
        Thread.sleep(10000);

        WebElement searchCityField = getDriver().findElement(
                By.xpath("//div[@id='weather-widget']"
                        + "//input[@placeholder = 'Search city']")
        );
        searchCityField.click();
        searchCityField.sendKeys(cityName);

        WebElement searchButton = getDriver().findElement(
                By.xpath("//div[@id = 'weather-widget']"
                        + "//button[@type = 'submit']")
        );
        searchButton.click();
        Thread.sleep(5000);

        WebElement parisFRChoiceInDropdownMenu = getDriver().findElement(
                By.xpath("//ul[@class = 'search-dropdown-menu']"
                        + "/li/span[text() = 'Paris, FR ']")
        );
        parisFRChoiceInDropdownMenu.click();

        WebElement h2CityCountryHeader = getDriver().findElement(
                By.xpath("//div[@id = 'weather-widget']//h2")
        );
        Thread.sleep(5000);
        String actualResult = h2CityCountryHeader.getText();

        Assert.assertEquals(actualResult, expectedResult);
    }

    @Test
    public void testHamburgerMenuAndGuidePageTitle() throws InterruptedException {

        String url = "https://openweathermap.org/";
        String expectedResult1 = "https://openweathermap.org/guide";
        String expectedResult2 = "OpenWeatherMap API guide - OpenWeatherMap";

        getDriver().manage().window().setSize(new Dimension(1024, 970));
        getDriver().get(url);
        Thread.sleep(10000);

        WebElement HamburgerMenu = getDriver().findElement(
                By.id("hamburger")
        );

        HamburgerMenu.click();

        WebElement linkToGuidePage = getDriver().findElement(
                By.xpath("//ul[@id='mobile-menu']//a[@href='/guide']")
        );
        linkToGuidePage.click();

        String actualResult1 = getDriver().getCurrentUrl();
        String actualResult2 = getDriver().getTitle();

        Assert.assertEquals(actualResult1, expectedResult1);
        Assert.assertEquals(actualResult2, expectedResult2);
    }

    @Test

    public void testMarketplaceMenuIsClickableOpenCustomWeatherProducts() throws InterruptedException {

        String url = "https://openweathermap.org/";
        String marketplaceMenuText = "Marketplace";
        String expectedResult1 = "https://home.openweathermap.org/marketplace";
        String expectedResult2 = "Custom Weather Products";

        getDriver().get(url);
        Thread.sleep(10000);

        String originalWindow = getDriver().getWindowHandle();
        assert getDriver().getWindowHandles().size() == 1;

        WebElement guideMenu = getDriver().findElement(By.linkText(marketplaceMenuText));
        guideMenu.click();

        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(10));
        wait.until(numberOfWindowsToBe(2));
        for (String windowHandle : getDriver().getWindowHandles()) {
            if(!originalWindow.contentEquals(windowHandle)) {
                getDriver().switchTo().window(windowHandle);
                break;
            }
        }

        String actualResult1 = getDriver().getCurrentUrl();
        String actualResult2 = getDriver().findElement(
                By.cssSelector("h1")
        ).getText();

        Assert.assertEquals(actualResult1, expectedResult1);
        Assert.assertEquals(actualResult2, expectedResult2);

    }
}
