import org.openqa.selenium.*;
import org.junit.*;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.LoadableComponent;

import junit.framework.Assert;

public class Chapter2 {
	WebDriver selenium;
	public Chapter2(WebDriver selenium){
		this.selenium=selenium;
		if (!"Chapter 2".equalsIgnoreCase(this.selenium.getTitle())){
			selenium.get("http://book.theautomatedtester.co.uk/chapter2");
		}
	}
	public boolean isButtonPresent(String button){
		selenium.findElements(By.xpath("//input[@id='" + button + "']")).size()>0;
	}
}
