package com.github.gmjordan.mustache.java.struts;

import java.io.File;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts2.dispatcher.PlainTextResult;

import com.github.mustachejava.DefaultMustacheFactory;
import com.github.mustachejava.Mustache;
import com.github.mustachejava.MustacheFactory;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.config.entities.Parameterizable;

/**
 * The Class MustacheResult.
 * BIG hat tip to https://twitter.com/rzuasti
 * http://ricardozuasti.com/2012/using-mustache-java-templates-with-struts-2/
 * I Added in the rootMustachePath config part.
 */
public class MustacheResult extends PlainTextResult implements Parameterizable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The log. */
	static Logger log = Logger.getLogger(MustacheResult.class);

	/** The root mustache path. */
	private String rootMustachePath;

	/* (non-Javadoc)
	 * @see org.apache.struts2.dispatcher.PlainTextResult#doExecute(java.lang.String, com.opensymphony.xwork2.ActionInvocation)
	 */
	@SuppressWarnings("unused")
	@Override
	protected void doExecute(String finalLocation, ActionInvocation invocation) throws Exception {

		HttpServletResponse response = (HttpServletResponse) invocation.getInvocationContext().get(HTTP_RESPONSE);
		ServletContext servletContext = (ServletContext) invocation.getInvocationContext().get(SERVLET_CONTEXT);

		PrintWriter writer = response.getWriter();

		// Open a stream to read the template passed to the action
		InputStreamReader reader = new FileReader(servletContext.getRealPath(finalLocation));

		// create the resource root
		String resourceRoot = null;

		if (rootMustachePath != null) {
			resourceRoot = rootMustachePath;
		} else {
			resourceRoot = servletContext.getRealPath("/");
		}

		if (reader == null) {
			log.warn("resource at location [" + finalLocation.toString() + "] cannot be obtained (return null) from ServletContext !!! ");
		}
		else {

			response.setContentType("text/html");

			// We need to pass the real path of the templates to the Mustache compiler, in order to support nested templates
			MustacheFactory mf = new DefaultMustacheFactory(new File(resourceRoot));
			Mustache mustache = mf.compile(reader, "mustacheResult");

			mustache.execute(writer, invocation.getAction());

			reader.close();
		}

		writer.flush();
		writer.close();

	}

	/**
	 * Gets the root mustache path.
	 * 
	 * @return the root mustache path
	 */
	public String getRootMustachePath() {
		return rootMustachePath;
	}

	/**
	 * Sets the root mustache path.
	 * 
	 * @param rootMustachePath the new root mustache path
	 */
	public void setRootMustachePath(String rootMustachePath) {
		this.rootMustachePath = rootMustachePath;
	}

	/* (non-Javadoc)
	 * @see com.opensymphony.xwork2.config.entities.Parameterizable#addParam(java.lang.String, java.lang.String)
	 */
	@Override
	public void addParam(String arg0, String arg1) {

	}

	/* (non-Javadoc)
	 * @see com.opensymphony.xwork2.config.entities.Parameterizable#getParams()
	 */
	@Override
	public Map<String, String> getParams() {
		return null;
	}

	/* (non-Javadoc)
	 * @see com.opensymphony.xwork2.config.entities.Parameterizable#setParams(java.util.Map)
	 */
	@Override
	public void setParams(Map<String, String> arg0) {

	}

}
