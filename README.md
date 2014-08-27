# AeroGear UnifiedPush Core [![Build Status](https://travis-ci.org/aerogear/aerogear-android-core.png)](https://travis-ci.org/aerogear/aerogear-android-core)


AeroGear's Android libraries are built as jar, apklib and aar using [Maven](http://maven.apache.org/) and the [android-maven-plugin](https://github.com/jayway/maven-android-plugin). The project follows the standard Android project layout as opposed to the standard Maven layout so sources will be in /src instead of /src/main/java and can be imported directly into IDE as an Android project.

The core library contains common components and interfaces which the rest of the Aerogear Android libraries depend on.  


## Building

Until the 2.0 modules are stable and in Maven Central, we will need to build the projects first.  Please take a look of the [step by step on our website](http://aerogear.org/docs/guides/aerogear-android/HowToBuildAeroGearAndroidLibrary/)


## Usage

There are three supported ways of developing apps using Aerogear for Android.  Development may be done with Maven, ADT on Eclipse, and Android Studio.  Maven and Android Studio feature good dependency and library management and are far easier to get set up and developing.  Eclipse with ADT is the current stable and traditional way of performing Android development.

### Android Studio
Add to your application's `build.gradle` file
```
dependencies {
  compile 'org.jboss.aerogear:aerogear-android-core:2.0-SNAPSHOT@aar'
  //My other dependencies
}
```

And in your project's `build.gradle` files you will need to add:
```
allprojects {
    repositories {
	mavenLocal();
        //All other repositories
    }
}

```
### Maven
Include the following dependencies in your project's `pom.xml`

```
<dependency>
  <groupId>org.jboss.aerogear</groupId>
  <artifactId>aerogear-android</artifactId>
  <version>1.3.1</version>
  <scope>provided</scope>
  <type>jar</type>
</dependency>

<dependency>
  <groupId>org.jboss.aerogear</groupId>
  <artifactId>aerogear-android</artifactId>
  <version>1.3.1</version>
  <type>apklib</type>
</dependency>
```

### Eclipse
After downloading the project run

```
mvn dependency:copy
```

Now you can import the library into Eclipse and link against it in your main projects.

## Documentation

For more details about the current release, please consult [our documentation](http://aerogear.org/docs/guides/aerogear-android/).

## Development

If you would like to help develop AeroGear you can join our [dev list](https://lists.jboss.org/mailman/listinfo/aerogear-dev), join #aerogear on Freenode, or shout at us on Twitter @aerogears.

Also takes some time and skim the [contributor guide](http://aerogear.org/docs/guides/Contributing/)

## Questions?

Join our [user list](https://lists.jboss.org/mailman/listinfo/aerogear-user) for any questions and help! We really hope you enjoy app development with AeroGear!

