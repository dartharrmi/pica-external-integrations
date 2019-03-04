package com.archilabs.pica.integrations.model;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(schema = "dannCarlton", name = "ROOM")
@EntityListeners(AuditingEntityListener.class)
@IdClass(Room.RoomPK.class)
public class Room implements Serializable {

    @Id
    @Column(name = "ROOM_NUMBER")
    private Integer roomNumber;

    @Id
    @Column(name = "HOTEL_ID")
    private Integer hotelId;

    @Column(name = "TYPE")
    private String type;

    @Column(name = "PRICE")
    private String price;

    public Room() {
    }

    public Room(Integer roomNumber, Integer hotelId, String type, String price) {
        this.roomNumber = roomNumber;
        this.hotelId = hotelId;
        this.type = type;
        this.price = price;
    }

    public Integer getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(Integer roomNumber) {
        this.roomNumber = roomNumber;
    }

    public Integer getHotelId() {
        return hotelId;
    }

    public void setHotelId(Integer hotelId) {
        this.hotelId = hotelId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Room room = (Room) o;
        return roomNumber.equals(room.roomNumber) &&
                hotelId.equals(room.hotelId) &&
                type.equals(room.type) &&
                price.equals(room.price);
    }

    @Override
    public int hashCode() {
        return Objects.hash(roomNumber, hotelId, type, price);
    }

    @Override
    public String toString() {
        return "Room{" +
                "roomNumber=" + roomNumber +
                ", hotelId=" + hotelId +
                ", type='" + type + '\'' +
                ", price='" + price + '\'' +
                '}';
    }

    public static class RoomPK implements Serializable {

        protected Integer roomNumber;
        protected Integer hotelId;

        public RoomPK() {
        }

        public RoomPK(Integer roomNumber, Integer hotelId) {
            this.roomNumber = roomNumber;
            this.hotelId = hotelId;
        }

        public Integer getRoomNumber() {
            return roomNumber;
        }

        public void setRoomNumber(Integer roomNumber) {
            this.roomNumber = roomNumber;
        }

        public Integer getHotelId() {
            return hotelId;
        }

        public void setHotelId(Integer hotelId) {
            this.hotelId = hotelId;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            RoomPK roomPK = (RoomPK) o;
            return roomNumber.equals(roomPK.roomNumber) &&
                    hotelId.equals(roomPK.hotelId);
        }

        @Override
        public int hashCode() {
            return Objects.hash(roomNumber, hotelId);
        }
    }
}
