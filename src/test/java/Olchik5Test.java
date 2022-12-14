import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;
import runner.BaseTest;

@Ignore
public class Olchik5Test extends BaseTest {

    @Test
    public void testH2TagText_WhenSearchingCityCountry() throws InterruptedException {

        String url = "https://openweathermap.org/";
        String cityName = "Paris";
        String expectedResult = "Paris, FR";

        getDriver().get(url);
        Thread.sleep(10000);

        WebElement searchCityField = getDriver().findElement(
                By.xpath("//div[@id='weather-widget']//input[@placeholder='Search city']")
        );
        searchCityField.click();
        searchCityField.sendKeys(cityName);
        Thread.sleep(10000);

        WebElement searchButton = getDriver().findElement(By.xpath("//button[@type='submit']"));
        searchButton.click();

        Thread.sleep(10000);

        WebElement parisFRChoiceInDropdownMenu = getDriver().findElement(
                By.xpath("//ul[@class='search-dropdown-menu']/li/span[text()='Paris, FR ']"));
        parisFRChoiceInDropdownMenu.click();

        WebElement h2CityCountryHeader  = getDriver().findElement(By.xpath("//div[@id='weather-widget']//h2"));

        Thread.sleep(10000);

        String actualResult = h2CityCountryHeader.getText();

        Assert.assertEquals(actualResult,expectedResult);

    }

    //    TC_11_02
    @Test
    public void test2() throws InterruptedException {

        String url = "https://openweathermap.org/";
        String expectedResult = "°F";

        getDriver().get(url);
        getDriver().manage().window().maximize();
        Thread.sleep(10000);

        WebElement menuImperial = getDriver().findElement(
                By.xpath("//div[@class='switch-container']/div[@class='option']/following-sibling::div"));

        menuImperial.click();

        WebElement tempF = getDriver().findElement(
                By.xpath("//div[@class='current-temp']/span"));

        Boolean isTemperatureInFahrenheit = tempF.getText().contains("°F");

        Assert.assertTrue(isTemperatureInFahrenheit);
        getDriver().quit();
    }
}
