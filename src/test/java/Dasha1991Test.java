import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;
import runner.BaseTest;

public class Dasha1991Test extends BaseTest {
    @Test
    public void testButtonGuide_OpenWeather() throws InterruptedException {

        String url = "https://openweathermap.org/";
        String expectedResult1 = "https://openweathermap.org/guide";
        String expectedResult2 = "OpenWeatherMap API guide - OpenWeatherMap";

        getDriver().get(url);
        Thread.sleep(5000);

        WebElement pushButtonGuide = getDriver().findElement(By.xpath("//a[@href = '/guide']"));

        pushButtonGuide.click();
        Thread.sleep(2000);

        String actualResult1 = getDriver().getCurrentUrl();
        Assert.assertEquals(actualResult1, expectedResult1);


        String actualResult2 = getDriver().getTitle();
        Assert.assertEquals(actualResult2, expectedResult2);

    }
    @Test
    public void testTemperatureF() throws InterruptedException {

        String url = "https://openweathermap.org/";

        getDriver().get(url);
        getDriver().manage().window().maximize();
        Thread.sleep(5000);

        WebElement temperatureF = getDriver().findElement(By.xpath("//div[@class = 'option'][2]"));

        temperatureF.click();
        Thread.sleep(2000);

        WebElement imageFTemperature = getDriver().findElement(By.xpath("//span[@class = 'heading']"));

        boolean actualResult = imageFTemperature.getText().contains("°F");
        Assert.assertTrue(actualResult);

    }
    @Test
    public void testMenuSupport() throws InterruptedException {

        String url = "https://openweathermap.org/";
        String expectedResult1 = "FAQ";
        String expectedResult2 = "How to start";
        String expectedResult3 = "Ask a question";

        getDriver().get(url);
        getDriver().manage().window().maximize();
        Thread.sleep(5000);

        WebElement findSupportButton = getDriver().findElement(By.xpath("//div[@id='support-dropdown']"));

        findSupportButton.click();
        Thread.sleep(2000);

        WebElement buttonFAQ = getDriver().findElement(By.xpath
                ("//ul[@class = 'dropdown-menu dropdown-visible']//li/a[@href = '/faq']"));
        WebElement buttonHowToStart = getDriver().findElement(By.xpath
                ("//ul[@class = 'dropdown-menu dropdown-visible']/li[2]//a"));
        WebElement buttonAskAQuestion = getDriver().findElement(By.xpath
                ("//ul[@class = 'dropdown-menu dropdown-visible']//li[3]/a"));
        Thread.sleep(2000);
        String actualResult1 = buttonFAQ.getText();
        Assert.assertEquals(actualResult1, expectedResult1);

        String actualResult2 = buttonHowToStart.getText();
        Assert.assertEquals(actualResult2, expectedResult2);

        String actualResult3 = buttonAskAQuestion.getText();
        Assert.assertEquals(actualResult3, expectedResult3);
    }
}
