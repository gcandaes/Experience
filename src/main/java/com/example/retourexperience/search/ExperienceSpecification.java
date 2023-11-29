package com.example.retourexperience.search;

import com.example.retourexperience.ui.model.entity.Experience;
import com.example.retourexperience.ui.model.entity.Place;
import com.example.retourexperience.ui.model.entity.Work;
import com.example.retourexperience.ui.model.enumeration.FunctionEnum;
import jakarta.persistence.criteria.*;
import org.springframework.data.jpa.domain.Specification;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

public class ExperienceSpecification implements Specification<Experience> {
    private final SearchCriteria searchCriteria;

    public ExperienceSpecification(final SearchCriteria searchCriteria) {
        super();
        this.searchCriteria = searchCriteria;
    }

    @Override
    public Predicate toPredicate(Root<Experience> root, CriteriaQuery<?> query, CriteriaBuilder cb) {

        String strToSearch = searchCriteria.getValue().toString();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        Date dateToSearch = null;

        switch (Objects.requireNonNull(SearchOperation.getSimpleOperation(searchCriteria.getOperation()))) {
            case PLACE_NAME_EQ:
                return cb.equal(placeJoin(root).get(searchCriteria.getFilterKey()), strToSearch);
            case PLACE_ZIP_CODE_BW:
                return cb.like(placeJoin(root).get(searchCriteria.getFilterKey()), strToSearch + "%");
            case PLACE_ZIP_CODE_EQ:
                return cb.like(placeJoin(root).get(searchCriteria.getFilterKey()), strToSearch);
            case WORK_START_DATE_GE:

                try {
                    dateToSearch = dateFormat.parse(strToSearch);
                } catch (ParseException e) {
                    throw new RuntimeException(e);
                }
                return cb.greaterThanOrEqualTo(workJoin(root).get(searchCriteria.getFilterKey()), dateToSearch);

            case WORK_END_DATE_LE:
                try {
                    dateToSearch = dateFormat.parse(strToSearch);
                } catch (ParseException e) {
                    throw new RuntimeException(e);
                }
                return cb.lessThanOrEqualTo(workJoin(root).get(searchCriteria.getFilterKey()), dateToSearch);

            case OCCUPIED_FUNCTION:
                // Supposons que strToSearch est une chaîne de valeurs séparées par des virgules
                strToSearch = strToSearch.replaceAll("\\[|\\]", "");

                System.out.println("strToSearc: " + strToSearch);

                Set<FunctionEnum> functionEnums = Arrays.stream(strToSearch.split(","))
                        .map(String::trim) // Supprimez les espaces autour de chaque valeur
                        .map(FunctionEnum::valueOf)
                        .collect(Collectors.toSet());

                System.out.println("functionEnums: " + functionEnums);

             //   return cb.isTrue(workJoin(root).get(searchCriteria.getFilterKey()).in(functionEnums));

            return cb.in(workJoin(root).get(searchCriteria.getFilterKey())).value(functionEnums);


            //return cb.in(workJoin(root).get(searchCriteria.getFilterKey()));
            case CONTAINS:
                return cb.like(root.get(searchCriteria.getFilterKey()), "%" + strToSearch + "%");

            case DOES_NOT_CONTAIN:
                return cb.notLike(root.get(searchCriteria.getFilterKey()), "%" + strToSearch + "%");

            case EQUAL:
                return cb.equal(placeJoin(root).get(searchCriteria.getFilterKey()), strToSearch);

            case NOT_EQUAL:
                return cb.notEqual(root.get(searchCriteria.getFilterKey()), strToSearch);

            case BEGINS_WITH:
                return cb.like(placeJoin(root).get(searchCriteria.getFilterKey()), strToSearch + "%");

            case GREATER_THAN_EQUAL:
                return cb.ge(root.get(searchCriteria.getFilterKey()),  Integer.parseInt(strToSearch));

            case LESS_THAN_EQUAL:
                return cb.le(root.get(searchCriteria.getFilterKey()),  Integer.parseInt(strToSearch));


            default:
                return null;
        }
    }

    private Join<Experience, Place> placeJoin(Root<Experience> root) {
        return root.join("place");
    }
    private Join<Experience, Work> workJoin(Root<Experience> root) {
        return root.join("work");
    }


}