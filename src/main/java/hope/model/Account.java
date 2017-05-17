package hope.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.OneToMany;
import java.util.List;
import java.util.ArrayList;

import hope.model.Todo;

@Entity
@Table(name="accounts")
public class Account {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer id;

    @OneToMany(mappedBy="account")
    private List<Todo> todos = new ArrayList<>();

    @JsonIgnore
    private String password;
    private String username;

    public Integer getId() {
        return id;
    }

    public List<Todo> getTodos() {
        return todos;
    }

    public String getPassword() {
        return password;
    }

    public String getUsername() {
        return username;
    }

    Account() { // jpa only
    }

    public Account(String username, String password) {
        this.username = username;
        this.password = password;
    }
}