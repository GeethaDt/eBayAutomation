package eBayTest.Tests;

import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentTest;

import eBayTest.PageObjects.ItemListingPage;
import eBayTest.PageObjects.OrdersPage;
import eBayTest.PageObjects.SearchResultsPage;
import eBayTest.TestComponents.BaseTest;
import eBayTest.TestComponents.Listener;
import eBayTest.TestComponents.RetryAnalyzer;

public class AddItemToCartTest extends BaseTest {

	Logger logger = Logger.getLogger(AddItemToCartTest.class);

	@Test(retryAnalyzer=RetryAnalyzer.class)
	public void checkCartItemCount() {
		ExtentTest test = Listener.extentTest.get();
		logger.info("Test Started: verifyAddToCartFunctionality");
		logger.info("Navigating to eBay home page");
		SearchResultsPage results = home.searchItem("book");
		test.info("Searched for book");

		String parentWindow = driver.getWindowHandle();
		int totalItems = results.getItemCount();
		boolean itemAdded = false;
		ItemListingPage item = null;

		for (int i = 0; i < totalItems; i++) {
			item = results.clickItemByIndex(i);
			if (item.addItemToCart()) {
				itemAdded = true;
				test.info("Item added from index: " + i);
				logger.info("Item added from index: " + i);
				break;
			} else {

				driver.close();
				driver.switchTo().window(parentWindow);
			}
		}
		Assert.assertTrue(itemAdded, "Failed to add any item to cart");

		OrdersPage order = item.closeSeeItemInCart();
		test.info("Checking Cart Badge");
		logger.info("Checking Cart Badge");
		int count = order.getCartItemCount();
		test.info("Cart item count is: " + count);
		logger.info("Cart item count is: " + count);

		 /**
	     * Note:
	     * Although the requirement screenshot shows '2' in the cart icon,
	     * this test adds only one item as per the written steps.
	     * Hence, cart is expected to show '1' — and our assertion is based on that.
	     */
		
		boolean checkCount = (count == 1);
		Assert.assertTrue(checkCount, "Cart should show 1 item after adding one product, but found: " + count);

		test.pass("Cart shows 1 item as expected");
		logger.info("Cart shows 1 item as expected");
	}
}
