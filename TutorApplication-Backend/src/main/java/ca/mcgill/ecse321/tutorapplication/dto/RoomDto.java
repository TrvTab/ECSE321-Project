package ca.mcgill.ecse321.tutorapplication.dto;

import java.util.Set;

import ca.mcgill.ecse321.tutorapplication.model.Booking;


public class RoomDto {
	
	public RoomSizeDto roomSize;
	public String roomId;
	
	public RoomDto() {
	}
	
	public RoomDto(RoomSizeDto roomSize, String roomId) {
		this.roomSize = roomSize;
		this.roomId = roomId;
	}
	
	public RoomSizeDto getroomSize() {
		return roomSize;
	}
	
	public void setRoomSize(RoomSizeDto roomSize) {
	    this.roomSize = roomSize;
	}
	
	public String getroomId() {
		return roomId;
	}
	
	public void setRoomId(String value) {
	    this.roomId = value;
	}
	
	
}
