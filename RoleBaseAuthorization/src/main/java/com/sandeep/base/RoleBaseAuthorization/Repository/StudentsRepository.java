package com.sandeep.base.RoleBaseAuthorization.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sandeep.base.RoleBaseAuthorization.Entity.Student;
@Repository
public interface StudentsRepository extends JpaRepository<Student,Integer> {

}
