import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;


import java.time.Duration;

import static org.junit.Assert.assertTrue;

public class ExaminerReviewAutomation {
    private WebDriver driver;
    private WebDriverWait wait;

    @Before
    public void CreateConnection() {
        System.setProperty("webdriver.chrome.driver", "C:\\chromedriver_win32\\chromedriver.exe");
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(5));

        driver.get("http://localhost:3000/login");

        // Login process
        driver.findElement(By.id("username")).sendKeys("Rahul");
        driver.findElement(By.id("password")).sendKeys("Qwerty@12");
        driver.findElement(By.id("login")).submit();
    }

    @Test
    public void testExaminerReview() {
        driver.get("http://localhost:3000/examiner");
        driver.findElement(By.id("examiner-nav")).click();
        WebElement viewButton = wait.until(ExpectedConditions.elementToBeClickable(By.id("view")));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", viewButton);

        try {
            viewButton.click(); // Attempt to click the button
        } catch (ElementClickInterceptedException e) {
            // If there's still an issue, use JavaScript to click the element
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", viewButton);
        }
        WebElement comments = driver.findElement(By.id("comments"));
        comments.clear();
        comments.sendKeys("Passed The Exam");

        Select resultDropdown = new Select(driver.findElement(By.id("result")));
        resultDropdown.selectByValue("true"); // Select "Pass" by value

        driver.findElement(By.id("submitResult")).submit();

        String currentUrl = driver.getCurrentUrl();
        assertTrue(currentUrl.contains("examiner"));
    }

    @After
    public void tearDown() {
        driver.quit();
    }
}
