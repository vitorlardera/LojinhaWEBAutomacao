package paginas;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class EdicaoDeProdutoPage {

    private WebDriver navegador;

    public EdicaoDeProdutoPage (WebDriver navegador){
        this.navegador = navegador;
    }

    public String capturarMensagemDeProdutoAdicionado(){
        return navegador.findElement(By.cssSelector(".toast.rounded")).getText();
    }

    public EdicaoDeProdutoPage adicionarComponente(){
            navegador.findElement(By.linkText("ADICIONAR COMPONENTE")).click();
            return this;
    }

    public EdicaoDeProdutoPage informarNomeDoComponente(String componenteNome) {
     //   navegador.findElement(By.cssSelector("label[for='componentenomeadicionar']")).click();
        navegador.findElement(By.id("componentenomeadicionar")).sendKeys(componenteNome);
        return this;
    }

    public EdicaoDeProdutoPage informarQuantidadeDoComponente(String componenteQuantidade){
     //   navegador.findElement(By.cssSelector("label[for='componentequantidadeadicionar']")).click();
        navegador.findElement(By.id("componentequantidadeadicionar")).sendKeys(componenteQuantidade);
        return this;
    }

    public EdicaoDeProdutoPage submeterFormularioDeComponente(){
        navegador.findElement(By.linkText("SALVAR COMPONENTE")).click();
        return this;
    }


    public String capturarMensagemDeComponenteAdicionado(){
        return navegador.findElement(By.cssSelector(".toast.rounded")).getText();
    }
}
