package ua.com.alevel.service;

import ua.com.alevel.persistence.entity.BaseEntity;

import java.io.File;


public interface HelpService<E extends BaseEntity> {

    void deletingFileWhenDeleteEntity(E entity);

    void recursiveDelete(File file);
}
