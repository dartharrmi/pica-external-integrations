package com.archilabs.pica.integrations.model;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(schema = "dannCarlton", name = "TOURESBALON_RESERVATION")
@EntityListeners(AuditingEntityListener.class)
@IdClass(TouresBalonReservation.TouresBalonReservationPK.class)
public class TouresBalonReservation implements Serializable {

    @Id
    @Column(name = "ORDER_ID")
    private String orderId;

    @Id
    @Column(name = "HOTEL_ID")
    private Integer hotelId;

    @Id
    @Column(name = "ROOM_NUMBER")
    private Integer roomNumber;

    @Id
    @Column(name = "CHECK_IN_DATE")
    private Date checkInDate;

    @Id
    @Column(name = "CHECK_OUT_DATE")
    private Date checkOutDate;

    @Column(name = "STATE")
    private Integer state = 0;

    @Column(name = "GUEST_NAME")
    private String guestName;

    public TouresBalonReservation() {
    }

    public TouresBalonReservation(String orderId, Integer hotelId, Integer roomNumber, Date checkInDate, Date checkOutDate, Integer state, String guestName) {
        this.orderId = orderId;
        this.hotelId = hotelId;
        this.roomNumber = roomNumber;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
        this.state = state;
        this.guestName = guestName;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public Integer getHotelId() {
        return hotelId;
    }

    public void setHotelId(Integer hotelId) {
        this.hotelId = hotelId;
    }

    public Integer getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(Integer roomNumber) {
        this.roomNumber = roomNumber;
    }

    public Date getCheckInDate() {
        return checkInDate;
    }

    public void setCheckInDate(Date checkInDate) {
        this.checkInDate = checkInDate;
    }

    public Date getCheckOutDate() {
        return checkOutDate;
    }

    public void setCheckOutDate(Date checkOutDate) {
        this.checkOutDate = checkOutDate;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public String getGuestName() {
        return guestName;
    }

    public void setGuestName(String guestName) {
        this.guestName = guestName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TouresBalonReservation that = (TouresBalonReservation) o;
        return orderId.equals(that.orderId) &&
                hotelId.equals(that.hotelId) &&
                roomNumber.equals(that.roomNumber) &&
                checkInDate.equals(that.checkInDate) &&
                checkOutDate.equals(that.checkOutDate) &&
                state.equals(that.state) &&
                guestName.equals(that.guestName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(orderId, hotelId, roomNumber, checkInDate, checkOutDate, state, guestName);
    }

    @Override
    public String toString() {
        return "TouresBalonReservation{" +
                "orderId='" + orderId + '\'' +
                ", hotelId=" + hotelId +
                ", roomNumber=" + roomNumber +
                ", checkInDate=" + checkInDate +
                ", checkOutDate=" + checkOutDate +
                ", state=" + state +
                ", guestName='" + guestName + '\'' +
                '}';
    }

    public static class TouresBalonReservationPK implements Serializable {

        private String orderId;
        private Integer hotelId;
        private Integer roomNumber;
        private Date checkInDate;
        private Date checkOutDate;

        public TouresBalonReservationPK() {
        }

        public TouresBalonReservationPK(String orderId, Integer hotelId, Integer roomNumber, Date checkInDate, Date checkOutDate) {
            this.orderId = orderId;
            this.hotelId = hotelId;
            this.roomNumber = roomNumber;
            this.checkInDate = checkInDate;
            this.checkOutDate = checkOutDate;
        }

        public String getOrderId() {
            return orderId;
        }

        public void setOrderId(String orderId) {
            this.orderId = orderId;
        }

        public Integer getHotelId() {
            return hotelId;
        }

        public void setHotelId(Integer hotelId) {
            this.hotelId = hotelId;
        }

        public Integer getRoomNumber() {
            return roomNumber;
        }

        public void setRoomNumber(Integer roomNumber) {
            this.roomNumber = roomNumber;
        }

        public Date getCheckInDate() {
            return checkInDate;
        }

        public void setCheckInDate(Date checkInDate) {
            this.checkInDate = checkInDate;
        }

        public Date getCheckOutDate() {
            return checkOutDate;
        }

        public void setCheckOutDate(Date checkOutDate) {
            this.checkOutDate = checkOutDate;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            TouresBalonReservationPK that = (TouresBalonReservationPK) o;
            return orderId.equals(that.orderId) &&
                    hotelId.equals(that.hotelId) &&
                    roomNumber.equals(that.roomNumber) &&
                    checkInDate.equals(that.checkInDate) &&
                    checkOutDate.equals(that.checkOutDate);
        }

        @Override
        public int hashCode() {
            return Objects.hash(orderId, hotelId, roomNumber, checkInDate, checkOutDate);
        }
    }
}
