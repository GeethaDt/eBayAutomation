package eBayTest.PageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import eBayTest.CommonComponents.CommonComponents;

public class ItemListingPage extends CommonComponents {

	WebDriver driver;

	public ItemListingPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//li//span[@class='ux-call-to-action__text' and contains(text(),'cart')]")
	WebElement addToCart;

	@FindBy(xpath = "//div[@class='lightbox-dialog__header']/button[@class='icon-btn lightbox-dialog__close' and @aria-label='Close overlay']")
	WebElement closeSeeInCart;

	public boolean addItemToCart() {
		try {
			waitForWebElementToBeClickable(addToCart, 5);
			addToCart.click();
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public OrdersPage closeSeeItemInCart() {
		waitForWebElementToAppear(closeSeeInCart);
		closeSeeInCart.click();
		OrdersPage order = new OrdersPage(driver);
		return order;
	}
}
