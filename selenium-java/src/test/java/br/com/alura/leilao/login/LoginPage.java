package br.com.alura.leilao.login;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import br.com.alura.leilao.PageObject;
import br.com.alura.leilao.leiloes.LeiloesPage;

public class LoginPage extends PageObject{
	private static final String URL_LOGIN = "http://localhost:8080/login";
	private static final String URL_LOGIN_ERROR = "http://localhost:8080/login?error";
	
	public LoginPage() {
		super(null);
		browser.navigate().to(URL_LOGIN);
	}

	public void preencheFormularioDeLogin(String username, String password) {
		browser.findElement(By.id("username")).sendKeys(username);
		browser.findElement(By.id("password")).sendKeys(password);
		
	}

	public LeiloesPage submeterFormularioClicandoNoBotaoLogin() {
		browser.findElement(By.id("btn-primary")).click();
		return new LeiloesPage(browser);
	}

	public boolean isPaginaDeLogin() {
		return browser.getCurrentUrl().equals(URL_LOGIN);
	}
	
	public boolean isPaginaDeLoginError() {
		return browser.getCurrentUrl().equals(URL_LOGIN_ERROR);
	}

	public String getNomeUsuarioLogado() {
		try {
			return  browser.findElement(By.id("usuario-logado")).getText();
		} catch(NoSuchElementException e) {
			return null;
		}
		
	}

	public String getAlertDanger() {
		return browser.findElement(By.id("alert-danger")).getText();
	}

	public void acessarUrlRestritaLeiloes() {
		browser.navigate().to("http://localhost:8080/leiloes/2");
	}

	public boolean dadoDaPaginaDeLeilao(String texto) {
		return browser.getPageSource().contains(texto);
	}

	

}















