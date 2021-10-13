package com.github.zjiajun.spring.sandbox.polling.repository;

import com.github.zjiajun.spring.sandbox.polling.model.Role;
import com.github.zjiajun.spring.sandbox.polling.model.RoleName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @author zhujiajun
 * @version 1.0
 * @since 2021/10/9 22:02
 */
@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

    Optional<Role> findByName(RoleName roleName);
}
