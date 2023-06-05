FROM openjdk:8

#作者信息
MAINTAINER huan.gao

ENV APP_NAME=gh-common-data-redis-example

RUN mkdir -p /app

COPY target/${APP_NAME}.jar /app/${APP_NAME}.jar

#暴露端口
EXPOSE 1002

#启动 如果启动时想要添加额外的配置可以通过docker run -e PARAM="-Dserver.port=8000 -Dspring.profiles.active-prod"
ENTRYPOINT ["/bin/bash","-c","nohup java -Dfile.encoding=utf8 ${PARAM} -jar /app/${APP_NAME}.jar"]