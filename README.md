# ksams [![Build Status](https://travis-ci.org/chesteric31/ksams.svg?branch=master)](https://travis-ci.org/chesteric31/ksams)
Kotlin Saint Seiya Armors Management System
-------------------------------------------
This project is a Kotlin API to manage all Saint Seiya Armors.

## <a name="configure"></a> Configure the dev database

- Run our database with Docker. For example, for postgresql use `docker-compose -f src/main/docker/postgresql.yml up`
- Run the app via `./gradlew bootRun --args='--spring.profiles.active=dev'`
- Go to http://localhost:8081/api/v2/armors/