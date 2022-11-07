import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import runner.BaseTest;

public class DimaZadrutsiyTest extends BaseTest {
    @Test
    public void testH2TagText_WhenSearchingCitiCountry() throws InterruptedException {

        String url = "https://openweathermap.org/";
        String cityName = "Paris";
        String expectedResult = "Paris, FR";

        getDriver().get(url);
        Thread.sleep(7000);
        WebElement searchCityField = getDriver().findElement(
                By.xpath("//div[@id = 'weather-widget']//input[@placeholder = 'Search city']"));
        searchCityField.click();
        searchCityField.sendKeys(cityName);


        WebElement searchButton = getDriver().findElement(
                By.xpath("//div[@id='weather-widget']//button[@type ='submit']"));
        searchButton.click();

        Thread.sleep(3000);
        WebElement firstChoiesInDropdownMenu = getDriver().findElement(
                By.xpath("//div[@id='weather-widget']//ul[@class='search-dropdown-menu']/li/span[text()"
                        + "='Paris, FR ']"));
        firstChoiesInDropdownMenu.click();

        WebElement h2CityCountryHeader = getDriver().findElement(
                By.xpath("//div[@id='weather-widget']//h2"));

        Thread.sleep(3000);
        String actualResult = h2CityCountryHeader.getText();

        Assert.assertEquals(actualResult, expectedResult);
    }

    @Test
    public void testPageReload() throws InterruptedException {

        String url = "https://openweathermap.org/";
        String expectedResult = "Loading";

        getDriver().get(url);
        Thread.sleep(5000);

        WebElement clickOnTheLogo = getDriver().findElement(By.xpath(
                "//a[@href='/']/img[@src='/themes/openweathermap/assets/img/logo_white_cropped.png']"));
        clickOnTheLogo.click();

        WebElement loading = getDriver().findElement(By.xpath("//div[@aria-label='Loading']"));

        String actualResult = loading.getAttribute("aria-label");

        Assert.assertEquals(actualResult, expectedResult);
    }
}
