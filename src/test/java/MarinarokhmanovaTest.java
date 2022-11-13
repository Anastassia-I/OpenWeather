import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
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
    public void testImperialMetric_FtoC() throws InterruptedException {
        String url = "https://openweathermap.org/";
        String expectedResult = "°C";

        getDriver().get(url);
        Thread.sleep(10000);

        WebElement panelImperial = getDriver().findElement(
                By.xpath("//div[@class ='option'][text()='Imperial: °F, mph']")
        );
        panelImperial.click();
        Thread.sleep(1000);

        WebElement panelMetric = getDriver().findElement(
                By.xpath("//div[@class ='option'][text()='Metric: °C, m/s']")
        );
        panelMetric.click();
        Thread.sleep(1000);

        WebElement panelTemperatureC = getDriver().findElement(
                By.xpath("//div[@class = 'controls']//div[@class ='option']")
        );

        boolean actualResult = panelTemperatureC.getText().contains("°C");

        Assert.assertTrue(actualResult, expectedResult);
    }
    @Test
    public void testLogoComp_OpenWeather() throws InterruptedException {
        String url = "https://openweathermap.org/";
        String expectedResult = "https://openweathermap.org/";

        getDriver().get(url);
        getDriver().manage().window().maximize();
        Thread.sleep(10000);

        WebElement logoCompOpenWeather = getDriver().findElement(
                By.xpath("//img[@src='/themes/openweathermap/assets/img/logo_white_cropped.png']"));
        logoCompOpenWeather.click();
        Thread.sleep(10000);

        String actualResult = getDriver().getCurrentUrl();

        Assert.assertEquals(actualResult, expectedResult);
    }
}
