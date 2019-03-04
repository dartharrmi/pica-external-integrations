package com.archilabs.pica.integrations.features.reservations.touresbalon.repository;

import com.archilabs.pica.integrations.model.TouresBalonReservation;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TouresBalonReservationRepository extends CrudRepository<TouresBalonReservation, TouresBalonReservation.TouresBalonReservationPK> {
}
