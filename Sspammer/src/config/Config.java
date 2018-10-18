package config;

public class Config {

	public static final int MAX_NUMBER_OF_SMS = 150;
	public static final int MAX_NUMBER_OF_EMAILS = 30;
	public static final String DeveloperContactUrl = "http://surajkulkarni.com";

	public static final String StartPageText = "Welcome to Sspammer\nCreated by Suraj Kulkarni\n\nThis application is created expressly "
	        + "for use in FRIENDLY pranks!\n\nPlease do not use this application for any malicious acts.\n\nTo begin, click on File, and "
	        + "enter your google credentials, then begin spamming!\nI have also created a way for you to spam your friend with all the spam engines at once.\n\n"
	        + "Only google voice and gmail are currently supported.\n\nI have imposed a limit of "
	        + MAX_NUMBER_OF_SMS
	        + " text messages at a time, and "
	        + MAX_NUMBER_OF_EMAILS
	        + " emails at a time.\n\n"
	        + "I do not store your google credentials, you will have to enter them every time you start this application\n\n\n\n\tEnjoy!!";

	public static String UserName;
	public static String Password;
}
