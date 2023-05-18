FROM openjdk:8

#作者信息
MAINTAINER huan.gao

RUN mkdir -p /server

COPY target/gh-demo-server.jar /server/app.jar

#暴露端口
EXPOSE 9999

#启动 如果启动时想要添加额外的配置可以通过docker run -e PARAM="-Dserver.port=8000 -Dspring.profiles.active-prod"
ENTRYPOINT ["/bin/bash","-c","java -Dfile.encoding=utf8 ${PARAM} -jar /server/app.jar"]