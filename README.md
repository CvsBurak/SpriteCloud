# SpriteCloud

This is a UI automation project.

In this project I used java for the coding language, selenium for the testing tool and Gauge for the BDD testing.

To run this project locally you need to update chromedriver in the web_driver folder. The driver's version must meet with the chrome version of yours.

Also you need to install gauge on your computer. To install it on mac you can use "brew install gauge" command on terminal. 

On windows you can install with npm "npm install -g @getgauge/cli"

After the installations you can run the Spec.spec under the specs file. 

Or you can run the test with mvn command "mvn gauge:execute -DspecsDir=specs" for all cases. You can also run the specific test case with this mvm command "mvn gauge:execute -DspecsDir=specs -Dscenario="Scenario Name"" using the Scenario Name
