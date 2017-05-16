package hope;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
// import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.PathVariable;

import hope.Todo; 
import hope.TodoRepository; 

@Controller
@RequestMapping(path="/todos")
public class TodoController {
    @Autowired // This means to get the bean called userRepository
               // Which is auto-generated by Spring, we will use it to handle the data
    private TodoRepository todoRepository;

    @GetMapping(path="")
    public @ResponseBody Iterable<Todo> index() {
        // This returns a JSON or XML
        return todoRepository.findAll();
    }

    @GetMapping(path="/{id}")
    public @ResponseBody Todo show(@PathVariable Integer id) {
        return todoRepository.findOne(id);
    }
}