package dropboxproject.controller;

import java.io.File;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

@WebServlet("/loginUserController")
@MultipartConfig(fileSizeThreshold=1024*1024*2, // 2MB
	location="C:/automation/j146851/advtopics/dropboxproject/resources",
	maxFileSize=1024*1024*10,      // 10MB
	maxRequestSize=1024*1024*50)   // 50MB
public class LoginUserController extends HttpServlet {

	   /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public void doPost(HttpServletRequest request, HttpServletResponse response)
	      throws ServletException, java.io.IOException {
	   
			
	        String savePath = "";
	        System.out.println("folder is "+savePath);
	        // creates the save directory if it does not exists
	        File fileSaveDir = new File(savePath);
	        if (!fileSaveDir.exists()) {
	            fileSaveDir.mkdir();
	        }
	         
	        for (Part part : request.getParts()) {
	            String fileName = extractFileName(part);
	            // refines the fileName in case it is an absolute path
	            fileName = new File(fileName).getName();
	            System.out.println("Filename is : "+savePath + "/" + fileName);
	            
	            part.write(fileName);
	        }
	        request.setAttribute("message", "Upload has been done successfully!");
	        getServletContext().getRequestDispatcher("/message.jsp").forward(
	                request, response);
	    }
	    /**
	     * Extracts file name from HTTP header content-disposition
	     */
	    private String extractFileName(Part part) {
	        String contentDisp = part.getHeader("content-disposition");
	        String[] items = contentDisp.split(";");
	        for (String s : items) {
	            if (s.trim().startsWith("filename")) {
	                return s.substring(s.indexOf("=") + 2, s.length()-1);
	            }
	        }
	        return "";
	    }

}