[![English Translation](https://img.shields.io/badge/English%20Translation-2d314a?style=for-the-badge)](https://github.com/gabriellabueno/controle-presenca-eventos/blob/main/README-translation.md) 


<div align="center">
  
# 📆 Event Track </h1>

</div>

<a href="https://github.com/gabriellabueno/controle-presenca-eventos">
 <img src="https://github.com/gabriellabueno/controle-presenca-eventos/blob/main/imagens/logo.png" width="250px" alt="Logo do aplicativo" align="right"> 
</a>

O projeto "Event Track" é um aplicativo mobile para dispositivos Android. Desenvolvido em Java, utilizando o padrão de projeto MVC e o padrão DAO (Data Access Object) para a camada de dados. 

O aplicativo tem o objetivo de atuar como uma ferramenta para controle de presença em eventos, com persistência de dados. O aplicativo utiliza a câmera do dispositivo para ler um QR Code e validar o ingresso de um evento, para então registrar a presença do participante no banco de dados. Para que a aplicação registre corretamente a presença via leitura de QR Code, este deve retornar as informações em formato `.json`.

> Projeto desenvolvido para a disciplina de Programação para Dispositivos Móveis.

 
## ✔️ Funcionalidades

- **Cadastro de Eventos**: Permite o cadastro, atualização e exclusão de eventos. Também é possível definir o status de um evento como "Aberto" ou "Encerrado".
- **Controle de Participantes:** Permite o cadastro e controle de participantes de um evento, o registro da presença pode ser feito de forma manual ou pela leitura de um QR Code com as informações do participante.

## :hammer_and_wrench: Tecnologias Utilizadas

- **Java:** Linguagem de programação para o desenvolvimento do aplicativo Android.
- **XML:** Extensible Markup Language; linguagem de marcação utilizada no Front-end.
- **MySQL:** Sistema de Gerenciamento de Banco de Dados (SGBD) do projeto.
- **Android SDK:** Software Development Kit; conjunto de ferramentas e bibliotecas para a construção da interface e funcionalidades do aplicativo.
- **Padrão MVC:** Model-View-Controller; organizar a estrutura do código em camadas.
- **Padrão DAO:** Data Access Object; gerenciar a persistência de dados de forma eficiente.
- **Biblioteca ZXing ("Zebra Crossing"):** Biblioteca Java para scaneamento de QR Code.
- **JSR 353:** API Java para processamento de dados em formato .json, foi utilizado a classe "JSONObject".


<h2 align="center">Interface</h2>

<div align="center">

### Home 

<img src="https://github.com/gabriellabueno/controle-presenca-eventos/blob/main/imagens/home.png" width="303px" alt="Tela Inicial">  

<img src="https://github.com/gabriellabueno/controle-presenca-eventos/blob/main/imagens/drawer.png" width="300px" alt="Menu de opções">  


### Cadastro de Eventos

<img src="https://github.com/gabriellabueno/controle-presenca-eventos/blob/main/imagens/cadastro-evento.png" width="303px" alt="Cadastro de Evento">  

<img src="https://github.com/gabriellabueno/controle-presenca-eventos/blob/main/imagens/eventos-cadastrados.png" width="300px" alt="Eventos Cadastrados">  


### Manutenção de Eventos

<img src="https://github.com/gabriellabueno/controle-presenca-eventos/blob/main/imagens/popup.png" width="300px" alt="Opções manutenção de Evento">  

<img src="https://github.com/gabriellabueno/controle-presenca-eventos/blob/main/imagens/atualizar-evento.png" width="300px" alt="Atualizar Evento">  

<br>
<br>

*Visualização de Eventos Encerrados*

<img src="https://github.com/gabriellabueno/controle-presenca-eventos/blob/main/imagens/evento-encerrado.png" width="303px" alt="Evento Encerrado">  

<img src="https://github.com/gabriellabueno/controle-presenca-eventos/blob/main/imagens/atualizar-evento-encerrado.png" width="300px" alt="Atualizar Evento Encerrado">  


### Check-in de Participantes em Evento

<img src="https://github.com/gabriellabueno/controle-presenca-eventos/blob/main/imagens/check-in-participante.png" width="300px" alt="Check-in de Participante">  

<img src="https://github.com/gabriellabueno/controle-presenca-eventos/blob/main/imagens/lista-participantes.png" width="303px" alt="Lista de Participantes">  


</div>

<div align="center">

## ⬇️ Baixe o app!

[![APK do aplicativo](https://img.shields.io/badge/Baixar%20APK-black?style=for-the-badge&logo=android)](https://github.com/gabriellabueno/controle-presenca-eventos/blob/main/apk/event-track.apk)  

</div>
