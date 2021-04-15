package com.qaprosoft.carina.demo;

import com.zebrunner.agent.core.annotation.TestLabel;
import org.apache.commons.lang3.RandomStringUtils;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.qaprosoft.carina.core.foundation.AbstractTest;
import com.qaprosoft.carina.core.foundation.utils.mobile.IMobileUtils;
import com.qaprosoft.carina.core.foundation.utils.ownership.MethodOwner;
import com.qaprosoft.carina.demo.mobile.gui.pages.common.CarinaDescriptionPageBase;
import com.qaprosoft.carina.demo.mobile.gui.pages.common.ContactUsPageBase;
import com.qaprosoft.carina.demo.mobile.gui.pages.common.LoginPageBase;
import com.qaprosoft.carina.demo.mobile.gui.pages.common.UIElementsPageBase;
import com.qaprosoft.carina.demo.mobile.gui.pages.common.WebViewPageBase;
import com.qaprosoft.carina.demo.mobile.gui.pages.common.WelcomePageBase;
import com.qaprosoft.carina.demo.utils.MobileContextUtils;
import com.qaprosoft.carina.demo.utils.MobileContextUtils.View;


public class MobileSampleTest extends AbstractTest implements IMobileUtils {

    @Test(description = "JIRA#DEMO-0011")
    @MethodOwner(owner = "qpsdemo")
    @TestLabel(name = "feature", value = {"mobile", "regression"})
    public void testLoginUser() {
        String username = "Test user";
        String password = RandomStringUtils.randomAlphabetic(10);
        WelcomePageBase welcomePage = initPage(getDriver(), WelcomePageBase.class);
        Assert.assertTrue(welcomePage.isPageOpened(), "Welcome page isn't opened");
        LoginPageBase loginPage = welcomePage.clickNextBtn();
        Assert.assertFalse(loginPage.isLoginBtnActive(), "Login button is active when it should be disabled");
        loginPage.typeName(username);
        loginPage.typePassword(password);
        loginPage.selectMaleSex();
        loginPage.checkPrivacyPolicyCheckbox();
        CarinaDescriptionPageBase carinaDescriptionPage = loginPage.clickLoginBtn();
        Assert.assertTrue(carinaDescriptionPage.isPageOpened(), "Carina description page isn't opened");
    }

	@Test(description = "JIRA#DEMO-0011")
    @MethodOwner(owner = "qpsdemo")
    @TestLabel(name = "feature", value = {"mobile", "regression"})
    public void testWebView() {
        WelcomePageBase welcomePage = initPage(getDriver(), WelcomePageBase.class);
        LoginPageBase loginPage = welcomePage.clickNextBtn();
        loginPage.login();
        WebViewPageBase webViewPageBase = initPage(getDriver(), WebViewPageBase.class);
        MobileContextUtils contextHelper = new MobileContextUtils();
        contextHelper.switchMobileContext(View.WEB);
        ContactUsPageBase contactUsPage = webViewPageBase.goToContactUsPage();
        contactUsPage.typeName("John Doe");
        contactUsPage.typeEmail("some@email.com");
        contactUsPage.typeQuestion("This is a message");
        //TODO: [VD] move page driver related action outside from test class!
        hideKeyboard();
        contactUsPage.submit();
        Assert.assertTrue(contactUsPage.isSuccessMessagePresent() || contactUsPage.isRecaptchaPresent(),
            "message was not sent or captcha was not displayed");
    }

    @Test(description = "JIRA#DEMO-0011")
    @MethodOwner(owner = "qpsdemo")
    @TestLabel(name = "feature", value = {"mobile", "acceptance"})
    public void testUIElements() {
        WelcomePageBase welcomePage = initPage(getDriver(), WelcomePageBase.class);
        LoginPageBase loginPage = welcomePage.clickNextBtn();
        CarinaDescriptionPageBase carinaDescriptionPage = loginPage.login();
        UIElementsPageBase uiElements = carinaDescriptionPage.navigateToUIElementsPage();
        final String text = "some text";
        final String date = "22/10/2018";
        final String email = "some@email.com";
        uiElements.typeText(text);
        Assert.assertEquals(uiElements.getText(), text, "Text was not typed");
        uiElements.typeDate(date);
        Assert.assertEquals(uiElements.getDate(), date, "Date was not typed");
        uiElements.typeEmail(email);
        Assert.assertEquals(uiElements.getEmail(), email, "Email was not typed");
        uiElements.swipeToFemaleRadioButton();
        uiElements.checkCopy();
        Assert.assertTrue(uiElements.isCopyChecked(), "Copy checkbox was not checked");
        uiElements.clickOnFemaleRadioButton();
        Assert.assertTrue(uiElements.isFemaleRadioButtonSelected(), "Female radio button was not selected!");
        uiElements.clickOnOtherRadioButton();
        Assert.assertTrue(uiElements.isOthersRadioButtonSelected(), "Others radio button was not selected!");
    }

    @Test(description = "JIRA#DEMO-0001")
    @MethodOwner(owner = "amyrchyk")
    @TestLabel(name = "feature", value = {"mobile", "acceptance"})
    public void testVerify() {
        WelcomePageBase welcomePage = initPage(getDriver(), WelcomePageBase.class);
        Assert.assertTrue(welcomePage.isPageOpened(), "Welcome page isn't opened");
        LoginPageBase loginPage = welcomePage.clickNextBtn();
        Assert.assertFalse(loginPage.isLoginBtnActive(), "Login button is active when it should be disabled");
        Assert.assertTrue(loginPage.isFieldNamePresent(),"Field isn't presented");
        Assert.assertTrue(loginPage.isFieldPassPresent(),"Field isn't presented");
        Assert.assertTrue(loginPage.isMaleSexRadioButtonPresent(),"RadioButton Male isn't presented");
        Assert.assertTrue(loginPage.isFemaleSexRadioButtonPresent(),"RadioButton Female isn't presented");
        Assert.assertTrue(loginPage.isPrivacyPolicyCheckboxPresent(),"CheckBox Privacy isn't presented");
        Assert.assertFalse(loginPage.isMaleSexRadioButtonCheck(), "Male is checked");
        Assert.assertFalse(loginPage.isFemaleSexRadioButtonCheck(), "Female is checked");
        Assert.assertFalse(loginPage.isPrivacyPolicyCheckboxCheck(), "Privacy is checked");
        String username = "Test user";
        String password = RandomStringUtils.randomAlphabetic(10);
        loginPage.typeName(username);
        loginPage.typePassword(password);
        loginPage.selectMaleSex();
        loginPage.checkPrivacyPolicyCheckbox();
        Assert.assertTrue(loginPage.isSexCheck(), "Sex isn't checked");
        Assert.assertTrue(loginPage.isPrivacyPolicyCheckboxCheck(), "Privacy isn't checked");
        CarinaDescriptionPageBase carinaDescriptionPage = loginPage.clickLoginBtn();
        Assert.assertTrue(carinaDescriptionPage.isPageOpened(), "Carina description page isn't opened");
        
        
    }
    @Test(description = "JIRA#DEMO-0002")
    @MethodOwner(owner = "amyrchyk")
    @TestLabel(name = "feature", value = {"mobile", "acceptance"})
    public void test2Verify() {
        WelcomePageBase welcomePage = initPage(getDriver(), WelcomePageBase.class);
        Assert.assertTrue(welcomePage.isPageOpened(), "Welcome page isn't opened");
        LoginPageBase loginPage = welcomePage.clickNextBtn();
        Assert.assertFalse(loginPage.isLoginBtnActive(), "Login button is active when it should be disabled");
        Assert.assertTrue(loginPage.isFieldNamePresent(),"Field isn't presented");
        Assert.assertTrue(loginPage.isFieldPassPresent(),"Field isn't presented");
        Assert.assertTrue(loginPage.isMaleSexRadioButtonPresent(),"RadioButton Male isn't presented");
        Assert.assertTrue(loginPage.isFemaleSexRadioButtonPresent(),"RadioButton Female isn't presented");
        Assert.assertTrue(loginPage.isPrivacyPolicyCheckboxPresent(),"CheckBox Privacy isn't presented");
        Assert.assertFalse(loginPage.isMaleSexRadioButtonCheck(), "Male is checked");
        Assert.assertFalse(loginPage.isFemaleSexRadioButtonCheck(), "Female is checked");
        Assert.assertFalse(loginPage.isPrivacyPolicyCheckboxCheck(), "Privacy is checked");
        String username = "Test user";
        String password = RandomStringUtils.randomAlphabetic(10);
        loginPage.typeName(username);
        loginPage.typePassword(password);
        loginPage.selectMaleSex();
        Assert.assertTrue(loginPage.isSexCheck(), "Sex isn't checked");
        Assert.assertTrue(loginPage.isPrivacyPolicyCheckboxCheck(), "Privacy isn't checked");
        Assert.assertFalse(loginPage.isLoginBtnActive(), "Button Sign Up is active");

        
        
    }

}
