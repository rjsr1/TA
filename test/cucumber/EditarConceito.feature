Feature: Editar Conceito
  Como o professor
  Eu quero editar os conceitos dados em vários critérios aos meus alunos
  Para que possa corrigir quaisquer erros cometidos

#Controller Scenario
Scenario: Alterar conceito de um aluno
  Given tenha cadastrado as informações: aluno "Marcos Antônio", login "ma2", tenha o conceito "MANA", no critério "X", de origem "Prova 2", da data "11/11/11"
  When eu modificar o conceito "MANA" para "MA", no critério "X", de origem "Prova 2", da data "11/11/11"
  Then o sistema fará a modificação

#Controller Scenario
Scenario: Remover conceito de um aluno
  Given tenha cadastrado as informações: aluno "Marcos Antônio", login "ma2", tenha o conceito "MANA", no critério "X", de origem "Prova 2", da data "11/11/11"
  When eu deletar o conceito "MANA", no critério "X", de origem "Prova 2", da data "11/11/11"
  Then o sistema irá deletar o conceito

#Controller Scenario
Scenario: Alterar um conceito para um não existente de um aluno
  Given tenha cadastrado as informações: aluno "Marcos Antônio", login "ma2", tenha o conceito "MANA", no critério "X", de origem "Prova 2", da data "11/11/11"
  When eu modificar o conceito "MANA" para "10", no critério "X", de origem "Prova 2", da data "11/11/11"
  Then o sistema não fará a modificação

#GUI Scenario
Scenario: Alterar um conceito para um não existente de um aluno
  Given tenha cadastrado as informações: aluno "Marcos Antônio", login "ma2", tenha o conceito "MANA", no critério "X", de origem "Prova 2", da data "11/11/11"
  When eu solicito ao sistema modificar o conceito "MANA" para "10"
  Then aparecerá uma mensagem de erro relacionada ao conceito inexistente