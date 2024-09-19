@UI
Feature: Create and login as admin

  Scenario Outline: Create local admin user
    Given User navigates to Unifi login page and create user with username "<username>" and password "<password>"

    Examples:
      | username       | password |
      | admin_test_user| psw      |

  Scenario Outline: Login as local admin user
    Given User navigates to Unifi login page and login with username "<username>" and password "<password>"
    When Validate admin activity name
    And Validate Country or Region in settings

    Examples:
      | username       | password |
      | admin_test_user| psw      |