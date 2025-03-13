package com.company.projectmanagementdata.app;

import com.company.projectmanagementdata.entity.Project;
import com.company.projectmanagementdata.entity.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsersService {

    @PersistenceContext
    private EntityManager entityManager;

    public List<User> getUsersNotInProject(Project project, int firstResult, int maxResults) {
        return entityManager.createQuery("SELECT u FROM User u " +
                "WHERE u.id NOT IN " +
                "(SELECT u1.id FROM User u1 " +
                "INNER JOIN u1.projects pul " +
                "WHERE pul.id = ?1)", User.class)
                .setParameter(1, project.getId())
                .setFirstResult(firstResult)
                .setMaxResults(maxResults)
                .getResultList();
    }
}