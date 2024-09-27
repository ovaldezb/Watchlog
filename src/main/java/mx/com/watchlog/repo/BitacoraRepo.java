package mx.com.watchlog.repo;

import mx.com.watchlog.entity.Bitacora;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface BitacoraRepo extends MongoRepository<Bitacora, String> {
}
