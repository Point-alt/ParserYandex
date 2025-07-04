import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class Main {
    public static void main(String[] args) throws InterruptedException {
//        1. Настройка драйвера хром через вебдрайверманагер
        WebDriverManager.chromedriver().setup();

//        2. браузер без интерфейса
        ChromeOptions options = new ChromeOptions();
//        options.addArguments("--headless");
        options.addArguments("--disable-gpu");
        options.addArguments("--window-size=1920,1080");

//        3. Запуск драйвера
        WebDriver driver = new ChromeDriver(options);

        try{
//            4. Открытие ссылкb
            String url = "https://disk.yandex.ru/d/STmPh3sQDarwRw";
            driver.get(url);
//            5. Ждем, что бы страница подгрузилась
            Thread.sleep(5000);
//            6. Прокрутка страницы вниз, что бы догрузить книги
            JavascriptExecutor js = (JavascriptExecutor) driver;
            for (int i = 0; i < 10; i ++) {
                js.executeScript("window.scrollTo(0, document.body.scrollHeight);");
                Thread.sleep(1500);
            }
//            7. Проверка загрузки
            System.out.println("Страница загружена " + driver.getTitle());
        }
        finally {
            driver.quit();
        }
    }
}