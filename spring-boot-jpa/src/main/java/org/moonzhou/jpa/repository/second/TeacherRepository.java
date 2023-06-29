package org.moonzhou.jpa.repository.second;

import org.moonzhou.jpa.entity.second.TeacherEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
 
@Repository
public interface TeacherRepository extends JpaRepository<TeacherEntity, Long> {
 
 
}