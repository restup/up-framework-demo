package com.github.restup.example.university;

import static com.github.restup.example.university.Student.RESOURCE_NAME;
import static com.github.restup.example.university.Student.TABLE_NAME;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.SafeHtml;
import org.hibernate.validator.constraints.SafeHtml.WhiteListType;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.restup.annotations.ApiName;

@Entity(name = TABLE_NAME)
@ApiName(value = RESOURCE_NAME)
public class Student {

    public static final String RESOURCE_NAME = "student";
    public static final String TABLE_NAME = RESOURCE_NAME;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // demonstrate different api, bean, persisted paths
    @JsonProperty("firstName")
    @Column(name = "first_name")
    // use javax validations
    @SafeHtml(whitelistType = WhiteListType.NONE)
    @NotBlank
    private String fName;

    // demonstrate different api, bean, persisted paths
    @JsonProperty("lastName")
    @Column(name = "last_name")
    // use javax validations
    @SafeHtml(whitelistType = WhiteListType.NONE)
    @NotBlank
    private String lName;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getfName() {
        return fName;
    }

    public void setfName(String fName) {
        this.fName = fName;
    }

    public String getlName() {
        return lName;
    }

    public void setlName(String lName) {
        this.lName = lName;
    }

}
