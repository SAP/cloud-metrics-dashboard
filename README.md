# cloud-metrics-dashboard

[![REUSE status](https://api.reuse.software/badge/github.com/SAP/cloud-metrics-dashboard/)](https://api.reuse.software/info/github.com/SAP/cloud-metrics-dashboard/)

Intro
-----------

You can use SAP Monitoring service for SAP BTP to receive states and metrics of Java applications and the applications' processes. These Java applications can be in different subaccounts or even on different regions. For more information about the currently supported regions and their hosts, see [Regions and Hosts Available for the Neo Environment](https://help.sap.com/viewer/ea72206b834e4ace9cd834feed6c0e09/Cloud/en-US/d722f7cea9ec408b85db4c3dcba07b52.html).

The only requirement for this scenario to work is that your user is a member of the subaccounts the Java applications are located in. For more information about how to check subaccount memberships, see [Managing Member Authorizations in the Neo Environment](https://help.sap.com/viewer/ea72206b834e4ace9cd834feed6c0e09/Cloud/en-US/a1ab5c4cc117455392cd0a512c7f890d.html).

The communication between your dashboard application and SAP Monitoring service works as follows:

<p align="center">
 <img src="doc/Monitoring Service Scenario_blog.png" width="75%"/>
</p>

1.	An operator accesses a dashboard application in the web browser.

2.	The dashboard application requests metrics of a Java application from SAP Monitoring service with a REST API call.
For more information about the REST call, see [Metrics API](https://api.hana.ondemand.com/monitoring/v1/documentation).

3.	The monitoring service sends back a JSON response with a status code 200 OK.
The response contains the state and metrics of the requested application.

4.	The dashboard application parses the JSON response and places the metrics in a list.

5.	The dashboard application requests the metrics of other Java applications by repeating steps 1 to 5.

6.	The dashboard application's UI uses the list with metrics to display those metrics in the browser.

This cloud-metrics-dashboard application is implemented to retrieve the state of two Java applications and the state and metrics of all the processes running on the two Java applications. However, you can change or extend this dashboard application to match your scenario.

Configuration
-------------

Download this project locally and follow the instructions in [Tutorial: Implementing a Dashboard Application](https://developers.sap.com/tutorials/cp-monitoring-dashboard.html).

Authors
-------------

**Yavor Mladenov**
+ http://github.com/yamladenov

**Nikola Simeonov**

Copyright and license
---------------------

Copyright (c) 2016-2021 SAP SE

Except as provided below, this software is licensed under the Apache License, Version 2.0 (the "License"); you may not use this software except in compliance with the License. You may obtain a copy of the License at:

[http://www.apache.org/licenses/LICENSE-2.0](http://www.apache.org/licenses/LICENSE-2.0)

Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the specific language governing permissions and limitations under the License.

Detailed information including third-party components and their licensing/copyright information is available [via the REUSE tool](https://api.reuse.software/info/github.com/SAP/cloud-metrics-dashboard/).
