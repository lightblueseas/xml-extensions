## Change log
----------------------

Version 8.3-SNAPSHOT
-------------



Version 8.2
-------------

ADDED:

- new method prettyPrint for an unformatted xml string with indent in extension class XmlExtensions
- new method prettyPrint for an unformatted xml string with default indent of 4 in extension class XmlExtensions

CHANGED:

- update gradle to new version 8.0.2
- update of gradle plugin dependency com.github.ben-manes.versions.gradle.plugin to new minor version 0.46.0
- update of gradle plugin dependency com.diffplug.spotless:spotless-plugin-gradle to new patch version 6.17.0
- update of dependency xstream to new patch version 1.4.20
- update of dependency jackson to new patch version 2.14.2
- update of dependency file-worker to new minor version 11.6
- update of test dependency lombok to new patch version 1.18.26
- update of test dependency jaxb-runtime to new patch version 4.0.2
- update of test dependency silly-collection to new major version 21
- update of test dependency silly-io to new minor version 2.2
- update of test dependency testng to new patch version 7.7.1
- update of test dependency equalsverifier to new minor version 3.14
- update of test dependency 'com.github.meanbeanlib:meanbean' to new version 3.0.0-M9

Version 8.1
-------------

ADDED:

- new class XmlFrameworkConverter that can convert xml strings from frameworks like jackson, xstream and jaxb to another framework xml string
- new class ObjectToXmlConverterStrategy for convert objects to xml string with classes that implement the interface ObjectToXml
- new dependency jaxb-extensions in version 1.2
- new test dependency org.projectlombok:lombok in version 1.18.24
- new test dependency jakarta.xml.bind:jakarta.xml.bind-api in version 4.0.0
- new test dependency org.glassfish.jaxb:jaxb-runtime in version 4.0.1
- new dependency io.github.astrapi69:strategy in version 6

CHANGED:

- update gradle to new version 7.6-rc-2
- update of gradle plugin dependency com.github.ben-manes.versions.gradle.plugin to new minor version 0.43.0
- update of gradle plugin dependency com.diffplug.spotless:spotless-plugin-gradle to new patch version 6.11.0
- update of dependency jackson to new minor version to 2.14.0
- update of dependency xml-api in minor version 1.4
- update of dependency xml-base in minor version 1.5
- update of dependency xstream-extensions in minor version 1.1
- update of dependency xml-jackson-extensions in minor version 1.1
- update of test dependency silly-collection to new minor version 20.1
- update of test dependency test-object to new minor version 7.2
- update of test dependency checksum-up to new minor version 2.2
- update of test dependency equalsverifier to new minor version 3.11
- remove of dependency io.github.astrapi69:jobj-clone
- moved tag classes and enum NodeType to module xml-base

Version 8
-------------

ADDED:

- new methods in class XmlToXsdExtensions for transform a xml string to a xsd string
- new methods in class XmlFileToObjectExtensions for transform a xml file to a java object
- new dependency xml-api in minor version 1.2
- new dependency xml-base in minor version 1.4
- new dependency xstream-extensions in major version 1
- new dependency xml-jackson-extensions in major version 1
- new test dependency nl.jqno.equalsverifier:equalsverifier in patch version 3.10.1

CHANGED:

- upgrade of jdk to version 11
- update gradle to new version 7.5.1
- extracted sections from build.gradle to own gradle files
- update of gradle plugin dependency com.diffplug.spotless:spotless-plugin-gradle to new patch version 6.10.0
- update of dependency jackson-core to new patch version 2.13.3
- update of dependency throwable to new minor version 2.3
- update of dependency xmlbeans to new minor version 5.1.0
- update of dependency crypt-api to new minor version 8.3
- update of dependency crypt-data to new patch version 8.1
- update of test dependency silly-collection to new major version 20
- update of test dependency silly-bean to new major version 2
- update of test dependency silly-io to new minor version 2.1
- update of test dependency checksum-up to new minor version 2.1
- update of test dependency test-object to new minor version 7.1
- update of test dependency testng to new minor version 7.6.1
- removed of test dependency jobj-contract-verifier
- tagged api interfaces as deprecated
- removed class ValidatorExtensions
- removed unused interfaces in api package and interface Xmlable and class XmlTransformation
- dependency xerces:xercesImpl to test dependency
- removed all classes that have moved to new module xml-jackson-extensions
- removed all unused dependencies and test dependencies

Version 7.6
-------------

ADDED:

- new gradle plugin dependency com.diffplug.spotless:spotless-plugin-gradle in minor version 6.3.0

CHANGED:

- update gradle to new version 7.4
- update of gradle plugin dependency com.github.ben-manes.versions.gradle.plugin to new minor version 0.42.0
- update of dependency xstream to new patch version 1.4.19
- update of dependency file-worker to new minor version 8.2
- update of dependency jobj-core to new minor version 5.3
- update of dependency crypt-api to new minor version 7.7
- update of dependency xercesImpl to new patch version 2.12.2
- update of dependency crypt-data to new patch version 7.11.1
- update of test dependency test-objects to new major version 6
- update of test dependency mystic-crypt to new minor version 7.11
- update of test dependency silly-io to new minor version 1.7

Version 7.5
-------------

ADDED:

- new interface Xmlable that provides a method for transform to xml string for all classes that implement it

CHANGED:

- update gradle to new version 7.3.3
- update dependency of com.github.ben-manes.versions.gradle.plugin to new version 0.41.0
- update of xmlbeans version to 5.0.3
- update of jobj-core dependency version to 5.2
- update of test dependency test-objects to new version 5.7
- update of test dependency jobj-contract-verifier to new version 3.5
- update of testng dependency version to 7.5

Version 7.4
-------------

CHANGED:

- update gradle to new version 7.1
- changed to new package io.github.astrapi69
- changed all dependencies from groupid de.alpharogroup to new groupid io.github.astrapi69 and to corresponding versions
- update gradle-plugin dependency of gradle.plugin.com.hierynomus.gradle.plugins:license-gradle-plugin to new version 0.16.1
- update of test dependency test-objects to new version 5.5
- update of test dependency velocity-extensions to new version 1.5
- update of test dependency meanbean-factories to new version 1.2
- removed deprecated classes

Version 7.3
-------------

ADDED:

- new gradle.properties file created and extracted dependency versions to it
- new XmlMapperFactory class for create XmlMapper objects
- new XStreamFactory class for create XStream objects
- new method in JsonStringToObjectExtensions for conversion of json string to java objects with jackson
- new method in JsonStringToObjectExtensions for conversion of json string to java map objects with jackson
- new method in ObjectToXmlExtensions for conversion of java object to xml with jackson
- new test dependency silly-collecctions in version 8 added

CHANGED:

- extended unit tests for increase code coverage
- update of mystic-crypt version to 7.4
- update of testng dependency version to 7.1.1
- update of jackson-core version to 2.10.2
- update of junit dependency version to 4.13
- update of jobj-core dependency version to 3.5
- update of jobj-clone dependency version to 3.3
- update of file-worker version to 5.5
- remove of junit test dependency
- remove of mockito-core test dependency

Version 7.2
-------------

ADDED:

- new JsonFileToObjectExtensions for conversion of json files created
- new JSONObjectToObjectExtensions for conversion of JSONObject and JSONArray object created

CHANGED:

- renamed JsonToObjectExtensions class to JsonStringToObjectExtensions and moved methods to appropriate classes
- unit tests for all new classes created

Version 7.1
-------------

CHANGED:

- reverted implementation and testImplementation back to compile and testCompile
- removed deprecated methods
- update of mockito-core test dependency version to 3.2.4

Version 7
-------------

CHANGED:

- moved xml specific classes to this project from mystic-crypt

Version 6.5.1
-------------

CHANGED:

- downgraded gradle version to 5.6.4

Version 6.5
-------------

ADDED:

- new factory method for create new object mapper with given features
- new unit tests for the ParserFactory created
- new idea run configurations for gradle builds created

CHANGED:

- tagged methods as deprecated in ObjectMapperFactory and replaced with the appropriate factory methods
- removed idea run configurations for maven

Version 6.4
-------------

ADDED:

- gradle as new build system

CHANGED:

- changed project nature from maven to gradle nature
- removed maven related files
- update of json dependency version to 20190722
- update of testng dependency version to 7.1.0
- update of mockito-core dependency version to 3.2.0
- update of junit dependency version to 4.13-rc-2

Version 6.3
-------------

ADDED:

- new methods created for transform JSONObject and JSONArray to java objects

CHANGED:

- update of parent version to 5.5
- update of file-worker version to 5.4
- replaced obsolete jobject-clone with the new jobj-clone test dependency
- update of jobj-core dependency version to 3.3
- update of jackson-core version to 2.10.1

Version 6.2.1
-------------

ADDED:

- new SAXParserFactory class created that hold factory method for deactivate parser that prevents a xml bomb attack

CHANGED:

- update of jackson-core version to 2.10.0.pr3

Version 6.2
-------------

ADDED:

- new methods for convert json to object and back with new object mapper argument

CHANGED:

- created idea run configuration scripts
- downgrade of parent version to 5


Version 6.1
-------------

CHANGED:

- created idea run configuration scripts
- update of parent version to 6.4.1
- java-target version is now 1.8

Version 6
-------------

ADDED:

- new dependency jobj-core in version 3.2.1

CHANGED:

- upgrade to jdk release 11
- update of parent version to 6.2
- update of xmlbeans version to 3.1.0
- update of file-worker version to 5.2
- update of test-objects dependency version to 5.2
- update of jobject-clone dependency version to 3.1.2
- replaced obsolete dependency jobject-evaluate with new jobj-contract-verifier
- update of jackson-core version to 2.9.9
- change provider of code coverage to codecov.io

Version 5
-------------

CHANGED:

- update of parent version to 4.5
- update of file-worker version to 5.0.1
- update of test-objects dependency version to 5
- update of velocity-extensions version to 1.3.1
- update of jobject-extensions version to 2.5
- update of xmlbeans version to 3.0.2
- update of xstream version to 1.4.11.1
- update of jackson-core version to 2.9.8
- excluded logging dependencies

Version 4.21
-------------

ADDED:

- new method created for convert a json string array to a java Collection that can be given as a type argument

CHANGED:

- update of parent version to 4.1
- update of jobject-extensions version to 1.12
- update of velocity-extensions version to 1.3
- update of file-worker version to 4.23
- update of xmlbeans version to 3.0.1
- update of json version to 20180813

Version 4.20
-------------

CHANGED:

- removed deprecated classes and methods
- unit tests extended for improve code coverage
- update of xercesImpl version to 2.12.0
- update of xmlbeans version to 3.0.0
- update of jettison version to 1.4.0
- update of json version to 20180130
- update of xstream version to 1.4.10
- update of jackson-core version to 2.9.6

Version 4.19
-------------

ADDED:

- new CHANGELOG.md file created

CHANGED:

- update of parent version to 3.12
- removed unneeded .0 at the end of version
- unit tests extended for improve code coverage
- tagged depracated methods in XmlExtensions as depracated and moved to appropriate classes
- tagged depracated methods in JsonTransformer as depracated and moved to appropriate classes

Notable links:
[keep a changelog](http://keepachangelog.com/en/1.0.0/) Don’t let your friends dump git logs into changelogs
