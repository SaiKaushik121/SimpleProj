import java.util.*;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class DragDrop {

	public static void main(String[] args) {

		System.setProperty("webdriver.chrome.driver", "chromedriver\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();

		driver.get("https://jqueryui.com/droppable/");
		driver.switchTo().frame(0);

		WebElement drag = driver.findElement(By.xpath("//*[@id=\"draggable\"]"));
		WebElement drop = driver.findElement(By.xpath("//*[@id=\"droppable\"]"));

		Actions action = new Actions(driver);
		action.dragAndDrop(drag, drop).perform();
	}

}
