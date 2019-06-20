#!/bin/dash
./gradlew sonarqube \
  -Dsonar.projectKey=CSJunio \
  -Dsonar.organization=segismundo-github \
  -Dsonar.host.url=https://sonarcloud.io \
  -Dsonar.login=d4b484c113fb91c1ce7e28277bd73cd744fe276c