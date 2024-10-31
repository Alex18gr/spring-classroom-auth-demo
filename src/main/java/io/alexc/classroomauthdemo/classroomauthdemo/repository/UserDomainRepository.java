package io.alexc.classroomauthdemo.classroomauthdemo.repository;

import io.alexc.classroomauthdemo.classroomauthdemo.entity.UserDomain;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserDomainRepository extends JpaRepository<UserDomain, Long> {

    Optional<UserDomain> findByUsername(String username);

}
