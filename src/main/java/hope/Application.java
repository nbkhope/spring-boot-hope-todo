package hope;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.boot.CommandLineRunner;
import java.util.Arrays;

import hope.model.Account;
import hope.model.Todo;
import hope.repository.AccountRepository;
import hope.repository.TodoRepository;

@SpringBootApplication
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean(name="messageSource")
    public ReloadableResourceBundleMessageSource messageSource() {
        ReloadableResourceBundleMessageSource messageBundle = new ReloadableResourceBundleMessageSource();
        messageBundle.setBasename("classpath:messages/messages");
        messageBundle.setDefaultEncoding("UTF-8");
        return messageBundle;
    }

    // BootStrap :) Initialize dummy data here
    @Bean
    CommandLineRunner init(
        AccountRepository accountRepository,
        TodoRepository todoRepository
    ) {
        return (evt) -> Arrays.asList("asmith,kyamada,rrivera".split(","))
            .forEach(username-> {
                Account newAccount = new Account(username, "password123");
                Account account = accountRepository.save(newAccount);

                todoRepository.save(new Todo(account, "Do the dishes"));
                todoRepository.save(new Todo(account, "Walk the dog"));
            });
    }
}