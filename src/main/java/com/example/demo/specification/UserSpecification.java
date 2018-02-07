package com.example.demo.specification;

import com.example.demo.domains.User;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.Predicate;
import java.util.LinkedList;
import java.util.List;

public class UserSpecification {

    public static Specification<User> deletedFalseUsers() {
        return (root, criteriaQuery, criteriaBuilder) -> {
            List<Predicate> predicates = new LinkedList<>();
            predicates.add(criteriaBuilder.equal(root.get("deleted"), Boolean.FALSE));
            return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
        };
    }
}
