#!/bin/bash
SHELL_FOLDER=$(cd "$(dirname "$0")";pwd)
echo $SHELL_FOLDER
rm -f $SHELL_FOLDER/src/main/java/com/example/demo/entity/*
rm -f  $SHELL_FOLDER//src/main/java/com/example/demo/mapper/*
rm -f  $SHELL_FOLDER/src/main/resources/mybatis/mapper/*

./gradlew mybatisGenerate

echo "mybatisGenerate package success!"
