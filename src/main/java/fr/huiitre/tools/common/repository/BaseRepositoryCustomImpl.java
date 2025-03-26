package fr.huiitre.tools.common.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

public abstract class BaseRepositoryCustomImpl implements BaseRepository {
    
    @PersistenceContext
    protected EntityManager entityManager;

}
