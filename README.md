------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
																								README
------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
 
What is this?
----------------
Every Qantas Deployed Application(QDA) has a "Project" assigned to it in Bitbucket (formerly Stash) and every Application Service(AS) is assigned a specific "repository" within that "Project". The repository should contain application code & it’s associated infrastructure component definitions. This will enable application teams to version control both application and infrastructure code as per their requirements.
 
Repository Structure
--------------------------
At a very high level stash repository structure will include following files and directories:
 
<root>
                |
                |--<application files>
                |--<platform>
                                |
                                |--<component 1 name>
                                                |
                                                |--build.sh
                                                |--<other component related files and scripts>
                                                :
                                                :
                                                :
                                |--<component n name>
                                                |             
                                                |--build.sh
                                                |--<other component related files and scripts>
               
                                |--<component 1 name>.yaml 
                               
                                |--<component 2 name>.yaml
                                                  :
                                                  :
                                                  :
                                                  :
                                |--<component n name>.yaml
               
Where:
<component 1 name>, <component 2 name> etc are the names of the various components (for example, database or web-tier); build.sh are the files required by the application to package the artefacts and download them into the application instances. This file is executed during the CI package-and-upload stage; <component 1 name>.yaml, <component 2 name>.yaml etc are the files required to define and configure various components (ex. web-tier, database, etc.)
 
Example
-----------
As an example, consider this dummy app consisting of two components: my-website and my-database. my-website is an aws/autoscale component, and therefore should have a corresponding directory containing the required build.sh script and any bootstrap scripts.
In this example, the repository also contains a number of application files (index.php and healthcheck.php) outside of the platform directory.
 
<root>
                |
                |--index.php
                |
                |--healthcheck.php
                |
                L--platform
                                |
                                |--my-website
                                |                              |
                                |                              |--build.sh
                                |                              L--bootstrap.sh
                                |
                                |--my-website.yaml
                                |
                                L--my-database.yaml
 
With this folder structure in place, the application build and deployment would roughly follow these steps:
The build.sh script is executed by the pipeline during the CI package-and-upload step.
The build.sh script gathers the index.php, healthcheck.php, and bootstrap.sh files into a payload directory The pipeline saves all files in the payload directory During deployment, the payload files are loaded onto the instance during the bake phase of the aws/autoscale component The bootstrap.sh script is executed on the instance. This script will install the necessary software and copy the index.php and healthcheck.php files to the correct locations on the instance.
 
<Ref: https://confluence.qantas.com.au/display/DTS/Repository+Directory+Structure>             
 
Documentation
--------------------
For details on usage instructions of this repository for facilitating provisioning of your application in QCP please refer document : https://confluence.qantas.com.au/display/DTS/User+Guide
 
                                                                                ***END***