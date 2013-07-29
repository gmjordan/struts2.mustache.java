struts2.mustache.java
=====================

Steps to include:

1. Include the [mustache.java](https://github.com/spullara/mustache.java) project in your app

Maven dependency information (ie. for most common cases you will just need the `compiler` module):

```xml
<dependency>
  <groupId>com.github.spullara.mustache.java</groupId>
  <artifactId>compiler</artifactId>
  <version>0.8.12</version>
</dependency>
``` 


2. Download the [mustache-java-struts2-1.0.0.jar](https://github.com/gmjordan/struts2.mustache.java/raw/master/target/mustache-java-struts2-1.0.0.jar) and add to your project.

3. Add the result type to your root package in the struts.xml file.

If you want to use a Context path other than the root context path, then update the rootMustachePath. If you do decide to 
use a custom path, then the content must have access to the path, i.e. through a Context alias

```xml
<package name="my-root-default" extends="struts-default">
	<result-types>
  		<result-type name="mustache" class="com.github.gmjordan.mustache.java.struts.MustacheResult">
  			<param name="rootMustachePath">/some/static/path/to/mark/up/</param>
		</result-type>
	</result-types>
</package>
```

If your mustache templates are on the root path of the context, then do:

```xml
<package name="my-root-default" extends="struts-default">
	<result-types>
		<result-type name="mustache" class="com.github.gmjordan.mustache.java.struts.MustacheResult" />
	</result-types>
</package>
```
