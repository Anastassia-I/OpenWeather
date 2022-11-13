import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;
import runner.BaseTest;

@Ignore
public class TimberhutOWeatherTest extends BaseTest {

        @Test
        public void test_SearchingCityCountry() throws InterruptedException {

            String cityName = "Paris";
            String expectedResult = "Paris, FR";

            getDriver().get("https://openweathermap.org/");
            Thread.sleep(10000);

            WebElement seachCityField = getDriver().findElement(
                    By.xpath("//div[@id = 'weather-widget']//input[@placeholder='Search city']")
            );
            seachCityField.click();
            seachCityField.sendKeys(cityName);
            Thread.sleep(2000);

            WebElement seachButton = getDriver().findElement(
                    By.xpath("//div[@id = 'weather-widget']//button[@type = 'submit']")
            );
            seachButton.click();
            Thread.sleep(3000);

            WebElement parisFRChoisInDropMenu = getDriver().findElement(
                    By.xpath("//ul[@class='search-dropdown-menu']//li//span[text() = 'Paris, FR ']")
            );
            parisFRChoisInDropMenu.click();
            Thread.sleep(2000);

            WebElement h2CityCountryHeader = getDriver().findElement(
                    By.xpath("//div[@id='weather-widget']//h2")
            );
            Thread.sleep(2000);

            Assert.assertEquals(h2CityCountryHeader.getText(), expectedResult);
        }
}
