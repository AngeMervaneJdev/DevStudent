package hello.auth;

import java.io.IOException;
import java.net.http.HttpRequest;
import java.security.Principal;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Servlet Filter implementation class UserSessionFilter
 */
@WebFilter("/UserSessionFilter")
public class UserSessionFilter extends HttpFilter implements Filter {
       
    /**
     * @see HttpFilter#HttpFilter()
     */
    public UserSessionFilter() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
//		HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
//		HttpServletRequest httpReq = (HttpServletRequest) request; 
//		Principal user = httpReq.getUserPrincipal(); 
//		System.out.print("++++++COME HERE +++++"+user);
//		 String url = httpReq.getRequestURI();                
//	          if (user == null || session == null || session.getAttribute("USER") == null) {                 
//	        	  ExternalContext ec = FacesContext.getCurrentInstance()
//	  			        .getExternalContext();
//	  			 ec.redirect(ec.getRequestContextPath()
//	  			            + "/faces/auth/student-login.xhtml");             
//	                return;             
//	          }        
//	   
	    
		
				chain.doFilter(request, response);
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
