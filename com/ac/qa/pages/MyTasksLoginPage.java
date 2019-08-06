package com.ac.qa.pages;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.junit.Assert;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.ac.qa.util.BaseClass;

public class MyTasksLoginPage extends BaseClass {
	
	public MyTasksLoginPage()
	{
		PageFactory.initElements(driver, this);
		
	}
	
	//Declaration of Locators
	
	@FindBy(xpath="/html[1]/body[1]/div[1]/div[2]/center[1]/a[1]")
    WebElement signIn;
	
	
	@FindBy(xpath="//input[@id='user_email']")
    WebElement userName;
	
	@FindBy(xpath="//input[@id='user_password']")
    WebElement userPassword;
	
	@FindBy(xpath="//input[@value='Sign in']")
    WebElement logIn;
	
	@FindBy(xpath="//a[@id='my_task']")
    WebElement myTasksBtn;
	
	
	@FindBy(xpath="/html[1]/body[1]/div[1]/h1[1]")
    WebElement userMsg;
	
	@FindBy(xpath="/html[1]/body[1]/div[1]/div[2]/div[1]/form[1]/div[2]/input[1]")
    WebElement newTask;
	
	@FindBy(xpath="/html[1]/body[1]/div[1]/div[2]/div[1]/form[1]/div[2]/span[1]")
    WebElement addBtn;
	
	@FindBy(xpath= "//table[@class='table']/tbody/tr")
    List <WebElement> listOfNewTask;
	
	@FindBy(xpath="//table[@class='table']/tbody/tr/td/button[@class='btn btn-xs btn-danger']")
    List <WebElement> removeButton;
	
	@FindBy(xpath = "//table[@class='table']/tbody/tr/td/button[@class='btn btn-xs btn-primary ng-binding']")
    List<WebElement> manageSubTasksButton;
	
	@FindBy(xpath = "//div[@class='modal-content']")
    WebElement popUpWindow;
	
	@FindBy(xpath = "//h3[@class='modal-title ng-binding']")
	WebElement taskIDOnPopUpWindow;
	
	@FindAll(@FindBy(xpath = "//table[@class='table']/tbody/tr"))
	List<WebElement> subTaskList;

	@FindBy(xpath = "//div[@class='modal-body ng-scope']/form/textarea[@id='edit_task']")
	WebElement taskDesscriptionOnPopUpWindow;
	
	@FindBy(xpath = "//form[@name='sub_task_form']/div/input[@id='new_sub_task']")
    WebElement subTaskDescriptionOnPopUpWindow;

    @FindBy(xpath = "//form[@name='sub_task_form']/div/p/input[@id='dueDate']")
    WebElement dueDateOnPopUpWindow;
	
    @FindBy(xpath = "//form[@name='sub_task_form']/div/button[@id='add-subtask']")
    WebElement addSubTaskButtonOnModalWindow;
    
    @FindAll(@FindBy(xpath ="//table[@class='table']/tbody/tr/td/button[contains(text(),'Remove SubTask')]"))
    List<WebElement> removeSubTaskButton;
    
    //implementation of methods
	
	public String verifyPageTitle()
	{
		return driver.getTitle();
	}
	
	public MyTasksLoginPage clickOnSignIn()
	{
		signIn.click();
		return new MyTasksLoginPage();		
	}
	
	public MyTasksLoginPage enterUnamePwd(String uname, String pwd)
	{
		userName.sendKeys(uname);
		userPassword.sendKeys(pwd);
		return new MyTasksLoginPage();
	}
	
	public MyTasksLoginPage hitSignIn()
	{
		logIn.click();
		return new MyTasksLoginPage();
	}
	
	public boolean validateMyTasksBtn()
	{
		return myTasksBtn.isDisplayed();
	}
	
	public MyTasksLoginPage clickOnMyTasksBtn()
	{
		myTasksBtn.click();
		return new MyTasksLoginPage();
	}
	
	public String validateMyTasksPage()
	{
		return driver.getTitle();
	}
	
	public String verifyUserMessage()
	{
		return userMsg.getText();
	}
	
	public MyTasksLoginPage enterNewTaskDescritption(String details)
	{
		newTask.sendKeys(details);
		return new MyTasksLoginPage();
	}
	
	public void hitEnter()
	{
		newTask.sendKeys(Keys.ENTER);
	}
	
	public int noOfMyTasks()
	{
		return listOfNewTask.size();
	}
	
	public void clickOnRemoveTaskButton()
	{
		removeButton.get(0).click();
	}
	
	public String checkNewTaskDescription()
	{
		return newTask.getAttribute("value");
	}
	
	public boolean verifyNewTaskDescription(String detail)
	{
		return detail.equals(newTask.getAttribute("value"));
	}
	
	public MyTasksLoginPage clickOnAddButton()
	{
		addBtn.click();
		return new MyTasksLoginPage();
	}
	
	public boolean validateManageSubTasksButton()
    {
        return manageSubTasksButton.get(0).isDisplayed();
    }
	
	public void clickOnManageSubTasksButton()
    {
        manageSubTasksButton.get(0).click();
    }
	
	 public Set<String> getPopUpWindow()
	    {
	        Set<String> handles = driver.getWindowHandles();
	        return handles;
	    }
	
	public void verifyModalDialogBox()
	{
		Set<String> handles = getPopUpWindow();
        String parentWindowHandler = driver.getWindowHandle();
        String popUpWindowHandler = null;
        Iterator<String> iterator = handles.iterator();
        while(iterator.hasNext())
            popUpWindowHandler = iterator.next();
        driver.switchTo().window(popUpWindowHandler);
        Assert.assertTrue(popUpWindow.isDisplayed());
	}
	
	public void verifyTaskIDOnMOdalWindow()
    {
        String str = taskIDOnPopUpWindow.getText();
        int k = str.indexOf(" ", str.indexOf(" ") + 1);
        String actualTaskID = str.substring(k);
        Assert.assertEquals(false, actualTaskID.isEmpty());
    }
	
	public void verifyTaskDescriptionOnModalWindow()
    {
        taskDesscriptionOnPopUpWindow.isDisplayed();
    }
	
	public MyTasksLoginPage entersDetailsForSubTask(String subTaskDesc, String dueDate)
    {
        subTaskDescriptionOnPopUpWindow.sendKeys(subTaskDesc);
        dueDateOnPopUpWindow.sendKeys(dueDate);
        return new MyTasksLoginPage();
    }
	
	public void clickOnAddSubTaskButton()
    {
        addSubTaskButtonOnModalWindow.click();
    }
	
	public void ClickOnRemoveSubTaskButton()
    {
        removeSubTaskButton.get(0).click();
    }
	

	public String checkSubTaskDescription()
	{
	        return subTaskDescriptionOnPopUpWindow.getAttribute("value");
	}

	public String checkDueDate()
	{
	        return dueDateOnPopUpWindow.getAttribute("value");
	}
	
	public int getNumberOfSubTasks()
    {
        return subTaskList.size();
    }
	
}
