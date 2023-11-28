package com.example.retourexperience.search;

import com.example.retourexperience.ui.model.entity.Place;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

public class PlaceSpecificationBuilder {
    private final List<SearchCriteria> params;

    public PlaceSpecificationBuilder() {
        this.params = new ArrayList<>();
    }

    public final PlaceSpecificationBuilder with(String key, String operation, Object value) {
        params.add(new SearchCriteria(key, operation, value));
        return this;
    }

    public final PlaceSpecificationBuilder with(SearchCriteria searchCriteria) {
        params.add(searchCriteria);
        return this;
    }

    public Specification<Place> build() {
        if (params.size() == 0) {
            return null;
        }

        Specification<Place> result = new PlaceSpecification(params.get(0));

        for (int idx = 1; idx < params.size(); idx++) {


            SearchCriteria criteria = params.get(idx);

            System.out.println("Construction de la spécification pour le critère : " + criteria);


            result = criteria.getDataOption().equals("ALL") ?
                    Specification.where(result).and(new PlaceSpecification(criteria))
                    : Specification.where(result).or(new PlaceSpecification(criteria));
        }
        return result;
    }
}
