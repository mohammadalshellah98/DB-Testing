package databaseTesting;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class myTestCases {

	Connection con;
	java.sql.Statement stmt;
	ResultSet rs;
	String URL = "https://magento.softwaretestingboard.com/customer/account/create/";
	WebDriver driver = new ChromeDriver();

	@BeforeTest
	public void setUP() throws SQLException {
		con = DriverManager.getConnection("jdbc:mysql://localhost:3306/classicmodels", "root", "1998");

	}

	@Test(priority = 3)
	public void getData() throws SQLException {
		stmt = con.createStatement();
		rs = stmt.executeQuery("select * from customers where customernumber = 1998");

		int customerNumber;
		String customerName = null;

		while (rs.next()) {

			customerNumber = rs.getInt("customerNumber");
			customerName = rs.getString("customerName");
		}

		driver.get(URL);

		driver.findElement(By.id("firstname")).sendKeys(customerName);
	}

	@Test(priority = 1)
	public void addData() throws SQLException {
		String Query = "INSERT INTO customers (customerNumber, customerName, contactLastName, contactFirstName, phone, addressLine1, city,country )"
				+ "VALUES (1998, 'ABC', 'Mohammad', 'Alshellah', '123-456-7890', 'Amman', 'Amman' ,'jordan')";
		;
		stmt = con.createStatement();

		int insertedRow = stmt.executeUpdate(Query);
		Assert.assertEquals(insertedRow>0, true);

	}

	@Test(priority = 2)
	public void updateDta() throws SQLException {
		String Query = "update customers set customerName = 'automation Group' where customerNumber = 1998";

		stmt = con.createStatement();

		int insertedRow = stmt.executeUpdate(Query);
		Assert.assertEquals(insertedRow>0, true);

	}
	
	@Test (priority = 4)
	public void deleteData () throws SQLException{
		String Qyery = "delete from customers where customerNumber = '1998'";
		
		stmt = con.createStatement();

		int insertedRow = stmt.executeUpdate(Qyery);
		Assert.assertEquals(insertedRow>0, true);
	}

}
