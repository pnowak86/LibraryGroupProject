package library.dao;

import library.entity.CrudBookEntity;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by pinq on 15.09.17.
 */
public interface CrudBookRepository extends CrudRepository<CrudBookEntity, Long> {
}
