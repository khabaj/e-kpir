package pl.ekpir.user;

import pl.ekpir.company.CompanyEntity;
import javax.persistence.*;
import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * Created by Krystian on 2016-03-26.
 */
@Entity
@Table(name = "user")
public class UserEntity {

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column
    private Long userId;

    @Column(unique=true)
    private String login;

    @Column
    private String password;

    @Enumerated(EnumType.STRING)
	private RoleEnum role;

    @JsonIgnore
    @OneToOne(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private CompanyEntity company;

    public UserEntity(){

    }

    public UserEntity(Long userId) {
        this.userId = userId;
    }

    public RoleEnum getRole() {
		return role;
	}

	public void setRole(RoleEnum role) {
		this.role = role;
	}

	public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
        
    public CompanyEntity getCompany() {
        return company;
    }

    public void setCompany(CompanyEntity company) {
        this.company = company;
    }
}
