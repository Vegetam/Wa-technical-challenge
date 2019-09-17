# WA-Technical-Challenge
 Wa-Technical-Challenge

# Technology Stack
I used to build this project - Java 8, Spring Boot, Swagger,  Git, Maven, Logger


# Explanation to Setup the Project

Project setup :

clone the github repository Vegetam/WA-Technical-Challenge.git

convert project to maven then run clean install it


#Run Project :

Put the required input csv file into resources folder like - /DataFilterUtility/src/main/resources/summary.csv

Run the application as a boot application

Go to the browser and put following URL : http://localhost:8080/WA-Technical-Project/swagger-ui.html#

You should see API call on swagger console, click on the single Post API existing : getFilteredData
enter request JSON like below { "dataFilterLimit": "3", "dataFilterMinValue": "0.8", "filePath": "summary.csv" }

Once you enter required input to any of the API just click on "Try it out!"

You can see the result in Response Body tab

Also final file with output data will be created on folder path like - /DataFilterUtility/data_result.csv
