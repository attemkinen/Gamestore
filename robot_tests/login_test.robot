*** Test Cases ***
Sign Up And Login Test
    [Documentation]    Testaa rekisteröityminen ja sen jälkeen kirjautuminen pelilistalle
    [Tags]    signup
    # Rekisteröidy uusi käyttäjä
    Open Browser    ${SIGNUP_URL}    Chrome
    Maximize Browser Window
    Input Text    xpath=//input[@name='username']    ${NEW_USERNAME}
    Input Text    xpath=//input[@name='email']       ${USER_EMAIL}
    Input Text    xpath=//input[@name='password']    ${NEW_PASSWORD}
    Input Text    xpath=//input[@name='passwordCheck']    ${NEW_PASSWORD}
    Click Button    xpath=//input[@type='submit']
    
    # Odotetaan, että ohjautuu login-sivulle
    Wait Until Page Contains    Kirjaudu sisään
    Close Browser

    # Kirjaudu sisään
    Open Browser    ${LOGIN_URL}    Chrome
    Maximize Browser Window
    Input Text    xpath=//input[@name='username']    ${NEW_USERNAME}
    Input Text    xpath=//input[@name='password']    ${NEW_PASSWORD}
    Click Button    xpath=//input[@type='submit']
    
    # Varmista, että pääset pelilistalle
    Wait Until Page Contains    Pelit
    Close Browser
