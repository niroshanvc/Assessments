# loqbox automation


## Purpose
This is a test automation project for loqbox sign up process that is developed using cucumber JVM and Selenium

### Prerequisites
One needs to have JVM on his/ her machine

### Installation and setup

#### Clone this project to your local
```` shell script
git clone https://github.com/niroshanvc/Assessments.git
````

## Execuition

### From IDE directly
Configure the [`CucumberRunnerTest`](src/test/java/com/loqbox/runner/CucumberRunnerTest.java) with the test (@Tags) that one wants to execute and running it IDE itself.

## Reporting
Configure the [`extent.properties`](src/test/resources/extent.properties) file for Spark reports, follow these steps.

`1.` Enable Spark Reporter:

Set the extent.reporter.spark.start property to true to enable the Spark reporter:

```` shell script
extent.reporter.spark.start=true
````

`2.` Specify Output File Path:

Set the extent.reporter.spark.out property to specify the output file path for the Spark report. You can specify the relative or absolute path:

```` shell script
extent.reporter.spark.out=Reports/Spark.html
````

`3.`Configure Base Folder Name and Date Time Pattern:

You can configure the base folder name and date time pattern for the Spark report using the following properties:

```` shell script
#FolderName
basefolder.name=ExtentReports/SparkReport_
basefolder.datetimepattern=d_MMM_YY HH_mm_ss
````

`4.`Configure Screenshot Settings:

If you need to include screenshots in your Spark report, configure the screenshot directory and relative path using the following properties:

```` shell script
#Screenshot
screenshot.dir=/Screenshots/
screenshot.rel.path=../Screenshots/
````

## Contacts
- Niroshan de Silva <niroshan@gmail.com>

## Current Status
In current state the project supports different browsers like Chrome and Firefox. In the future, we are trying to add the support for Safari as well.

## Technology Stack
* [Cucumber](https://cucumber.io/)