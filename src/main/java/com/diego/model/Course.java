package com.diego.model;

import com.diego.enums.Category;
import com.diego.enums.Status;
import com.diego.enums.converters.CategoryConverters;
import com.diego.enums.converters.StatusConvertrs;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.ArrayList;
import java.util.List;

@Data // getter e setters
@Entity // classe que vai ser salva no banco de dados

@SQLDelete(sql = "UPDATE Course Set status= 'Inativo' WHERE id = ?")
@Where(clause = "status= 'Ativo'")
public class Course {


    @Id // identificador da classe, codigo
    @GeneratedValue(strategy = GenerationType.AUTO) // o codigo vai ser gerado automatico
    @JsonProperty("_id") // so pra mudar o atributo no json
    private long id;


    @NotBlank  // nao pod ser vazio " "
    @NotNull // nao pode ser null
    @Length(min = 5, max = 100)
    @Column(length = 100,nullable = false) // nullable nao pod ser null
    private String nome;

    @NotBlank
    @Column(length =  10 ,nullable = false)
    @Convert(converter = CategoryConverters.class)
    private Category category;



    @NotNull
    @Column(length =10,nullable = false )
    @Convert(converter = StatusConvertrs.class)
    private Status status = Status.ACTIVE;


    @OneToMany(cascade = CascadeType.ALL,orphanRemoval = true,mappedBy = "course")
   // @JoinColumn(name = "course_id")
    private List<Lesson> lessons = new ArrayList<>();


    public Course() {
    }
}
