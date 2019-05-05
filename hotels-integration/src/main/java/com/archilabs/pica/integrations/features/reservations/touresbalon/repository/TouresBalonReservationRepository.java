package com.archilabs.pica.integrations.features.reservations.touresbalon.repository;

import com.archilabs.pica.integrations.model.TouresBalonReservation;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface TouresBalonReservationRepository extends CrudRepository<TouresBalonReservation, TouresBalonReservation.TouresBalonReservationPK> {

    List<TouresBalonReservation> findAllByCheckInDate(Date checkinDate);

    List<TouresBalonReservation> findAllByCheckOutDate(Date checkinDate);

    List<TouresBalonReservation> findAllByCheckInDateEqualsAndCheckOutDateEquals(Date checkinDate, Date checkoutDate);

    List<TouresBalonReservation> findAllByHotelId(Integer hotelId);

    List<TouresBalonReservation> findAllByGuestName(String guestName);

    List<TouresBalonReservation> findAllByOrderId(String orderId);
}
