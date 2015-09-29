# TA
teaching assistant system

grails version: 2.4.3

JDK 1.7.0_65 SDK 7 (1.8 Não funciona)

Faça o download do chromedriver compatível com sua máquina e coloque ele na pasta chromedrivers.

Em GebConfig.groovy setar caminho do chromeDriver no File

Mark as Test Source todas as subpastas imediatas de test (não as subpastas das subpastas) 

Run configurations:

Grails:TA

  run-app

Cucumber:TA

(IntelliJ) Para rodar os testes, crie uma configuração do grails com a seguinte linha de comando: 

test-app -Dgeb.env=chrome functional:cucumber

Production:
https://ancient-refuge-7019.herokuapp.com/
