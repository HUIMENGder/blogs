mvn install:install-file -Dfile=./mk-parent-1.4.0.pom -DgroupId=com.mk.base \
-DartifactId=mk-parent -Dversion=1.4.0 -Dpackaging=pom

mvn install:install-file -Dfile=./mk-spg-common-utils-2.2.2-WB20230503.jar -DgroupId=com.mk.spg \
-DartifactId=mk-spg-common-utils -Dversion=2.2.2-WB20230503 -Dpackaging=jar

mvn install:install-file -Dfile=./mk-spg-entity-apis-1.3.6.jar -DgroupId=com.mk.spg \
-DartifactId=mk-spg-entity-apis -Dversion=1.3.6 -Dpackaging=jar

mvn install:install-file -Dfile=./mk-spg-permission-user-1.0.7.jar -DgroupId=com.mk.spg \
-DartifactId=mk-spg-permission-user -Dversion=1.0.7 -Dpackaging=jar