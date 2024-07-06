<h1><center>Estudo de Caso sobre Desenvolvimento e Implantação de uma Solução baseada em Microsserviços</center></h1>

<b>OBS!!! </b> <br> Documento em evolução, ultima atualização na issue https://github.com/devBino/api_calculos_spring/issues/186

## Pipelines CI/CD
|Release | Status |
|--------|--------|
|Release/24.07|![CI Release/24.07](https://github.com/devBino/api_calculos_spring/actions/workflows/current_release_ci.yml/badge.svg?branch=release/24.07) |
|Release/24.07.1|![CI Release/24.07.2](https://github.com/devBino/api_calculos_spring/actions/workflows/current_release_ci.yml/badge.svg?branch=release/24.07.1) |
|Release/24.07.2|![CI Release/24.07.2](https://github.com/devBino/api_calculos_spring/actions/workflows/current_release_ci.yml/badge.svg?branch=release/24.07.2) |

## Apresentação do Projeto
<p style="text-align: justify;">
O presente projeto consiste em um estudo de caso cujo objetivo é cientificamente contemplar de forma mais abrangente o ciclo de desenvolvimento de um software, desde a primeira linha de código até o deploy em ambiente produtivo.
</p>

<p style="text-align: justify;">
Para isso, não buscaremos criar uma aplicação robusta, pois esse não é o objetivo desse estudo. Então, o business será em torno de uma calculadora bem simples. Serão criados microsserviços distintos para receber os parâmetros dos cálculos, bem como processá-los. Além disso, será seprado um espaço no repositório para criação das aplicações Frontend que irão consumir esses microsserviços.
</p>

<p style="text-align: justify;">
Para buscar contemplar o caráter mais abrangente nesse experimento, será configurado um pipeline de CICD, 100% automático. O objetivo desse pipeline é enviar automaticamente as novas features para a VPS, otimizando tempo e buscando manter o foco no desenvolvimento e na qualidade da solução.
</p>

<p style="text-align: justify;">
Além disso, para ser mais abrangente no desenvolvimento da solução, buscaremos respeitar o que manda o <b>Gitflow</b>. Será considerada e levada muito a sério a hierarquia entre as branchs main(master), releases, develop e então features.
</p>

<p style="text-align: justify;">
Tenho certeza que após o término do presente estudo, será apresentada aqui uma visão abrangente e muito profissional no que diz respeito a não só desenvolver uma solução, mas também sobre implantá-la e dar continuidade de maneira eficiente.
</p>


## 1 - Microsserviços Backend

<p style="text-align: justify;">
Para criação dos Microsserviços <b>foram escolhidas as tecnologias</b> Java com Spring Boot e MySql. Foram usados algumas bibliotécas padrões no desenvolvimento de APIs, tais como Spring JPA, Security, Flywaydb entre outras.
</p>

<p style="text-align: justify;">
Foram <b>implementados 2 microsserviços</b> para trabalharem em conjunto e atendenderem a demanda vinda do client para processamento de calculos. A API Rest no sub diretório <b>api-rest</b> é o serviço responsável por receber os parâmetros dos calculos, ela pode receber também uploads de arquivos .csv para salvar parâmetros de calculos em lote. Além de receber as demandas vindas do client, ele á capaz de retornar os dados dos calculos após terem sido processados, isso inclui os históricos gerados pelo serviço de processamento.
</p>
<p style="text-align: justify;">
Por isso, o segundo serviço implementado encontra-se no sub diretório <b>calculo-processor</b>. Esse serviço é agendado e responsável por identificar os novos calculos recebidos através do serviço da api-rest. Ele então identifica esses novos registros, os processa e cria históricos resultantes do processamento.
</p>

<p style="text-align: justify;">
Foi utilizado Spring JPA bem como o Flywaydb, para <b>facilitar a evolução do banco de dados</b>. No tocante as tabelas, optamos por trabalhar com 5 entidades para viabilizar a persistência dos dados trafegados entre o client e services. As tabelas utilizadas foram: tb_usuarios, tb_calculos, tb_calculohist, tb_anexos e tb_anexohist. A database name escolhida foi: db_calculos.
</p>

<p style="text-align: justify;">
Foi utilizado o postman para criação de <b>fluxos automatizados</b>, além disso foram criados <b>testes unitários com JUnit</b>. Estou considerando migrar os testes do Postman para o RestAssured, em futuras features para deixar os testes de integração e unitários no mesmo escopo do Java.
</p>

<p style="text-align: justify;">
No que diz respeito as <b>funcionalidades dos microsserviços</b> cobertas pelos testes, podemos resumí-las da seguinte maneira: <br/>
</p>

1. Client envia requisição desejando obter o resultado de um calculo
2. Microsserviço api-rest salva o calculo
3. Microsserviço calculo-processor processa o calculo recém criado gerando os históricos
4. Client pode enviar requisição para consultar dados do calculo recém processado
5. Microsserviço api-rest devolve os dados do calculo
6. Client pode enviar requisição para realizar uma atualização no calculo
7. Microsserviço calculo-processor processa o calculo recém atualizado gerando os históricos
8. Client envia requisição para consultar dados sobre o calculo recém processado
9. Client pode enviar um arquivo para processar calculos em lote
10. Microsserviço api-rest salva o novo arquivo anexo no banco
11. Microsserviço calculo-processor processa o arquivo anexo recém criado efetuando os calculos e já os criando na base
12. Client pode enviar requisições para consultar o anexo recém criado
13. Client pode enviar requisições para consultar os calculos criados através do anexo recém criado
14. Client pode enviar requisições para listar todos os calculos de maneira paginada
15. Client pode enviar requisições para listar todos os arquivos anexos de maneira paginada
16. Client pode enviar requisições para listar todos os calculos de maneira paginada de acordo com sinal da operação do calculo
17. Client pode enviar requisição para obter todos os históricos de um calculo
18. Client pode enviar requisição para obter todos os históricos de um arquivo anexo
19. Client pode enviar requisição para deletar calculos

<p style="text-align: justify;">
Um ponto importante a destacar sobre o microsserviço <b>calculo-processor</b>, foi que o processamento dos calculos foi implementado para ser Multi-Threading além de respeitar as Transactions com banco de dados. Isso foi feito de maneira padronizada de acordo com Spring.
</p>

<p style="text-align: justify;">
Outro aspecto muito importante foi o uso do Docker e docker-compose para levantar facilmente a stack das aplicações tanto no ambiente localhost quanto em ambiente produtivo.
</p>

<p style="text-align: justify;">
Durante o ciclo de vida desse projeto, esse documento será atualizado com mais pontos importantes, mas por hora concluo esse tópico tendo implementado os microsserviços respeitando as tradicionais abordagens tais como JsonWebToken, Controllers, Models, Repositories, VOs, Converters, Configs Beans, Testes Unitários e de Integração, entre outros. Além dessas abordagens tradicionais, alguns tópicos avançados foram trabalhados nesses microsserviços, tais como Transactions, Multi-Threading, EnableAsync, padrão Factory, EnableScheduling entre outros. 
</p>

<p style="text-align: justify;">
Se somar todos esses tópicos implementados na prática ao longo das issues nesse projeto, temos um cenário bastante interessante e abrangente no tocante a microsserviços.
</p>

## 2 - Aplicações FrontEnd
<p style="text-align: justify;">
No repositório desse projeto foi separado um espaço para implementação de aplicações Frontend. Comecei com a implementação básica de uma aplicação em React.
</p>

<p style="text-align: justify;">
Essa aplicação provê uma interface amigável, não muito avançada no tocante a leiaute, porém muito funcional. Todas as funcionalidades cobertas pelos testes foram incluídas nessa aplicação em React.
</p>

<p style="text-align: justify;">
Em futuras issues, posso até considerar evoluir o design da aplicação. No entando preciso lembrar que o objetivo do presente estudo é contemplar o desenvolvimento de uma solução de forma mais abrangente, desde sua implementação até a implantação. A solução proposta aqui foram os microsserviços cujo business é o processamento de calculos.
</p>

<p style="text-align: justify;">
Uma vez que essa solução atenda as regras desse negócio e seja implantada em ambiente produtivo, qualquer aplicação frontend pode se integrar a ela, então como profissional focado em backend nem preciso dizer que o espaço que abri nesse projeto para frontend, é uma espécie de bônus, onde me proponho a sair da zona de conforto.
</p>

<p style="text-align: justify;">
Fico muito feliz em ter obtido êxito nessa tarefa, já que mesmo não sabendo nada sobre React, em 3 dias criei a aplicação que já está publicada em produção. É claro que no tocante ao React, sai de zero conhecimento para o conhecimento básico, mas em 3 dias publiquei uma aplicão em React. E posso evoluir essa solução. Então, com o passar do tempo conforme a necessidade, vou priorizar evoluir as boas práticas com React.
</p>

<p style="text-align: justify;">
Ainda falando sobre frontend, pretendo criar mais algumas aplicações para consumir os microsserviços. Pretendo utilizar Spring MVC, Java Swing, PHP Laravel MVC além de Android. Mas isso será priorizado aos poucos em futuras issues.
</p>


## 3 - Pipelines Automatizados de CI/CD

<p style="text-align: justify;">
Em 2017/18 quando vendi alguns freelances em PHP, meus deploys eram via FTP com Filezila. Quem nunca?
</p>

<p style="text-align: justify;">
Em build...
</p>

## Conclusão
<p style="text-align: justify;">
Em build...
</p>