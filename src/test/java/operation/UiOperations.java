package operation;

import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class UiOperations {

	WebDriver driver;

	public static String customerCreationId=null;

	public static String customerCreationMessage=null;

	public static String accountCreationMessage=null;

	public static String accountCreationId=null;

	public UiOperations(WebDriver driver) {

		this.driver=driver;
	}

	public void actions(Properties p, String Key, String obj, String objType, String val ) throws Exception {

		switch (Key.toUpperCase()) {

		case "NAVIGATEURL" :
			driver.get(p.getProperty(val));
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
			break;

		case "MAXIMIZE" :
			driver.manage().window().maximize();
			break;

		case "CLICK":
			driver.findElement(this.getObject(p,obj,objType)).click();
			break;

		case "SETTEXT":
			driver.findElement(this.getObject(p, obj, objType)).sendKeys(val);
			break;

		case "GETCUSTOMERCREATIONMESSAGE":
			customerCreationMessage=driver.findElement(this.getObject(p, obj, objType)).getText();
			Assert.assertEquals(customerCreationMessage, val);
			break;

		case "GETACCOUNTCREATIONMESSAGE":
			accountCreationMessage=driver.findElement(this.getObject(p, obj, objType)).getText();
			Assert.assertEquals(accountCreationMessage, val);
			break;

		case "GETCUSTOMERID":
			customerCreationId=driver.findElement(this.getObject(p, obj, objType)).getText();
			System.out.println("customer id is "+customerCreationId);
			break;

		case "GETACCOUNTID":
			accountCreationId=driver.findElement(this.getObject(p, obj, objType)).getText();
			System.out.println("account id is "+accountCreationId);
			break;

		case "SETCUSTOMERID":
			driver.findElement(this.getObject(p, obj, objType)).sendKeys(customerCreationId);
			break;

		case "SETACCOUNTID":
			driver.findElement(this.getObject(p, obj, objType)).sendKeys(accountCreationId);
			break;

		case "BACK":
			driver.navigate().back();
			break;

		case "ALERT":
			driver.switchTo().alert().accept();
			break;

		case "CLOSE":
			driver.quit();
			break;
		}
	}

	private By getObject(Properties p, String obj, String objType) throws Exception {

		if(objType.equalsIgnoreCase("xpath")) {

			return By.xpath(p.getProperty(obj));
		}
		else if(objType.equalsIgnoreCase("css")) {

			return By.cssSelector(p.getProperty(obj));
		}
		else if(objType.equalsIgnoreCase("id")) {

			return By.id(p.getProperty(obj));
		}
		else if(objType.equalsIgnoreCase("name")) {

			return By.name(p.getProperty(obj));
		}
		else if(objType.equalsIgnoreCase("className")) {

			return By.className(p.getProperty(obj));
		}
		else if(objType.equalsIgnoreCase("link")) {

			return By.linkText(p.getProperty(obj));
		}
		else if(objType.equalsIgnoreCase("partialLink")) {

			return By.partialLinkText(p.getProperty(obj));
		}
		else {

			throw new Exception("Invalid Object Type!!!");
		}
	}
}
