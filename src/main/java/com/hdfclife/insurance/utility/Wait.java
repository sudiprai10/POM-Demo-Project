package com.hdfclife.insurance.utility;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.google.common.base.Function;

public class Wait {
    private WebDriver driver;
    private Configuration config = Configuration.getInstance();
    private int timeout = Integer.parseInt(config.get("selenium.explicitWaitSeconds"));

    public Wait(WebDriver driver) {
        this.driver = driver;
    }

    /**
     * Waits until the given element is visible
     *
     * @param element Element which needs to be checked for visibility
     */
    public void untilElementIsVisible(WebElement element) {
        new WebDriverWait(driver, timeout).until(ExpectedConditions.visibilityOf(element));
    }

    /**
     * Waits until the given element is present on the DOM
     *
     * @param elementLocator Reference to the element
     */
    public void untilPresenceOfElementLocated(By elementLocator) {
        new WebDriverWait(driver, timeout).until(ExpectedConditions.presenceOfElementLocated(elementLocator));
    }

    /**
     * Waits until the given element becomes invisible on page.
     *
     * @param elementLocator Reference to the element
     */
    public void untilElementBecomesInvisible(By elementLocator) {
        new WebDriverWait(driver, timeout).until(ExpectedConditions.invisibilityOfElementLocated(elementLocator));
    }

    /**
     * Switches to a frame as soon as it becomes available
     *
     * @param frameName Name or Id of the frame to switch to
     */
    public void untilFrameIsAvailableAndSwitchToIt(String frameName) {
        WebDriverWait wait = new WebDriverWait(driver, timeout);
        wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frameName));
    }

    /**
     * Ensures that an element can be clicked. It is recommended to use before calling click() when race conditions are likely to
     * happen
     *
     * @param element Element that must be clickable
     */
    public void untilElementIsClickable(WebElement element) {
        WebDriverWait wait = new WebDriverWait(driver, timeout);
        wait.until(ExpectedConditions.visibilityOf(element));
        wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    /**
     * Ensures that clicking on an element results in a second element being displayed.
     * Use this method when the element is dynamically created or the WebElement object is not available yet.
     * Do not use when the click is going to trigger navigation to a different page.
     *
     * @param clickOnThis Element to click
     * @param selector Selector that identifies the element being displayed
     */
    public void untilClickDisplaysElement(final WebElement clickOnThis, final By selector) throws Exception {
        WebDriverWait wait = new WebDriverWait(driver, timeout);
        WebDriverFactory.getInstance().disableImplicitWaits(driver);
        try {
            wait.until(new Function<WebDriver, Boolean>() {
                public Boolean apply(WebDriver driver) {
                    try {
                        clickOnThis.click();
                        WebElement element = driver.findElement(selector);
                        return element.isDisplayed();
                    } catch (Exception e) {
                        return false;
                    }
                }
            });
        } catch (Exception e) {
            throw e;
        } finally {
            WebDriverFactory.getInstance().enableImplicitWaits(driver);
        }
    }

    /**
     * Ensures that clicking on an element will display another element.
     * Use ONLY on dynamic pages (not when the click will load a new page).
     *
     * @param clickOnThis Element to click
     * @param displaysThis Element that will be displayed
     */
    public void untilClickDisplaysElement(final WebElement clickOnThis, final WebElement displaysThis) {
        WebDriverWait wait = new WebDriverWait(driver, timeout);
        wait.until(new Function<WebDriver, Boolean>() {
            public Boolean apply(WebDriver driver) {
                if (!displaysThis.isDisplayed()) {
                    clickOnThis.click();
                }
                return displaysThis.isDisplayed();
            }
        });
    }

    /**
     * Ensures that clicking on an element will hide another element.
     * Use ONLY on dynamic pages (not when the click will load a new page).
     *
     * @param clickOnThis
     * @param hidesThis
     */
    public void untilClickHidesElement(final WebElement clickOnThis, final WebElement hidesThis) {
        WebDriverWait wait = new WebDriverWait(driver, timeout);
        wait.until(new Function<WebDriver, Boolean>() {
            public Boolean apply(WebDriver driver) {
                if (hidesThis.isDisplayed()) {
                    clickOnThis.click();
                }
                return !hidesThis.isDisplayed();
            }
        });
    }

    /**
     * Ensures that the page is completely loaded
     */
    public void untilPageIsLoaded() {
        WebDriverWait wait = new WebDriverWait(driver, timeout);
        wait.until(new Function<WebDriver, Boolean>() {
            public Boolean apply(WebDriver driver) {
                return String
                    .valueOf(((JavascriptExecutor) driver).executeScript("return document.readyState"))
                    .equals("complete");
            }
        });
    }


    /**
     * Wait until element attribute has some value
     * Use this method only when attribute value like css property or class name is changed
     *
     * @param element element whose attribute value is going to change
     * @param attribute attribute whose value will be changed
     * @param cssProperty name of css property whose value will change like bottom or margin
     * @param attributeValue changed value of attribute or css property
     */
    private void untilAttributeContains(final WebElement element, final String attribute, final String cssProperty,
            final String attributeValue) {
        WebDriverWait wait = new WebDriverWait(driver, timeout);
        wait.until(new Function<WebDriver, Boolean>() {
            public Boolean apply(WebDriver driver) {
                if (attribute.equalsIgnoreCase("css")) {
                    return element.getCssValue(cssProperty).contains(attributeValue);
                } else {
                    return element.getAttribute(attribute).contains(attributeValue);
                }
            }
        });
    }

    /**
     * Wait until element css property has some value
     *
     * @param element element whose css property value is going to change
     * @param cssProperty name of css property whose value will change like bottom
     * @param cssPropertyValue changed value of css property
     */
    public void untilElementCssIs(final WebElement element, final String cssProperty, final String cssPropertyValue) {
        untilAttributeContains(element, "css", cssProperty, cssPropertyValue);
    }

    /**
     * Wait until element 'class' attribute has given value
     *
     * @param element element whose 'class' attribute value is going to change
     * @param classValue changed value of class attribute
     */
    public void untilElementHasClass(final WebElement element, final String classValue) {
        // To avoid the stale exception it is necessary to maintain below code in try-catch block
        try {
            untilAttributeContains(element, "class", null, classValue);
        } catch (StaleElementReferenceException s) {
        }
    }

    /**
     * Wait until an alert is displayed
     */
    public void untilAlertIsPresent(){
        WebDriverWait wait = new WebDriverWait(driver, timeout);
        wait.until(ExpectedConditions.alertIsPresent());
    }

    /**
     * Wait until an element is no longer attached to the DOM
     *
     * @param element The element to wait for
     */
    public void untilStalenessOfElement(WebElement element) {
        new WebDriverWait(driver, timeout).until(ExpectedConditions.stalenessOf(element));
    }

    /**
     * Wait until the attribute of giv(WebElement) elementent changes to the given attribute value
     *
     * @param element Webelement whose attribute will change
     * @param attribute Attribute that will change
     * @param finalAttributeValue final Attribute value
     */
    public void untilElementHasAttribute(final WebElement element, final String attribute,String finalAttributeValue) {
        // To avoid the stale exception it is necessary to maintain below code in try-catch block
        try {
            untilAttributeContains(element, attribute, null, finalAttributeValue);
        } catch (StaleElementReferenceException s) {
        }
    }



    /**
     * Wait for an attribute value to appear and the other attribute value to disappear
     * NOTE: Call this method when the attribute value is going to change and some of the values in the attribute will disappear
     *
     * @param locator Locator of the webelement
     * @param attr Attribute of the given webelment
     * @param hasAttributeValue Value that the attribute should have
     * @param doesNotHaveAttributeValue Value that the attribute should not have
     */
    public void waitForElementToHaveAndDoesNotHaveAttribute(By locator, String attr, String hasAttributeValue,
            String doesNotHaveAttributeValue) {
        WebDriverWait wait = new WebDriverWait(this.driver, timeout);

        wait.until(new ExpectedCondition<Boolean>() {
            private By locator;
            private String attr;
            private String hasAttributeValue;
            private String doesNotHaveAttributeValue;

            private ExpectedCondition<Boolean> init(By locator, String attr, String hasAttributeValue,
                    String doesNotHaveAttributeValue) {
                this.locator = locator;
                this.attr = attr;
                this.hasAttributeValue = hasAttributeValue;
                this.doesNotHaveAttributeValue = doesNotHaveAttributeValue;
                return this;
            }

            public Boolean apply(WebDriver driver) {
                WebElement element = driver.findElement(this.locator);
                String attribute = element.getAttribute(this.attr);
                if (!attribute.equals(this.hasAttributeValue) || attribute.contains(this.doesNotHaveAttributeValue)) {
                    return false;
                } else {
                    return true;
                }
            }
        }.init(locator, attr, hasAttributeValue, doesNotHaveAttributeValue));
    }
}
