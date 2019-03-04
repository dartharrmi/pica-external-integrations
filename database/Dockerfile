# Derived from offical MySQL images
FROM mysql

# Adds a database
ENV MYSQL_DATABASE dannCarlton

COPY ./scripts/dannCarlton_export.sql /docker-entrypoint-initdb.d/
