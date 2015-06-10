Feature: Register evaluation
	As a teacher
	I want to register evaluation
	So I can see evaluations and send them to students

	# before review
	#Scenario: register evaluation
	#	Given I am on Teaching Assistant Teacher's home page
	#	When I follow "register evaluation"
	#	Then I should be on the Register evaluation page
	#	When I fill in "title" with "Evaluation 1"
	#	And I fill in "item one" with "Question 1"
	#	And I fill in "item one criterion" with "criterion 1"
	#	And I press "Register evaluation"
	#	Then I should see "Evaluation registered"

	# after review
  	@ignore
	Scenario: register evaluation GUI
			Given I am on Register evaluation page
			When I fill in the field "title" with "Git evaluation"
			And I fill in the field "question" "1" with "How does 'git push' works?"
			And I fill in the field "alternative" "1" with "Sends a file to cloud repositorie"
			And I fill in the field "alternative" "2" with "gets a file from cloud repositorie"
			And I press "register" button
			Then I should see the message "Avaliação registrada."

	# before review
	#Scenario: register evaluation with no items
	#	Given I am on Register evaluation page
	#	When I fill in "title" with "Evaluation 1"
	#	And no items are informed
	#	And I press "Register evaluation"
	#	Then I should see "Evaluation registered"

	# after review
	Scenario: register evaluation
		Given the system has no evaluation entitled "Git evaluation" stored
		When I create an evaluation entitled "Git evaluation"
		Then the evaluation "Git evaluation" should be stored in the system
		
	# before review
	#Scenario: register duplicate evaluation
	#	Given I am on Register evaluation page
	#	And the evaluation "Evaluation 1" is stored in the system
	#	When I fill "title" with "Evaluation 1"
	#	And I press "Register evaluation"
	#	Then I should see "Evaluation already exist"

	# after review
	Scenario: register duplicate evaluation
		Given the system already has an evaluation entitled "Git evaluation" stored
		When I create an evaluation entitled "Git evaluation" with question "How to send files to your repository"
		Then no evaluation should be store in the system

	# before review
	#Scenario: register evaluation with no title
	#	Given I am on Register evaluation page
	#	When I press "Register evaluation"
	#	Then I should see "Evaluation's title is required"

	# after review
	@ignore
	Scenario: register evaluation with no title GUI
		Given I am on Register evaluation page
		When I press "Register" button
		Then I should see the message "Campo de título é obrigatório. Nenhuma avaliação foi registrada."