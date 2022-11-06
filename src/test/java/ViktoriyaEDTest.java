import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;
import runner.BaseTest;

public class ViktoriyaEDTest extends BaseTest {

    @Test
    public void testH2TagText_WhenSearchingCityCountry() throws InterruptedException {

        String url = "https://openweathermap.org/";
        String cityName = "Paris";
        String expectedResult = "Paris, FR";

        getDriver().get(url);
        Thread.sleep(6000);

        WebElement searchCityField = getDriver().findElement(
                By.xpath("//div[@id = 'weather-widget']//input[@placeholder = 'Search city']")
        );
        searchCityField.click();
        searchCityField.sendKeys(cityName);

        WebElement searchButton = getDriver().findElement(
                By.xpath("//div[@id='weather-widget']//button[@type='submit']\n")
        );
        searchButton.click();

        Thread.sleep(2000);

        WebElement parisFrChoiceInDropDownMenu = getDriver().findElement(
                By.xpath("//ul[@class=\"search-dropdown-menu\"]//li//span[text() = 'Paris, FR ']")
        );
        parisFrChoiceInDropDownMenu.click();

        WebElement h2CityNameHeader = getDriver().findElement(By.xpath("//div[@id = 'weather-widget']//h2"));

        Thread.sleep(1000);

        String actualResult = h2CityNameHeader.getText();

        Assert.assertEquals(actualResult, expectedResult);
    }
}


