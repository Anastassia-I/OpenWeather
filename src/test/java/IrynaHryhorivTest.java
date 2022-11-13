import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;
import runner.BaseTest;

@Ignore
public class IrynaHryhorivTest extends BaseTest {
    @Test
    public void testH2Text_WhenSearchingCityCountry() throws InterruptedException {

        String url = "https://openweathermap.org/";
        String cityName = "Paris";
        String expectedResult = "Paris, FR";

        getDriver().get(url);
        WebElement searchCityField = getDriver().findElement(
                By.xpath("//div[@id ='weather-widget']//input[@placeholder = 'Search city']" )
        );
        Thread.sleep(10000);

        searchCityField.click();
        searchCityField.sendKeys(cityName);

        WebElement searchButton = getDriver().findElement(
                By.xpath("//div[@id ='weather-widget']//button[@type = 'submit']")
        );
        searchButton.click();
        Thread.sleep(1000);

        WebElement parisFRChoiceInDropdownMenu = getDriver().findElement(
                By.xpath("//ul[@class = 'search-dropdown-menu']/li/span[text() = 'Paris, FR ']")
        );
        parisFRChoiceInDropdownMenu.click();
        Thread.sleep(5000);

        WebElement h2CityContryHeader = getDriver().findElement(
                By.xpath("//div[@id='weather-widget']//h2")
        );

        Thread.sleep(2000);

        String actualResult = h2CityContryHeader.getText();
        Assert.assertEquals(actualResult, expectedResult);
    }
    @Test
    public void testOpenAndClickToGuide() throws InterruptedException {

        String url = "https://openweathermap.org/";
        String expectedResult = "https://openweathermap.org/guide";
        String expectedResult1 ="OpenWeatherMap API guide - OpenWeatherMap" ;

        getDriver().get(url);
        Thread.sleep(10000);

        WebElement GuideButton = getDriver().findElement(
                By.xpath("//a[@href='/guide']")
        );
        GuideButton.click();
        Thread.sleep(2000);

        String actualResult1 = getDriver().getTitle();
        String actualResult = getDriver().getCurrentUrl();

        Assert.assertEquals(actualResult1, expectedResult1);
        Assert.assertEquals(actualResult, expectedResult);
    }
}
