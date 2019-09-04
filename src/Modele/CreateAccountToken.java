package Modele;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "createaccounttoken")
public class CreateAccountToken {

	public CreateAccountToken(String email, int code, String token) {
		super();
		this.email = email;
		this.code = code;
		this.token = token;
	}
	@Id
	@GeneratedValue
	private int id;
	private String email;
	private int code;
	private String token;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
}
