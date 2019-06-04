---
layout: post
title: "Advanced Prototype"
date: 2019-06-04
---

**Welcome Back**

In this blog we will see the various stages of Implementation and User Interface Design of our Celedger Application.

## Design Patterns

The Design pattern of any android application is broadly classified into three categories

![DP]({{site.baseurl}}/images/DP.jpg "DP"){:height="40%" width="70%"}

## Creational Pattern

This pattern describes how an object of a class is created. We are using Singleton pattern here. The ViewHolder used to view the incomes & expenses in a list view is an example of the singleton design pattern. It's used to improve performance of a ListView while scrolling it.

## Structural Pattern

It specifies how the classes and objects are arranged in an arrangement that performs a particular task. We are using Adapter to do the same for us. In order to implement the RecyclerView onto a layout an Adapter is used.

## Behavioural Pattern

It refers to three divisions of classes used.

Model: It is the data class, where all the user data is stored.

View: It is the visual class, which includes everything the user sees.

Controller: It is the glue between the above two. It updates the view, takes user input and make changes to the model.

![DP1]({{site.baseurl}}/images/DP1.jpg "DP1"){:height="35%" width="60%"}

![DP2]({{site.baseurl}}/images/DP2.jpg "DP2"){:height="35%" width="60%"}

## Coding Conventions

There are few coding guidelines we came up with as a team to improve ease of understanding of the coding part. Coming to java file names, for the activities and classes we have used .java and for the layouts we have used .xml. We have packages, import statements, overrides of methods etc; in our source file structure. The miscellaneous we used are the drawable and colored strings. The variables are first declared then they are defined and then they are used in the coding.

And for the ease access of coding we gave spaces in between each sections. And we also gave comments for different sections to provide information or explanation about the variable, method, class or any statement.

![CC1]({{site.baseurl}}/images/CC1.png "CC1"){:height="35%" width="60%"}

![CC2]({{site.baseurl}}/images/CC2.png "CC2"){:height="35%" width="60%"}

## Context of use

We created personas from two major user groups for our celedger application to identify their needs and expectations. They will define the main functions of our application. For this personas we have gathered their age, profession, personality, goals, frustations and their bio.

![UP]({{site.baseurl}}/images/UP.png "UP"){:height="44%" width="77%"}

![UP2]({{site.baseurl}}/images/UP2.png "UP2"){:height="44%" width="77%"}

## Design Solution

We created a prototype from the above user group stories. We provided the numeric keyboard for the app considering it as a major requirement for the user. And we did the application in such a way that he/she can only see recent activities or entries in the homepage as it is one of the user requirement we have gathered.

![DS1]({{site.baseurl}}/images/DS1.jpg "DS1"){:height="42%" width="72%"}

![DS2]({{site.baseurl}}/images/DS2.jpg "DS2"){:height="42%" width="72%"}

## Summary of Changes

We only made few changes to our application comparing to the initial design. Before we had addition button for both income and expenses seperately but later we have changed it into a common button based on the user requirements.

We worked in way where we don't get such unexpected user input that could ruin the whole user interface of our application.

## Working Prototype

The following is the link for the advanced working prototype of our Celedger application. We will provide you with the complete celedeger application in the next blog.

<a href="https://github.com/DBSE-teaching/isee2019-TeamMachine/blob/CeledgerAppAPK/docs/Celedger2.apk" target="_blank"><b>Click here to download Apk</b></a>

Homepage with Recent Activities

![Homepage]({{site.baseurl}}/images/Homepage.jpg "Homepage"){:height="16%" width="28%"}

Adding Income/Expense

![MP]({{site.baseurl}}/images/MP.png "MP"){:height="40%" width="70%"}

Predefined Categories

![PD]({{site.baseurl}}/images/PD.png "PD"){:height="40%" width="70%"}

Filters

![Filters]({{site.baseurl}}/images/Filters.jpg "Filters"){:height="16%" width="28%"}
