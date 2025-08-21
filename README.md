# practice_interview
This is an app which helps me to ilustrate the back and   front  services

## NOTES
backend 

---------------------------------------------- sqlserver
```
<dependency>
			<groupId>com.microsoft.sqlserver</groupId>
			<artifactId>mssql-jdbc</artifactId>
			<version>13.2.0.jre11</version>
		</dependency>
```




---------------------------------------------- h2-console
```
<dependency>
			<groupId>com.h2database</groupId>
			<artifactId>h2</artifactId>
			<scope>runtime</scope>
		</dependency>
```		
		
http://localhost:8080/h2-console





## If need change bd engines, I should use profiles
```
spring:
  profiles:
    active: sqlserver
  application:
    name: test
```	
in pom 
- web 
- jpa
- engine jdbc
- log4j2


## Structure
- entities
- repositories
- services (impl)
- controllers
- dtos
- enums
- validations
- clients


## From Dto to model
```
BeanUtils.copyProperties(solDto, solModel)
```

## SpecificationTemplate
```
<dependency>
	<groupId>net.kaczmarzyk</groupId>
	<artifactId>specification-arg-resolver</artifactId>
	<version>3.0.1</version>
</dependency>
```		
and it needs `ResolverConfig extends WebMvcConfigurationSupport`		

and en repo implements `JpaSpecificationExecutor<Model>`

in services and controller spec and page for getall



## appClient

in ClassClient call to other mservice

it needs class `RestTemplateConfig`

and utilService to createUrl


for sp `@Query(value = "EXEC sp_get_color_by_name :name", nativeQuery = true)` and create projections

```
mvn clean package -DskipTests
```
```
docker network connect isspol-web_dev-net sqlserver-dev
```
