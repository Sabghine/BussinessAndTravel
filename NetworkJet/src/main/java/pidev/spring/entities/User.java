package pidev.spring.entities;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.sun.istack.NotNull;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.FieldDefaults;

@SuppressWarnings("ALL")
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "T_User")
public class User implements Serializable {

	/**
	 *
	 */

	/*
	 * This's a simple check for last commit
	 */
	private static final long serialVersionUID = 1L;
	/*-----------------------****Bean_Attributes****-------------------------------------*/
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "User_id")
	Long id;

	@NotNull
	@Column(name = "User_First_Name")
	String firstName;

	@NotNull
	@Column(name = "User_Last_Name")
	String lastName;

	@NotNull
	@Column(columnDefinition = "boolean default false")
	boolean actif;

	@Column(name = "User_Birthday_Date")
	@Temporal(TemporalType.DATE)
	Date date = Calendar.getInstance().getTime();;

	@NotNull
	@Column(name = "User_Email")
	String email;

	@NotNull
	@Column(name = "User_Password")
	String password;

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
	Set<Role> role = new HashSet<>();

	@Column(name = "created_time", updatable = false)
	Date createdTime;

	@Column(name = "Last_Logged_In", updatable = false)
	Date lastLoggedIn;

	@Column(name = "Last_Logged_out", updatable = false)
	Date lastLoggedOut;

	@Column(name = "Last_Session_Id_Generated")
	String Session_Id;

	@Column(name = "account_non_locked")
	boolean accountNonLocked;

	@Column(name = "failed_attempt")
	int failedAttempt;

	@Column(name = "lock_time")
	Date lockTime;
	@Enumerated(EnumType.STRING)
	Domaine domaine;
	@OneToOne
	Invitation invitation;

	public User(String email, String firstName, String lastName, boolean actif, Date date, String password) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.actif = actif;
		this.email = email;
		this.password = password;
	}

	public String getFullName() {
		return getFirstName() + " " + getLastName();
	}

}
