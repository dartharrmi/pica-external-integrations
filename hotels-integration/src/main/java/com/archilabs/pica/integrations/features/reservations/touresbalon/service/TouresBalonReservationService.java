package com.archilabs.pica.integrations.features.reservations.touresbalon.service;

import com.archilabs.pica.integrations.features.reservations.touresbalon.repository.TouresBalonReservationRepository;
import com.archilabs.pica.integrations.model.TouresBalonReservation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TouresBalonReservationService implements ITouresBalonReservationService {

    private final TouresBalonReservationRepository repository;

    @Autowired
    public TouresBalonReservationService(TouresBalonReservationRepository repository) {
        this.repository = repository;
    }

    @Override
    public TouresBalonReservation createReservation(TouresBalonReservation newReservation) {
        return repository.save(newReservation);
    }
}
