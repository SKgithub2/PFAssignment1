package pathFactoryTest;

import org.openqa.selenium.By;

public class AutomationPractice extends Base{

//***************************************************************************************************************************************	

	String validEmail = "test@pathfactory.com";
	String invalidEmail = "test123@pathfactory.com";
	String validPassword = "Password123";
	String invalidPassword = "Password321";
	String actualError = "";
	String expectedLoginTitle = "Login - My Store";
	String expectedError_noEmail = "There is 1 error\nAn email address required.";
	String expectedError_wrongPassword = "There is 1 error\nAuthentication failed.";
	String expectedError_wrongEmail = "There is 1 error\nInvalid email address.";
	String expectedWelcomeText = "Welcome to your account. Here you can manage all of your personal information and orders.";
	String expectedForgotPassworrdText = "Please enter the email address you used to register. We will then send you a new password.";
	
    //Locators
	String signInLocator = "//*[contains(text(),'Sign in')]";
	String forgotPasswordLocator = "//*[contains(text(),'Forgot your password')]";
	String emailFieldLocator = "//*[@id='email_create']";
	String submitLogInBtnLocator = "//*[@id='SubmitLogin']";
	String registeredEmailLocator = "//*[@id='email']";
	String passwordLocator = "//*[@id='passwd']";
	
//***************************************************************************************************************************************

	public void signIn() {
		
		driver.findElement(By.xpath(signInLocator)).click();
		String actualTitle = driver.getTitle();
		
		if(actualTitle.equals(expectedLoginTitle))
			System.out.println("Navigated to Sign In page successfully");
		else
			System.out.println("Navigation to Sign In page FAILED");
		}
	
	public void verifyRegisterUser() {
		
		// Test 1 : Verify error is displayed when "Sign in" btn is selected with empty fields 
		driver.findElement(By.xpath(submitLogInBtnLocator)).click();
		actualError = driver.findElement(By.xpath("//*[@id=\'center_column\']/div[1]")).getText();
				
		if(actualError.equals(expectedError_noEmail))
			System.out.println("Verify error is displayed when \"Sign in\" btn is selected with empty fields  - PASSED");
		else
			System.out.println("Verify error is displayed when \"Sign in\" btn is selected with empty fields  - FAILED");
		
		// Test 2 : Verify error is displayed when incorrect password is entered 
		driver.findElement(By.xpath(registeredEmailLocator)).sendKeys(validEmail);
		driver.findElement(By.xpath(passwordLocator)).sendKeys(invalidPassword);
		
		driver.findElement(By.xpath(submitLogInBtnLocator)).click();
		actualError = driver.findElement(By.xpath("//*[@id=\'center_column\']/div[1]")).getText();
						
		if(actualError.equals(expectedError_wrongPassword))
			System.out.println("Verify error is displayed when incorrect password is entered  - PASSED");
		else
			System.out.println("Verify error is displayed when incorrect password is entered  - FAILED");
		
		// Test 3 : Verify error is displayed when incorrect email is entered 
		driver.findElement(By.xpath(registeredEmailLocator)).clear();
		driver.findElement(By.xpath(registeredEmailLocator)).sendKeys(invalidEmail);
		driver.findElement(By.xpath(passwordLocator)).clear();
		driver.findElement(By.xpath(passwordLocator)).sendKeys(validPassword);
		
		driver.findElement(By.xpath(submitLogInBtnLocator)).click();
		actualError = driver.findElement(By.xpath("//*[@id=\'center_column\']/div[1]")).getText();
		
		
		if(actualError.equals(expectedError_wrongPassword))
			System.out.println("Verify error is displayed when incorrect email is entered - PASSED");
		else
			System.out.println("Verify error is displayed when incorrect email is entered - FAILED");
		
		// Test 4 : Verify Forgot your password link is navigating user to the expected page 
		driver.findElement(By.xpath(forgotPasswordLocator)).click();
	    String forgotPasswordLandingPage = driver.findElement(By.xpath("//*[contains(text(),'Please enter the email')]")).getText();
				
	    try {
	        Thread.sleep(5000);
	    } catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				driver.findElement(By.xpath(signInLocator)).click();
				
				if(forgotPasswordLandingPage.equals(expectedForgotPassworrdText))
					System.out.println("Verify Forgot your password link is navigating user to the expected page - PASSED");
				else
					System.out.println("Verify Forgot your password link is navigating user to the expected page - FAILED");
				
		
		// Test 5 : Verify user logs in successfully with valid credentials 
		driver.findElement(By.xpath(registeredEmailLocator)).clear();
		driver.findElement(By.xpath(registeredEmailLocator)).sendKeys(validEmail);
		driver.findElement(By.xpath(passwordLocator)).clear();
		driver.findElement(By.xpath(passwordLocator)).sendKeys(validPassword);
		
		driver.findElement(By.xpath(submitLogInBtnLocator)).click();
		String welcomeText = driver.findElement(By.xpath("//*[@class=\'info-account\']")).getText();
		
		if(welcomeText.equals(expectedWelcomeText))
			System.out.println("Verify user logs in successfully with valid credentials - PASSED");
		else
			System.out.println("Verify user logs in successfully with valid credentials - FAILED");
		
		driver.quit();
		
		}


	public static void main(String[] args) {
		AutomationPractice obj = new AutomationPractice();
		obj.setupBrowser("chrome", "http://automationpractice.com/index.php");

		obj.signIn();
		obj.verifyRegisterUser();
		
	}

}
