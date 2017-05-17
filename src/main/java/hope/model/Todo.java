package hope.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.validation.constraints.NotNull;
import javax.persistence.ManyToOne;

import hope.model.Account;

@Entity
@Table(name="todos")
public class Todo {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer id;
    @NotNull(message="error.content.notnull")
    private String content;

    @JsonIgnore
    @ManyToOne
    private Account account;

    public Integer getId() {
        return id;
    }

    public String getContent() {
        return content;
    }

    public Account getAccount() {
        return account;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setContent(String content) {
        this.content = content;
    }

    Todo() { // jpa only (???)
    }

    public Todo(Account account, String content) {
        this.account = account;
        this.content = content;
    }
}