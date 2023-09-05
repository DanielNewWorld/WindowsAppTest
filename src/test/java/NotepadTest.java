import io.appium.java_client.windows.WindowsDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import org.testng.annotations.*;
import java.net.URL;
import java.time.LocalDate;
import java.util.concurrent.TimeUnit;

public class NotepadTest {
    private static WindowsDriver notepadSession = null;
    public static String getDate(){
        LocalDate date = LocalDate.now();
        return date.toString();
    }
    @BeforeClass
    public static void setUp() {
        try {
            DesiredCapabilities capabilities = new DesiredCapabilities();
            capabilities.setCapability("app", "C:\\Windows\\System32\\notepad.exe");
            capabilities.setCapability("platformName","Windows");
            capabilities.setCapability("deviceName", "WindowsPC");
            notepadSession = new WindowsDriver(new URL("http://127.0.0.1:4723"), capabilities);
            notepadSession.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @AfterMethod
    public void cleanApp(){
        notepadSession.quit();
        setUp();
    }
    @AfterSuite
    public void tearDown(){
        notepadSession.quit();
    }
    @Test
    public void checkAboutWindow() {
        notepadSession.findElementByName("Довідка").click();
        notepadSession.findElementByName("Про програму").click();
        notepadSession.findElementByName("ОК").click();
    }
    @Test
    public void sendTestText(){
        notepadSession.findElementByClassName("Редагування").sendKeys(getDate());
        notepadSession.findElementByClassName("Редагування").clear();
    }
    @Test()
    public void pressTimeAndDateButton(){
        notepadSession.findElementByName("Редагування").click();
        notepadSession.findElementByAccessibilityId("26").click();
        Assert.assertNotNull(notepadSession.findElementByClassName("Редагування"));
        notepadSession.findElementByClassName("Редагування").clear();
    }
}