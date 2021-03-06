# Method Interception In OSGi

Objective of this project is to understand the method interception in java in general and in OSGi in particular. There are multiple ways of doing method interception. The dimensions investigated here are 

|            |  MVN                 |         | Enroute/OSGi |         |
| ---------- | -------------------- | ------- | ------------ | ------- |
|            | Agent                | Factory | Agent        | Factory |
| ---------- | -------------------- | ------- | ------------ | ------- |
| Java Proxy | none                 | none    | none         | yes     |
| Byte Buddy | ByteBuddy and Custom | yes     | Custom Agent | yes     |

Where "none" means not investigates in this project.

## Technology 

We consider the following technologies
* Standard Java proxy libraries in JavaJRE, this are quite basic and can only be used creating proxies for interfaces. It does not have support for rebasing or transforming existing code. However it is good startig point for understanding proxying 
* Byte buddy libraries: these are designed as a DSL for defining, rebasing, subclassing existing code. it is quite powerfull and has a healthy activity.

The technologies we have not included in this release are 
* ASM: byte level coding
* JavaAssist: string level code generation without type checking and not up to date with the latest javac specs
* CGLIB: one of the first libraries for binary manipulation but recent development is stopped and support for future JVM is not certain

Also following frameworks are not considered
* AspectJ aspects: they are not easy to integrate with OSGi and is over powerful for our purpose
* Spring intercept: build on top of AspectJ and brings annotations on top of aspectJ. Again this is even more heavy weight then we expect to use in our simple OSGi based project

## Method 

We consider the following methods
* Proxy factories: replaces "new" in java and used to get instances through proxy factory.
* Java Agents: starts the JVM start up and uses JVM agents and instrumentation such that even the "new" is used the objects are intercepted by the framework. We will also consider annotations in this context


## Build System 

We consider the following build systems (frameworks)
* Maven: just a dependency management system at build time. works on vanila Java class loader
* Enroute: build system that extends MVN and deploys on OSGi framework taht can also handle runtime dependecy managagement.

# Requirements

we have consodered the following requirements

1. We want to intercept every method independent of the method signature
2. We want to provide annotations to software developers that use our framework. Custom annotations and relevant interceptors should be easy to extend
3. We prefer want to stay away from the developer's ways. That is interception should be transparent to the developers such that the developers do not need to learn the framework except for the annotations.
4. Framework should support dependency injection such that if the developers want to use more intricate intercaption functionality through proxy factories they should be able to do so
5. The framework should be easy extend either by annotatins or proxy factory injection.
