#!/bin/bash

nohup java -cp ./lib/pircbot.jar:./lib/irc-edmund.jar teatime.robot.edmund.irc.IRCEdmund >> ./log/edmund.log &
echo $! > .pid_edmund
