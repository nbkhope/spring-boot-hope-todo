package hope.model;

// import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.OneToMany;
import javax.persistence.CascadeType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;
import java.util.ArrayList;

import hope.model.Todo;

@Entity
@Table(name="accounts")
public class Account {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer id;

    @OneToMany(mappedBy="account", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<Todo> todos = new ArrayList<>();

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @NotNull
    @Size(min=6)
    private String password;
    @NotNull
    @Size(min=2, max=30)
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