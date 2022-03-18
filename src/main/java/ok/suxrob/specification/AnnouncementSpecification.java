package ok.suxrob.specification;

import ok.suxrob.entity.AnnouncementEntity;
import org.springframework.data.jpa.domain.Specification;

public class AnnouncementSpecification {

    public static Specification<AnnouncementEntity> idIsNotNull(String field) {
        return ((root, query, criteriaBuilder) ->
                criteriaBuilder.isNotNull(root.get(field)));
    }

    public static Specification<AnnouncementEntity> equal(String feild, Integer value) {
        return (((root, query, criteriaBuilder) -> {
            return criteriaBuilder.equal(root.get(feild), value);
        }));
    }

    public static Specification<AnnouncementEntity> price(Long price) {
        return (((root, query, criteriaBuilder) -> {
            return criteriaBuilder.equal(root.get("price"), price);
        }));
    }

    public static Specification<AnnouncementEntity> transmission(String transmission) {
        return (((root, query, criteriaBuilder) -> {
            return criteriaBuilder.equal(root.get("transmission"), transmission);
        }));
    }
}
