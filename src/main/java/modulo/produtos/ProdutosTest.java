package modulo.produtos;

import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import paginas.LoginPage;

import java.time.Duration;

@DisplayName("Testes Web no Modulo de Produtos")
public class ProdutosTest {

    private WebDriver navegador;

    @BeforeEach
    public void BeforeEach(){
        // Abrir o navegador
        System.setProperty("webdriver.chrome.driver", "C:\\drivers\\chromedriver.exe");
        this.navegador = new ChromeDriver();

        // Vou maximizar a tela
        this.navegador.manage().window().maximize();

        // Vou definir um tempo de espera padrão de 5 segundos
        this.navegador.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

        // Navegar para a página lojinha web
        this.navegador.get("http://165.227.93.41/lojinha-web/v2/");
    }

    @Test
    @DisplayName("Não é permitido registrar um produto com valor igual a zero")
    public void testNaoEPermitidoRegistrarProdutoValorIgualAZero(){

        String mensagemApresentada = new LoginPage(navegador)
                .informarOUsuario("admin")
                .informarASenha("admin")
                .submeterFormularioDeLogin()
                .acessarFormularioAdicaoNovoProduto()
                .informarNomeDoProduto("MacBook Pro")
                .informarValorDoProduto("000")
                .informarCoresDoProduto("preto, branco")
                .submeterFormularioDeAdicaoComErro()
                .capturarMensagemApresentada();

        Assertions.assertEquals("O valor do produto deve estar entre R$ 0,01 e R$ 7.000,00", mensagemApresentada);
    }

    @Test
    @DisplayName("Teste não é permitido registrar produto com valor maior que 7000")
    public void testNaoEPermitidoRegistrarProdutoComValorMaior7000(){
        String mensagemApresentada = new LoginPage(navegador)
                .informarOUsuario("admin")
                .informarASenha("admin")
                .submeterFormularioDeLogin()
                .acessarFormularioAdicaoNovoProduto()
                .informarNomeDoProduto("MacBook Pro")
                .informarValorDoProduto("700001")
                .informarCoresDoProduto("preto, branco")
                .submeterFormularioDeAdicaoComErro()
                .capturarMensagemApresentada();

        Assertions.assertEquals("O valor do produto deve estar entre R$ 0,01 e R$ 7.000,00", mensagemApresentada);
    }

    @Test
    @DisplayName("Teste adição de produto com valor válido")
    public void testRegistrarProdutoComValorValido(){
        String mensagemApresentada = new LoginPage(navegador)
                .informarOUsuario("admin")
                .informarASenha("admin")
                .submeterFormularioDeLogin()
                .acessarFormularioAdicaoNovoProduto()
                .informarNomeDoProduto("MacBook Pro")
                .informarValorDoProduto("7000")
                .informarCoresDoProduto("preto, branco")
                .submeterFormularioDeAdicaoComSucesso()
                .capturarMensagemDeProdutoAdicionado();

        Assertions.assertEquals("Produto adicionado com sucesso", mensagemApresentada);

    }

    @Test
    @DisplayName("Teste adição de produto com valor válido e adicionar componente")
    public void testRegistrarProdutoEAdicionarComponente() {
        String mensagemApresentada = new LoginPage(navegador)
                .informarOUsuario("admin")
                .informarASenha("admin")
                .submeterFormularioDeLogin()
                .acessarFormularioAdicaoNovoProduto()
                .informarNomeDoProduto("MacBook Pro")
                .informarValorDoProduto("7000")
                .informarCoresDoProduto("preto, branco")
                .submeterFormularioDeAdicaoComSucesso()
                .adicionarComponente()
                .informarNomeDoComponente("Mouse")
                .informarQuantidadeDoComponente("1")
                .submeterFormularioDeComponente()
                .capturarMensagemDeComponenteAdicionado();

        Assertions.assertEquals("capturarMensagemDeComponenteAdicionado", mensagemApresentada);
    }

   // @AfterEach
  //  public void afterEach(){
        // Vou fechar o navegador
       // navegador.quit();
    //}
}
