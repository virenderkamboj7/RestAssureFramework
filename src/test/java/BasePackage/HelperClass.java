package BasePackage;

import java.text.DateFormatSymbols;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;


public class HelperClass  extends BaseClass{
	public static DateTimeFormatter dtf, dtf1; //Date time formater
	public static LocalDateTime now; //Get local time

	public static String dateTime() {
//		Fri Feb 19 15:17:32 GMT+05:30 2021
		 dtf = DateTimeFormatter.ofPattern("E MMM dd HH:mm:ss ");  
		 dtf1 = DateTimeFormatter.ofPattern(" yyyy");
//		 dtf2 = DateTimeFormatter.ofPattern(" yyyy");
		 
		   now = LocalDateTime.now();  
		   String time=dtf.format(now)+"GMT+05:30" + dtf1.format(now);
//		   System.out.println("Current Time is: "+time);  
		   return time;
	}
	
	public static String currentDateTime(String format) {
		 dtf = DateTimeFormatter.ofPattern(format);  
		 dtf1 = DateTimeFormatter.ofPattern(" yyyy");
		 
		   now = LocalDateTime.now();  
		   String time=dtf.format(now);
		   return time;
	}
	
	public static String dateTimeCustomise(String pattren) {
		 dtf = DateTimeFormatter.ofPattern(pattren);  
		   now = LocalDateTime.now();  
		   String time = dtf.format(now);
		   return time;
	}
	
	
	
	
	public static String getMonth(int month) {
//		return new DateFormatSymbols().getMonths()[month - 1];
		return new DateFormatSymbols().getShortMonths()[month - 1];
	}
	

	public static int randomNum(int limit) {
		Random random = new Random();
		return random.nextInt(limit);
	}
	
	public static int randomNumRange(int min, int max) {
	      return (int)Math.floor(Math.random()*(max-min+1)+min);
	}
	
	public static void email(String sub, String body) {
		Email email = new SimpleEmail();
		
		email.setHostName("smtp.gmail.com");
		email.setSmtpPort(465);
		email.setAuthenticator(new DefaultAuthenticator("virender.orangemantra@gmail.com", ""));
		email.setSSLOnConnect(true);
		try {
			email.setFrom("virender.orangemantra@gmail.com");
		
		email.setSubject(sub);
		email.setMsg(body);
		email.addTo("virender.orangemantra@gmail.com");
		email.send();
		} catch (EmailException e) {
			e.printStackTrace();
		}
		
		
	}
	
	
	
	
}
