package com.istudyenglish.mobilebackend.adapter.student;

import com.istudyenglish.mobilebackend.domain.Education.Student;
import com.istudyenglish.mobilebackend.port.out.student.StudentDBPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.UUID;

@Component
public class StudentDAO implements StudentDBPort {

    JdbcTemplate jdbcTemplate;

    StudentMapper studentMapper;

    @Autowired
    public StudentDAO(JdbcTemplate jdbcTemplate, StudentMapper studentMapper) {
        this.jdbcTemplate = jdbcTemplate;
        this.studentMapper = studentMapper;
    }

    public void save(Student student) {
    }


    public Student get(UUID studentUUID) {
        String sql = "select * from students where uuid in ('" + studentUUID.toString() + "');";
        return jdbcTemplate.query(sql,studentMapper).get(0);
    }

    public Student get(String studentCode) {
        String sql = "select * " +
                "from students " +
                "where code in ('" + studentCode + "');";
        return jdbcTemplate.query(sql,studentMapper).get(0);
    }


    public Collection<Student> get(Collection<UUID> uuidCollection) {
        String str = makeSqlString(uuidCollection);
        return jdbcTemplate.query(str,studentMapper);
    }



    private String makeSqlString(Collection<UUID> uuidCollection){
        StringBuilder stringBuilder = new StringBuilder("");

        for(UUID i:uuidCollection){
            stringBuilder.append("select * from student s where uuid in ('" + i.toString() + "';)");
        }

        return stringBuilder.toString();
    }

    public void update(Student student){
        //todo
    }

    public boolean checkForExistence(String code) {
        String str = "select * from student s where code in ('" + code + "');";
        try {
            jdbcTemplate.query(str, studentMapper).get(0);
            return false;
        } catch (NullPointerException npe) {
            return true;
        }
    }
}
