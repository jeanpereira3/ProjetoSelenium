package br.com.alura.leilao.leiloes;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.junit.Assert;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import br.com.alura.leilao.login.LoginPage;

public class LeiloesTest {
	
	private LeiloesPage leiloesPage;
	private CadastroLeilaoPage cadastroLeilaoPage;
	
	@BeforeEach
	public void beforeEach() {
		LoginPage loginPage = new LoginPage();
		loginPage.preencheFormularioDeLogin("fulano", "pass");
		this.leiloesPage = loginPage.submeterFormularioClicandoNoBotaoLogin();
		
		this.cadastroLeilaoPage = leiloesPage.acessaURLNovoLeilao();
		
	}
		
	@AfterEach
	public void afterEach() {
		this.leiloesPage.fechar();
	}
	
	@Test
	public void deveriaCadastrarLeilao() {
		
		String hoje = LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
		String nome = "Leilao do dia" + hoje;
		String valor = "500.00";
		cadastroLeilaoPage.preecherFormularioCadastroLeilao(nome, valor, hoje);
		this.leiloesPage = cadastroLeilaoPage.submeterFormularioClicandoNoBotaoSalvar();
		
		
		Assert.assertTrue(leiloesPage.isPaginaDeLeiloes());
		Assert.assertTrue(leiloesPage.isLeilaoCadastrado(nome, valor, hoje));
		
	}
	
	@Test
	public void testeDeCamposObrigatorios() {
		
		this.cadastroLeilaoPage.submeterFormularioClicandoNoBotaoSalvar();
		
		Assert.assertTrue(cadastroLeilaoPage.verificarMensagensDeCampoObrigatorio("minimo 3 caracteres",
				"deve ser um valor maior de 0.1",
				"deve ser uma data no formato dd/MM/yyyy"));
	}
	
}


































