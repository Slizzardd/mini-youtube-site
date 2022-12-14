package ua.com.alevel.persistence.crud.impl;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import ua.com.alevel.exception.EntityNotFoundException;
import ua.com.alevel.persistence.crud.CrudRepositoryHelper;
import ua.com.alevel.persistence.datatable.DataTableRequest;
import ua.com.alevel.persistence.datatable.DataTableResponse;
import ua.com.alevel.persistence.entity.BaseEntity;
import ua.com.alevel.persistence.repository.BaseRepository;

import java.util.Optional;

@Service
public class CrudRepositoryHelperImpl<
        E extends BaseEntity,
        R extends BaseRepository<E>>
        implements CrudRepositoryHelper<E, R> {

    @Override
    @Transactional(isolation = Isolation.REPEATABLE_READ)
    public void create(R repository, E entity) {
        repository.save(entity);
    }

    @Override
    @Transactional(isolation = Isolation.REPEATABLE_READ)
    public void update(R repository, E entity) {
        checkExist(repository, entity.getId());
        repository.save(entity);
    }

    @Override
    @Transactional(isolation = Isolation.REPEATABLE_READ)
    public void delete(R repository, Long id) {
        checkExist(repository, id);
        repository.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<E> findById(R repository, Long id) {
        checkExist(repository, id);
        return repository.findById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public DataTableResponse<E> findAll(R repository, DataTableRequest request) {
        RequestHelper requestHelper = new RequestHelper();
        requestFill(requestHelper, request);

        Page<E> pageEntity = repository.findAll(requestHelper.pageRequest);

        return generateDataTableResponse(pageEntity, requestHelper, request);
    }

    @Override
    @Transactional(readOnly = true)
    public DataTableResponse<E> findAll(R repository, DataTableRequest request, Class<E> entityClass) {
        RequestHelper requestHelper = new RequestHelper();
        requestFill(requestHelper, request);

//        SearchSpecification<E> searchSpecification = new SearchSpecificationProcess<>();
//        Specification<E> specification = searchSpecification.generateSpecification(request, entityClass);

//        Page<E> pageEntity = repository.findAll(specification, requestHelper.pageRequest);
        Page<E> pageEntity = repository.findAll(requestHelper.pageRequest);

        return generateDataTableResponse(pageEntity, requestHelper, request);
    }

    private void checkExist(R repository, Long id) {
        if (!repository.existsById(id)) {
            throw new EntityNotFoundException("this entity is not found");
        }
    }

    private void requestFill(RequestHelper requestHelper, DataTableRequest dataTableRequest) {
        requestHelper.size = dataTableRequest.getSize();
        requestHelper.sortBy = dataTableRequest.getSort();
        requestHelper.orderBy = dataTableRequest.getOrder();

        requestHelper.sort = dataTableRequest.getOrder().equals("desc")
                ? Sort.by(dataTableRequest.getSort()).descending()
                : Sort.by(dataTableRequest.getSort()).ascending();

        requestHelper.pageRequest = PageRequest.of(requestHelper.page, requestHelper.size, requestHelper.sort);
    }

    private DataTableResponse<E> generateDataTableResponse(
            Page<E> pageEntity,
            RequestHelper requestHelper,
            DataTableRequest dataTableRequest) {
        DataTableResponse<E> dataTableResponse = new DataTableResponse<>();
        dataTableResponse.setItemsSize(pageEntity.getTotalElements());
        dataTableResponse.setTotalPageSize(pageEntity.getTotalPages());
        dataTableResponse.setItems(pageEntity.getContent());
        dataTableResponse.setOrder(requestHelper.orderBy);
        dataTableResponse.setSort(requestHelper.sortBy);
        dataTableResponse.setPageSize(dataTableRequest.getSize());
        return dataTableResponse;
    }

    private static class RequestHelper {

        private int size;
        private final int page;
        private String sortBy;
        private String orderBy;
        private Sort sort;
        private PageRequest pageRequest;

        public RequestHelper() {
            this.page = 0;
        }
    }
}
