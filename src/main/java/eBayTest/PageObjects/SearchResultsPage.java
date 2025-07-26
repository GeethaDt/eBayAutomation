package eBayTest.PageObjects;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import eBayTest.CommonComponents.CommonComponents;

public class SearchResultsPage extends CommonComponents {

	WebDriver driver;

	public SearchResultsPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "(//div[@class='su-card-container__content']/div[@class='su-card-container__header']) | ((//a[contains(@class, 's-item__link')])[position()>2])")
	List<WebElement> searchItems;

	public int getItemCount() {
		waitForWebElementToAppear(searchItems);
		int totalItems = searchItems.size();
		return totalItems;
	}

	public ItemListingPage clickItemByIndex(int index) {
		waitForWebElementToAppear(searchItems);
		searchItems.get(index).click();

		Set<String> handles = driver.getWindowHandles();
		Iterator<String> iterator = handles.iterator();
		String parentID = iterator.next();
		String childID = iterator.next();
		driver.switchTo().window(childID);
		ItemListingPage item = new ItemListingPage(driver);
		return item;
	}
}