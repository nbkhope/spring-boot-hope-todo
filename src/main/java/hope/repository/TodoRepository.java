package hope.repository;

import org.springframework.data.repository.CrudRepository;
import java.util.Collection;

import hope.model.Todo;

// This will be AUTO IMPLEMENTED by Spring into a Bean called todoRepository
// CRUD refers Create, Read, Update, Delete

public interface TodoRepository extends CrudRepository<Todo, Integer> {

    Collection<Todo> findByAccountUsername(String username);

}