package client;

import annotation.SetInject;
import org.springframework.beans.factory.annotation.Value;

import java.util.Set;

public class Client {

    private String id;
    private String fullName;
    private String greeting;
    @SetInject
//    @Value("${set.values}")
    private Set<String> valueSet;

    public Client(String id, String fullName, String greeting) {
        super();
        this.id = id;
        this.fullName = fullName;
        this.greeting = greeting;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Set<String> getValueSet() {
        return valueSet;
    }

    public void setValueSet(Set<String> valueSet) {
        this.valueSet = valueSet;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getGreeting() {
        return greeting;
    }

    public void setGreeting(String greeting) {
        this.greeting = greeting;
    }
}
