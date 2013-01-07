<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
	xmlns:aop="http://www.springframework.org/schema/aop" xmlns:context="http://www.springframework.org/schema/context"
	xmlns:jee="http://www.springframework.org/schema/jee" xmlns:tx="http://www.springframework.org/schema/tx"
	xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	xsi:schemaLocation="http://www.springframework.org/schema/aop http://www.springframework.org/schema/aop/spring-aop-3.1.xsd         http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans-3.1.xsd         http://www.springframework.org/schema/context http://www.springframework.org/schema/context/spring-context-3.1.xsd         http://www.springframework.org/schema/jee http://www.springframework.org/schema/jee/spring-jee-3.1.xsd         http://www.springframework.org/schema/tx http://www.springframework.org/schema/tx/spring-tx-3.1.xsd">
	<!-- This will automatically locate any and all property files you have 
		within your classpath, provided they fall under the META-INF/spring directory. 
		The located property files are parsed and their values can then be used within 
		application context files in the form of ${'$'}{propertyKey}. -->
	<context:property-placeholder location="classpath*:META-INF/spring/*.properties" />
	<!-- Turn on AspectJ @Configurable support. As a result, any time you instantiate 
		an object, Spring will attempt to perform dependency injection on that object. 
		This occurs for instantiation via the "new" keyword, as well as via reflection. 
		This is possible because AspectJ is used to "weave" Roo-based applications 
		at compile time. In effect this feature allows dependency injection of any 
		object at all in your system, which is a very useful feature (without @Configurable 
		you'd only be able to dependency inject objects acquired from Spring or subsequently 
		presented to a specific Spring dependency injection method). Roo applications 
		use this useful feature in a number of areas, such as @PersistenceContext 
		injection into entities. -->
	<context:spring-configured />
	<!-- This declaration will cause Spring to locate every @Component, @Repository 
		and @Service in your application. In practical terms this allows you to write 
		a POJO and then simply annotate the new POJO as an @Service and Spring will 
		automatically detect, instantiate and dependency inject your service at startup 
		time. Importantly, you can then also have your new service injected into 
		any other class that requires it simply by declaring a field for your service 
		inside the relying class and Spring will inject it. Note that two exclude 
		filters are declared. The first ensures that Spring doesn't spend time introspecting 
		Roo-specific ITD aspects. The second ensures Roo doesn't instantiate your 
		@Controller classes, as these should be instantiated by a web tier application 
		context. Refer to web.xml for more details about the web tier application 
		context setup services. Furthermore, this turns on @Autowired, @PostConstruct 
		etc support. These annotations allow you to use common Spring and Java Enterprise 
		Edition annotations in your classes without needing to do any special configuration. 
		The most commonly used annotation is @Autowired, which instructs Spring to 
		dependency inject an object into your class. -->
	<context:component-scan base-package="${basePkg}">
		<context:exclude-filter expression="org.springframework.stereotype.Controller"
			type="annotation" />
	</context:component-scan>
</beans>
