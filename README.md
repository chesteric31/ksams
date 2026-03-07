# ksams

Kotlin Saint Seiya Armors Management System
-------------------------------------------
This project is a Kotlin API to manage all Saint Seiya Armors.

## <a name="configure"></a> Dev env

- Run our database with Docker. For example, for postgresql use `docker compose -f src/main/docker/postgresql.yml up -d`
- Run the app via `./gradlew bootRun --args='--spring.profiles.active=dev'`
- Go to https://localhost:8081/api/v2/armors/

## Build and push Docker image

This project uses [Jib](https://github.com/GoogleContainerTools/jib) to build optimized Docker images without a Docker daemon.

### Using Docker credentials helper (recommended)

```bash
./gradlew jib --image=ericbinard/ksams:x.y.z
```

### Using environment variables

```bash
export JIB_TO_AUTH_USERNAME=your_username
export JIB_TO_AUTH_PASSWORD=your_password
./gradlew jib --image=ericbinard/ksams:x.y.z
```

### Build locally without pushing

```bash
./gradlew jibDockerBuild --image=ericbinard/ksams:x.y.z
```

