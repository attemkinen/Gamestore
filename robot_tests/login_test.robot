*** Settings ***
Library    SeleniumLibrary

*** Variables ***
${LOGIN_URL}      http://localhost:8080/login
${USERNAME}       testi
${PASSWORD}       testi
${INVALID_USER}   invalid_user
${INVALID_PASS}   invalid_pass

*** Test Cases ***

Successful Login
    [Documentation]    Testaa, että käyttäjä voi kirjautua sisään oikeilla tiedoilla.
    Open Browser    ${LOGIN_URL}    Chrome
    Maximize Browser Window
    Input Text    name=username    ${USERNAME}
    Input Text    name=password    ${PASSWORD}
    Click Button    //input[@type="submit"]
    ${current_url}=    Get Location
    Should Be Equal As Strings    ${current_url}    http://localhost:8080/games
    Close Browser


Unsuccessful Login with Invalid Username
    [Documentation]    Testaa, että kirjautuminen epäonnistuu väärällä käyttäjänimellä.
    Open Browser    ${LOGIN_URL}    Chrome
    Maximize Browser Window
    Input Text    name=username    ${INVALID_USER}
    Input Text    name=password    ${PASSWORD}
    Click Button    //input[@type="submit"]
    Wait Until Page Contains    Invalid username and password.
    Close Browser

Unsuccessful Login with Invalid Password
    [Documentation]    Testaa, että kirjautuminen epäonnistuu väärällä salasanalla.
    Open Browser    ${LOGIN_URL}    Chrome
    Maximize Browser Window
    Input Text    name=username    ${USERNAME}
    Input Text    name=password    ${INVALID_PASS}
    Click Button    //input[@type="submit"]
    Wait Until Page Contains    Invalid username and password.
    Close Browser



