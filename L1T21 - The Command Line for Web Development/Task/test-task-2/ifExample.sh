#!/bin/bash

# Shell script for Task 2

# Checks whether the folder "newFolder" exists, and if it does not, it creates the folder "ifFolder".

if [ -d "newFolder" ]; then
    mkdir ifFolder
fi

# Checks whether the folder "ifFolder" exists, and if it does, it creates the folder "hyperiondev". If the folder "ifFolder" does not exist, it creates the folder "reactProjects".
if [ -d "ifFolder" ]; then
    mkdir hyperiondev
else 
    mkdir reactProjects
fi