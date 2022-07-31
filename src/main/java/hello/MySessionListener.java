package hello;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;

@WebListener
public class MySessionListener implements HttpSessionAttributeListener {

	static final Logger LOG=Logger.getLogger(MySessionListener.class.getName());
	
	@Override
	public void attributeAdded(HttpSessionBindingEvent event) {
		LOG.log(Level.INFO,"********| Session attribute added |*********");
		HttpSessionAttributeListener.super.attributeAdded(event);
	}
	
	@Override
	public void attributeRemoved(HttpSessionBindingEvent event) {
		// TODO Auto-generated method stub
		HttpSessionAttributeListener.super.attributeRemoved(event);
		LOG.log(Level.INFO,"********| Session attribute removed |*********");
		
	}
	
	@Override
	public void attributeReplaced(HttpSessionBindingEvent event) {
		HttpSessionAttributeListener.super.attributeReplaced(event);
		LOG.log(Level.INFO,"********| Session attribute replaced studentId =|"+event.getSession().getAttribute("studentId")+"*********");

		
	}
	
}
