
 Feature: SelfEvaluation Compare


 Scenario:Visualization table of SelfEvaluation compare
   Given I am on the self-evaluation comparison page
   And   the student "joao" has "MPA" in "requisitos" and "MPA" in "Testes" in self-evaluation
   And   the student "joao" has "MA" in criterion "requisitos" and "MANA" in criterion "Testes" in evaluation
   When  I trigger Compare Self-Evaluation
   Then  I can see a table with "joao" in a row
   And   in the "Testes" column i can see "MANA|MPA" painted red
   And   in the "Requisitos" column i can see "MA|MPA" painted blue


Scenario: Highest student rating on 3 criteria or more
  Given the student "Jose da Silva" has "MA" in criterion "Escrever requisitos com qualidade", "MA" in criterion "Testes", "MPA" in criterion "Implementar funcionalidades" in self-evaluation
  And   the student "Jose da Silva" has "MPA" in criterion "Escrever requisitos com qualidade", "MANA" in criterion "Testes", "MANA" in criterion "Implementar funcionalidades" in evaluation
  When  i trigger compare self-evaluation
  Then  a message is sent to "Jose da Silva" to look for the teacher



