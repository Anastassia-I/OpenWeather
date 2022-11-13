import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;
import runner.BaseTest;

@Ignore
public class NadiaLidtTest extends BaseTest {

    @Test
    public void testH2TagTextWhenSearchingCityCountry() throws InterruptedException {
        String url = "https://openweathermap.org/";
        String cityName = "Paris";
        String expectedResult = "Paris, FR";

        getDriver().get(url);
        Thread.sleep(8000);

        WebElement searchCityField = getDriver().findElement(
                By.xpath("//div[@id='weather-widget']//input[@placeholder='Search city']"));
        Thread.sleep(1000);
        searchCityField.sendKeys(cityName);

        WebElement searchButton = getDriver().findElement(
                By.xpath("//div[@id='weather-widget']//button[@type='submit']"));
        searchButton.click();
        Thread.sleep(1000);

        WebElement parisFrChoiInDropDownMenu = getDriver().findElement(
                By.xpath("//ul[@class='search-dropdown-menu']/li/span[text()='Paris, FR ']"));
        parisFrChoiInDropDownMenu.click();

        WebElement h2CityNameHeader = getDriver().findElement(By.xpath("//div[@id='weather-widget']//h2"));
        Thread.sleep(2000);

        String actualResultat = h2CityNameHeader.getText();

        Assert.assertEquals(actualResultat, expectedResult);
    }
}
