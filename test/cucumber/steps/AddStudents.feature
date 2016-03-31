Feature: Cadastrar aluno
  As o professor
  I want to cadastrar alunos no sistema
  So

#Cenário Controler
  Scenario: Cadastrar novo aluno
    Given o aluno "Roberto Alves" com o login "ra" não esta cadastrado no sistema
    When eu cadastro o aluno "Roberto Alves" com o login "ra"
    Then o aluno "Roberto Alves" com o login "rc" é cadastrado no sistema

#Cenário GUI
  Scenario: Mensagem do cadastramento de novo aluno
    Given eu estou na página para adicionar alunos
    And o aluno "Roberto Alves" com o login "ra" não esta no sistema
    When eu adiciono o aluno "Roberto Alves" com o login "ra"
    And finalizo o cadastramento dos alunos
    Then uma mensagem com o nome de "Roberto Alves" e o login "ra" me avisa que o aluno foi cadastrado

#Cenário Controler
  Scenario: Cadastrar um aluno duas vezes
    Given o aluno "Roberto Alves" com o login "ra" esta cadastrado no sistema
    When eu cadastro o aluno "Roberto Alves" com o login "ra"
    Then o aluno "Roberto Alves" com o login "rc" não é cadastrado no sistema

#Cenário GUI
  Scenario: Mensagem de erro ao cadastrar um aluno duas vezes
    Given  eu estou na página para adicionar alunos
    And o aluno "Roberto Alves" com o login "ra" não esta no sistema
    When eu adiciono o aluno "Roberto Alves" com o login "ra"
    And finalizo o cadastramento dos alunos
    Then um mensagem de erro nome de "Roberto Alves" e o login "ra" me avisa que o aluno não pode ser cadastrado