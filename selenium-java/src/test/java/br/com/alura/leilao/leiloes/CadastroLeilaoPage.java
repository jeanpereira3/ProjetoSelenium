package br.com.alura.leilao.leiloes;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import br.com.alura.leilao.PageObject;

public class CadastroLeilaoPage extends PageObject{
	
	public CadastroLeilaoPage(WebDriver browser) {
		super(browser);
	}
	
	public void preecherFormularioCadastroLeilao(String nome, String valor, String hoje) {
		browser.findElement(By.id("nome")).sendKeys(nome);
		browser.findElement(By.id("valorInicial")).sendKeys(valor);
		browser.findElement(By.id("dataAbertura")).sendKeys(hoje);
	}
	
	public LeiloesPage submeterFormularioClicandoNoBotaoSalvar() {
		browser.findElement(By.id("button-submit")).click();	
		return new LeiloesPage(browser);
	}

	public boolean verificarMensagensDeCampoObrigatorio(String nome, String data, String valor) {
		return browser.getPageSource().contains(nome) && browser.getPageSource().contains(data) && browser.getPageSource().contains(valor);
	}
}
