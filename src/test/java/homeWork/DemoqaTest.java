package homeWork;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.io.File;
import java.util.concurrent.TimeUnit;


public class DemoqaTest {
    private WebDriver driver;
    private File file;
    private SoftAssertions softAssertions;

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
        file = new File("src/main/resources/pictureForSelenium.jfif");
        softAssertions = new SoftAssertions();
    }

    @Test
    public void typingInFieldsAndCheckResults() {
        WebElement firstNameInput = driver.findElement(By.id("firstName"));
        WebElement lastNameInput = driver.findElement(By.id("lastName"));
        WebElement emailInput = driver.findElement(By.id("userEmail"));
        WebElement genderRadiobutton = driver.findElement(By.cssSelector("input[name='gender'][value='Male']"));
        WebElement mobileNumberInput = driver.findElement(By.id("userNumber"));
        WebElement dateOfBirthInput = driver.findElement(By.id("dateOfBirthInput"));
        dateOfBirthInput.click();
        WebElement dateOfBirthMonth = driver.findElement(By.cssSelector(".react-datepicker__month-select [value='7']"));
        dateOfBirthMonth.click();
        WebElement dateOfBirthYear = driver.findElement(By.cssSelector(".react-datepicker__year-select [value='1989']"));
        dateOfBirthYear.click();
        WebElement dateOfBirthDay = driver.findElement(By.cssSelector("[aria-label*=\"Choose Thursday, August 10th, 1989\"]"));
        dateOfBirthDay.click();
        WebElement subjectsAutocomplete = driver.findElement(By.id("subjectsInput"));
        WebElement hobbiesCheckbox = driver.findElement(By.id("hobbies-checkbox-1"));
        WebElement selectPictureButton = driver.findElement(By.id("uploadPicture"));
        WebElement currentAddressTextarea = driver.findElement(By.cssSelector("#currentAddress[placeholder='Current Address']"));
        WebElement stateDropdown = driver.findElement(By.id("state"));
        WebElement cityDropdown = driver.findElement(By.id("city"));
        WebElement submit = driver.findElement(By.id("submit"));

        firstNameInput.sendKeys("Ivan");
        lastNameInput.sendKeys("Ivanov");
        emailInput.sendKeys("ivan.ivanov@mail.ru");
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", genderRadiobutton);
        mobileNumberInput.sendKeys("0123456789");
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", subjectsAutocomplete);
        subjectsAutocomplete.sendKeys("English");
        subjectsAutocomplete.sendKeys(Keys.ENTER);
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", hobbiesCheckbox);
        selectPictureButton.sendKeys(file.getAbsolutePath());

        currentAddressTextarea.sendKeys("14, Santhome High Road");
        stateDropdown.click();
        driver.switchTo().activeElement().sendKeys("NCR");
        driver.switchTo().activeElement().sendKeys(Keys.ENTER);
        cityDropdown.click();
        driver.switchTo().activeElement().sendKeys("Delhi");
        driver.switchTo().activeElement().sendKeys(Keys.ENTER);
        submit.click();

        softAssertions.assertThat(driver.findElement(By.id("example-modal-sizes-title-lg")).getText()).isEqualTo("Thanks for submitting the form");
        softAssertions.assertThat(driver.findElement(By.xpath("//td[text()=\"Ivan Ivanov\"]")).getText()).isEqualTo("Ivan Ivanov");
        softAssertions.assertThat(driver.findElement(By.xpath("//td[text()=\"ivan.ivanov@mail.ru\"]")).getText()).isEqualTo("ivan.ivanov@mail.ru");
        softAssertions.assertThat(driver.findElement(By.xpath("//td[text()=\"Male\"]")).getText()).isEqualTo("Male");
        softAssertions.assertThat(driver.findElement(By.xpath("//td[text()=\"0123456789\"]")).getText()).isEqualTo("0123456789");
        softAssertions.assertThat(driver.findElement(By.xpath("//td[text()=\"10 August,1989\"]")).getText()).isEqualTo("10 August,1989");
        softAssertions.assertThat(driver.findElement(By.xpath("//td[text()=\"English\"]")).getText()).isEqualTo("English");
        softAssertions.assertThat(driver.findElement(By.xpath("//td[text()=\"Sports\"]")).getText()).isEqualTo("Sports");
        softAssertions.assertThat(driver.findElement(By.xpath("//td[text()=\"pictureForSelenium.jfif\"]")).getText()).isEqualTo("pictureForSelenium.jfif");
        softAssertions.assertThat(driver.findElement(By.xpath("//td[text()=\"14, Santhome High Road\"]")).getText()).isEqualTo("14, Santhome High Road");
        softAssertions.assertThat(driver.findElement(By.xpath("//td[text()=\"NCR Delhi\"]")).getText()).isEqualTo("NCR Delhi");
        softAssertions.assertAll();
    }

    @AfterEach
    public void tearDown() {
        driver.quit();
    }
}
