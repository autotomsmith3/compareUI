package AdminPortal;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class AdminPortalControllerData {
	public WebDriver driver;
	public int wt_Secs;
	public By dealershipNameField;
	public By webSite;
	public By tagLineMarkingMsg;
	public By dealershipEmail;
	public By dealershipPhoneNumber;
	public By accountEmail;
	public By firstName;
	public By lastName;
	public By receiveDailyInventoryEmailCheckBox;
	public By address1;
	public By address2;
	public By city;
	public By country;
	public By stateProvince;
	public By zipPostalCode;
	public By passwordLocator;
	public By confirmPasswordLocator;
	public By saveLocator;
	public By dealershipLogo;
	public By upLoadNewPicture;
	public By removeLogo;
	public By VINpxCheckBox;
	public By LOTpxCheckBox;
	public By STOCKpxCheckBox;
	public By backGround7;

	public AdminPortalControllerData(int wt_Secs, By dealershipNameField, By webSite, By tagLineMarkingMsg,
			By dealershipEmail, By dealershipPhoneNumber, By accountEmail, By firstName, By lastName,
			By receiveDailyInventoryEmailCheckBox, By address1, By address2, By city, By country, By stateProvince,
			By zipPostalCode, By passwordLocator, By confirmPasswordLocator, By saveLocator, By dealershipLogo,
			By upLoadNewPicture, By removeLogo, By vINpxCheckBox, By lOTpxCheckBox, By sTOCKpxCheckBox,
			By backGround7) {
		this.wt_Secs = wt_Secs;
		this.dealershipNameField = dealershipNameField;
		this.webSite = webSite;
		this.tagLineMarkingMsg = tagLineMarkingMsg;
		this.dealershipEmail = dealershipEmail;
		this.dealershipPhoneNumber = dealershipPhoneNumber;
		this.accountEmail = accountEmail;
		this.firstName = firstName;
		this.lastName = lastName;
		this.receiveDailyInventoryEmailCheckBox = receiveDailyInventoryEmailCheckBox;
		this.address1 = address1;
		this.address2 = address2;
		this.city = city;
		this.country = country;
		this.stateProvince = stateProvince;
		this.zipPostalCode = zipPostalCode;
		this.passwordLocator = passwordLocator;
		this.confirmPasswordLocator = confirmPasswordLocator;
		this.saveLocator = saveLocator;
		this.dealershipLogo = dealershipLogo;
		this.upLoadNewPicture = upLoadNewPicture;
		this.removeLogo = removeLogo;
		VINpxCheckBox = vINpxCheckBox;
		LOTpxCheckBox = lOTpxCheckBox;
		STOCKpxCheckBox = sTOCKpxCheckBox;
		this.backGround7 = backGround7;
	}
}