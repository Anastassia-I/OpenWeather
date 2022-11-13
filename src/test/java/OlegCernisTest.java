import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;
import runner.BaseTest;

@Ignore
public class OlegCernisTest extends BaseTest {

    @Test
    public void testH2TagWhenSearchingCityCountry() throws InterruptedException {

        String url = "https://openweathermap.org/";
        String cityName = "Paris";
        String expectedResult = "Paris, FR";

        getDriver().get(url);
        Thread.sleep(10000);
        WebElement searchCityField = getDriver().findElement(
                By.xpath("//div[@id='weather-widget']//input[@placeholder= 'Search city']")
        );
        searchCityField.click();
        searchCityField.sendKeys(cityName);
        Thread.sleep(4000);

        WebElement searchButton = getDriver().findElement(
                By.xpath("//div[@id='weather-widget']//button[@type='submit']")
        );
        searchButton.click();
        Thread.sleep(1000);

        WebElement parisFRChoiseIndropdownMenu = getDriver().findElement(By.xpath(
                "//ul[@class='search-dropdown-menu']/li/span[text()='Paris, FR ']"));
        parisFRChoiseIndropdownMenu.click();

        WebElement h2CityCountryHeader =getDriver().findElement(By.xpath("//div[@id = 'weather-widget']//h2"));

        Thread.sleep(2000);
        String actualResult = h2CityCountryHeader.getText();
        Assert.assertEquals(actualResult, expectedResult);
    }

    @Test
    public void testButtonGuideAndConfirmThatWeAreAtOpenWeather() throws InterruptedException {

        String url = "https://openweathermap.org/";
        String button = "guide";
        String expectedResult = "OpenWeatherMap API guide - OpenWeatherMap";
        String expectedResult2 = "https://openweathermap.org/guide";

        getDriver().get(url);
        Thread.sleep(10000);
        WebElement title = getDriver().findElement(By.
                xpath("//div[@id='desktop-menu']//a[@ href='/guide']"));
        title.click();
        Thread.sleep(10000);

        String actualResult = getDriver().getTitle();
        String actualResult2 = getDriver().getCurrentUrl();
        Thread.sleep(2000);
        Assert.assertEquals(actualResult, expectedResult);
        Assert.assertEquals(actualResult2, expectedResult2);
    }

    @Test
    public void testFahrenheitInCity() throws InterruptedException {

        String url = "https://openweathermap.org/";
        String expectedResult2 = "°F";

        getDriver().get(url);
        getDriver().manage().window().maximize();
        Thread.sleep(10000);
        WebElement fahrenheit = getDriver().findElement(
                By.xpath("//div[text()= 'Imperial: °F, mph']"));
        fahrenheit.click();
        Thread.sleep(10000);

        WebElement confirmF = getDriver().findElement(
                By.xpath("//div[@class = 'current-temp']/span"));

        String fahrenheitConfirm = confirmF.getText();
        String actualResult = fahrenheitConfirm.substring((fahrenheitConfirm.length() - 2));
        Assert.assertEquals(actualResult, expectedResult2);
    }

}
