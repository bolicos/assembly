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

 Veja o arquivo [application-dev.yml](https://github.com/analuciabolico/assembly/blob/master/src/main/resources/application-dev.yaml) para saber qual usuário e senha será utilizado.

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

3. Crie e inicie o container (Substitua {user} pelo seu usuário:) :

```sh
sudo docker run -d --name postgres12 -e POSTGRES_PASSWORD=root -e PGDATA=/var/lib/postgresql/data/pgdata -v /home/{user}/docker/postgres:/var/lib/postgresql/data postgres --add-host postgres12
```

----

## Após instalar e configurar as dependecias para executar o projeto, execute estes passos


1. Abra um terminal e rode este comando para baixar o projeto:
```sh
git clone https://github.com/analuciabolico/assembly.git
```

2. Entre na pasta do projeto:
```sh
cd ./assembly
```

3. Dentro da pasta do projeto rode este comando para iniciar a aplicação:
```sh
./gradlew bootRun
```

## E para rodar os testes, execute esse comando

```sh
./gradlew test
```

----

## Wiki com infos adicionais

- [Wiki](https://github.com/analuciabolico/assembly/wiki/Assembly)

----

## Autora

* **Ana Lúcia Bolico** - [GitHub](https://github.com/analuciabolico)

