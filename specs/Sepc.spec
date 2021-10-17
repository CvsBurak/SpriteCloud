Specification Heading
=====================
Created by cvsburak on 12.10.2021

This is an executable specification file which follows markdown syntax.
Every heading in this file denotes a scenario. Every bulleted point denotes a step.

Add New Record To Web Tables
-----------------------------
* Click card at index "0"
* Check if title equals to "Elements"
* Click element which values equals to "Web Tables"
* Check if title equals to "Web Tables"
* Add new record
* Check web tables title
* Fill the form with "Burak", "Cavusoglu", "brkcvs321@gmail.com", "26", "4000" and "Automation Test Engineering"
* Submit the record
* Check if record has added by "Burak"

Click Random Card And Check Its Title
--------------------------------------
* Click random card and save its text
* Check if title equals to saved text

Simple Alert
---------------
* Click card at index "2"
* Check if title equals to "Alerts, Frame & Windows"
* Click element which values equals to "Alerts"
* Check if title equals to "Alerts"
* Click alert button
* Check alerts text equals to "You clicked a button"
* Accept alert

Timer Alert
-----------
* Click card at index "2"
* Check if title equals to "Alerts, Frame & Windows"
* Click element which values equals to "Alerts"
* Check if title equals to "Alerts"
* Click timer alert button
* Wait "5" seconds
* Check alerts text equals to "This alert appeared after 5 seconds"
* Accept alert

Confirm The Alert
---------------------
* Click card at index "2"
* Check if title equals to "Alerts, Frame & Windows"
* Click element which values equals to "Alerts"
* Check if title equals to "Alerts"
* Click confirmational alert button
* Check alerts text equals to "Do you confirm action?"
* Accept alert
* Check the result after selecting confirm

Not Confirm The Alert
----------------------
* Click card at index "2"
* Check if title equals to "Alerts, Frame & Windows"
* Click element which values equals to "Alerts"
* Check if title equals to "Alerts"
* Click confirmational alert button
* Check alerts text equals to "Do you confirm action?"
* Decline alert
* Check the result after selecting cancel

Prompt Alert
--------------
* Click card at index "2"
* Check if title equals to "Alerts, Frame & Windows"
* Click element which values equals to "Alerts"
* Check if title equals to "Alerts"
* Click prompt alert button
* Check alerts text equals to "Please enter your name"
* Send text with alert "asd"
* Accept alert
* Wait "3" seconds
* Check the promt alert result "You entered asd"

New Tab
--------
* Click card at index "2"
* Check if title equals to "Alerts, Frame & Windows"
* Click element which values equals to "Browser Windows"
* Check if title equals to "Browser Windows"
* Click new tab button
* Focus on new tab
* Check if new tabs heading equals to "This is a sample page"

Close Current Tab
-----------------
* Click card at index "2"
* Check if title equals to "Alerts, Frame & Windows"
* Click element which values equals to "Browser Windows"
* Check if title equals to "Browser Windows"
* Click new tab button
* Focus on new tab
* Check if new tabs heading equals to "This is a sample page"
* Close the current tab
* Check if title equals to "Browser Windows"


