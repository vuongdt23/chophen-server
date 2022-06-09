package com.uit.chophen.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;

/**
 * The persistent class for the user_profiles database table.
 * 
 */
@Entity
@Table(name = "universities")
@NamedQuery(name = "University.findAll", query = "SELECT u FROM University u")
public class University implements Serializable {
	private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "university_id")
    private int universityId;

    @Column(name = "university_name")
    private String universityName;
    @Column(name = "university_abb_name")
    private String universityAbbName;
    @Column(name = "university_description")
    private String universityDescription;
    
    @Column(name = "university_email_suffix")
    private String universityEmailSuffix;

    @OneToMany(mappedBy="userUniversity")
	private List<UserProfile> users;

    public int getUniversityId() {
        return universityId;
    }

    public void setUniversityId(int universityId) {
        this.universityId = universityId;
    }

    public String getUniversityName() {
        return universityName;
    }

    public void setUniversityName(String universityName) {
        this.universityName = universityName;
    }

    public String getUniversityAbbName() {
        return universityAbbName;
    }

    public void setUniversityAbbName(String universityAbbName) {
        this.universityAbbName = universityAbbName;
    }

    public String getUniversityDescription() {
        return universityDescription;
    }

    public void setUniversityDescription(String universityDescription) {
        this.universityDescription = universityDescription;
    }

    public List<UserProfile> getUsers() {
        return users;
    }

    public void setUsers(List<UserProfile> users) {
        this.users = users;
    }

	public String getUniversityEmailSuffix() {
		return universityEmailSuffix;
	}

	public void setUniversityEmailSuffix(String universityEmailSuffix) {
		this.universityEmailSuffix = universityEmailSuffix;
	}
    
}
