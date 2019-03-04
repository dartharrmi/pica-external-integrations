package com.archilabs.pica.integrations.features.hotels.repository;

import com.archilabs.pica.integrations.model.Hotel;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HotelRepository extends CrudRepository<Hotel, Integer> {
}
