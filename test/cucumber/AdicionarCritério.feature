#Arthur Lapprand

Feature: Adicionar critério
  Como professor
  Eu quero poder adicionar novos critérios
  Para que eu possa avaliar os alunos com estes critérios

#Controller Scenario
  Scenario: Cadastrar um critério que não existe
    Given o critério com nome "P1" não esteja no sistema
    When eu crio o critério "P1"
    Then o critério com nome "P1" é devidamente adicionado no sistema

#Controller Scenario
  Scenario: Cadastrar um critério que já existe
    Given o critério com nome "P1" já esteja no sistema
    When eu crio o critério "P1"
    Then o sistema não faz nada

#GUI Scenario
  Scenario: Erro ao cadastrar um critério já existente
    Given eu estou na página "Adicionar Critério"
    When eu clico em adicionar o critério "P1"
    And o critério "P1" já existe
    Then eu deveria ver uma mensagem de erro relacionada ao cadastramento do critério

#GUI Scenario
  Scenario: Adicionar critério não existente
    Given eu estou na página "Adicionar Critério"
    When eu preencho o campo Nome com o nome "P1"
    And eu clico no botão Finalizar
    Then eu deveria ver o novo critério adicionado na lista de critérios