# analysis

[![Build Status](https://jenkins.din-cloud.com/buildStatus/icon?job=mydnacodes%2Fanalysis-service%2Fmaster&subject=CI/CD)](https://jenkins.din-cloud.com/job/mydnacodes/job/analysis-service/job/master/)

### Docker

*Note: This service requires DB.*

Pull docker image:
```bash
docker pull mydnacodes/analysis
```

Run docker image:
```bash
docker run -d -p <PORT>:8080 
    -e KUMULUZEE_GRPC_CLIENTS0_ADDRESS=<SEQUENCE_BANK_GRPC_CLIENT_URL>
    -e KUMULUZEE_GRPC_CLIENTS0_PORT=<SEQUENCE_BANK_GRPC_CLIENT_PORT>
    -e KUMULUZEE_GRPC_CLIENTS2_ADDRESS=<ANALYSIS_REPORT_GRPC_CLIENT_URL>
    -e KUMULUZEE_GRPC_CLIENTS2_PORT=<ANALYSIS_REPORT_GRPC_CLIENT_PORT>
    -e KEYCLOAK_REALM=<KEYCLOAK_REALM_NAME>
    -e KEYCLOAK_CLIENTID=<KEYCLOAK_CLIENT_ID>
    -e KEYCLOAK_AUTHSERVERURL=<KEYCLOAK_SERVER_URL>
    -e KEYCLOAK_AUTH_CLIENTSECRET=<KEYCLOAK_CLIENT_SECRET>
    --name analysis-service
    mydnacodes/analysis
```
