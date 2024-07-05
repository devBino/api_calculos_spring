# Projeto para estudo de Caso sobre Desenvolvimento e Implantação de uma Solução de Software

## Introdução
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

### Pipelines CI/CD
|Release | Status |
|--------|--------|
|Release/24.07|![CI Release/24.07](https://github.com/devBino/api_calculos_spring/actions/workflows/current_release_ci.yml/badge.svg?branch=release/24.07) |
|Release/24.07.2|![CI Release/24.07.2](https://github.com/devBino/api_calculos_spring/actions/workflows/current_release_ci.yml/badge.svg?branch=release/24.07.2) |


## 1 - Criação dos Microsserviços

## 2 - Criação das Aplicações FrontEnd

## 3 - Pipelines Automatizados de CI/CD

## Conclusão