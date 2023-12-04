package cl.soge.api.repositories;

import cl.soge.api.models.gastoComunModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;

public interface gastoComunRepository extends JpaRepository<gastoComunModel, Integer> {

}

