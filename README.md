#Coursera: Resource Downloader
######_Now Beta Testing!_


##About
"Take the world's best courses, online, for free." - [Coursera](https://www.coursera.org/).

This program enables you to quickly download resources from all the Coursera courses you are enrolled in.


##Building and Running

coursera-downloader.jar is the current release of the program. If you would like to build the program on your own, the src files can be found above.

To run the project, navigate to the directory with the jar file and execute the following commands:

/dir> **java** -jar coursera-downloader.jar

##Notes 
This program is still in beta phase due to the nature of web-scraping applications. However, I have had much success using this application to quickly download resources from my own courses. If you encounter any problem at all, please make a note of it and contact me so that I can get started on a solution.

This program requires user credentials to access your course information. If you are wary of privacy concerns, feel free to peruse the source code above. User information is stored once in class UserInformation, and quickly erased after it is used to log in to the website.

##GUI Walkthrough
1. Upon successfully running the program, you will be prompted for user information. 
![screen1](https://github.com/KevinRamsunder/coursera-downloader/blob/master/tutorial/Screen1.PNG "First Screen")

2. Next, you will select which classes you would like to download from.
![screen2](https://github.com/KevinRamsunder/coursera-downloader/blob/master/tutorial/screen2.PNG "Second Screen")


3. Select the resources you want to download from each class you chose in the previous step.
![screen3](https://github.com/KevinRamsunder/coursera-downloader/blob/master/tutorial/screen3.PNG "Third Screen")

4. You will then select a directory for your download. After the download is finished...
![screen4](https://github.com/KevinRamsunder/coursera-downloader/blob/master/tutorial/screen5.PNG "Last Screen")

