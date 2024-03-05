# ksams [![Build Status](https://travis-ci.org/chesteric31/ksams.svg?branch=master)](https://travis-ci.org/chesteric31/ksams)
Kotlin Saint Seiya Armors Management System
-------------------------------------------
This project is a Kotlin API to manage all Saint Seiya Armors.

## <a name="configure"></a> Dev env

- Run our database with Docker. For example, for postgresql use `docker compose -f src/main/docker/postgresql.yml up -d`
- Run the app via `./gradlew bootRun --args='--spring.profiles.active=dev'`
- Go to https://localhost:8081/api/v2/armors/

## Build a docker image

- Run `./gradlew jib --image=ericbinard/ksams:x.y.z -Djib.to.auth.username=username '-Djib.to.auth.password=password'`
