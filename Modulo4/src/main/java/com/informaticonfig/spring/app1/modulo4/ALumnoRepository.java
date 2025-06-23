package com.informaticonfig.spring.app1.modulo4;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ALumnoRepository extends JpaRepository<Alumno, Long> {
}
