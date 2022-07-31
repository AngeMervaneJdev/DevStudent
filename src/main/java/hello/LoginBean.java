package hello;

import java.io.IOException;
import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import hello.dao.UserDAO;
import hello.models.Student;

@ManagedBean
public class LoginBean implements Serializable {

    private static final long serialVersionUID = -5433850275008415405L;

    private String login;
    private String password;
    
    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }
    
    public String getPassword() {
        return password;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }
    
    
    public String returnAction() {
        System.out.println( "in returnAction" );
        return password.equals( "007" ) ? "success" : "failure";
    }
    
    public  String loginStudent() throws NamingException, IOException {
    	
		  
		  
		  Student std=UserDAO.isValidLogin(getLogin(), getPassword());
		  System.out.print("name <"+getLogin()+">");
		  ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
		if(std!=null) {
			
			
			FacesContext context = FacesContext.getCurrentInstance();
	        HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
	        HttpSession session=request.getSession(true);
	        
	        session.setAttribute("connected", true);
	        session.setAttribute("connectedUser", std);
	        session.setAttribute("studentId", std.getId());
	        
	        System.out.print("------ID-----"+std.getId());
			
			
			      
			 ec.redirect(ec.getRequestContextPath()
			            + "/faces/ihm/student-response.xhtml");
			 return "";
			  
		}else {
			return "../auth/student-login.xhtml";
		}
  
   
}
    
}