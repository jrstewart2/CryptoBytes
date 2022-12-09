*** Settings ***
Documentation   Suite Description   This suite will contain tests for the portfolio API
Library         RequestsLibrary
Library         SeleniumLibrary
Library         JSONLibrary
Library         Collections
Resource        ../keywords/my_keywords.robot

*** Variables ***
${HOST}     http://localhost:8080



*** Test Cases ***
GET Request - validate response code and response body
    [Documentation]    This test verifies that the response code of the GET request should be 200,
    ...     the response body will contain 'name' key with value 'Bitcoin',
    ...     and the response body contains the key 'amount'.
    [Tags]    Smoke
    Create Session  session         ${HOST}       verify=false
    ${response}=    GET On Session  session     /portfolio      params=query=
    Status Should Be    200     ${response}

    ${name}=            Get Value From Json     ${response.json()}[0]   name
    ${nameFromList}=    Get From List           ${name}                 0
    Should be equal     ${nameFromList}                                 Bitcoin

    ${body}=            Convert To String       ${response.content}
    Should Contain      ${body}                 amount



GET Request by ID - validate response code and body when requesting portfolio by ID
    [Documentation]    This test will verify that a response code of 200 and the correct body is received
    ...     when a query parameter is used in the URL.
    [Tags]    Smoke
    Create Session  session         ${HOST}       verify=false
    ${response}=    GET On Session  session     /portfolio/ETH      params=query=
    Status Should Be    200     ${response}

    ${name}=            Get Value From Json     ${response.json()}      name
    ${nameFromList}=    Get From List           ${name}                 0
    Should be equal     ${nameFromList}                                 Ethereum

    ${body}=            Convert To String       ${response.content}
    Should Contain      ${body}                 amount



POST Request
    [Documentation]    This test verifies that the response code from a POST request is 201,
    ...     the respone body contains the 'id' key with value 'LTC',
    ...     and the response header should return 'application/json'.
    [Tags]    Regression
    Create Session  session         ${HOST}   verify=false
    &{body}=    Create Dictionary   id=LTC  name=Litecoin   amount=123

    ${response}=    POST On Session     session     /portfolio  json=${body}
    Status Should Be    201         ${response}

    ${id}=          Get Value From Json     ${response.json()}      id
    ${idFromList}=  Get From List           ${id}                   0
    Should be equal As Strings              ${idFromList}           LTC

    ${getHeaderValue}=  Get From Dictionary  ${response.headers}  Content-Type
    Should be equal  ${getHeaderValue}  application/json



DELETE Request
    [Documentation]    This test will verify that an entity will be deleted upon request.
    ...     The response code should return 202.
    [Tags]  Regression
    Create Session      session         ${HOST}     verify=false
    ${response}=    DELETE On Session   session     /portfolio/ETH
    Status Should Be    202             ${response}

CONFIRMATION OF DELETE REQUEST
    [Documentation]    This test will verify that the previous Delete request has removed
    ...     the entity from the database. The response code should be 404.
    [Tags]    Smoke
    Create Session    session           ${HOST}     verify=false
                GET          session       /portfolio/ETH
    run keyword and continue on failure     Integer    response status  404



PATCH Request
    [Documentation]    This test verifies that the response code from a PATCH request is 200,
    ...     the response body contains the 'amount' key with value '2.0',
    ...     and the response header should return 'application/json'.
    [Tags]    Regression
    Create Session  session         ${HOST}   verify=false
    &{body}=    Create Dictionary   amount=2.0

    ${response}=    PATCH On Session     session     /portfolio/BTC  json=${body}
    Log To Console    ${response}
    Status Should Be    200         ${response}

CONFIRMATION OF PATCH REQUEST
    [Documentation]    This test will verify that the previous Delete request has removed
    ...     the entity from the database. The response code should be 404.
    [Tags]    Smoke
    Create Session  session         ${HOST}       verify=false
    ${response}=    GET On Session  session     /portfolio      params=query=
    Status Should Be    200     ${response}

    ${name}=            Get Value From Json     ${response.json()}[0]   name
    ${nameFromList}=    Get From List           ${name}                 0
    Should be equal     ${nameFromList}                                 Bitcoin

    ${body}=            Convert To String       ${response.content}
    Should Contain      ${body}                 amount