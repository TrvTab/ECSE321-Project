package ca.mcgill.ecse321.tutorapplication.model;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import java.util.Set;
import javax.persistence.OneToMany;

@Entity
public class Room{
	
	@Enumerated(EnumType.STRING)
   private RoomSize roomSize;

public void setRoomSize(RoomSize value) {
    this.roomSize = value;
}
public RoomSize getRoomSize() {
    return this.roomSize;
}
private String roomId;

public void setRoomId(String value) {
    this.roomId = value;
}
@Id
public String getRoomId() {
    return this.roomId;
}
   
   }
