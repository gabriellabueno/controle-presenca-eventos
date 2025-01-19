<div align="center">
  
# 📆 Event Track </h1>

</div>

<a href="https://github.com/gabriellabueno/controle-presenca-eventos">
 <img src="https://github.com/gabriellabueno/controle-presenca-eventos/blob/main/imagens/logo.png" width="250px" alt="App Logo" align="right"> 
</a>

The "Event Track" project is a mobile application for Android devices. Developed in Java, using the MVC project pattern and the DAO (Data Access Object) pattern for the data layer.

The app is a tool for attendance control at events, with data persistence. The app uses the device's camera to read a QR Code and validate the event ticket, then registers the participant's presence in the database. For the application to correctly register presence via QR Code reading, it must return the information in `.json` format.

> Project developed for the Mobile Device Programming subject.

## ✔️ Features

- **Event Registration**: Allows registering, updating, and deleting events. It is also possible to set an event's status to "Open" or "Closed".
- **Participant Control**: Allows registering and controlling participants of an event. Presence registration can be done manually or by reading a QR Code with the participant's information.

## :hammer_and_wrench: Technologies Used

- **Java:** Programming language for Android app development.
- **XML:** Extensible Markup Language; markup language used in the Front-end.
- **MySQL:** Database Management System (DBMS) for the project.
- **Android SDK:** Software Development Kit; a set of tools and libraries for building the app's interface and features.
- **MVC Pattern:** Model-View-Controller; organizes the code structure into layers.
- **DAO Pattern:** Data Access Object; manages data persistence efficiently.
- **ZXing ("Zebra Crossing") Library:** Java library for QR Code scanning.
- **JSR 353:** Java API for processing data in `.json` format, using the "JSONObject" class.

<h2 align="center">Interface</h2>

<div align="center">

### Home

<img src="https://github.com/gabriellabueno/controle-presenca-eventos/blob/main/imagens/home.png" width="303px" alt="Home Screen">  

<img src="https://github.com/gabriellabueno/controle-presenca-eventos/blob/main/imagens/drawer.png" width="300px" alt="Options Menu">  


### Event Registration

<img src="https://github.com/gabriellabueno/controle-presenca-eventos/blob/main/imagens/cadastro-evento.png" width="303px" alt="Event Registration">  

<img src="https://github.com/gabriellabueno/controle-presenca-eventos/blob/main/imagens/eventos-cadastrados.png" width="300px" alt="Registered Events">  


### Event Maintenance

<img src="https://github.com/gabriellabueno/controle-presenca-eventos/blob/main/imagens/popup.png" width="300px" alt="Event Maintenance Options">  

<img src="https://github.com/gabriellabueno/controle-presenca-eventos/blob/main/imagens/atualizar-evento.png" width="300px" alt="Update Event">  

<br>
<br>

*View of Closed Events*

<img src="https://github.com/gabriellabueno/controle-presenca-eventos/blob/main/imagens/evento-encerrado.png" width="303px" alt="Closed Event">  

<img src="https://github.com/gabriellabueno/controle-presenca-eventos/blob/main/imagens/atualizar-evento-encerrado.png" width="300px" alt="Update Closed Event">  


### Participant Check-in at Event

<img src="https://github.com/gabriellabueno/controle-presenca-eventos/blob/main/imagens/check-in-participante.png" width="300px" alt="Participant Check-in">  

<img src="https://github.com/gabriellabueno/controle-presenca-eventos/blob/main/imagens/lista-participantes.png" width="303px" alt="Participant List">  


</div>

<div align="center">

## ⬇️ Download the app!

[![App APK](https://img.shields.io/badge/Download%20APK-black?style=for-the-badge&logo=android)](https://github.com/gabriellabueno/controle-presenca-eventos/blob/main/apk/event-track.apk)  

</div>
