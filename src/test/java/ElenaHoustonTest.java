import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;
import runner.BaseTest;

@Ignore
public class ElenaHoustonTest extends BaseTest {

    @Ignore
    @Test
    public void testH2searchWeather_CityCountry () throws InterruptedException {

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

        WebElement parisFRChoice = getDriver().findElement(
                By.xpath("//ul[@class='search-dropdown-menu']//li//span[text() = 'Paris, FR ']")
        );
        parisFRChoice.click();


        WebElement h2CityCountryHeader = getDriver().findElement(
                By.xpath("//div[@id = 'weather-widget']//h2")
        );

        Thread.sleep(5000);
        String actualResult = h2CityCountryHeader.getText();

        Assert.assertEquals(actualResult, expectedResult);
    }

    @Test
    public void testConfirmTheLinkAndTitleOnThePage_OpenWeatherMap() throws InterruptedException {
        String url = "https://openweathermap.org/";
        String guideMenuText = "Guide";
        String expectedResult1 = "https://openweathermap.org/guide";

        String expectedResult2 = "OpenWeatherMap API guide - OpenWeatherMap";

        getDriver().get(url);
        Thread.sleep(10000);

        WebElement guideMenu = getDriver().findElement(By.linkText(guideMenuText));
        guideMenu.click();
        Thread.sleep(2000);

        String actualResult1 = getDriver().getCurrentUrl();
        String actualResult2 = getDriver().getTitle();

        Assert.assertEquals(actualResult1, expectedResult1);
        Assert.assertEquals(actualResult2, expectedResult2);
    }

    @Test
    public void testUnitsOfMeasurement_ImperialFMphConfirmFLondon() throws InterruptedException {
        String url = "https://openweathermap.org/";
        String fTempSymbol = "°F";
        String expectedResult = "°F";

        getDriver().get(url);
        getDriver().manage().window().maximize();
        Thread.sleep(10000);

        WebElement pushImperialFahrenheit = getDriver().findElement(By.xpath("//div[@class = 'switch-container']//div"
                + "[text()= 'Imperial: °F, mph']")
        );
        pushImperialFahrenheit.click();

        WebElement tempInF = getDriver().findElement(By.xpath("//div[@class='current-temp']/span")
        );
        String tempF = tempInF.getText();
        String actualResult = tempF.substring((tempF.length()-2));

        Assert.assertTrue(tempInF.getText().contains(fTempSymbol));
        Assert.assertEquals(actualResult, expectedResult);
    }
    @Test
    public void testCheckPresenceOfTreeSubMenusInTheHead () throws InterruptedException {
        String url = "https://openweathermap.org/";

        String expectedResult1 = "FAQ";
        String expectedResult2 = "How to start";
        String expectedResult3 = "Ask a question";

        getDriver().get(url);
        Thread.sleep(10000);

        WebElement searchSupport = getDriver().findElement(By.xpath("//div[@id = 'support-dropdown']")
        );
        searchSupport.click();
        Thread.sleep(2000);

        WebElement findSub1 = getDriver().findElement(By.xpath("//ul[@class ='dropdown-menu dropdown-visible']//"
                + "a[@href = '/faq']")
        );

        WebElement findSub2 = getDriver().findElement(By.xpath("//ul[@class ='dropdown-menu dropdown-visible']//"
                + "a[@href = '/appid']")
        );

        WebElement findSub3 = getDriver().findElement(By.xpath("//ul[@class ='dropdown-menu dropdown-visible']//"
                + "a[text() = 'Ask a question']")
        );

        Assert.assertEquals(getDriver().findElements(By.xpath("//ul[@id = 'support-dropdown-menu']/li")).size(), 3);

        String actualResult1 = findSub1.getText();
        String actualResult2 = findSub2.getText();
        String actualResult3 = findSub3.getText();

        Assert.assertEquals(actualResult1, expectedResult1);
        Assert.assertEquals(actualResult2, expectedResult2);
        Assert.assertEquals(actualResult3, expectedResult3);
    }
}
