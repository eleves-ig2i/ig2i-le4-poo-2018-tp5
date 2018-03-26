# Session 5 [![Build Status](https://travis-ci.org/eleves-ig2i/ig2i-le4-poo-2018-tp5.svg?branch=master)](https://travis-ci.org/eleves-ig2i/ig2i-le4-poo-2018-tp5) [![License: MIT](https://img.shields.io/badge/License-MIT-blue.svg)](LICENSE)

## Description

> This TP was realized with JPA inheritance. It's the continuation of the previous TP and allows to illustrate the following notions :
> * local transformation concept
> * intra-tour improvement operators
> * inter-tour improvement operators
> * notion of local search

## Table of contents
- [Installation](#installation)
	- [Prerequisites](#prerequisites)
		- [Java](#java)
		- [NetBeans](#netbeans)
	- [Installing](#installing)
		- [Create the project](#create-the-project)
		- [Setup Project](#setup-project)
		- [Setup Database](#setup-database)
		- [Setup JDBC](#setup-jdbc)
		- [Setup Persistance](#setup-persistance)
		- [Persistance Unit](#persistance-unit)
- [Authors](#authors)
- [License](#license)

## Installation
### Prerequisites
You need to :
* Java
* NetBeans

#### Java
##### Ressources
* [https://stackoverflow.com/questions/25289482/installing-jdk8-on-ubuntu-unable-to-locate-package-update-doesnt-fix/31869659#31869659](https://stackoverflow.com/questions/25289482/installing-jdk8-on-ubuntu-unable-to-locate-package-update-doesnt-fix/31869659#31869659)
* [http://www.webupd8.org/2014/03/how-to-install-oracle-java-8-in-debian.html](http://www.webupd8.org/2014/03/how-to-install-oracle-java-8-in-debian.html)
* [https://netbeans.org/kb/docs/ide/java-db.html#registering](https://netbeans.org/kb/docs/ide/java-db.html#registering)
* [https://docs.oracle.com/netbeans/nb82/netbeans/NBDAG/work_app_servers.htm#NBDAG1687](https://docs.oracle.com/netbeans/nb82/netbeans/NBDAG/work_app_servers.htm#NBDAG1687)

##### Install
```bash
sudo aptitude update
sudo aptitude install openjdk-8-jdk
```
```bash
#Probably useless stuff
#sudo apt-get purge openjdk
#sudo apt-get purge oracle-java8-installer
#sudo apt-get purge oracle-java9-installer
#sudo aptitude install libderby-java libderbyclient-java
#sudo apt-get install python-software-properties software-properties-common
#sudo add-apt-repository ppa:webupd8team/java
#sudo apt-get install oracle-java8-installer
#sudo apt-get install oracle-java8-set-default
```

#### NetBeans
##### Ressources
* [https://netbeans.org/downloads/](https://netbeans.org/downloads/)
* [https://netbeans.org/community/releases/80/install.html#installation](https://netbeans.org/community/releases/80/install.html#installation)

##### Install
* Go to [https://netbeans.org/downloads/](https://netbeans.org/downloads/)
* Download `Java EE`

```bash
cd ~
mv Downloads/netbeans-8.2-javaee-linux.sh ./
chmod +x ./netbeans-8.2-javaee-linux.sh
# Select Glassfish AND Tomcat
# For JDK installation path, select /usr/lib/jvm/java-8-openjdk-amd64
./netbeans-8.2-javaee-linux.sht
```

### Installing
#### Create the project
* Clone the repository in your local machine
* Open NetBeans
* File > Open Project > ${repo name}
* [Open Project]

#### Setup Database
* Open Netbeans
* Services > Databases > Java DB > Start Server
* Services > Databases > Java DB > Create Database
	* Database Name : database
	* User Name : username
	* Password : password
	* Password : password
* [OK]
* Services > Databases > jdbc:derby... > connect

#### Setup Project
* Open Netbeans
* File > Project Properties ${project name} > Build > Compilation
	* [Check] Compile on Save
	* [Check] Enable Annotation Processing
* [OK]

#### Setup JDBC
* Open Netbeans
* Projects > ${project name} > Librairies > Add Librairies
	* [Import] > Java DB Driver
	* [Add Library]

#### Setup Persistance
* Open Netbeans
* Projects > ${project name} > Librairies > Add Librairies
	* [Import] > EclipseLink (JPA X.X)
	* [Add Library]

#### Setup Persistance Unit
* Open Netbeans
* [Open] META-INF > persistence.xml
	* PU name : ${persistance name}
	* Database Connection : jdbc:derby...
	* Table Generation Strategy : Drop and create
	* [Add] Properties > eclipselink.logging.level : INFO
	* [Add] Properties > eclipselink.ddl-generation.ouput-mode : both
	* [Add] Properties > eclipselink.ddl-generation : drop-and-create-tables

## Authors
* [Loïc BOURGOIS](https://github.com/loicbourgois)
* [Sébastien CORNUEL](https://github.com/Hercules0402)

## License
This TP is licensed under the MIT License - see the [LICENSE](LICENSE) file for details
