*** Settings ***
Library    SeleniumLibrary
Library    RequestsLibrary
Library    Collections   

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
${DELETE_GAME_ID}            28  # Pelin ID, jota haluat poistaa    
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
    


Edit Newest Game After Login
    [Documentation]    Etsii uusimman lisätyn pelin ja muokkaa sitä.

    # Oletetaan, että käyttäjä on jo kirjautunut sisään ja on pelilistalla
    
    # Etsi uusimman pelin edit-linkki ja paina sitä
    ${last_game_edit_link}=    Get Element Attribute    xpath=(//a[contains(@href, '/edit')])[last()]    href
    Go To    ${last_game_edit_link}

    # Päivitä pelin tiedot
    Input Text    xpath=//input[@name='name']    ${EDIT_NAME}
    Input Text    xpath=//input[@name='console']    ${EDIT_CONSOLE}
    Input Text    xpath=//input[@name='description']    ${EDIT_DESCRIPTION}
    Input Text    xpath=//input[@name='ageLimit']    ${EDIT_AGE_LIMIT}
    Input Text    xpath=//input[@name='published']    ${EDIT_PUBLISHED}
    Input Text    xpath=//input[@name='price']    ${EDIT_PRICE}
    Select From List By Label    id=category    ${CATEGORY_EDIT_NAME}

    # Lähetä muokattu lomake
    Click Button    xpath=//input[@type='submit']

    # Varmista, että muokatut tiedot näkyvät pelilistalla
    Wait Until Page Contains    ${EDIT_NAME}

    

Delete Game

    # Poista peli
    Click Link    xpath=(//a[contains(@href, '/deletegame')])[last()]

    # Päivitä sivu varmistaaksesi, että peli on poistettu
    SeleniumLibrary.Reload Page
   Wait Until Page Does Not Contain    ${EDIT_NAME}  # Varmistetaan, että peli ei ole enää listassa
    
    # Varmista, että peli on poistettu
    Wait Until Element Is Not Visible    xpath=//td[text()='${DELETE_GAME_ID}']  # Tarkistetaan, ettei pelin ID enää näy listassa
    
    Close Browser
