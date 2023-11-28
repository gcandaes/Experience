package com.example.retourexperience.search;

import com.example.retourexperience.ui.model.entity.Experience;
import com.example.retourexperience.ui.model.entity.Place;
import jakarta.persistence.criteria.*;
import org.springframework.data.jpa.domain.Specification;

import java.util.Objects;

public class PlaceSpecification implements
        Specification<Place> {
    private final SearchCriteria searchCriteria;

    public PlaceSpecification(final SearchCriteria searchCriteria) {
        super();
        this.searchCriteria = searchCriteria;
    }

    @Override
    public Predicate toPredicate(Root<Place> root, CriteriaQuery<?> query, CriteriaBuilder cb) {

        String strToSearch = searchCriteria.getValue().toString().toLowerCase();

        switch (Objects.requireNonNull(SearchOperation.getSimpleOperation(searchCriteria.getOperation()))) {
            case CONTAINS:
                return cb.like(root.get(searchCriteria.getFilterKey()), "%" + strToSearch + "%");

            case DOES_NOT_CONTAIN:
                return cb.notLike(root.get(searchCriteria.getFilterKey()), "%" + strToSearch + "%");

/*            case EQUAL:
                return cb.equal(root
                                .get(searchCriteria.getFilterKey()),
                        "%" + strToSearch + "%");

            case NOT_EQUAL:
                return cb.notEqual(root
                                .get(searchCriteria.getFilterKey()),
                        "%" + strToSearch + "%");*/
            case EQUAL:
                return cb.equal(root.get(searchCriteria.getFilterKey()), strToSearch);

            case NOT_EQUAL:
                return cb.notEqual(root.get(searchCriteria.getFilterKey()), strToSearch);

            case BEGINS_WITH:
                return cb.like(root.get(searchCriteria.getFilterKey()), strToSearch + "%");

            default:
                return null;
        }
    }

    private Join<Experience, Place> placeJoin(Root<Experience> root) {
        return root.join("place");
    }


}