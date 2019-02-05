start /high cmd /k docker run --net=host -p 8500 consul agent -server -bind 127.0.0.1 -bootstrap-expect 1
start /high cmd /k docker run --net=host -p 9411 openzipkin/zipkin
pause
start /high cmd /k docker-compose -f docker-compose-config.yml up

call tcping.exe -t -s -i 5 10.75.0.2 8345
if (%errorlevel%==2) OR (%errorlevel%==0)
  start /high cmd /k docker-compose -f docker-compose-dashboard.yml up

SET errorlevel=0

call tcping.exe -t -s -i 5 10.75.0.2 8345
if (%errorlevel%==0) OR (%errorlevel%==2)
  start /high cmd /k docker-compose -f docker-compose-gateway.yml up

SET errorlevel=0

call tcping.exe -t -s -i 5 10.75.0.2 8080
if (%errorlevel%==2) OR (%errorlevel%==0)
  start /high cmd /k docker-compose -f docker-compose-auth.yml up

SET errorlevel=0

call tcping.exe -t -s -i 5 10.75.0.2 8081
if (%errorlevel%==2) OR (%errorlevel%==0)
  start /high cmd /k docker-compose -f docker-compose-backend.yml up

SET errorlevel=0

call tcping.exe -t -s -i 5 10.75.0.2 8082
if (%errorlevel%==2) OR (%errorlevel%==0)
  start /high cmd /k docker-compose -f docker-compose-backend-2.yml up

  SET errorlevel=0
exit 0