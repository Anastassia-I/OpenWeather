import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;import org.testng.annotations.Test;
import runner.BaseTest;

public class aidai001Test extends BaseTest {

        @Test
        public void testH2TagTextWhenSearchingCityCountry() throws InterruptedException {
                String url = "https://openweathermap.org/";
                String cityName = "Paris";
                String expectedResult = "Paris, FR";

                getDriver().get(url);
                Thread.sleep(7000);

                WebElement searchCityField = getDriver().findElement(
                        By.xpath("//div[@id='weather-widget']//input[@placeholder = 'Search city']")
                );
                searchCityField.click();
                searchCityField.sendKeys(cityName);

                WebElement searchButton = getDriver().findElement(
                        By.xpath("//div[@id='weather-widget']//button[@type='submit']")
                );
                searchButton.click();

                Thread.sleep(1000);

                WebElement parisFRChoiceInDropDownMenu = getDriver().findElement(
                        By.xpath("//ul[@class = 'search-dropdown-menu']/li/span[text ()= 'Paris, FR ']")
                );
                parisFRChoiceInDropDownMenu.click();
                Thread.sleep(1000);

                WebElement h2CityNameHeader = getDriver().findElement(
                        By.xpath("//div[@id = 'weather-widget']//h2")
                );

                Thread.sleep(2000);
                String actualResult = h2CityNameHeader.getText();

                Assert.assertEquals(actualResult, expectedResult);
        }
}