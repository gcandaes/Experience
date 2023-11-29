package com.example.retourexperience.search;

import com.example.retourexperience.ui.model.entity.Experience;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

public class ExperienceSpecificationBuilder {
    private final List<SearchCriteria> params;

    public ExperienceSpecificationBuilder() {
        this.params = new ArrayList<>();
    }

    public final ExperienceSpecificationBuilder with(String key, String operation, Object value) {
        params.add(new SearchCriteria(key, operation, value));
        return this;
    }

    public final ExperienceSpecificationBuilder with(SearchCriteria searchCriteria) {
        params.add(searchCriteria);
        return this;
    }

    public Specification<Experience> build() {
        if (params.size() == 0) {
            return null;
        }


        Specification<Experience> result = new ExperienceSpecification(params.get(0));

        for (int idx = 1; idx < params.size(); idx++) {


            SearchCriteria criteria = params.get(idx);

            System.out.println("Construction de la spécification pour le critère : " + criteria);


            result = criteria.getDataOption().equals("ALL") ?
                    Specification.where(result).and(new ExperienceSpecification(criteria))
                    : Specification.where(result).or(new ExperienceSpecification(criteria));

        }
        return result;
    }
}
