struts2.mustache.java
=====================

big thanks to [Ricardo Zuasti](http://ricardozuasti.com/2012/using-mustache-java-templates-with-struts-2/) for providing the core code.


Steps to include:

- include the [mustache.java](https://github.com/spullara/mustache.java) project in your app

Maven dependency information (ie. for most common cases you will just need the `compiler` module):

```xml
<dependency>
  <groupId>com.github.spullara.mustache.java</groupId>
  <artifactId>compiler</artifactId>
  <version>0.8.12</version>
</dependency>
``` 
- Download the [mustache-java-struts2.jar](https://github.com/gmjordan/struts2.mustache.java/raw/master/target/mustache-java-struts2.jar) and add to your project.

- Add the result type to your root package in the struts.xml file.

When your mustache templates are within the default path of the context (e.g. /WEB-INF/mustache/home/index.html), then do:

```xml
<package name="my-root-default" extends="struts-default">
	<result-types>
		<result-type name="mustache" class="com.github.gmjordan.mustache.java.struts.MustacheResult" />
	</result-types>
</package>
```

your actions would look like

```xml
<action name="home" class="com.package.action.MyHome" method="showAppHomeContent" >
	<result name="success" type="mustache">/WEB-INF/mustache/home/index.html</result>
</action>
```

or with annotations

```java

@Actions({
	@Action(value = "", results = { @Result(name = "success", type = "mustache", location = "/WEB-INF/mustache/home/index.html") }),
	@Action(value = "home", results = { @Result(name = "success", type = "mustache", location = "/WEB-INF/mustache/home/index.html") })
})
```

If you want to use a path other than the default path, then provide a param called rootMustachePath in the result type configuration.

This approach is useful if you've got html/css coders who don't have access or don't want access to update the web app.

To be sure, the context must have access to the path, i.e. through a context alias

```xml
<package name="my-root-default" extends="struts-default">
	<result-types>
  		<result-type name="mustache" class="com.github.gmjordan.mustache.java.struts.MustacheResult">
  			<param name="rootMustachePath">/Users/myname/www/path/to/mydotcom/webassets/</param>
		</result-type>
	</result-types>
</package>
```

the alias could be /html=/Users/myname/www/path/to/mydotcom/webassets/html

your mustache code would go under /Users/myname/www/path/to/mydotcom/webassets/html/mustache and your actions would look like:

```xml
<action name="home" class="com.package.action.MyHome" method="showAppHomeContent" >
	<result name="success" type="mustache">/html/mustache/home/index.html</result>
</action>
```

or with annotations

```java

@Actions({
	@Action(value = "", results = { @Result(name = "success", type = "mustache", location = "/html/mustache/home/index.html") }),
	@Action(value = "home", results = { @Result(name = "success", type = "mustache", location = "/html/mustache/home/index.html") })
})
```


- Start using [the mustache code](http://mustache.github.com/mustache.5.html) in your templates

{{< /html/mustache/global/base-private.html}}

{{somecontent}}
{{/somecontent}}

{{/ /html/mustache/global/base-private.html}}


