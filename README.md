RPG MAKER XP on Android

Rmxp4droid was released in 2013.It`s nearly 10 years later.Open source now.

Because the code is old, it is not sure whether the new version of Android can still run.


Source Tips:


This path is the encrypted RGSS file:

baijiacms/rmxp4droid/tree/main/pc/src/jruby/data/ruby.key


The decrypted RGSS files are in this directory:

rmxp4droid/tree/main/pc/src/com/publish/rb/



Rmxp Game Deployment steps:

1. Copy the complete rmxp project to the SD card (including RTP materials, and the game.ini file in the game root directory)

2. Run rmxp4droid, find game.ini and select it

3. Wait for rmxp4droid loading to complete

4. Experience the game


Common Problem:

1. Clicking "exit game" in the rmxp game title menu will cause rmxp4droid to fake death

Answer: you need to add scripts in the RGSS database of the rmxp project and commands in class Scene_Title and  Scene_End class Add rmxp4droid Exit() to exit the game

2. What are the limitations of rmxp4droid on rmxp games

Answer: not all rmxp games are perfectly supported. RGSS custom scripts may cause various problems in rmxp and cause the game to fail to start normally.

3. What is the automatic exit of the program when the progress bar reaches about 80%?

Answer: the remaining 20% is the script file of scripts.rxdata in the rmxp project. If there is a problem with the custom script, it will automatically exit here.



![image text](https://github.com/baijiacms/rmxp4droid/raw/main/0.png)
![image text](https://github.com/baijiacms/rmxp4droid/raw/main/1.png)
![image text](https://github.com/baijiacms/rmxp4droid/raw/main/2.png)
![image text](https://github.com/baijiacms/rmxp4droid/raw/main/3.png)
![image text](https://github.com/baijiacms/rmxp4droid/raw/main/4.png)
![image text](https://github.com/baijiacms/rmxp4droid/raw/main/5.png)
![image text](https://github.com/baijiacms/rmxp4droid/raw/main/6.png)
