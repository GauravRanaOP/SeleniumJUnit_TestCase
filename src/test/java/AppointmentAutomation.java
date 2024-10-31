import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static org.junit.Assert.assertTrue;


public class AppointmentAutomation {
    private WebDriver driver;
    private WebDriverWait wait;

    @Before
    public void CreateConnection() {
        System.setProperty("webdriver.chrome.driver", "C:\\chromedriver_win32\\chromedriver.exe");
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(5));

        driver.get("http://localhost:3000/login");

        // Login process
        driver.findElement(By.id("username")).sendKeys("Gaurav");
        driver.findElement(By.id("password")).sendKeys("Qwerty@12");
        driver.findElement(By.id("login")).submit();
    }

    @Test
    public void testAppointmentBooking() {
        driver.get("http://localhost:3000/examiner");
        driver.findElement(By.id("appointment-nav")).click();

        // Check if header is displayed correctly
        WebElement header = driver.findElement(By.cssSelector(".page-heading h1"));
        assertTrue(header.isDisplayed());
        assertTrue(header.getText().contains("Appointment"));

        // Set appointment date to tomorrow
        LocalDate tomorrow = LocalDate.now().plusDays(1);
        String formattedTomorrowDate = tomorrow.format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));

        // Find the date input field and set tomorrow's date
        WebElement dateInput = driver.findElement(By.id("aptdate"));
        dateInput.sendKeys(formattedTomorrowDate);

        // Select a time from the dropdown
        Select timeDropdown = new Select(driver.findElement(By.id("apttime")));
        timeDropdown.selectByValue("09:30");

        // Submit the form
        WebElement submitButton = driver.findElement(By.id("submitButton"));
        submitButton.submit();

        String currentUrl = driver.getCurrentUrl();
        assertTrue(currentUrl.contains("dashboard"));
    }

    @After
    public void tearDown() {
        driver.quit();
    }
}
