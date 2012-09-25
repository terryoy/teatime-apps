#!/bin/bash

read proc < .pid_edmund
kill -9 $proc

