import { AppPage } from './app.po';
import { browser, by, element, protractor } from 'protractor';
import { async } from 'q';

describe('Nutritionist App', () => {
  let page: AppPage;

  beforeEach(() => {
    page = new AppPage();
  });

  it('should display title', () => {
    page.navigateTo();
    expect(browser.getTitle()).toEqual('NutritionistUI');
  });

  it('should be redirected to /register route', () => {
    browser.element(by.id('registerButton')).click();
    expect(browser.getCurrentUrl()).toContain('/register');
  });

  it('should be able to register user', () => {
    browser.element(by.id('firstName')).sendKeys('Bipul');
    browser.element(by.id('lastName')).sendKeys('Dan');
    browser.element(by.id('userId')).sendKeys('bipuldan');
    browser.element(by.id('password')).sendKeys('bipuldan');
    browser.element(by.id('registeredButton')).click();
    expect(browser.getCurrentUrl()).toContain('/login');
  });

  it('should be redirected to /login route on opening the application', () => {
    expect(browser.getCurrentUrl()).toContain('/login');
  });

  it('login user', () => {
    browser.element(by.id('userId')).sendKeys('bipuldan');
    browser.element(by.id('password')).sendKeys('bipuldan');
    browser.element(by.id('loginButton')).click();
    expect(browser.getCurrentUrl()).toContain('/foods/foodSearch');
  });

  it('searching foods', () => {
    browser.element(by.css('.search-button')).click();
    expect(browser.getCurrentUrl()).toContain('/foods/foodSearch');
    browser.element(by.id('search-button-input')).sendKeys('grapes');
    browser.element(by.id('search-button-input')).sendKeys(protractor.Key.ENTER);
    const searchItems = element.all(by.css('.foodName'));
    expect(searchItems.count()).toBe(25);
    for(let i = 0; i < 1; i += 1) {
      expect(searchItems.get(i).getText()).toContain('ACAI GRAPE, UPC: 068274342233');
    }
    searchItems.get(0).click();
    browser.element(by.id('foodReport')).click();
    expect(browser.getCurrentUrl()).toContain('/foods/nutrient');
    browser.driver.sleep(3000);
    browser.element(by.id('foodReportViewed')).click();
    expect(browser.getCurrentUrl()).toContain('/foods/foodSearch');
    browser.element(by.id('search-button-input')).sendKeys('grapes');
    browser.element(by.id('search-button-input')).sendKeys(protractor.Key.ENTER);
    const newSearchItems = element.all(by.css('.foodName'));
    expect(newSearchItems.count()).toBe(25);
    for(let i = 0; i < 1; i += 1) {
      expect(searchItems.get(i).getText()).toContain('ACAI GRAPE, UPC: 068274342233');
    }
    searchItems.get(0).click();
    browser.element(by.id('addButton')).click();
    browser.driver.sleep(3000);
  });

  it('should be able to view favouriteList and delete an item and then logout', async() => {
    browser.driver.manage().window().maximize();
    browser.element(by.id('favouriteList')).click();
    expect(browser.getCurrentUrl()).toContain('/foods/favouriteList');
    browser.driver.sleep(2000);
    browser.element(by.id('deleteFood')).click();
    browser.driver.sleep(5000);
    browser.element(by.id('finallyLogout')).click();
  });

});
