Introduction

put following configuration into your pom.xml. and then use mvn jiandan:gen to generate code. and use mvn jiandan:clean to clean up. 

			<plugin>
				<groupId>jiandan</groupId>
				<artifactId>jiandan-maven-plugin</artifactId>
				<version>0.1.1-RELEASE</version>
			</plugin>
			
configuration is optional. available configurations are following . in-line are default values.

<configuration>
<subDaoPkg>generated.dao</subDaoPkg> 
<subRestPkg>generated.services</subRestPkg>
<excludes>codegen,MainMojo<excludes> <!-- keyword in file path that will be ignored. comma separated.-->
</configuration>