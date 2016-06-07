import org.openqa.selenium.chrome.ChromeDriver

driver = {
    File file = new File("chromedrivers/chromedriver.exe"); //configurar com o enderço correto do chromedriver.
    System.setProperty("webdriver.chrome.driver", file.getAbsolutePath());
    new ChromeDriver();
}

environments {
    // run as “grails -Dgeb.env=chrome test-app”
    // See: http://code.google.com/p/selenium/wiki/ChromeDriver
    chrome {
        driver = { File file = new File("chromedrivers/chromedriver.exe"); //configurar com o enderço correto do chromedriver.
            System.setProperty("webdriver.chrome.driver", file.getAbsolutePath());
            new ChromeDriver(); }
    }
}

quitCachedDriverOnShutdown = false