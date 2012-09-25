#!/bin/bash

nohup java -cp ./lib/pircbot.jar:./lib/irc-edmund.jar teatime.robot.irc.splunge.IRCSplunge >> ./log/splunge.log &
echo $! > .pid_splunge
