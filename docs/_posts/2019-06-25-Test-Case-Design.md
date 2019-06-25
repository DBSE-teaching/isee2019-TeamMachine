---
layout: post
current: post
cover: 'assets/images/Testcase.jpeg'
navigation: true
title: "Test Case Design"
date: 2019-06-25
class: post-template
subclass: 'post tag-fiction'
author: Pranay Teja Arikatla
---

**Hello People**

Welcome to our fifth or we can say key blog of Team Machine. In this blog you will see the details and documentation of the testing methods that we used to test our beta prototype of Celedger application.

## Testing Process

For any Software Development process, testing plays a crucial role to identify bugs/issues or to check whether they satisfied the customer requirements. There are different types for testing methods. Some of them are:

*	Functionality Testing

* Unit Testing

*	Acceptance Testing

*	System Testing

* Integration Testing

## White-Box Testing

Testing the application with the knowledge of internal structure to check how the system is performing. Following are the classes tested using this White-Box testing.

![WB]({{site.baseurl}}/images/WB.jpg "WB"){:height="40%" width="70%"}

**1)Limit Homepage entries to three:**

The code below is to show the recent three entries in the income/expenses in the homepage. The method getAllXpense() fetches the recent entries in the expense entry database using timestamp feed and gives limited entries in the homepage. Similarly the method getAllIncome() gives the recent entries for income in the homepage.

![Homepage]({{site.baseurl}}/images/Homepage.png "Homepage"){:height="44%" width="77%"}

**Scenario 1:**

* Calledmethod: getAllXpense()

* Path(line numbers): 168-178

* Errors: Nil

* Status: Working as expected

**Scenario 2:**

* Calledmethod: getAllIncome()

* Path(line numbers): 180-190

* Errors: Nil

* Status: Working as expected

**2)Filter by categories or by payment method:**

The code below is to filter the entries to view the list with only required categories or the payment method.

![filtercat]({{site.baseurl}}/images/filtercat.jpeg "filtercat"){:height="40%" width="70%"}

![Filter]({{site.baseurl}}/images/Filter.PNG "Filter"){:height="20%" width="35%"}

**Scenario 1:**

* Filtering Categories

* Path(line numbers): 122, 123, 124-134, 135-137

* Errors: Nil

* Status: Working as expected

**Scenario 2:**

* Filtering Payment Methods

* Path(line numbers): 139-149, 150-154

* Errors: Nil

* Status: Working as expected

**3)Creating Piechart:**

The statistics from time to time are represented graphically in the form of pie chart.

![Piechart]({{site.baseurl}}/images/Piechart.png "Piechart"){:height="44%" width="77%"}

* Calledmethod: setupcatPieChart()

* Path(line numbers): 147-170

* Errors: Nil

* Status: Working as expected

**4)Navigation Drawer:**

This is to easily navigate between the screens of the application. The method onNavigationItemSelected() is used to switch between each and every class.

![Navigation]({{site.baseurl}}/images/Navigation.jpeg "Navigation"){:height="40%" width="70%"}

![Navigation]({{site.baseurl}}/images/Navigation.PNG "Navigation"){:height="20%" width="35%"}

* Calledmethod: onNavigationItemSelected()

* Path(line numbers): 132-163

* Errors: Nil

* Status: Working as expected

**5)Enabling add button:**

The add button in the income/expense entry page is enabled only when all the fields are entered. There are no optional fields.

![Enabling]({{site.baseurl}}/images/Enabling.png "Enabling"){:height="48%" width="84%"}

* Calledmethod: onTextChanged()

* Path(line numbers): 172-179

* Errors: Nil

* Status: Working as expected

## Black-Box Testing

Testing the application without the knowledge of internal structure to check on what functionality is performing by the system under test. This is completely based on requirement specifications.

![BB]({{site.baseurl}}/images/BB.jpg "BB"){:height="40%" width="70%"}

We tested 5 functionalities using Black-Box Testing which are shown below:

![Black-Box]({{site.baseurl}}/images/Black-Box.png "Black-Box"){:height="40%" width="70%"}

## Source Code Analysis using IBM Application Security on Cloud:

We also tested our source code using static techniques on IBM application security on cloud to find out if there are any issues.

![IBM]({{site.baseurl}}/images/IBM.png "IBM"){:height="60%" width="105%"}

## Summary of changes

* No changes are made to the project objective.

* But we have added navigation drawer to each and every screen as per the requirement of the customer.
