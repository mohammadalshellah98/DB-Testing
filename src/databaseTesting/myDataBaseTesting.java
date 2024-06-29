package databaseTesting;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class myDataBaseTesting {
	Connection con;
	java.sql.Statement stmt;
	ResultSet rs;
	String URL = "https://magento.softwaretestingboard.com/customer/account/create/";
	WebDriver driver = new ChromeDriver();

	@BeforeTest
	public void setUP() throws SQLException {
		con = DriverManager.getConnection("jdbc:mysql://localhost:3306/classicmodels", "root", "1998");

	}
	
	
	@Test
	public void signUP() throws SQLException {
		stmt = con.createStatement();
		rs = stmt.executeQuery("select * from employees where employeeNumber = 1056");

		String firstName = null;
		String lastName = null;
		String email = null;
		String extension = null ;

		

		while (rs.next()) {

			firstName = rs.getString("firstname");
			lastName = rs.getString("lastname");
			email = rs.getString("Email");
			extension = rs.getString("extension");


		}

		driver.get(URL);
		

		driver.findElement(By.id("firstname")).sendKeys(firstName);
		driver.findElement(By.id("lastname")).sendKeys(lastName);
		driver.findElement(By.id("email_address")).sendKeys(email);
		driver.findElement(By.id("password")).sendKeys(extension+"Afgfd");
		driver.findElement(By.id("password-confirmation")).sendKeys(extension+"Afgfd");
		driver.findElement(By.xpath("//button[@title='Create an Account']")).click();

		
	}
	
}
