#!/bin/bash

read proc < .pid_splunge
kill -9 $proc

