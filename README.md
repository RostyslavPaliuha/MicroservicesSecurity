# Demo microservices 
##Author: Rostyslav Paliuha

   
# 0. All FILES ARE NEEDED IN MAIN DIR NEED TO LUNCH AUTOMATICALLY SERVICES
# 0.1 You need:
 - maven 3.5.3
 - jdk 8+
 - docker 18.03+.

# 1. Quick review
## 1.1 **auth-service**
The service to issue the `JWT` token.
- The client POST `{username,password}` to `/login`.
- This service will authenticate the username and password via `Spring Security`,
  generate the token, and issue it to client.

## 1.2 **backend-service**
Provide three simple services:
- `/admin`
- `/user`
- `/guest`
 
## 1.3. **gateway-service**
The `Zuul` gateway:
- Define `Zuul` routes to `auth-service` and `backend-service`.
- Verify `JWT` token.
- Define role-based auth via `Spring Security`:
    - `/login` is public to all.
    - `/backend/admin` can only be accessed by role `ADMIN`.
    - `/backend/user` can only be accessed by role `USER`.
    - `/backend/guest` is public to all.
## 1.4. **discovery-service**
`Consul server`:
   - Register other services to know itch other
## 1.5. **config-service**
`Config server`:
   - Spring Cloud Config provides server and client-side support for externalized configuration in a distributed system.
## 1.6 **dashboard-service**
`Spring boot admin server` - managment tool, check all possible info via actuator and jmx.
## 1.7 **backend-service-2**
  Service include own authorization logic and method security useinf @Preauthorize
  Provide three simple services:
  - `/api/admin` -secured ROLE_ADMIN
  - `/api/user` -secured ROLE_USER or ROLE_ADMIN
  - `/api/guest` -not secured

##1.8 **Sleuth + zipkin**
  Distributed tracing system. Gather timing data needed to troubleshoot latency problems in microservice architectures. 

# Run 

## 1. LUNCH With Docker 
  - Package all services in jar:
  ```bash
  cd auth-service
  mvn -U clean package
  cd ../backend-service
  mvn -U clean package
  cd ../backend-service-2
  mvn  -U clean package
  cd ../config-service
  mvn -U clean package
  cd ../dashboard-service 
  mvn -U clean package
  cd ../gateway-service
  mvn -U clean package
  
  ```
  - Run start.bat script on WIN OS:
```bash
start.bat
```
  - Or start.sh on Unix:
  ```
./start.sh
```


# 3. Verify 
Send post request to endpoint localhost:8080/login to get token. 

Next step use token to send get request to secured endpoints.
```bash
send get request via postman with header "Authorization: Bearer token-you-got" http://localhost:8080/backend/api/user
```
or without token:
```bash
send get request without any header to http://localhost:8080/backend/api/guest
```

## 4. MANAGEMENT VIA SPRING BOOT ADMIN

  - After start all services, go in browser to ${HOSTNAME}:9000. It will be relate from your machine settings.
## 5. Tracing management system 
  - Go to address http://{docker-network-host}:9411/zipkin 
  - Inspect trace in UI.
#HOPE THIS README HELPS YOU LUNCH QUICKLY. With best regards.
