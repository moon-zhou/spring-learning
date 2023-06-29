package org.moonzhou.jpa;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.moonzhou.jpa.entity.primary.StudentEntity;
import org.moonzhou.jpa.entity.second.TeacherEntity;
import org.moonzhou.jpa.repository.primary.StudentRepository;
import org.moonzhou.jpa.repository.second.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class JpaRepositoryTest {
    @Autowired
    StudentRepository studentRepository;

    @Autowired
    TeacherRepository teacherRepository;

    @Test
    public void jpaOperation() {
        StudentEntity studentEntity = new StudentEntity();
        studentEntity.setName("BUG弄潮儿");
        studentEntity.setSex(1);
        studentEntity.setGrade("一年级");
        studentRepository.save(studentEntity);

        TeacherEntity teacherEntity = new TeacherEntity();
        teacherEntity.setName("Java乐园");
        teacherEntity.setSex(2);
        teacherEntity.setOffice("语文");
        teacherRepository.save(teacherEntity);

        // clear test data
        studentRepository.delete(studentEntity);
        teacherRepository.delete(teacherEntity);
    }
}