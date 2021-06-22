Framework overview : 
		- This framework can be implemented any project where have to scope to automate rest apis.
		- It is up and running and designed is such a way where QA has to add new test case only under "TestCase" package.
	
	'BaseClass' (Under BasePackage package) class contains static method to execute a get or post.
	'Apis class' contains the end points of the request urls
	'ApisUtil' class contains the post request method so that it can be directly used inside test class to avoid extra code and we can direct play with json and validations
	'HelperClass' class contains helper method like get DateTime, Curent Time, send email etc. 
	'utilities' package contains utility classes like Read text file, Excel utilities, read json file, Extent report configuration class etc.
	
	
	logs are getting stored under "logs" folder.
	Extent report are getting stored under "Reports" folder to get overall html test report.
	
	
	Steps to run the project:
 1. Eclipse should be installed
 2. testng should be installed in eclipse
 4. Now take the pull of framework
 5. Right click on testng.xml file and run as TestNGSuite
 
 You can run the using cmd also
 1. Download maven from "https://maven.apache.org/download.cgi" based on your operating system
 2. Set up environment variables
 3. Run cmd and cd the project home directory where you have downloaded 
 4. Execute the command: "mvn clean test -DtestngFile=testng.xml"    (This command will run the testng.xml file)
 	Hence your test can be run without eclipse, which helps to schedule the build using Jenkins.
 	bat file file the there under project home directory. double click on the "run.bat" file. It will execute the testng.xml file.
 5. Logger will tell the steps we followed in every test cases.
	
	
	 **Please feel to write me "virender.kamboj@yahoo" in case on any query or clarification.
	