Feature: Feature used in testing runOnEnv tag aspect

  @runOnEnv(WAIT)
  Scenario: RunOnEnv with param defined.
    Given I run 'sleep ${WAIT}' locally

  @runOnEnv(NO_WAIT)
  Scenario: RunOnEnv with param NOT defined.
    Given I run 'sleep ${WAIT}' locally

  @runOnEnv(WAIT,NO_WAIT)
  Scenario: RunOnEnv with more than one param. Ones defined and others not defined.
    Given I run 'sleep ${WAIT}' locally

  @skipOnEnv(NO_WAIT)
  Scenario: SkipOnEnv with param NOT defined.
    Given I run 'sleep ${WAIT}' locally

  @skipOnEnv(WAIT)
  Scenario: SkipOnEnv with param defined.
    Given I run 'sleep ${WAIT}' locally

  @skipOnEnv(WAIT,NO_WAIT)
  Scenario: AND skipping option. SkipOnEnv with more than one param.
    Given I run 'sleep ${WAIT}' locally
    Given I run 'sleep ${WAIT}' locally

  @skipOnEnv(WAIT)
  @skipOnEnv(NO_WAIT)
  Scenario: OR skipping option. SkipOnEnv with more than one param.
    Given I run 'sleep ${WAIT}' locally

  @runOnEnv(WAIT)
  @skipOnEnv(NO_WAIT)
  Scenario: RunOnEnv with more than one param. Ones defined and others not defined.
    Given I run 'sleep ${WAIT}' locally

#  @runOnEnv(WAIT)
#  @skipOnEnv(NO_WAIT)
#  Scenario: This is an omitted scenario so it contains a failing assert
#    Given I run 'sleep ${WAIT}' locally

  @runOnEnv(WAIT~1)
  Scenario: RunOnEnv with param defined and it has a value.
    Given I run 'sleep ${WAIT}' locally

  @runOnEnv(WAIT=2)
  Scenario: RunOnEnv with param defined and it has a value.
    Given I run 'sleep ${WAIT}' locally