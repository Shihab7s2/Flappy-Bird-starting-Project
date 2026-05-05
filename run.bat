@echo off
if not exist bin mkdir bin
javac -d bin src\App.java src\User.java src\UserManager.java src\LoginFrame.java src\RegisterFrame.java src\MenuFrame.java src\GameObject.java src\Bird.java src\Pipe.java src\FlappyBird.java
if errorlevel 1 pause & exit
java -cp bin App
pause
