# QA Automation Assignment - Ubiquiti UniFi

## Project Description

This project is a QA automation assignment for Ubiquiti's UniFi system. It utilizes Selenium WebDriver for browser automation, Cucumber for BDD (Behavior-Driven Development) and REST Assured for API testing. The project covers automating the setup and login process, validating user settings and ensuring that the country settings are correctly applied.

## Setup

### Prerequisites

Ensure you have the following installed:

- Java Development Kit (JDK) 17 or higher
- Maven
- Docker
- Lombok plugin for IDE and enabled annotation processing 

### Building the Project

1. Clone the repository:

   ```bash
   git clone https://github.com/popup2132/qa-automation-assigment-ubiquiti-unifi.git
2. Run container:

   ```bash
   docker-compose up -d

3. Run tests against UI:

   ```bash
   currently run is available only from IDE

4. Run tests against API

   ```bash
   mvn test -Dtest=ApiTests:

### TODO
- add shell script to pipeline for run container and await for loading
- add another script for cleaning data folder after closing containers
- hardcoded data could be moved to properties layer
- add utility for webdriver waits