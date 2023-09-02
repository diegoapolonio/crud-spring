package com.diego.service;

import com.diego.dto.CourseDTO;
import com.diego.dto.mapper.CourseMapper;
import com.diego.enums.Category;
import com.diego.exception.RecordNotFoudException;
import com.diego.model.Course;
import com.diego.repository.CourseRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service

public class CourseService {
    private final CourseRepository courseReposirory; // o repository tem acesso ao banco de dados
    private final CourseMapper courseMapper;

    public CourseService(CourseRepository courseRepository, CourseMapper courseMapper) {
        this.courseReposirory = courseRepository;
        this.courseMapper = courseMapper;
    }

    public List<CourseDTO> list() {
//        List<Course> courses = courseReposirory.findAll();
//
//        List<CourseDTO> courseDTOS = new ArrayList<>();
//
//        for (int i = 0; i < courses.size() ; i++) {
//            CourseDTO courseDTO = new CourseDTO(courses.get(i).getId(), courses.get(i).getNome(), courses.get(i).getCategory());
//            courseDTOS.add(courseDTO);
//        }
//
//        return courseDTOS;
//
        return courseReposirory.findAll()
                .stream() // stream é funcionalidade para listas
                .map(courseMapper::toDto)// map transforma o objeto da lista
                .collect(Collectors.toList()); // pega cada elemento bota numa lista e retorna
    }

    public CourseDTO findById(Long id) {
//        Optional<Course> courseOptional = courseReposirory.findById(id);
//
//        if (courseOptional.isPresent()) {
//            Course course = courseOptional.get(); // sempre pega o objeto dentro do optionala traves do metodo get, sempre tem que verificar antes
//            CourseDTO courseDTO = courseMapper.toDto(course);
//            return courseDTO;
//        } else {
//            throw new RecordNotFoudException(id);
//        }
//

        return courseReposirory.findById(id).map(courseMapper::toDto)
                .orElseThrow(()-> new RecordNotFoudException(id));

    }

    public CourseDTO create(CourseDTO courseDto) {
//        Course course = courseMapper.toEntity(courseDto);
//        course = courseReposirory.save(course);
//        courseDto = courseMapper.toDto(course);
//        return courseDto;

        return courseMapper.toDto(courseReposirory.
                save(courseMapper.toEntity(courseDto)));
    }

    public CourseDTO update(Long id, CourseDTO course) {
        return courseReposirory.findById(id)
                .map(cursoEncontrado -> { // cursoEncontrado é o curso que foi encontado
                    cursoEncontrado.setNome(course.nome());
                    cursoEncontrado.setCategory(courseMapper.convertCategoryValue(course.category()));
                    return courseReposirory.save(cursoEncontrado); // o save atualiza no banco de dados.
                }).map(courseMapper::toDto).orElseThrow(()->
                        new RecordNotFoudException(id));
    }

    public void delete(Long id) {
        Course curso = courseReposirory.findById(id)
                .orElseThrow(() -> new RecordNotFoudException(id));
        courseReposirory.delete(curso);

    }
}