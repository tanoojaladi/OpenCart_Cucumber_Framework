package stepDefinitions;



import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.ResourceBundle;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.*;
import pageObjects.*;
import utilities.DataReader;

public class steps {

	WebDriver driver;
	HomePage hp;
	LoginPage lp;
	MyAccountPage ap;
	Logger logger;
	ResourceBundle rb;
	String br;
	List<HashMap<String, String>> exceldata;
	
	@Before
	public void setUp()
	{
		logger=LogManager.getLogger(this.getClass());
		rb=ResourceBundle.getBundle("config");
		br=rb.getString("browser");
	}
	
	@After
	public void tearDown(Scenario scenario) {
		
		System.out.println("the secnario "+scenario.getName() + scenario.getStatus());
		if(scenario.isFailed()) {
		//TakesScreenshot ts=(TakesScreenshot)driver;
		//byte[]	 screenshot=ts.getScreenshotAs(OutputType.BYTES);
	  //or
		byte[]   screenshot=((TakesScreenshot)driver).getScreenshotAs(OutputType.BYTES);
		
		scenario.attach(screenshot, "image/png", scenario.getName());
		}
		
		
		
		driver.quit();
	}
	
	
	@Given("user Launch browser")
	public void user_launch_browser() {
	   if(br.equalsIgnoreCase("chrome")) {
		   driver=new ChromeDriver();
	   }else if(br.equalsIgnoreCase("edge")) {
		   driver=new EdgeDriver();
	   }else {
		   driver=new FirefoxDriver();
	   }
	   driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	}

	@Given("opens url {string}")
	public void opens_url(String appURL) {
	    driver.get(appURL);
	    driver.manage().window().maximize();
	}

	@When("user navigates to My Account menu")
	public void user_navigates_to_my_account_menu() {
	    hp=new HomePage(driver);
	    hp.clickMyAccount();
	    logger.info("clicked on My Account");
	}

	@When("click on login")
	public void click_on_login() {
	   hp.clickLogin();
	   logger.info("clicked on login");
	}

	@When("user enters email as {string} and password as {string}")
	public void user_enters_email_as_and_password_as(String email, String pwd) {
	   lp=new LoginPage(driver);
	   lp.setemailAddress(email);
	   logger.info("Entered email");
	   lp.setPassword(pwd);
	   logger.info("Entered password");
	}

	@When("click on login button")
	public void click_on_login_button() {
	    lp.clickLogin();
	    logger.info("clicked on login button");
	}

	@Then("verify user navigates to my account page according to the {string}")
	public void verify_user_navigates_to_my_account_page_according_to_the(String results) {
	    try {
		ap=new MyAccountPage(driver);
		boolean targetPage=ap.isMyAccountPageExists();
		if(results.equalsIgnoreCase("valid")) {
			if(targetPage==true) {
				 logger.info("account page existed with valid credentials so test passed");
				Assert.assertTrue(true);
			}else {
				 logger.info("account page not existed with valid credentials so test failed");
				Assert.fail();
			}
		}if(results.equalsIgnoreCase("invalid")) {
			if(targetPage==true) {
				 logger.info("account page existed with invalid credentials so test failed");
				Assert.fail();
			}else {
				 logger.info("account page not existed with invalid credentials so test passed");
				Assert.assertTrue(true);
			}
		}
	    }catch(Exception e) {
	    	logger.info("entered into catch block test failed");
	    	System.out.println(e.getMessage());
	    	Assert.fail();
	    }
		
		
	}
	
	@Then("user nagivates MY Account Page")
	public void user_nagivates_my_account_page() {
	  ap=new MyAccountPage(driver);
	 boolean result= ap.isMyAccountPageExists();
	 if(result) {
		
		 Assert.assertTrue(true);
		 logger.info("My Account page is existed and test passed");
	 }else {
		 logger.info("My Account page is not existed and test failed");
		 Assert.assertTrue(false);
		 
	 }
		
		
	}

	@Then("check User navigates to MyAccount Page by passing Email and Password with excel row {string}")
	public void check_user_navigates_to_my_account_page_by_passing_email_and_password_with_excel_row(String row) {
	exceldata=	DataReader.data(System.getProperty("user.dir")+"//testdata/Opencart_LoginData.xlsx", "Sheet1");
	System.out.println(exceldata);
	int index=Integer.parseInt(row)-1;
	String email=exceldata.get(index).get("username");
	String password=exceldata.get(index).get("password");
	String exp_res=exceldata.get(index).get("res");
	
	lp=new LoginPage(driver);
    lp.setemailAddress(email);
    logger.info("entered email address");
    lp.setPassword(password);
    logger.info("entered password address");
    lp.clickLogin();
    logger.info("clicked on login button");
    try
    { 
    	ap=new MyAccountPage(driver);
        boolean targetpage=ap.isMyAccountPageExists();

        if(exp_res.equalsIgnoreCase("Valid"))
        {
            if(targetpage==true)
            {
               
               // ap.clickLogout();
            	logger.info("with valid credentials account page existed so test passed");
                Assert.assertTrue(true);
                
            }
            else
            {
            	 logger.info("with valid credentials account page not existed so test failed");
                Assert.assertTrue(false);
            }
        }

        if(exp_res.equals("Invalid"))
        {
            if(targetpage==true)
            {
               // ap.clickLogout();
                logger.info("with invalid credentials account page existed so test failed");
                Assert.assertTrue(false);
            }
            else
            {
            	logger.info("with invalid credentials account page not existed so test passed");
                Assert.assertTrue(true);
                
            }
        }


    }
    catch(Exception e)
    {
       logger.info("exception entered into catch block");
       System.out.println(e.getMessage());
        Assert.assertTrue(false);
    }
    
}
	

	
	




	
}
