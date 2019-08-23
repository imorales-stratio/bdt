Feature: LDAP steps test

  Background: Establish connection to LDAP server
    Given I connect to LDAP

  @manual @ignore
  Scenario: Search for a specific user and get some of its attributes
    When I search in LDAP using the filter 'uid=abrookes' and the baseDn 'dc=stratio,dc=com'
    Then the LDAP entry contains the attribute 'uid' with the value 'abrookes'
    And the LDAP entry contains the attribute 'sn' with the value 'Anthony'
    And the LDAP entry contains the attribute 'gidNumber' with the value '101'

  @manual @ignore
  Scenario: Test if multiple scenarios can be run sequentially
    When I search in LDAP using the filter 'uid=abrookes' and the baseDn 'dc=stratio,dc=com'
    Then the LDAP entry contains the attribute 'uid' with the value 'abrookes'

  @manual @ignore
  Scenario: Test if an attribute which has more than one value is correctly found
    When I search in LDAP using the filter 'cn=Developers' and the baseDn 'dc=stratio,dc=com'
    Then the LDAP entry contains the attribute 'memberUid' with the value 'uid=adoucet,ou=People,dc=stratio,dc=com'
    And the LDAP entry contains the attribute 'memberUid' with the value 'uid=irossi,ou=People,dc=stratio,dc=com'

  @manual @ignore
  Scenario: Test create user
    Then I create LDAP user 'test1' with password '1234'
    And I create LDAP user 'test2' with password '1234' and assign it to LDAP group 'admin'
    And I create LDAP user 'test3' with password '1234' and assign it to LDAP group 'intelligence'

  @manual @ignore
  Scenario: Test assign user to group
    Then I assign LDAP user 'test1' to LDAP group 'manager_admin'

  @manual @ignore
  Scenario: Test unassign user from group/s
    Then I unassign LDAP user 'test3' from LDAP group 'intelligence'
    And I unassign LDAP user 'test1' from all LDAP groups

  @manual @ignore
  Scenario: Test delete user
    Then I delete LDAP user 'test1'

  @manual @ignore
  Scenario: Test create group
    Then I create LDAP group 'gr_test1'

  @manual @ignore
  Scenario: Test delete group
    Then I delete LDAP group 'gr_test1'

  @manual @ignore
  Scenario: Test get all user groups
    Then I get all LDAP groups where LDAP user 'test2' belongs and save it in environment variable 'groupsList'
    And I run 'echo !{groupsList}' locally

  @manual @ignore
  Scenario: Test change user password
    Then I change the password of LDAP user 'test2' to '4321'

  @manual @ignore
  Scenario: Test user belonging
    Then I check that LDAP user 'test2' belongs to LDAP group 'manager_admin'
    And I check that LDAP user 'test2' does not belong to LDAP group 'stratio'

  @manual @ignore
  Scenario: Test user existence
    Then I check that LDAP user 'test2' exists
    And I check that LDAP user 'blabla' does not exist

  @manual @ignore
  Scenario: Test group existence
    Then I check that LDAP group 'stratio' exists
    And I check that LDAP group 'bleble' does not exist

  @manual @ignore
  Scenario: Clean everything left
    Then I delete LDAP user 'test2'
    And I delete LDAP user 'test3'
