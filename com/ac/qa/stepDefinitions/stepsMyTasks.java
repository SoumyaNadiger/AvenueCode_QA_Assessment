package com.ac.qa.stepDefinitions;



import org.junit.Assert;

import com.ac.qa.pages.MyTasksLoginPage;
import com.ac.qa.util.BaseClass;
import cucumber.api.java.en.*;


public class stepsMyTasks extends BaseClass
{
		
	MyTasksLoginPage myTasks;
	int countOfMyTasks;
	int countOfSubTasks;
	
	
	@Given("^User launches browser$")
	public void user_launches_browser() throws Throwable {
	    initializeBrowser();
	}

	@When("^User lands on QA Assessment page$")
	public void user_lands_on_QA_Assessment_page() throws Throwable {
	    myTasks = new MyTasksLoginPage();
	    String title = myTasks.verifyPageTitle();
	    Assert.assertEquals("QA Assessment", title);   
	}

	@When("^User hits Sign In button$")
	public void user_hits_Sign_In_button() throws Throwable {
	    myTasks.clickOnSignIn();
	}

	@When("^User enters email and password$")
	public void user_enters_email_and_password() throws Throwable {
	    myTasks.enterUnamePwd(prop.getProperty("useremail"), prop.getProperty("userpassword"));
	}

	@When("^User clicks on sign in$")
	public void user_clicks_on_sign_in() throws Throwable {
	    myTasks.hitSignIn();
	}

	@Then("^close browser$")
	public void close_browser() throws Throwable {
	    quit();
	}
	
	@When("^Validate My Tasks button on home page$")
	public void validate_My_Tasks_button_on_home_page() throws Throwable {
	    boolean myTasksButton = myTasks.validateMyTasksBtn();
	    Assert.assertTrue(myTasksButton);
	}

	@When("^User clicks on My Tasks button$")
	public void user_clicks_on_My_Tasks_button() throws Throwable {
	    myTasks.clickOnMyTasksBtn();
	}

	@Then("^My Tasks page is displayed$")
	public void my_Tasks_page_is_displayed() throws Throwable {
		myTasks = new MyTasksLoginPage();
	    String title = myTasks.validateMyTasksPage();
	    Assert.assertEquals("My Tasks", title);   
	}
	
	@Then("^Validate display message on My Tasks Page$")
	public void validate_display_message_on_My_Tasks_Page() throws Throwable {
	    myTasks = new MyTasksLoginPage();
	    String msg = myTasks.verifyUserMessage();
	    Assert.assertEquals("Hey Soumya Nadiger, this is your todo list for today", msg);
	    System.out.println(msg);
	}
	
	@Then("^Then user enters <validTaskDescription>$")
	public void then_user_enters_validTaskDescription(String newTaskDescription) throws Throwable {
	    myTasks.enterNewTaskDescritption(prop.getProperty("validTaskDescription"));
	    countOfMyTasks = myTasks.noOfMyTasks();
	}
	
	@Then("^user hits enter$")
	public void user_hits_enter() throws Throwable {
		myTasks.enterNewTaskDescritption(prop.getProperty("validTaskDescription"));
	    myTasks.hitEnter();
	}

	@Then("^a new task should be created and append in task list$")
	public void a_new_task_should_be_created_and_append_in_task_list() throws Throwable {
	    int noOfMyTasks = myTasks.noOfMyTasks();
	    countOfMyTasks++;
	    Assert.assertEquals(countOfMyTasks, noOfMyTasks);
	}

	@Then("^remove newly added task from task list$")
	public void remove_newly_added_task_from_task_list(String newTaskDescription) throws Throwable {
	    String actualNewDescrption = myTasks.checkNewTaskDescription();
	    myTasks.clickOnRemoveTaskButton();
	    Assert.assertTrue(myTasks.verifyNewTaskDescription(actualNewDescrption));
	}

	@Then("^task is removed successfully$")
	public void task_is_removed_successfully() throws Throwable {
		int actualNumberOfTasks = myTasks.noOfMyTasks();
        countOfMyTasks--;
        Assert.assertEquals(countOfMyTasks, actualNumberOfTasks);	
	
	}
	
	
	@Then("^user hits add button$")
	public void user_hits_add_button() throws Throwable {
		myTasks.clickOnAddButton();
	}
	
	@Then("^new task should not be created")
	public void new_task_should_not_be_created() throws Throwable {
		myTasks.enterNewTaskDescritption(prop.getProperty("invalidTaskDescription1"));
		int noOfMyTasks = myTasks.noOfMyTasks();
	    Assert.assertEquals(countOfMyTasks, noOfMyTasks);
	}
	
	@Then("^Validate Manage SubTasks button in present in Task list$")
	public void Validate_Manage_SubTasks_button_in_present_in_Task_list() throws Throwable {
		if(myTasks.noOfMyTasks()==0)
        {
            Assert.assertFalse(myTasks.validateManageSubTasksButton());
        }
        else
        {
            Assert.assertTrue(myTasks.validateManageSubTasksButton());
        }
	}	
		
	@Then("^user clicks on Manage SubTasks button$")
	public void user_clicks_on_Manage_SubTasks_button() throws Throwable {
	    myTasks.clickOnManageSubTasksButton();
	}
	
	@Then("^Modal dialog box opens up$")
	public void Modal_dialog_box_opens_up() throws Throwable {
	    myTasks.verifyModalDialogBox();
	}
	
	@Then("^user checks Task Id on Manage Subtasks window$")
	public void user_checks_Task_Id_on_Manage_Subtasks_window() throws Throwable {
		myTasks.verifyTaskIDOnMOdalWindow();	    
	}
	
	@Then("^user checks Task description on Manage Subtasks window$")
	public void user_checks_Task_description_on_Manage_Subtasks_window() throws Throwable {
	    myTasks.verifyTaskDescriptionOnModalWindow();
	}
	
	@Then("^user enters subtask description and duedate$")
	public void user_enters_subtask_descrption_and_duedate(String subTskDesc, String dueDate) throws Throwable {
	    myTasks.entersDetailsForSubTask(prop.getProperty("validTaskDescription"), prop.getProperty("validduedate"));
	}
	
	@Then("^user clicks on add sub tasks button$")
	public void user_clicks_on_add_sub_tasks_button() throws Throwable {
	    myTasks.clickOnAddSubTaskButton();
	}
	
	@Then("^remove newly added subtask$")
	public void remove_newly_added_subtask(String subTaskDesc) throws Throwable {
		String actualSubTaskDescription = myTasks.checkSubTaskDescription();
        myTasks.ClickOnRemoveSubTaskButton();
        Assert.assertTrue(myTasks.verifyNewTaskDescription(actualSubTaskDescription));
	}
	
	@Then("^verify newly added sub task appends to subtasks list$")
	public void verify_newly_added_sub_task_appends_to_subtasks_list() throws Throwable {
		int actualNoOfSubTask = myTasks.getNumberOfSubTasks();
        countOfSubTasks++;
        Assert.assertEquals(countOfSubTasks,actualNoOfSubTask);
	    
	}
	
}
