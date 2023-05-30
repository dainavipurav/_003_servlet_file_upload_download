package com.abc.learning.servlet;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

@WebServlet("/FileUploadServlet")
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 1, // 1 MB
		maxFileSize = 1024 * 1024 * 10, // 10 MB
		maxRequestSize = 1024 * 1024 * 100 // 100 MB
)
public class FileUploadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		Part file = request.getPart("file");
		PrintWriter out = response.getWriter();
		Pattern special = Pattern.compile("[!@#$%&*()+=|<>?{}\\[\\]~,]");
		List<String> allowedExtensions = new ArrayList<>(
				Arrays.asList("jpeg", "jpg", "pdf", "txt", "docx", "docs", "png"));

		String baseFileName = file.getSubmittedFileName();

		System.out.println("Base file Name : " + baseFileName);

		if (special.matcher(baseFileName).find()) {
			out.println("Invalid file name. File name should not consist special characters.");
			return;
		}

		System.out.println("BaseFileName: " + baseFileName);

		String[] data = baseFileName.split("\\.");

		for (int i = 0; i < data.length; i++) {
			System.out.println("data : " + i + " : " + data[i]);
		}

		String extension = baseFileName.substring(baseFileName.lastIndexOf(".") + 1);

		System.out.println("Extension : " + extension);

		if (!allowedExtensions.contains(extension)) {
			out.println("Invalid file type. Please upload valid file. jpeg, jpg, pdf, txt, docx, docs, png are valid");
			return;
		}

		String uploadPath = getServletContext().getRealPath("") + File.separator + "uploaded_files";

		System.out.println("Path : " + uploadPath);

		File uploadDir = new File(uploadPath);
		if (!uploadDir.exists()) {
			System.out.println("Folder Not Exists");
			uploadDir.mkdir();
		} else {
			System.out.println("Folder already exists");
		}

		for (Part part : request.getParts()) {
			String filePath = uploadPath + File.separator + baseFileName;
			System.out.println("Final file path : " + filePath);
			File storeFile = new File(filePath);
			part.write(storeFile.getPath());
		}
		response.sendRedirect("download_file.jsp?name="+baseFileName+"");

	}

}
