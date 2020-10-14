package com.charartpav.converte.models;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/*@author Artem Charykov*/

@Entity
@Table(name = "UserRole")
@XmlRootElement
@NamedQueries({
   @NamedQuery(name = "UserRole.findAll", query = "SELECT u FROM UserRole u"),
   @NamedQuery(name = "UserRole.findByUserRoleID", query = "SELECT u FROM UserRole u WHERE u.userRoleID = :userRoleID"),
   @NamedQuery(name = "UserRole.findByRole", query = "SELECT u FROM UserRole u WHERE u.role = :role")})
public class UserRole implements Serializable {

   private static final long serialVersionUID = 1L;

   @Id
   @Basic(optional = false)
   @Column(name = "UserRoleID")
   private int userRoleID;

   @Basic(optional = false)
   @Column(name = "Role")
   private String role;

   @OneToMany(cascade = CascadeType.ALL, mappedBy = "userRoleID", fetch = FetchType.LAZY)
   private List<UserList> userListList;

   public UserRole() {}
   public UserRole(int userRoleID) { this.userRoleID = userRoleID; }
   public UserRole(int userRoleID, String role) { this.userRoleID = userRoleID; this.role = role; }

   public int getUserRoleID() { return userRoleID; }
   public void setUserRoleID(int userRoleID) { this.userRoleID = userRoleID; }

   public String getRole() { return role; }
   public void setRole(String role) { this.role = role; }

   @XmlTransient
   public List<UserList> getUserListList() { return userListList; }

   public void setUserListList(List<UserList> userListList) { this.userListList = userListList; }

	@Override
	public int hashCode() {
		return 0;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		final UserRole other = (UserRole) obj;
		return this.userRoleID == other.userRoleID;
	}

   @Override
   public String toString() {
      return "UserRole[" + userRoleID + " "+ role + " ]";
   }
}