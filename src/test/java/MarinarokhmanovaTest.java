import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;
import runner.BaseTest;

public class MarinarokhmanovaTest extends BaseTest {

    @Test
    public void testH2TagText_WhenSearchingCityCountry() throws InterruptedException {
        String url = "https://openweathermap.org/";
        String cityName = "Paris";
        String expectedResult = "Paris, FR";

        getDriver().get(url);
        Thread.sleep(10000);

        WebElement searchCityField = getDriver().findElement(
                By.xpath("//div[@id= 'weather-widget']//input[@ placeholder='Search city']")
        );
        searchCityField.click();
        searchCityField.sendKeys(cityName);

        WebElement searchButton = getDriver().findElement(
                By.xpath("//div[@id = 'weather-widget']//button[@type = 'submit']")
        );
        searchButton.click();

        Thread.sleep(1000);

        WebElement parisFRChoiceINDropdownMenu = getDriver().findElement(
                By.xpath("//ul[@class ='search-dropdown-menu']/li/span[text() = 'Paris, FR ']")
        );
        parisFRChoiceINDropdownMenu.click();

        WebElement h2CityCountryHeard = getDriver().findElement(
                By.xpath("//div[@id = 'weather-widget']//h2")
        );

        Thread.sleep(2000);
        String actualResult = h2CityCountryHeard.getText();

        Assert.assertEquals(actualResult, expectedResult);
    }
    @Test
    public void testOpenWeatherMapGuide() throws InterruptedException {
        String url = "https://openweathermap.org/";
        String expectedResult1 = "https://openweathermap.org/guide";
        String expectedResult2 = "OpenWeatherMap API guide - OpenWeatherMap";

        getDriver().get(url);
        getDriver().manage().window().maximize();
        Thread.sleep(10000);

        WebElement guideSearchField = getDriver().findElement(
                By.xpath("//a[@href][text()='Guide']")
        );
        guideSearchField.click();
        Thread.sleep(2000);

        String confirmPageWithLink = getDriver().getTitle();

        String actualResult1 = getDriver().getCurrentUrl();
        String actualResult2 = confirmPageWithLink.intern();

        Assert.assertEquals(actualResult1, expectedResult1);
        Assert.assertEquals(actualResult2, expectedResult2);
    }

    @Test
    public void testUnitsImperialCtoF() throws InterruptedException {
        String url = "https://openweathermap.org/";
        String expectedResult = "°F";

        getDriver().get(url);
        getDriver().manage().window().maximize();
        Thread.sleep(7000);

        WebElement unitsImperialF = getDriver().findElement(
                By.xpath("//div[@class ='option'][text()='Imperial: °F, mph']")
        );
        unitsImperialF.click();
        Thread.sleep(2000);

        WebElement unitsImperialFCity = getDriver().findElement(
                By.xpath("//div[@class ='current-temp']/span")
        );
        unitsImperialFCity.click();
        Thread.sleep(2000);

        boolean actualResult = unitsImperialFCity.getText().contains("F");

        Assert.assertTrue(actualResult, expectedResult);
    }

    @Test
    public void testConfirmPanelWithTextAtBottom() throws InterruptedException {
        String url = "https://openweathermap.org/";
        String expectedResult1 = "We use cookies which are essential for the site to work. We also use non-essential " +
                "cookies to help us improve our services. Any data collected is anonymised. You can allow all " +
                "cookies or manage them individually.";
        String expectedResult2 = "Allow all";
        String expectedResult3 = "Manage cookies";

        getDriver().get(url);
        Thread.sleep(3000);

        WebElement panelWithText = getDriver().findElement(
                By.xpath("//div[@class = 'stick-footer-panel__container']" +
                        "/p[@class = 'stick-footer-panel__description']")
        );

        WebElement panelWithAllowAll = getDriver().findElement(
                By.xpath("//div[@class ='stick-footer-panel__btn-container']/button[@type = 'button']")
        );

        WebElement panelWithManageCookies = getDriver().findElement(
                By.xpath("//div[@class ='stick-footer-panel__btn-container']" +
                        "/a[@class = 'stick-footer-panel__link']")
        );

        String actualResult1 = panelWithText.getText();
        String actualResult2 = panelWithAllowAll.getText();
        String actualResult3 = panelWithManageCookies.getText();

        Assert.assertEquals(actualResult1, expectedResult1);
        Assert.assertEquals(actualResult2, expectedResult2);
        Assert.assertEquals(actualResult3, expectedResult3);
    }
}
