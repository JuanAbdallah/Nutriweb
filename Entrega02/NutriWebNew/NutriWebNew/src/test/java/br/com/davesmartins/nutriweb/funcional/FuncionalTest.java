package br.com.davesmartins.nutriweb.funcional;

import br.com.davesmartins.nutriweb.model.Usuario;
import br.com.davesmartins.nutriweb.model.dto.CadastroUsuarioDTO;
import br.com.davesmartins.nutriweb.repo.UsuarioRepo;
import br.com.davesmartins.nutriweb.service.UsuarioService;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ActiveProfiles;

import javax.validation.Valid;
import java.time.Duration;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;

@ActiveProfiles("test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class FuncionalTest {
    private WebDriver driver;

    @Autowired
    UsuarioRepo uDao;

    @Autowired
    PasswordEncoder password;

    @BeforeEach
    public void setUp(){

        WebDriverManager.firefoxdriver().setup();
        driver = new FirefoxDriver();

        try{
            Thread.sleep(3500);
        }catch (InterruptedException e){
            Logger.getLogger(FuncionalTest.class.getName()).log(Level.SEVERE, null, e);
        }
    }
    @AfterEach
    public void tearDown(){
        driver.quit();
    }

    @Test
    @DisplayName("Teste Login falha")
    public void teste001() {
        driver.get("http://localhost:9910/");
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        driver.manage().window().maximize();

        WebElement loginButton = driver.findElement(By.cssSelector(".btn:nth-child(5)"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", loginButton);


        driver.findElement(By.id("username")).sendKeys("aa@aa");
        driver.findElement(By.id("password")).sendKeys("123");


        loginButton.click();


        WebElement errorMessage = driver.findElement(By.cssSelector("blockquote"));
        assertThat(errorMessage.getText(), is("Login ou senha incorreta"));
    }


    @Test
    @DisplayName("Teste Cadastro")
    public void teste002() {
        driver.get("http://localhost:9910/home");
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);


        driver.manage().window().maximize();


        WebElement cadastroLink = driver.findElement(By.linkText("Cadastro"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", cadastroLink);
        cadastroLink.click();


        driver.findElement(By.id("form-nome")).sendKeys("Nome");
        driver.findElement(By.id("form-peso")).sendKeys("90");
        driver.findElement(By.id("form-altura")).sendKeys("80");
        driver.findElement(By.id("form-username")).sendKeys("abc@abc");
        driver.findElement(By.id("cpPassword1")).sendKeys("12345");
        driver.findElement(By.id("cpPassword")).sendKeys("12345");


        WebElement cadastrarButton = driver.findElement(By.cssSelector(".btn:nth-child(9)"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", cadastrarButton);
        cadastrarButton.click();


        WebElement successMessage = driver.findElement(By.cssSelector("blockquote > h3"));
        assertThat(successMessage.getText(), is("cadastro criado com sucesso."));
    }


//    @Test
//    @DisplayName("Teste De falha no cadastro senhas diferentes")
//    public void teste003() {
//        driver.get("http://localhost:9910/");
//        driver.manage().window().maximize();
//
//
//        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
//        WebElement navbarButton = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".btn-navbar-highlight")));
//
//        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", navbarButton);
//
//        navbarButton.click();
//
//        driver.findElement(By.linkText("Cadastro")).click();
//        driver.findElement(By.id("form-nome")).sendKeys("nome");
//        driver.findElement(By.id("form-peso")).sendKeys("90");
//        driver.findElement(By.id("form-altura")).sendKeys("80");
//        driver.findElement(By.id("form-username")).sendKeys("abc@abc");
//        driver.findElement(By.id("cpPassword1")).sendKeys("12345");
//        driver.findElement(By.id("cpPassword")).sendKeys("12344");
//
//        WebElement submitButton = driver.findElement(By.cssSelector(".btn:nth-child(9)"));
//
//
//        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", submitButton);
//
//
//        submitButton.click();
//
//        WebElement blockquote = driver.findElement(By.cssSelector("blockquote > h3"));
//        String message = blockquote.getText();
//
//
//        assertThat(message, is(not("cadastro criado com sucesso.")));
//    }


    @Test
    @DisplayName("Teste de Falha Cadastro Sem Nome")
    public void teste004() {
        driver.get("http://localhost:9910/");
        driver.manage().window().setSize(new Dimension(1320, 805));

        driver.findElement(By.linkText("Cadastro")).click();


        driver.findElement(By.id("form-peso")).sendKeys("90");
        driver.findElement(By.id("form-altura")).sendKeys("90");
        driver.findElement(By.id("form-username")).sendKeys("abc@abc");
        driver.findElement(By.id("cpPassword1")).sendKeys("12345");
        driver.findElement(By.id("cpPassword")).sendKeys("12345");

        WebElement submitButton = driver.findElement(By.cssSelector(".btn:nth-child(9)"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", submitButton);
        submitButton.click();

        WebElement formTitle = new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".form-top-left > h3")));

        assertThat(formTitle.getText(), is("Usuário"));
    }

    @Test
    @DisplayName("Teste de Falha Cadastro Sem Peso")
    public void teste005() {
        driver.get("http://localhost:9910/");
        driver.manage().window().setSize(new Dimension(1320, 805));

        driver.findElement(By.linkText("Cadastro")).click();


        driver.findElement(By.id("form-nome")).sendKeys("abc");
        driver.findElement(By.id("form-altura")).sendKeys("1.9");
        driver.findElement(By.id("form-username")).sendKeys("abc@abc");
        driver.findElement(By.id("cpPassword1")).sendKeys("12345");
        driver.findElement(By.id("cpPassword")).sendKeys("12345");

        WebElement submitButton = driver.findElement(By.cssSelector(".btn:nth-child(9)"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", submitButton);
        submitButton.click();

        WebElement formTitle = new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".form-top-left > h3")));

        assertThat(formTitle.getText(), is("Usuário"));
    }

    @Test
    @DisplayName("Teste de Falha Cadastro Sem Altura")
    public void teste006() {
        driver.get("http://localhost:9910/");
        driver.manage().window().setSize(new Dimension(1320, 805));

        driver.findElement(By.linkText("Cadastro")).click();


        driver.findElement(By.id("form-nome")).sendKeys("nome");
        driver.findElement(By.id("form-peso")).sendKeys("90");
        driver.findElement(By.id("form-username")).sendKeys("abc@abc");
        driver.findElement(By.id("cpPassword1")).sendKeys("12345");
        driver.findElement(By.id("cpPassword")).sendKeys("12345");

        WebElement submitButton = driver.findElement(By.cssSelector(".btn:nth-child(9)"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", submitButton);
        submitButton.click();

        WebElement message = new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".form-top-left > p")));

        assertThat(message.getText(), is("cadastre-se para ter acesso ao sistema:"));
    }

    @Test
    @DisplayName("Teste de Falha Cadastro Sem Email")
    public void teste007() {
        driver.get("http://localhost:9910/");
        driver.manage().window().setSize(new Dimension(1320, 805));

        driver.findElement(By.linkText("Cadastro")).click();


        driver.findElement(By.id("form-nome")).sendKeys("nome");
        driver.findElement(By.id("form-peso")).sendKeys("90");
        driver.findElement(By.id("form-altura")).sendKeys("1.9");
        driver.findElement(By.id("cpPassword1")).sendKeys("12345");
        driver.findElement(By.id("cpPassword")).sendKeys("12345");

        WebElement submitButton = driver.findElement(By.cssSelector(".btn:nth-child(9)"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", submitButton);
        submitButton.click();

        WebElement message = new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".form-top-left > p")));

        assertThat(message.getText(), is("cadastre-se para ter acesso ao sistema:"));
    }

    @Test
    @DisplayName("Teste de Falha Cadastro Email fora do padrão")
    public void teste008() {
        driver.get("http://localhost:9910/");
        driver.manage().window().setSize(new Dimension(1320, 805));

        driver.findElement(By.linkText("Cadastro")).click();

        // Preenche o formulário com email fora do padrão
        driver.findElement(By.id("form-nome")).sendKeys("nme");
        driver.findElement(By.id("form-peso")).sendKeys("90");
        driver.findElement(By.id("form-altura")).sendKeys("1.9");
        driver.findElement(By.id("form-username")).sendKeys("abc");
        driver.findElement(By.id("cpPassword1")).sendKeys("12345");
        driver.findElement(By.id("cpPassword")).sendKeys("12345");

        WebElement submitButton = driver.findElement(By.cssSelector(".btn:nth-child(9)"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", submitButton);
        submitButton.click();

        WebElement message = new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".form-top-left > p")));

        assertThat(message.getText(), is("cadastre-se para ter acesso ao sistema:"));
    }



//    @Test
//    @DisplayName("Teste de cadastro de alimento")
//    public void teste009() throws InterruptedException {
//        driver.get("http://localhost:9910/");
//        driver.manage().window().setSize(new Dimension(1320, 805));
//
//        driver.findElement(By.linkText("Cadastro")).click();
//
//        driver.findElement(By.id("form-nome")).sendKeys("Tiao");
//        driver.findElement(By.id("form-peso")).sendKeys("90");
//        driver.findElement(By.id("form-altura")).sendKeys("1");
//        driver.findElement(By.id("form-username")).sendKeys("abc@abc");
//        driver.findElement(By.id("cpPassword1")).sendKeys("12345");
//        driver.findElement(By.id("cpPassword")).sendKeys("12345", Keys.ENTER);
//
//
//        WebElement usernameField = new WebDriverWait(driver, Duration.ofSeconds(10))
//                .until(ExpectedConditions.elementToBeClickable(By.id("username")));
//        usernameField.sendKeys("abc@abc");
//        driver.findElement(By.id("password")).sendKeys("12345", Keys.ENTER);
//
//        Thread.sleep(3000);
//
//        driver.navigate().back();
//        new WebDriverWait(driver, Duration.ofSeconds(10))
//                .until(ExpectedConditions.elementToBeClickable(By.cssSelector(".odd:nth-child(1) .arrow"))).click();
//        driver.findElement(By.cssSelector("tr:nth-child(2) .add")).click();
//
//        WebElement dropdown = new WebDriverWait(driver, Duration.ofSeconds(10))
//                .until(ExpectedConditions.elementToBeClickable(By.id("cpAlimento")));
//        dropdown.click();
//        Thread.sleep(500);
//        dropdown.findElement(By.xpath("//option[contains(text(), 'Água de coco verde')]")).click();
//        WebElement quantityField = driver.findElement(By.id("cpQtde"));
//        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", quantityField);
//        quantityField.sendKeys("01");
//
//        driver.findElement(By.cssSelector("p:nth-child(3) > .btn")).click();
//
//        WebElement successMessage = new WebDriverWait(driver, Duration.ofSeconds(10))
//                .until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("blockquote > h3")));
//        assertThat(successMessage.getText(), is("alimento cadastrado com sucesso."));
//    }
}
