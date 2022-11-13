import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;
import runner.BaseTest;

@Ignore
public class MariannaLissTest extends BaseTest {

    @Test
    public void testH2TagText_WhenSearchingCityCountry() throws InterruptedException {
        String url = "https://openweathermap.org/";
        String cityName = "Paris";
        String expectedResult = "Paris, FR";

        getDriver().get(url);
        Thread.sleep(10000);

        WebElement searchCityField = getDriver().findElement(
                By.xpath("//div[@id = 'weather-widget']//input[@placeholder = 'Search city']")
        );
        searchCityField.click();
        searchCityField.sendKeys(cityName);

        WebElement searchButton = getDriver().findElement(
                By.xpath("//div[@id = 'weather-widget']//button[@type = 'submit']")
        );
        searchButton.click();
        Thread.sleep(1000);

        WebElement parisFRChoiceInDropdownMenu = getDriver().findElement(
                By.xpath("//ul[@class = 'search-dropdown-menu']/li/span[text() = 'Paris, FR ']")
        );
        parisFRChoiceInDropdownMenu.click();

        WebElement h2CityCountryHeader = getDriver().findElement(
                By.xpath("//div[@id = 'weather-widget']//h2")
        );
        Thread.sleep(2000);
        String actualResult = h2CityCountryHeader.getText();

        Assert.assertEquals(actualResult, expectedResult);
    }

    @Test
    public void testOpenAndCheckTheMenuGuide() throws InterruptedException {

        String url = "https://openweathermap.org/";
        String expectedResult1 = "https://openweathermap.org/guide";
        String expectedResult2 = "OpenWeatherMap API guide - OpenWeatherMap";

        getDriver().get(url);

        Thread.sleep(10000);

        WebElement guideElementInMenu = getDriver().findElement(
                By.xpath("//a[@href='/guide']")
        );

        guideElementInMenu.click();
        String actualResult2 = getDriver().getTitle();
        String actualResult1 = getDriver().getCurrentUrl();

        Assert.assertEquals(actualResult1, expectedResult1);
        Assert.assertEquals(actualResult2, expectedResult2);
    }

    @Test
    public void testOpenAndCheckTheTemperatureInFahrenheit() throws InterruptedException {
        String url = "https://openweathermap.org/";
        String fTempSymbol = "°F";

        getDriver().get(url);
        Thread.sleep(7000);
        WebElement menuImperial = getDriver().findElement(
                By.xpath("//div[@class = 'switch-container']/div[@class='option']/following-sibling::div")
        );
        menuImperial.click();
        Thread.sleep(7000);
        WebElement tempF = getDriver().findElement(
                By.xpath("//div[@class='current-temp']/span")
        );
        String tempInF = tempF.getText();

        Assert.assertTrue(tempInF.contains(fTempSymbol));
    }
}
