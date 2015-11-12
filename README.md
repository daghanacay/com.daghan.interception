# Method Interception In OSGi

Objective of this project is to understand the method interception in java in general and in OSGi in particular. There are multiple ways of doing method interception. The dimensions investigated here are 

| MVN | Enroute/OSGi|
|Agent|Factory|Agent|Factory|
|----|-----|
Java Proxy|none|none|none|yes|


* Technology: We consider the following technologies
** Standard Java proxy libraries in JavaJRE, this are quite basic and can only be used creating proxies for interfaces. It does not have support for rebasing or transforming existing code. However it is good startig point for understanding proxying 
** Byte buddy libraries: these are designed as a DSL for defining, rebasing, subclassing existing code. it is quite powerfull and has a healthy activity.

The technologies we have not included in this release are 
** ASM: byte level coding
** JavaAssist: string level code generation without type checking and not up to date with the latest javac specs
** CGLIB: one of the first libraries for binary manipulation but recent development is stopped and support for future JVM is not certain

Also following frameworks are not considered
** AspectJ aspects: they are not easy to integrate with OSGi and is over powerful for our purpose
** Spring intercept: build on top of AspectJ and brings annotations on top of aspectJ. Again this is even more heavy weight then we expect to use in our simple OSGi based project

* Method: we consider the following methods
** Proxy factories: replaces "new" in java and used to get instances through proxy factory.
** Java Agents: starts the JVM start up and uses JVM agents and instrumentation such that even the "new" is used the objects are intercepted by the framework. We will also consider annotations in this context


Build System: we consider the following build systems (frameworks)
** Maven: just a dependency management system at build time. works on vanila Java class loader
** Enroute: build system that extends MVN and deploys on OSGi framework taht can also handle runtime dependecy managagement.
