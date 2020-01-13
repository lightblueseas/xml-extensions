## Change log
----------------------

Version 7.3-SNAPSHOT
-------------

ADDED:
 
- new gradle.properties file created and extracted dependency versions to it
- new XmlMapperFactory class for create XmlMapper objects
- new method in JsonStringToObjectExtensions  for conversion of json string to java objects with jackson
- new method in ObjectToXmlExtensions for conversion of java object to xml with jackson

CHANGED:

- extended unit tests for increase code coverage
- update of mystic-crypt version to 7.3
- update of testng dependency version to 7.1.1
- update of jackson-core version to 2.10.2
- update of junit dependency version to 4.13

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
