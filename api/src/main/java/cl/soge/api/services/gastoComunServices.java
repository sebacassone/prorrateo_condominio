package cl.soge.api.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import cl.soge.api.models.gastoComunModel;
import cl.soge.api.repositories.gastoComunRepository;

@Service
public class gastoComunServices {

    private final gastoComunRepository gastoComunRepository;

    @Autowired
    public gastoComunServices(gastoComunRepository gastoComunRepository) {
        this.gastoComunRepository = gastoComunRepository;
    }

    public boolean registrarGastoComun(gastoComunModel gastoComun) {
        try {
            gastoComunRepository.save(gastoComun);
            return true;
        } catch (Exception e) {
            // Log the exception
            e.printStackTrace();
            return false;
        }
    }
}
