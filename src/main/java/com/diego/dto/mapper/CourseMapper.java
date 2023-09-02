package com.diego.dto.mapper;

import com.diego.dto.CourseDTO;
import com.diego.dto.LerssonDTO;
import com.diego.enums.Category;
import com.diego.model.Course;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CourseMapper {
    public CourseDTO toDto (Course course) {
        if (course == null) {
            // null representa que a variavel course nao esta referenciando nenhum objeto
            return null;
        }

        List<LerssonDTO> lessons = course.getLessons()
                .stream()
                .map(lesson -> new LerssonDTO(lesson.getId(), lesson.getNome(),
                        lesson.getYoutubeUrl()))
                .collect(Collectors.toList());
        return new CourseDTO(course.getId(), course.getNome(), course.getCategory().getValue(),
        lessons);
    }
    public Course toEntity(CourseDTO courseDTO){
        if (courseDTO==null){
            return null;
        }
        Course course = new Course();
        if (courseDTO.id() != null){
           course.setId(courseDTO.id());
        }
        course.setNome(courseDTO.nome());
        course.setCategory(convertCategoryValue(courseDTO.category()));
        return course;
    }
    public Category convertCategoryValue(String value){
        if (value==null){
            return null;
        }
        return switch (value){
            case "Front_end"-> Category.FRONT_END;
            case "Back_end"-> Category.BACk_END;
            default -> throw new IllegalArgumentException("categoria invalida"+ value);

        };
    }
}

