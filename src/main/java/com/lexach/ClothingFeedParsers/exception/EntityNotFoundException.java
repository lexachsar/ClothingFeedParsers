package com.lexach.ClothingFeedParsers.exception;

/**
 * If entity ot found in the database and it's needed for method
 * right execution, then this exception is throwed.
 */
public class EntityNotFoundException extends Exception {

    /**
     * Not found entity class.
     */
    private Class entityClass;

    /**
     * Search term, that didn't match with any of entities.
     */
    private String searchTerm;

    public EntityNotFoundException(Class entityClass, String searchTerm) {
        this.entityClass = entityClass;
        this.searchTerm = searchTerm;
    }

    public Class getEntityClass() {
        return entityClass;
    }

    public String getSearchTerm() {
        return searchTerm;
    }

}