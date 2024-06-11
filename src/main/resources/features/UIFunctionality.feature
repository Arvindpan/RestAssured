Feature: Validating UI and plave APIs
	This feature file verifies the both UI and API functionality
	
	Scenario Outline: Verify if ticket is booked and place is being successfully added
		Given Add the place Payload and verify user from website
	  When user call "AddPlaceAPI" with POST http request
	  Then API call got success with status code 200
	  And "status" in response code is "ok"
	  And "scope" in response body is "APP"
	  
	  