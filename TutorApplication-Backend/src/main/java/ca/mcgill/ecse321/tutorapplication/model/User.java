/*package ca.mcgill.ecse321.tutorapplication.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name= "users")
public class User{
   private String username;

public void setUsername(String value) {
    this.username = value;
}
@Id
public String getUsername() {
    return this.username;
}
private String password;

public void setPassword(String value) {
    this.password = value;
}
public String getPassword() {
    return this.password;
}
private Student student;

public void setStudent(Student student) {
	this.student = student;
}

@OneToOne(optional=true)
public Student getStudent() {
	return this.student;
}

private Tutor tutor;

@OneToOne(optional=true)
public Tutor getTutor() {
	   return this.tutor;
}

public void setTutor(Tutor tutor) {
	   this.tutor=tutor;
}

//   private UserRole userRole;
//   
//   @OneToOne(mappedBy="users" , optional=false)
//   public UserRole getUserRole() {
//      return this.userRole;
//   }
//   
//   public void setUserRole(UserRole userRole) {
//      this.userRole = userRole;
//   }
//   
}
*/