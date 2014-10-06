package cn.mobiledaily.p2plite.repository;

import cn.mobiledaily.p2plite.domain.IdCardBack;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by johnson on 14-10-6.
 */
@Repository
public interface IdCardBackRepository extends MongoRepository<IdCardBack, String> {
}
