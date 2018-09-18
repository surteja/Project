package com.example.helloWorld;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class HelloWorld extends HttpServlet {

	public void init(ServletConfig config) throws ServletException {
		super.init(config);
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String sessionId = "";
		String sessionName = "";

		/*
		 * Get the value of form parameter
		 */
		String name = request.getParameter("name");
		String helloMessage = "Hello " + name;
 
		
		/*
		 * Set the content type(MIME Type) of the response.
		 */
		response.setContentType("text/html");

		PrintWriter out = response.getWriter();
		/*
		 * Write the HTML to the response
		 */
		out.println("<html>");
		out.println("<head>");
		out.println("<title> Simple Example</title>");
		out.println("</head>");
		out.println("<body>");
		out.println("<br/>");
		out.println("<h1>" + helloMessage + "</h1>");

		/*
		 * Set Session related information
		 */

		HttpSession session = request.getSession(false);
		if (session == null) {
			System.out.println("Session is null.  Creating a new one...");
			session = request.getSession(true);
		} else {
			sessionId = session.getId();
			Person person = (Person) session.getAttribute("person");
			if (person != null)
				sessionName = person.getName();
			System.out.println("Session exists with a name : " + sessionName);
		}

		Person person = new Person();
		person.setName(name);
		session.setAttribute("person", person);

		out.println("<br/><br/>");
		out.println("<h3>Session ID    : " + sessionId + "</h3>");
		out.println("<h3>Session Value : " + sessionName + "</h3>");
		out.println("</body>");
		out.println("</html>");
		out.close();

		System.out
				.println("Displaying the output with Hello World Message for "
						+ name);

	}

	public void destroy() {

	}
}