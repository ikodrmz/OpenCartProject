package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.AccountRegistrationPage;
import pageObjects.HomePage;
import testBase.BaseClass;

public class TC_001_AccountRegistrationTest extends BaseClass {

    @Test(groups = {"Regression","Master"})
    void test_account_Registration(){

        logger.info("***   Starting TC_001_AccountRegistrationTest ***");
        try
        {
        HomePage hp=new HomePage(driver);
        hp.clickMyAccount();
        logger.info("Clicked on My account link");
        hp.clickRegister();
        logger.info("Clicked on Register link");

        AccountRegistrationPage regpage=new AccountRegistrationPage(driver);
        logger.info("Providing the customer data");
        regpage.setFirstName("abc");
        regpage.setLastName("xyz");
        regpage.setEmail(randomString()+"@gmail.com");  //randomly create an email.
        //regpage.setEmail(rb.getString("email"));
        //regpage.setPassword(rb.getString("password"));
        regpage.setPassword("test1234");
        regpage.setPrivacy();
        regpage.clickContinue();
        logger.info("Clicked on continue");

        String confmsg= regpage.getConfirmation();
        logger.info("Validating expected Message");
        Assert.assertEquals(confmsg,"Your Account Has Been Created!","Test failed");

        } catch (Exception e)
        {
            logger.error("test failed");
            Assert.fail();
        }
    logger.info("*** Finished TC_001_AccountRegistrationTest ***");

    }
}
