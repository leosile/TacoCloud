package tacos.controller;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class AfterStart implements CommandLineRunner {
    @Override
    public void run(String... args) throws Exception {
        String cmd="C:\\Program Files (x86)\\Google\\Chrome\\Application\\chrome.exe " +
                "http://localhost:8080/design";
        Runtime.getRuntime().exec(cmd);
    }
}
