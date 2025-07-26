package eBayTest.PageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import eBayTest.CommonComponents.CommonComponents;

public class HomePage extends CommonComponents {

	WebDriver driver;

	public HomePage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(css = ".ui-autocomplete-input")
	WebElement searchBox;
	@FindBy(css = ".gh-search-button__label")
	WebElement searchIcon;

	public void goTo() {
		driver.get("https://www.ebay.com/");
	}

	public SearchResultsPage searchItem(String book) {
		searchBox.sendKeys(book);
		searchIcon.click();
		SearchResultsPage results = new SearchResultsPage(driver);
		return results;
	}
}
