# analysis

[![Build Status](https://jenkins.din-cloud.com/buildStatus/icon?job=mydnacodes%2Fanalysis%2Fmaster&subject=CI/CD)](https://jenkins.din-cloud.com/job/mydnacodes/job/analysis/job/master/)

### Library
```xml
<dependency>
    <groupId>codes.mydna</groupId>
    <artifactId>analysis-lib</artifactId>
    <version>${analysis.version}</version>
</dependency>
```

### Docker

*Note: This service requires DB.*

Pull docker image:
```bash
docker pull mydnacodes/analysis
```

Run docker image:
```bash
docker run -d -p <PORT>:8080 
    -e KUMULUZEE_DATASOURCES0_CONNECTIONURL=jdbc:postgresql://<DB_HOST>:<DB_PORT>/analysis
    -e KUMULUZEE_DATASOURCES0_USERNAME=<DB_USERNAME> 
    -e KUMULUZEE_DATASOURCES0_PASSWORD=<DB_PASSWORD> 
    --name analysis-service
    mydnacodes/analysis
```
