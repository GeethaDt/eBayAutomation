package eBayTest.PageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import eBayTest.CommonComponents.CommonComponents;

public class OrdersPage extends CommonComponents {

	WebDriver driver;

	public OrdersPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//span[@class='badge gh-badge']")
	WebElement cartProduct;

	public int getCartItemCount() {
		waitForWebElementToAppear(cartProduct);
		String countText = cartProduct.getText().trim();
		return Integer.parseInt(countText);
	}
}
