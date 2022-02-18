package homeWork;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.concurrent.TimeUnit;

public class DemoqaTest {
    private WebDriver driver;

    @BeforeEach
    public void setUp() {
        String browser = System.getProperty("browser");
        if (browser.equals("chrome")) {
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();
        } else if (browser.equals("firefox")) {
            WebDriverManager.firefoxdriver().setup();
            driver = new FirefoxDriver();
        } else if (browser.equals("edge")) {
            WebDriverManager.edgedriver().setup();
            driver = new EdgeDriver();
        }
        driver.get("https://demoqa.com/automation-practice-form");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @Test
    public void typingInFieldsAndCheckResults() {
        WebElement fieldFirstName = driver.findElement(By.id("firstName"));
        WebElement fieldLastName = driver.findElement(By.id("lastName"));
        WebElement fieldEmail = driver.findElement(By.id("userEmail"));
        WebElement radiobuttonGender = driver.findElement(By.cssSelector("input[name='gender'][value='Male']"));
        WebElement fieldMobileNumber = driver.findElement(By.id("userNumber"));
        WebElement fieldDateOfBirth = driver.findElement(By.id("dateOfBirthInput"));
        fieldDateOfBirth.click();
        WebElement dateOfBirthMonth = driver.findElement(By.cssSelector(".react-datepicker__month-select [value='7']"));
        dateOfBirthMonth.click();
        WebElement dateOfBirthYear = driver.findElement(By.cssSelector(".react-datepicker__year-select [value='1989']"));
        dateOfBirthYear.click();
        WebElement dateOfBirthDay = driver.findElement(By.cssSelector("[aria-label*=\"Choose Thursday, August 10th, 1989\"]"));
        dateOfBirthDay.click();
        WebElement autocompleteSubjects = driver.findElement(By.id("subjectsInput"));
        WebElement hobbiesCheckbox = driver.findElement(By.id("hobbies-checkbox-1"));
        WebElement selectPictureButton = driver.findElement(By.id("uploadPicture"));
        WebElement textareaCurrentAddress = driver.findElement(By.cssSelector("#currentAddress[placeholder='Current Address']"));
        WebElement dropdownState = driver.findElement(By.id("state"));
        WebElement dropdownCity = driver.findElement(By.id("city"));
        WebElement submit = driver.findElement(By.id("submit"));

        fieldFirstName.sendKeys("Ivan");
        fieldLastName.sendKeys("Ivanov");
        fieldEmail.sendKeys("ivan.ivanov@mail.ru");
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", radiobuttonGender);
        fieldMobileNumber.sendKeys("0123456789");
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", autocompleteSubjects);
        autocompleteSubjects.sendKeys("English");
        autocompleteSubjects.sendKeys(Keys.ENTER);
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", hobbiesCheckbox);
        selectPictureButton.sendKeys("C:\\pictureForSelenium.jfif");
        textareaCurrentAddress.sendKeys("14, Santhome High Road");
        dropdownState.click();
        driver.switchTo().activeElement().sendKeys("NCR");
        driver.switchTo().activeElement().sendKeys(Keys.ENTER);
        dropdownCity.click();
        driver.switchTo().activeElement().sendKeys("Delhi");
        driver.switchTo().activeElement().sendKeys(Keys.ENTER);
        submit.click();


        Assertions.assertEquals("Thanks for submitting the form", driver.findElement(By.id("example-modal-sizes-title-lg")).getText());
        Assertions.assertEquals("Ivan Ivanov", driver.findElement(By.xpath("//tr[1]/td[2]")).getText());
        Assertions.assertEquals("ivan.ivanov@mail.ru", driver.findElement(By.xpath("//tr[2]/td[2]")).getText());
        Assertions.assertEquals("Male", driver.findElement(By.xpath("//tr[3]/td[2]")).getText());
        Assertions.assertEquals("0123456789", driver.findElement(By.xpath("//tr[4]/td[2]")).getText());
        Assertions.assertEquals("10 August,1989", driver.findElement(By.xpath("//tr[5]/td[2]")).getText());
        Assertions.assertEquals("English", driver.findElement(By.xpath("//tr[6]/td[2]")).getText());
        Assertions.assertEquals("Sports", driver.findElement(By.xpath("//tr[7]/td[2]")).getText());
        Assertions.assertEquals("pictureForSelenium.jfif", driver.findElement(By.xpath("//tr[8]/td[2]")).getText());
        Assertions.assertEquals("14, Santhome High Road", driver.findElement(By.xpath("//tr[9]/td[2]")).getText());
        Assertions.assertEquals("NCR Delhi", driver.findElement(By.xpath("//tr[10]/td[2]")).getText());
    }

    @AfterEach
    public void tearDown() {
        driver.quit();
    }
}
