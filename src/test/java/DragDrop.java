import java.util.*;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;



public class DragDrop
{
	WebDriver driver =null;
	@Test
	@BeforeMethod
	public void test1()
	{
		System.setProperty("webdriver.chrome.driver", "chromedriver\\chromedriver.exe");
		 driver = new ChromeDriver();

		driver.get("https://jqueryui.com/droppable/");
		driver.switchTo().frame(0);

		WebElement drag = driver.findElement(By.xpath("//*[@id=\"draggable\"]"));
		WebElement drop = driver.findElement(By.xpath("//*[@id=\"droppable\"]"));

		Actions action = new Actions(driver);
		action.dragAndDrop(drag, drop).perform();
	}
	@AfterTest
	public void mailsent()
	{
		String filepath = "C:/Users/ssaikaus/Documents/workspace-spring-tool-suite-4-4.15.1.RELEASE/SimpleProj/test-output/emailable-report.html";
		
			// Create object of Property file
					Properties props = new Properties();

					// this will set host of server- you can change based on your requirement 
					props.put("mail.smtp.host", "smtp.gmail.com");

					// set the port of socket factory 
					props.put("mail.smtp.socketFactory.port", "465");

					// set socket factory
					props.put("mail.smtp.socketFactory.class","javax.net.ssl.SSLSocketFactory");

					// set the authentication to true
					props.put("mail.smtp.auth", "true");

					// set the port of SMTP server
					props.put("mail.smtp.port", "465");

					// This will handle the complete authentication
					Session session = Session.getDefaultInstance(props,

							new javax.mail.Authenticator() {

								protected PasswordAuthentication getPasswordAuthentication() {

								return new PasswordAuthentication("sair37206@gmail.com","mkvoytrzaawfzelu");

								}

							});

					try {

						// Create object of MimeMessage class
						Message message = new MimeMessage(session);

						// Set the from address
						message.setFrom(new InternetAddress("sair37206@gmail.com"));

						// Set the recipient address
						// here using cc for adding multiple people
						
						message.setRecipients(Message.RecipientType.TO,InternetAddress.parse("sridharakaushik666@gmail.com"));
						message.setRecipients(Message.RecipientType.CC,InternetAddress.parse("manishajenkins@gmail.com"));
						
			  
			            // Add the subject link
						message.setSubject("Latest Test Report");

						// Create object to add multimedia type content
						BodyPart messageBodyPart1 = new MimeBodyPart();

						// Set the body of email
						messageBodyPart1.setText("Hello Guys attached copy of latest test report");

						// Create another object to add another content
						MimeBodyPart messageBodyPart2 = new MimeBodyPart();

						// Mention the file which you want to send
						String filename = filepath;
						// Create data source and pass the filename
						DataSource source = new FileDataSource(filename);

						// set the handler
						messageBodyPart2.setDataHandler(new DataHandler(source));

						// set the file
						messageBodyPart2.setFileName(filename);

						// Create object of MimeMultipart class
						Multipart multipart = new MimeMultipart();

						// add body part 1
						multipart.addBodyPart(messageBodyPart2);

						// add body part 2
						multipart.addBodyPart(messageBodyPart1);

						// set the content
						message.setContent(multipart);

						// finally send the email
						Transport.send(message);

						System.out.println("=====Email Sent=====");

					} catch (MessagingException e) {

						throw new RuntimeException(e);

					}
		}
}
