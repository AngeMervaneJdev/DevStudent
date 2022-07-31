package hello.models;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.naming.NamingException;
import javax.servlet.http.HttpSession;

import hello.dao.ArticleDAO;

@ManagedBean
public class Article {

	private int idArticle;
	private String description;
	private String brand;
	private double unitaryPrice;
	
	private List<Article> articles=new ArrayList<>();
	
	
	public Article() {
		
	}
	
	
	public Article( int idArticle, String description, String brand, double unitaryPrice ) {
		this.setIdArticle( idArticle );
		this.setDescription( description );
		this.setBrand( brand );
		this.unitaryPrice= unitaryPrice ;
	}


	public int getIdArticle() {
		return idArticle;
	}
	
	public void setIdArticle(int idArticle) {
		this.idArticle = idArticle;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	public String getBrand() {
		return brand;
	}
	
	public void setBrand(String brand) {
		this.brand = brand;
	}
	
	public double getUnitaryPrice() {
		return unitaryPrice;
	}
	
	public void setUnitaryPrice(double unitaryPrice) {
		this.unitaryPrice = unitaryPrice;
	}

	
	public  void saveArticle() throws NamingException, IOException {
		System.out.print("-----------"+this);
		HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
		ArticleDAO.addArticle(this,(int) session.getAttribute("studentId"));
		this.setIdArticle(ArticleDAO.getId());
		session.setAttribute("article", this);
		
		ExternalContext ec = FacesContext.getCurrentInstance()
		        .getExternalContext();
		 ec.redirect(ec.getRequestContextPath()
		            + "/faces/article/show-article.xhtml");
	}
	
	public  void updateArticle(int articleId) throws NamingException, IOException {
		this.setIdArticle(articleId);
		ArticleDAO.updateArticle(this);
		HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
		
		session.setAttribute("article", this);
		ExternalContext ec = FacesContext.getCurrentInstance()
		        .getExternalContext();
		 ec.redirect(ec.getRequestContextPath()
		            + "/faces/article/show-article.xhtml");
	}
	
	
	public  List<Article> getArticles() throws NamingException, IOException {
		
	
		HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
		articles= ArticleDAO.getArticles((int) session.getAttribute("studentId"));
		return articles;
	}
	
	public  void showArticle(int id) throws NamingException, IOException {
		try {
			Article art=ArticleDAO.getArticleById(id);
			
			HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
		
			session.setAttribute("article", art);
			ExternalContext ec = FacesContext.getCurrentInstance()
			        .getExternalContext();
			 ec.redirect(ec.getRequestContextPath()
			            + "/faces/article/show-article.xhtml");
		} catch (Exception e) {
		
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	
	
	
	public  void editArticle(int id) throws NamingException, IOException {
		System.out.print("dddddddddddd");
		try {
			Article art=ArticleDAO.getArticleById(id);
			
			HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
		
			session.setAttribute("article", art);
			ExternalContext ec = FacesContext.getCurrentInstance()
			        .getExternalContext();
			 ec.redirect(ec.getRequestContextPath()
			            + "/faces/article/edit-article.xhtml");
		} catch (Exception e) {
		
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	
	


	
	
}

