---
layout: post
title: "System Design"
date: 2019-05-13
---

**Welcome again!**

This is our third blog on our way in developing the app and we are going to present you the activity diagrams, class diagrams and explanation for each class and attribute. 

So, right after gathering requirements we had to think about the user interface, logic and most importantly programming part. We had a meeting to discuss the UI part and after eliminating a lot of designs, we finally fixed on one which was not more complex for the programmers. System Design process involves describing the modules, interfaces, data and architecture of the app. We have created activity diagrams based on the user stories.

## Activity Diagrams

These diagrams gives a pictorial representation of work flows from one activity to another. So, they basically shows the operations performed when user clicks on the certain buttons.

Activity Diagram

![Activity1]({{site.baseurl}}/images/Activity1.png "Activity1"){:height="40%" width="70%"}

![Activity2]({{site.baseurl}}/images/Activity2.jpeg "Activity2"){:height="20%" width="40%"}

## Class Diagram

These diagrams shows the different aspects for evolving the code for the application by viewing the classes, attributes and relationships among the objects that are used in developing the code. This make the code readable and extensible.

![Class]({{site.baseurl}}/images/Class.png "Class Diagram"){:height="70%" width="80%"}

Login 

![Login]({{site.baseurl}}/images/Login.png "Login Attributes")

Home

![Home]({{site.baseurl}}/images/Home.png "Home")

Navigation

![Navigation]({{site.baseurl}}/images/Navigation.png "Navigation")

Income/Expenses

![Income]({{site.baseurl}}/images/Income_Expenses.png "Income")

Categories

![Categories]({{site.baseurl}}/images/Categories.png "Categories")

Savings

![Savings]({{site.baseurl}}/images/Savings.png "Savings")

Settings

![Settings]({{site.baseurl}}/images/Settings.png "Settings")

Contact Us

![Contact US]({{site.baseurl}}/images/Contact.png "Contact Us")



## Development Strategy

We have quickly started with the implementation of the application since we were asked to submit the basic prototype by mid May. We assigned tasks through slack and GitHub and the progress was always tracked by scrum master and reviewed the same during our meetings that happened twice in a week.The time for designing each user story was estimated and we will try to finish it without any deadline slip. In each sprint, a steady progress is always mandatory which helps in predicting that the end product completion happens in time. As of now, we will follow the software prototyping method along with continuous integration which means that incomplete developments of the end product will happen along with merging of all development copies to a common repository.

![Development]({{site.baseurl}}/images/DS.JPG "D Strategy")

## Basic Prototype

And we are ready with the basic prototype of the app.

![Login]({{site.baseurl}}/images/Loginapp.jpg "Login"){:height="15%" width="40%"}

User will be able to view the data in the app only after entering the passcode which will be stored in the database and thus providing security.

![Home Page]({{site.baseurl}}/images/Homeapp.png "Home app"){:height="15%" width="40%"}

The above is the Home screen in which you will be able to add income and expenditure. We are working on the UI part and the addExpenses part which we will complete soon.

![Income]({{site.baseurl}}/images/Income.png "Income Page"){:height="15%" width="40%"}

Once the + button is clicked under income, user will be redirected to the the above screen where he can add the amount, source of the income and the time (i.e, date) on which the amount was earned.

We'll come soon with more updates. Stay tuned. 





