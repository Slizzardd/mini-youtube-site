package ua.com.alevel.service;

import ua.com.alevel.persistence.entity.BaseEntity;


public interface HelpService<E extends BaseEntity> {

    void deletingFileWhenDeleteEntity(E entity);
}
