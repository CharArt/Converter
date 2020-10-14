package com.charartpav.converte.models;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/*@author Artem Charykov*/

@Entity
@Table(name = "UserList")
@XmlRootElement
@NamedQueries({
	@NamedQuery(name = "UserList.findAll", query = "SELECT u FROM UserList u"),
	@NamedQuery(name = "UserList.findByUserID", query = "SELECT u FROM UserList u WHERE u.userID = :userID"),
	@NamedQuery(name = "UserList.findByUserLogin", query = "SELECT u FROM UserList u WHERE u.userLogin = :userLogin"),
	@NamedQuery(name = "UserList.findByUserPassword", query = "SELECT u FROM UserList u WHERE u.userPassword = :userPassword"),
	@NamedQuery(name = "UserList.findByName", query = "SELECT u FROM UserList u WHERE u.name = :name"),
	@NamedQuery(name = "UserList.findBySurname", query = "SELECT u FROM UserList u WHERE u.surname = :surname"),
	@NamedQuery(name = "UserList.findByPatronymic", query = "SELECT u FROM UserList u WHERE u.patronymic = :patronymic"),
	@NamedQuery(name = "UserList.findByEmail", query = "SELECT u FROM UserList u WHERE u.email = :email"),
	@NamedQuery(name = "UserList.findByRegistrationDate", query = "SELECT u FROM UserList u WHERE u.registrationDate = registrationDate")})
public class UserList implements Serializable {

	private static final long serialVersionUID = 1L;
   
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	@Column(name = "UserID")
	private Long userID;
   
	@Basic(optional = false)
	@Column(name = "UserLogin")
	@Size(min = 2, max=25, message = "Login should be from 1 to 25 symbols.")
	@NotEmpty(message = "Login is required.")
	private String userLogin;
	
	@Basic(optional = false)
	@Column(name = "UserPassword")
	@NotEmpty(message = "Password is required.")
	@Size(min = 2, max=25, message = "Password should be from 1 to 25 symbols.")
	private String userPassword;
   
	@Basic(optional = false)
	@Column(name = "Name")
	@NotEmpty(message = "Name is required.")
	@Size(min = 2, max=25, message = "Name should be from 1 to 25 symbols.")
	private String name;
   
	@Basic(optional = false)
	@Column(name = "Surname")
	@NotEmpty(message = "Surname is required.")
	@Size(min = 2, max=25, message = "Surname should be from 1 to 25 symbols.")
	private String surname;
   
	@Basic(optional = false)
	@Column(name = "Patronymic")
	@NotEmpty(message = "Patronymic is required.")
	@Size(min = 2, max=25, message = "Patronymic should be from 1 to 25 symbols.")
	private String patronymic;
	
	@Basic(optional = false)
	@Column(name = "Email")
	@NotEmpty(message = "Email is required.")
	@Size(min = 2, max=25, message = "Email should be from 1 to 25 symbols.")
	@Email(message = "In entered an incorrect Email address.")
	private String email;
   
	@Basic(optional = false)
	@Column(name = "RegistrationDate")
	@Temporal(TemporalType.TIMESTAMP)
	private Date registrationDate;
   
	@JoinColumn(name = "UserRoleID", referencedColumnName = "UserRoleID")
	@ManyToOne(optional = false, fetch = FetchType.LAZY)
	private UserRole userRoleID;

	public UserList() {}

	public UserList(Long userID) { this.userID = userID; }

	public UserList(String userLogin, 
					String userPassword, 
					String name, 
					String surname, 
					String patronymic, 
					String email) {
		this.userLogin = userLogin;
		this.userPassword = userPassword;
		this.name = name;
		this.surname = surname;
		this.patronymic = patronymic;
		this.email = email;
	}

	public Long getUserID() { return userID; }
	public void setUserID(Long userID) { this.userID = userID; }

	public String getUserLogin() { return userLogin; }
	public void setUserLogin(String userLogin) { this.userLogin = userLogin; }

	public String getUserPassword() { return userPassword; }
	public void setUserPassword(String userPassword) { this.userPassword = userPassword; }

	public String getName() { return name; }
	public void setName(String name) { this.name = name; }

	public String getSurname() { return surname; }
	public void setSurname(String surname) { this.surname = surname; }

	public String getPatronymic() { return patronymic; }
	public void setPatronymic(String patronymic) { this.patronymic = patronymic; }

	public String getEmail() { return email; }
	public void setEmail(String email) { this.email = email; }

	public Date getRegistrationDate() { return registrationDate; }
	public void setRegistrationDate(Date registrationDateTime) { this.registrationDate = registrationDateTime; }

	public UserRole getUserRoleID() { return userRoleID; }
	public void setUserRoleID(UserRole userRoleID) { this.userRoleID = userRoleID; }

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (userID != null ? userID.hashCode() : 0);
		return hash;
   }

	@Override
	public boolean equals(Object object) {
		if (!(object instanceof UserList)) {
			return false;
		}
		UserList other = (UserList) object;
		return !((this.userID == null && other.userID != null) || (this.userID != null && !this.userID.equals(other.userID)));
	}

	@Override
	public String toString() {
		return "UserList[ " + userID + " " + userLogin + " " + userPassword + " " + name + " " + surname + " " + 
						patronymic +" "+ email +" "+ registrationDate +" "+ userRoleID + " ]";
	}
}