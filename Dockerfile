FROM jenkins/jenkins:lts-jdk17

USER root

# Maven
RUN apt-get update && apt-get install -y maven && rm -rf /var/lib/apt/lists/*

# Docker CLI внутри Jenkins — пригодится, если понадобится дергать docker-команды из pipeline
RUN apt-get update && curl -fsSL https://get.docker.com | sh

USER jenkins