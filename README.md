# Assembléia

----

No cooperativismo, cada associado possui um voto e as decisões são tomadas em assembleias, por votação.

----

<img src='https://img.shields.io/github/tag/analuciabolico/assembly.svg' alt='latest semver version' /> <img src='https://img.shields.io/github/issues/analuciabolico/assembly.svg' alt='open issues badge' /> <img src='https://img.shields.io/github/issues-pr/analuciabolico/assembly.svg' alt='open pull requests badge' />

## Dependências

O que você precisa para instalar o software e como instalá-lo:

- [Java 14](https://computingforgeeks.com/install-oracle-java-openjdk-14-on-ubuntu-debian-linux/)
- [PostgreSQL 12](https://www.postgresql.org/download/)
- Gradle 6.3

- Obs .: O gradle está incorporado no projeto e não requer instalação.

----

## Configurando banco de dados local

 Veja o arquivo /src/main/resources/application-dev.yaml para saber qual usuário e senha será utilizado.

Você precisará criar um banco de dados vazio antes de iniciar a aplicação. O nome do banco padrão é assembly (veja o arquivo de configuração mencionado anteriormente). Para criá-lo, execute:

```sh
sudo -u postgres psql -c 'CREATE DATABASE assembly'
```

----

## Usando Postgres via Docker

Caso não queira instalar o Postgres na sua máquina, é possível utilizá-lo via Docker:

1. Crie estas pastas:

```sh
mkdir -p docker/postgres
```

2. Instale o Docker via Snap:

```sh
sudo snap install docker
```

Se você não tiver o snap instalado, execute esse comando no terminal:

```sh
sudo apt update
sudo apt install snapd
```

3. Crie e inicie o container (Substitue {user} pelo seu usuário:) :

```sh
sudo docker run -d --name postgres12 -e POSTGRES_PASSWORD=root -e PGDATA=/var/lib/postgresql/data/pgdata -v /home/{user}/docker/postgres:/var/lib/postgresql/data postgres -h 127.0.0.1
```

----

## Após isso para executar o projeto, execute esse comando

```sh
./gradlew bootRun
```

## E para rodar os testes, execute esse comando 

```sh
./gradlew test
```

----

- [Wiki](https://github.com/analuciabolico/assembly/wiki/Assembly)

----

## Autora

* **Ana Lúcia Bolico** - [GitHub](https://github.com/analuciabolico)

