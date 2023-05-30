package com.abc.learning.servlet;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/FileDownloadServlet")
public class FileDownloadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
  
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String fileName = request.getParameter("Download");
		downloadFile(fileName,request,response);
	}
	
	void downloadFile(String file, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		PrintWriter out = response.getWriter();
		int length = 0;
		
			ServletContext context = getServletConfig().getServletContext();
			response.setContentType(context.getMimeType(file) != null ? context.getMimeType(file) : "application/octet-stream");
			response.setHeader("Content-Disposition","attachment; filename=\"" + file);// + "\""
			
			InputStream inputStream = context.getResourceAsStream("/WEB-INF/files/"+file);
			
			while (inputStream!=null && (length = inputStream.read()) != -1) {
				out.write(length);
			}
			out.flush();
	}

}
