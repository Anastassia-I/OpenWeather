import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import runner.BaseTest;

public class LolitaMnTest extends BaseTest {


    @Test(priority = 0)
    public void testH2TagText_WhenSearchingCtyCountry() throws InterruptedException {

        String url = "https://openweathermap.org/";
        String cityName = "Paris";
        String expectedResult = "Paris, FR";

        getDriver().get(url);

        Thread.sleep(10000);
        WebElement searchCityField = getDriver().findElement(
                By.xpath("//div[@id = 'weather-widget']//input[@placeholder='Search city']")
        );

        searchCityField.click();
        searchCityField.sendKeys(cityName);

        WebElement searchButton = getDriver().findElement(
                By.xpath("//div[@id ='weather-widget']//button[@type='submit']")
        );

        searchButton.click();

        Thread.sleep(2000);
        WebElement parisChoiceInDropdownMenu = getDriver().findElement(
                By.xpath("//ul[@class ='search-dropdown-menu']/li/span[text()='Paris, FR ']")
        );

        parisChoiceInDropdownMenu.click();

        WebElement h2CityCountryHeader = getDriver().findElement(
                By.xpath("//div[@id ='weather-widget']//h2")
        );

        Thread.sleep(2000);

        String actualResult = h2CityCountryHeader.getText();

        Assert.assertEquals(actualResult, expectedResult);

    }

    @Test(priority = 1)
    public void testCheckPageTitle_WhenClickOnGuide() throws InterruptedException {

        String url = "https://openweathermap.org/";
        String expectedResultTitle = "OpenWeatherMap API guide - OpenWeatherMap";
        String expectedResultURL = "https://openweathermap.org/guide";

        getDriver().get(url);
        Thread.sleep(10000);

        WebElement guideButton = getDriver().findElement(
                By.xpath("//div[@id='desktop-menu']//a[@href = '/guide']")
        );

        guideButton.click();

        Thread.sleep(5000);

        String actualResultURL = getDriver().getCurrentUrl();
        String actualResultTitle = getDriver().getTitle();

        Assert.assertEquals(actualResultURL, expectedResultURL);
        Assert.assertEquals(actualResultTitle, expectedResultTitle);


    }

    @Test(priority = 2)
    public void testCheck_F_TemperatureMeasurement() throws InterruptedException {
        String url = "https://openweathermap.org/";
        String fSymbol = "°F";
        String expectedResult = "°F";

        getDriver().get(url);
        getDriver().manage().window().maximize();

        Thread.sleep(10000);

        WebElement fButton = getDriver().findElement(
                By.xpath("//div[@class='option' and text()='Imperial: °F, mph']")
        );

        Thread.sleep(2000);
        fButton.click();

        WebElement fTitle = getDriver().findElement(By.xpath("//span[@class='heading']"));
        String fTitle2 = fTitle.getText();
        String actualResult = fTitle2.substring(fTitle2.length() - 2);

        Assert.assertEquals(actualResult, expectedResult);
        Assert.assertTrue(fTitle2.contains(fSymbol));

    }

}
