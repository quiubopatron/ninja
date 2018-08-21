package com.udemy.backendninja.repository;


import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQuery;
import com.udemy.backendninja.entity.Course;
import com.udemy.backendninja.entity.QCourse;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.function.Predicate;

@Repository("queryDslExampleRepository")
public class QueryDSLExampleRepository {

    private QCourse qCourse = QCourse.course; // Q seguida del nombre de la tabla, es la clase q nos ha autogenerado el pluguin de QueryDSL

    @PersistenceContext // Se encarga de generar las entidades de la persistencia
    private EntityManager em;

    public Course find() {

        /*
        Ejemplos queries simples
         */
        JPAQuery<Course> query = new JPAQuery<Course>(em); // Representa la query,

        // Ejemplo obtencion de un curso con todas las columnas
        Course course = query.select(qCourse).from(qCourse).where(qCourse.id.eq(23L)).fetchOne();

        // Ejemplo de un curso que nos devuelve determinadas columnas
        Course courseColumns = (Course) query.select(qCourse.name, qCourse.price).from(qCourse).where(qCourse.id.eq(23L)).fetchOne();

        // Ejemplo lista de cursos
        List<Course> courses = query.select(qCourse).from(qCourse).where(qCourse.hours.between(20,50)).fetch();

        /*
        Ejemplo queries con predicates. Imagnemos un caso con logica, o que necesitemos ejecutar una consulta de forma dinamica
         */

        boolean exists = true;

        BooleanBuilder predicateBuilder = new BooleanBuilder(qCourse.description.endsWith("OP"));

        Predicate predicate1 = (Predicate) qCourse.description.endsWith("OP");

        if(exists) {

           predicateBuilder.and(qCourse.id.eq(23L));

        } else {

            predicateBuilder.or(qCourse.name.endsWith("op"));

        }

        return query.select(qCourse).from(qCourse).where(predicateBuilder).fetchOne();
    }
}
