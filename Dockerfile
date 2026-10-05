FROM jenkins/jenkins:lts-jdk17

USER root

# Maven
RUN apt-get update && apt-get install -y maven && rm -rf /var/lib/apt/lists/*

# Docker CLI внутри Jenkins — пригодится, если понадобится дергать docker-команды из pipeline
RUN apt-get update && curl -fsSL https://get.docker.com | sh

# GitVersion — self-contained бинарь, отдельный .NET runtime не нужен
ARG GITVERSION_VERSION=6.8.2
ENV DOTNET_SYSTEM_GLOBALIZATION_INVARIANT=1
RUN mkdir -p /opt/gitversion \
    && curl -fsSL "https://github.com/GitTools/GitVersion/releases/download/${GITVERSION_VERSION}/gitversion-linux-x64-${GITVERSION_VERSION}.tar.gz" \
       | tar -xz -C /opt/gitversion \
    && chmod 755 /opt/gitversion/gitversion \
    && ln -s /opt/gitversion/gitversion /usr/local/bin/gitversion

USER jenkins