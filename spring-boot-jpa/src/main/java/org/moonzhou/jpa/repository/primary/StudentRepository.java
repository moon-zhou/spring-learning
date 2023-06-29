package org.moonzhou.jpa.repository.primary;

import org.moonzhou.jpa.entity.primary.StudentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
 
@Repository
public interface StudentRepository extends JpaRepository<StudentEntity, Long> {
 
}