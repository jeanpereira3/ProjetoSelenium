package br.com.alura.leilao.login;

import org.junit.Assert;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import br.com.alura.leilao.leiloes.LeiloesPage;

public class LoginTest {
	
	private LoginPage loginPage;
	
	@BeforeEach
	public void beforeEach() {
		this.loginPage = new LoginPage();
	}
	
	@AfterEach
	public void afterEach() {
		this.loginPage.fechar();
	}
	
	@Test
	public void deveriaEfetuarLoginComDadosValidos() {
		
		loginPage.preencheFormularioDeLogin("fulano", "pass");
		loginPage.submeterFormularioClicandoNoBotaoLogin();
		
		
		Assert.assertFalse(loginPage.isPaginaDeLogin());
		Assert.assertEquals("fulano", loginPage.getNomeUsuarioLogado());
			
	}
	
	@Test
	public void naoDeveriaLogarComDadosInvalidos() {
		
		loginPage.preencheFormularioDeLogin("invalido", "pass");
		loginPage.submeterFormularioClicandoNoBotaoLogin();
		
		Assert.assertTrue(loginPage.isPaginaDeLoginError());
		Assert.assertEquals("Usuário e senha inválidos.", loginPage.getAlertDanger());
		Assert.assertNull(loginPage.getNomeUsuarioLogado());
		
	}
	
	@Test
	public void naoDeveriaAcessarPaginaRestritaSemEstarLogado() {
		loginPage.acessarUrlRestritaLeiloes();
		
		Assert.assertTrue(loginPage.isPaginaDeLogin());
		Assert.assertFalse(loginPage.dadoDaPaginaDeLeilao("Dados do Leilão"));
	}
	
}


































