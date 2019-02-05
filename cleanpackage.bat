
REM TRY TO CREATE SCRIPT FOR AUTOMATIC START OF COMMANDS
cd auth-service & mvn -U clean package & cd backend-service & mvn -U clean package
start cd ../backend-service-2 & mvn -U clean package &
start cd ../config-service & mvn -U clean package &
start cd ../dashboard-service & mvn -U clean package &
start cd ../gateway-service & mvn -U clean package
