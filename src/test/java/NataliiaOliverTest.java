import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import runner.BaseTest;

public class NataliiaOliverTest extends BaseTest {

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

        WebElement searchButton = getDriver().findElement(
                By.xpath("//div[@id='weather-widget']//button[@type='submit']")
        );
        searchButton.click();

        Thread.sleep(1000);

        WebElement parisFRChoiceInDropdownMenu = getDriver().findElement(
                By.xpath("//ul[@class='search-dropdown-menu']/li/span[text()='Paris, FR ']")
        );
        parisFRChoiceInDropdownMenu.click();

        WebElement h2CityCountryHeader = getDriver().findElement(
                By.xpath("//div[@id='weather-widget']//h2")
        );

        Thread.sleep(2000);
        String actualResult = h2CityCountryHeader.getText();

        Assert.assertEquals(actualResult, expectedResult);
    }

    @Test
    public void testPressImperialButtonF_TempIsdIsDisplayedInF() throws InterruptedException {
        String url = "https://openweathermap.org/";
        String expectedResult = "°F";

        getDriver().get(url);
        Thread.sleep(10000);

        WebElement searchImperial = getDriver().findElement(
                By.xpath("//div[@id='weather-widget']//div[text()='Imperial: °F, mph']")
        );
        searchImperial.click();

        Thread.sleep(2000);

        WebElement tempF = getDriver().findElement(By.xpath("//div[@class='current-temp']/span[@class='heading']"));
        String tempInF = tempF.getText();

        Assert.assertTrue(tempInF.contains(expectedResult));
    }
}
