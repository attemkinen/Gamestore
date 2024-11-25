*** Settings ***
Library    SeleniumLibrary

*** Variables ***
${BASE_URL}           http://localhost:8080
${LOGIN_URL}          ${BASE_URL}/login
${GAME_NAME}          Test Game
${GAME_CONSOLE}       PlayStation
${GAME_DESCRIPTION}   A test game description
${GAME_AGE_LIMIT}     12
${GAME_PUBLISHED}     2023
${GAME_PRICE}         49.99
${CATEGORY_NAME}      Fantasy
${EDIT_NAME}          EDITED Game
${EDIT_CONSOLE}       PlayStation
${EDIT_DESCRIPTION}   Edited game description
${EDIT_AGE_LIMIT}     14
${EDIT_PUBLISHED}     2025
${EDIT_PRICE}         39.99
${CATEGORY_EDIT_NAME}      Fantasy    
${USERNAME}           testi  
${PASSWORD}           testi  

*** Test Cases ***

Add New Game After Login
    [Documentation]    Testaa uuden pelin lisääminen kirjautumisen jälkeen ja varmistaa, että peli ilmestyy pelilistalle.
    
    # Kirjaudu sisään
    Open Browser    ${LOGIN_URL}    Chrome
    Maximize Browser Window
    Input Text    name=username    ${USERNAME}
    Input Text    name=password    ${PASSWORD}
    Click Button    xpath=//input[@type='submit']
    ${current_url}=    Get Location
    Should Be Equal As Strings    ${current_url}    http://localhost:8080/games
    
    # Mene pelin lisäyslomakkeeseen
    Click Link    xpath=//a[contains(@href, '/newgame')]
    
    # Täytä pelin tiedot
    Input Text    xpath=//input[@name='name']    ${GAME_NAME}
    Input Text    xpath=//input[@name='console']    ${GAME_CONSOLE}
    Input Text    xpath=//input[@name='description']    ${GAME_DESCRIPTION}
    Input Text    xpath=//input[@name='ageLimit']    ${GAME_AGE_LIMIT}
    Input Text    xpath=//input[@name='published']    ${GAME_PUBLISHED}
    Input Text    xpath=//input[@name='price']    ${GAME_PRICE}
    Select From List By Label    id=category    ${CATEGORY_NAME}
    
    # Lähetä lomake
    Click Button    xpath=//input[@type='submit']
    Wait Until Page Contains    ${GAME_NAME}  # Varmistetaan, että peli ilmestyy pelilistalle
    


Edit Game After Login

    Click Link    xpath=//a[contains(@href, '/edit/1')]

    Input Text    xpath=//input[@name='name']    ${GAME_NAME}
    Input Text    xpath=//input[@name='console']    ${GAME_CONSOLE}
    Input Text    xpath=//input[@name='description']    ${GAME_DESCRIPTION}
    Input Text    xpath=//input[@name='ageLimit']    ${GAME_AGE_LIMIT}
    Input Text    xpath=//input[@name='published']    ${GAME_PUBLISHED}
    Input Text    xpath=//input[@name='price']    ${GAME_PRICE}
    Select From List By Label    id=category    ${CATEGORY_NAME}

    Click Button    xpath=//input[@type='submit']
    Wait Until Page Contains    ${GAME_NAME}  # Varmistetaan, että peli ilmestyy pelilistalle
    Close Browser