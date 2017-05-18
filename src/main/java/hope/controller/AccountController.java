package hope.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
// import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.validation.annotation.Validated;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.dao.EmptyResultDataAccessException;

import hope.model.Todo; 
import hope.model.Account; 
import hope.repository.TodoRepository; 
import hope.repository.AccountRepository; 
import hope.HttpError;

@Controller
@RequestMapping("/accounts")
public class AccountController {
    @Autowired // This means to get the bean called userRepository
               // Which is auto-generated by Spring, we will use it to handle the data
    private AccountRepository accountRepository;
    private TodoRepository todoRepository;

    @GetMapping
    // public ResponseEntity<?> index() {
    public @ResponseBody Iterable<Account> index() {
        // This returns a JSON or XML
        return accountRepository.findAll();
        // return new ResponseEntity(todoRepository.findAll(), HttpStatus.OK);
    }

    @GetMapping(path="/{id}")
    public ResponseEntity<?> show(@PathVariable Integer id) {
        Account account = accountRepository.findOne(id);

        if (account == null) {
            // return ResponseEntity.notFound().build();
            HttpError httpError = new HttpError(404, "Resource " + id + " not found");
            return new ResponseEntity(httpError, HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity(account, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id) {
        try {
            accountRepository.delete(id);
        }
        catch (EmptyResultDataAccessException e) {
            HttpError httpError = new HttpError(404, "Resource " + id + " not found");
            return new ResponseEntity(httpError, HttpStatus.NOT_FOUND);
        }
        catch (Exception e) {
            System.out.println("An exception occurred: " + e);
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity(HttpStatus.NO_CONTENT);
        // return "Deleted";
    }

    @PostMapping
    public ResponseEntity<?> save(@Validated @RequestBody Account account) {
        Account savedAccount = accountRepository.save(account);

        return new ResponseEntity(savedAccount, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Integer id, @Validated @RequestBody Account accountBody) {
        Account account = accountRepository.findOne(id);

        if (account == null) {
            HttpError httpError = new HttpError(404, "Resource " + id + " not found");
            return new ResponseEntity(httpError, HttpStatus.NOT_FOUND);
        }

        if (accountBody.getUsername() != null) {
            account.setUsername(accountBody.getUsername());
        }
        if (accountBody.getPassword() != null) {
            account.setPassword(accountBody.getPassword());
        }

        return new ResponseEntity(accountRepository.save(account), HttpStatus.OK);
    }
}