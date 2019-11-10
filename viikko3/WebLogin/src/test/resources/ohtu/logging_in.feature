Feature: As a registered user can log in with valid username/password-combination

    Scenario: user can login with correct password
        Given login is selected
        When correct username "jukka" and password "akkuj" are given
        Then user is logged in

    Scenario: user can not login with incorrect password
        Given login is selected
        When correct username "jukka" and incorrect password "wrong" are given
        Then user is not logged in and error message is given

    Scenario: nonexistent user can not login to application
        Given login is selected
        When nonexisting username "keijo" and random password like "password" are given
        Then user is not logged in and error message is given

