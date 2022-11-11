import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;
import runner.BaseTest;

public class NataliadylaiTest extends BaseTest {

    @Test
    public void testH2TagText_WhenSearchCityCountry() throws InterruptedException {

        String url = "https://openweathermap.org/";
        String cityName = "Paris";
        String expectedResult = "Paris, FR";


        getDriver().get(url);
        Thread.sleep(10000);

        WebElement searchCityField =
                getDriver().findElement(
                        By.xpath("//div[@id = 'weather-widget']//input[@placeholder = 'Search city']"));

        searchCityField.click();
        searchCityField.sendKeys(cityName);

        WebElement searchButton = getDriver().findElement(By.xpath("//button[@type = 'submit']"));
        searchButton.click();
        Thread.sleep(2000);

        WebElement parisFRChoiceInDropdownMenu = getDriver().findElement(
                By.xpath("//ul[@class = 'search-dropdown-menu']/li/span[text() = 'Paris, FR ']")
        );
        parisFRChoiceInDropdownMenu.click();

        WebElement h2CityCountryHeader = getDriver().findElement(
                By.xpath("//div[@id = 'weather-widget']//h2")
        );
        Thread.sleep(2000);
        String actualResult = h2CityCountryHeader.getText();

        Assert.assertEquals(actualResult, expectedResult);
    }

    @Test
    public void test_OpenWeatherMapAPIGuide() throws InterruptedException {

        String url = "https://openweathermap.org/";
        String expectedResult1 = "https://openweathermap.org/guide";
        String expectedResult2 = "OpenWeatherMap API guide - OpenWeatherMap";
        getDriver().get(url);
        Thread.sleep(10000);

        WebElement guideHeader = getDriver().findElement(
                By.xpath("//div/ul/li/a[@href = '/guide']"));
        guideHeader.click();
        Thread.sleep(2000);
        String actualResult1 = getDriver().getCurrentUrl();
        Assert.assertEquals(actualResult1, expectedResult1);
        String actualResult2 = getDriver().getTitle();
        Assert.assertEquals(actualResult2, expectedResult2);
    }

    @Test
    public void testChangingTempUnitInHeading_WhenSwitchTempUnitButton() throws InterruptedException {
        String url = "https://openweathermap.org/";
        getDriver().get(url);
        Thread.sleep(10000);
        WebElement tempUnit = getDriver().findElement(
                By.xpath("//div[text()='Imperial: °F, mph']"));
        tempUnit.click();
        Thread.sleep(2000);
        WebElement tempUnitHeading = getDriver().findElement(
                By.xpath("//div[@class='current-temp']/span"));

        boolean actualResult = tempUnitHeading.getText().contains("°F");

        Assert.assertTrue(actualResult);
    }
    @Test
    public void test_ConfirmCookiesOnTheFooter() throws InterruptedException {
        String url = "https://openweathermap.org/";
        String expectedResult = "We use cookies which are essential for the site to work. We also use non-essential cookies to help us improve our services. Any data collected is anonymised. You can allow all cookies or manage them individually.";
        String button1 = "Allow";
        String button2 = "Manage cookies";
        getDriver().get(url);
        getDriver().manage().window().maximize();
        Thread.sleep(10000);
        Assert.assertTrue(getDriver().findElement(By.className("stick-footer-panel__container")).isDisplayed());
        Assert.assertEquals(getDriver().findElements(
                By.xpath("//div[@class = 'stick-footer-panel__btn-container']/*")).size(), 2);
        WebElement cookies = getDriver().findElement(
                By.className("stick-footer-panel__description"));
        Thread.sleep(2000);
        String actualResult = cookies.getText();
        Assert.assertEquals(actualResult,expectedResult);

        Assert.assertTrue(getDriver().findElement(
                By.xpath("//div[@class = 'stick-footer-panel__btn-container']/*[text() = 'Allow all']")).isDisplayed());
        Assert.assertTrue(getDriver().findElement(
                By.xpath("//div[@class = 'stick-footer-panel__btn-container']/*[text() = ' Manage cookies ']")).isDisplayed());
    }

    @Test
    public void test_checkSupportDropdownOnPAgeHeader() throws InterruptedException {
        String url = "https://openweathermap.org/";
        String expectedResult1 = "FAQ";
        String expectedResult2 = "How to start";
        String expectedResult3 = "Ask a question";

        getDriver().get(url);
        Thread.sleep(10000);

        WebElement support = getDriver().findElement(
                By.xpath("//div[@id = 'support-dropdown']"));
        support.click();
        Assert.assertEquals(getDriver().findElements(By.xpath("//ul[@id = 'support-dropdown-menu']/li")).size(), 3);
        WebElement faq = getDriver().findElement(
                By.xpath("//ul[@id = 'support-dropdown-menu']//a[@href ='/faq']")
        );
        String actualResult1 = faq.getText();
        Assert.assertEquals(actualResult1,expectedResult1);
        WebElement start = getDriver().findElement(
                By.xpath("//ul[@id ='support-dropdown-menu']//a[@href ='/appid']")
        );
        String actualResult2 = start.getText();
        Assert.assertEquals(actualResult2,expectedResult2);
        WebElement question = getDriver().findElement(
                By.xpath("//ul[@id ='support-dropdown-menu']//a[@href = 'https://home.openweathermap.org/questions']")
        );
        String actualResult3 = question.getText();
        Assert.assertEquals(actualResult3,expectedResult3);
    }

    @Test
    public void test_SwitchingMeasurementsBetweenFandC() throws InterruptedException {
        String url = "https://openweathermap.org/";
        boolean expectedResult = true;

        getDriver().get(url);
        getDriver().manage().window().maximize();
        Thread.sleep(10000);
        WebElement imperial = getDriver().findElement(
                By.xpath("//div[@class= 'option'][2]")
        );
        imperial.click();
        Thread.sleep(3000);
        WebElement metric = getDriver().findElement(
                By.xpath("//div[@class= 'option'][1]")
        );
        metric.click();
        Thread.sleep(3000);
        boolean actualResult = metric.getText().contains("°C");
        Assert.assertEquals(actualResult,expectedResult);
    }
    @Test
    public void test_CorrectPageUpdatedAfterClickOnLogo() throws InterruptedException {

        String url = "https://openweathermap.org/";
        String expectedResult = "https://openweathermap.org/";
        getDriver().get(url);
        getDriver().manage().window().maximize();
        Thread.sleep(10000);
        WebElement logo = getDriver().findElement(
                By.xpath("//a[@href]//img[@src = '/themes/openweathermap/assets/img/logo_white_cropped.png']"));
        logo.click();
        Thread.sleep(2000);
        String actualResult = getDriver().getCurrentUrl();
        Assert.assertEquals(actualResult,expectedResult);
        Thread.sleep(2000);
    }
}