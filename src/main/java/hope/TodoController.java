package hope;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
// import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;

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
    public @ResponseBody ResponseEntity<?> show(@PathVariable Integer id) {
        Todo todo = todoRepository.findOne(id);

        if (todo == null) {
            // return ResponseEntity.notFound().build();
            HttpError httpError = new HttpError(404, "Resource " + id + " not found");
            return new ResponseEntity(httpError, HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity(todoRepository.findOne(id), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public @ResponseBody String delete(@PathVariable Integer id) {
        todoRepository.delete(id);
        return "Deleted";
    }
}