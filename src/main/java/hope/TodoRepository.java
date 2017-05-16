package hope;

import org.springframework.data.repository.CrudRepository;

import hope.Todo;

// This will be AUTO IMPLEMENTED by Spring into a Bean called todoRepository
// CRUD refers Create, Read, Update, Delete

public interface TodoRepository extends CrudRepository<Todo, Integer> {

}