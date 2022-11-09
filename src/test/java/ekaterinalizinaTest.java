import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import runner.BaseTest;

public class ekaterinalizinaTest extends BaseTest {

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

        WebElement parisFrhoiseInDropDownMenu = getDriver().findElement(
                By.xpath("//ul[@class ='search-dropdown-menu']/li/span[text() = 'Paris, FR ']")
        );

        parisFrhoiseInDropDownMenu.click();

        WebElement h2CityCountHeader = getDriver().findElement(
                By.xpath("//div[@id = 'weather-widget']//h2")
        );

        Thread.sleep(2000);

        String actualResult = h2CityCountHeader.getText();

        Assert.assertEquals(actualResult, expectedResult);
    }

    @Test
    public void testTemperatureChangedInToF() throws InterruptedException {

        String url = "https://openweathermap.org/";
        getDriver().manage().window().maximize();
        String fTempSymbol = "°F";

        getDriver().get(url);
        Thread.sleep(10000);

        WebElement ButtonTemperatureFarenheit = getDriver().findElement(
                By.xpath("//div[@id = 'weather-widget']//div[text() = 'Imperial: °F, mph']"));
        ButtonTemperatureFarenheit.click();

        Thread.sleep(5000);

        WebElement tempF = getDriver().findElement(
                By.xpath("//div[@id = 'weather-widget']//span[@class ='heading']"));

        Assert.assertTrue(tempF.getText().contains(fTempSymbol));
    }
}
