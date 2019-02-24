#FROM castlemock/castlemock
#VOLUME /tmp
#COPY mocks/test.xml .
#RUN cd /usr/local/tomcat
#RUN curl -X POST "http://localhost:8080/castlemock/api/rest/core/project/import?type=SOAP" -H "accept: */*" -H "Authorization: Basic YWRtaW46YWRtaW4=" -H "Content-Type: multipart/form-data" -F "file=@test.xml;type=text/xml"
#EXPOSE 8080

FROM castlemock/castlemock
VOLUME /tmp
COPY mocks/project-soap-aa.xml .
COPY mocks/project-soap-creditCards.xml .
COPY mocks/project-soap-hiltonBook.xml .
RUN cd /usr/local/tomcat
CMD ["startup.sh"] 
EXPOSE 8080
RUN curl -X POST "http://localhost:8080/castlemock/api/rest/core/project/import?type=SOAP" -H "accept: */*" -H "Authorization: Basic YWRtaW46YWRtaW4=" -H "Content-Type: multipart/form-data" -F "file=@project-soap-aa.xml;type=text/xml"
RUN curl -X POST "http://localhost:8080/castlemock/api/rest/core/project/import?type=SOAP" -H "accept: */*" -H "Authorization: Basic YWRtaW46YWRtaW4=" -H "Content-Type: multipart/form-data" -F "file=@project-soap-creditCards.xml;type=text/xml"
RUN curl -X POST "http://localhost:8080/castlemock/api/rest/core/project/import?type=SOAP" -H "accept: */*" -H "Authorization: Basic YWRtaW46YWRtaW4=" -H "Content-Type: multipart/form-data" -F "file=@project-soap-hiltonBook.xml;type=text/xml"