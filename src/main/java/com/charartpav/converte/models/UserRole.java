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

/* @author Artem Charykov*/

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
   private Long userRoleID;
   
   @Basic(optional = false)
   @Column(name = "Role")
   private String role;
   
   @OneToMany(cascade = CascadeType.ALL, mappedBy = "userRoleID", fetch = FetchType.LAZY)
   private List<UserList> userListList;

   public UserRole() {}
   public UserRole(Long userRoleID) { this.userRoleID = userRoleID; }
   public UserRole(Long userRoleID, String role) { this.userRoleID = userRoleID; this.role = role; }

   public Long getUserRoleID() { return userRoleID; }
   public void setUserRoleID(Long userRoleID) { this.userRoleID = userRoleID; }

   public String getRole() { return role; }

   public void setRole(String role) { this.role = role; }

   @XmlTransient
   public List<UserList> getUserListList() { return userListList; }

   public void setUserListList(List<UserList> userListList) { this.userListList = userListList; }

   @Override
   public int hashCode() {
      int hash = 0;
      hash += (userRoleID != null ? userRoleID.hashCode() : 0);
      return hash;
   }

   @Override
   public boolean equals(Object object) {
      // TODO: Warning - this method won't work in the case the id fields are not set
      if (!(object instanceof UserRole)) {
         return false;
      }
      UserRole other = (UserRole) object;
      if ((this.userRoleID == null && other.userRoleID != null) || (this.userRoleID != null && !this.userRoleID.equals(other.userRoleID))) {
         return false;
      }
      return true;
   }

   @Override
   public String toString() {
      return "UserRole[" + userRoleID + " "+ role + " ]";
   }

}
