package com.diego.dto;

import com.diego.model.Lesson;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.List;

public record CourseDTO( // so vai ter o que o frontend precisa realmente
                         @JsonProperty("_id") Long id,
                         @NotBlank @NotNull @Length(min = 5,max = 100)String nome,
                         @NotNull @Length(max = 10) @Pattern(regexp = "Back-end|Front End") String category,
                         List<LerssonDTO> lessons)
 {

 }
