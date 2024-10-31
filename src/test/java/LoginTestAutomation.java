import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.Assert.assertTrue;


public class LoginTestAutomation {
    private WebDriver driver;
    private WebDriverWait wait;

    @Before
    public void CreateConnection() {
        System.setProperty("webdriver.chrome.driver", "C:\\chromedriver_win32\\chromedriver.exe");
        driver = new ChromeDriver();

        driver.get("http://localhost:3000/login");
        wait = new WebDriverWait(driver, Duration.ofSeconds(20));

    }

    @Test
    public void testLoginSuccess() throws InterruptedException{
        WebElement userName = driver.findElement(By.id("username"));
        userName.sendKeys("Kunal");

        WebElement password = driver.findElement(By.id("password"));
        password.sendKeys("Qwerty@12");

        WebElement login = driver.findElement(By.id("login"));
        login.submit();

        String currentUrl = driver.getCurrentUrl();
        assertTrue(currentUrl.contains("dashboard"));
    }

    @Test
    public void testLoginFailure() {
        driver.findElement(By.id("username")).sendKeys("wrongUser");
        driver.findElement(By.id("password")).sendKeys("wrongPass");
        driver.findElement(By.id("login")).submit();

        String currentUrl = driver.getCurrentUrl();
        assertTrue(currentUrl.contains("register"));
    }

    @After
    public void tearDown() {
        driver.quit(); // Closes the browser after each test
    }
}
