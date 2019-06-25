---
layout: post
current: post
cover: 'assets/images/AP.jpeg'
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

![Homepage]({{site.baseurl}}/images/Homepage.png "Homepage"){:height="40%" width="70%"}

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

![Filter]({{site.baseurl}}/images/Filter.PNG "Filter"){:height="40%" width="70%"}

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

![Piechart]({{site.baseurl}}/images/Piechart.png "Piechart"){:height="40%" width="70%"}

* Calledmethod: setupcatPieChart()

* Path(line numbers): 147-170

* Errors: Nil

* Status: Working as expected

