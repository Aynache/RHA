package com.example.RHA.repositories;

import com.example.RHA.models.Role;
import com.example.RHA.models.Role_user;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long>{

    Optional<Role> findByName(Role_user name);

}
