package hello.models;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import hello.dao.UserDAO;

@ManagedBean
public class Student {

	private String firstName,lastName,country,language,password;
	private List<String> knowsLanguages;
	private int id;
	
	List<String> countryListOptions;
	List<String> languagesOptions;
	
	public Student() {
		id=0;
		countryListOptions=new ArrayList<>();
		
		languagesOptions=new ArrayList<>();
		
		countryListOptions.add("Bénin");
		countryListOptions.add("Togo");
		countryListOptions.add("Mali");
		countryListOptions.add("Niger");
		countryListOptions.add("Burkina-fasso");
		countryListOptions.add("Côte d'ivoire");
		
		languagesOptions.add("PHP");
		languagesOptions.add("Python");
		languagesOptions.add("Java");
		languagesOptions.add("C++");
		languagesOptions.add("Ruby");
		
		
	}
	
	public Student(int id,String firstName, String lastName, String country, String language, List<String> knowsLanguages) {
		super();
		this.id=id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.country = country;
		this.language = language;
		this.knowsLanguages = knowsLanguages;
	}

	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public List<String> getCountryListOptions() {
		return countryListOptions;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getLanguage() {
		return language;
	}
	public void setLanguage(String language) {
		this.language = language;
	}
	public List<String> getKnowsLanguages() {
		return knowsLanguages;
	}
	public void setKnowsLanguages(List<String> knowsLanguages) {
		this.knowsLanguages = knowsLanguages;
	}
	public List<String> getLanguagesOptions() {
		return languagesOptions;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	public  void register() throws IOException {
		try {
			if(UserDAO.addUser(firstName, lastName, country, language, knowsLanguages, password)) {
				FacesContext context = FacesContext.getCurrentInstance();
		        HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
		        HttpSession session=request.getSession(true);
		        
		        ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
				 ec.redirect(ec.getRequestContextPath()
				            + "/faces/haut/student-login.xhtml");
			}
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	
	
	
}
