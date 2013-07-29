struts2.mustache.java
=====================


Download the 
[mustache-java-struts2-1.0.0.jar](https://github.com/gmjordan/struts2.mustache.java/raw/master/target/mustache-java-struts2-1.0.0.jar) and add to your project.


Add 
```xml
  <package name="my-root-default" extends="struts-default">
		<result-types>
    		<result-type name="mustache" class="com.github.gmjordan.mustache.java.struts.MustacheResult">
    			<param name="rootMustachePath">/Users/gregorymjordan/Sites/business-workspace/cp2.0-static/WebContent/</param>
			</result-type>
		</result-types>
	</package>
```
