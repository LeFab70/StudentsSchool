package com.netbeans.projet01.utils.Validations;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class UniqueValueValidator implements ConstraintValidator<UniqueValue,Object> {
    @PersistenceContext
    private EntityManager entityManager;

    private String fieldName;
    private Class<?> domainClass;

    @Override
    public void initialize(UniqueValue constraintAnnotation) {
        this.fieldName = constraintAnnotation.fieldName();
        this.domainClass = constraintAnnotation.domainClass();
    }

//    @Override
//    public boolean isValid(Object o, ConstraintValidatorContext constraintValidatorContext) {
//        return false;
//    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        if (value == null) {
            return true; // ou false, selon votre logique métier
        }

        String queryStr = String.format("SELECT COUNT(*) > 0 FROM %s WHERE %s = :value",
                domainClass.getSimpleName(), fieldName);
        Query query = entityManager.createQuery(queryStr);
        query.setParameter("value", value);

        boolean exists = (Boolean) query.getSingleResult();
        return !exists;
    }

}
