FROM openjdk:8

#作者信息
MAINTAINER huan.gao

ARG JAR_FILE=target/gh-jasperreports-server.jar

COPY ${JAR_FILE} app.jar

#暴露端口
EXPOSE 1001

#启动 如果启动时想要添加额外的配置可以通过docker run -e PARAM="-Dserver.port=8000 -Dspring.profiles.active-prod"
ENTRYPOINT ["/bin/bash","-c","nohup java -Dfile.encoding=utf8 ${PARAM} -jar app.jar"]