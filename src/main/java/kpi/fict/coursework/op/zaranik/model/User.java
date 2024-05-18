package kpi.fict.coursework.op.zaranik.model;


import java.util.Objects;
import lombok.Getter;

@Getter
public class User {
  private String username;
  private String password;
  private int id;
  private String passwordHash;
  private RoleType roleType;


  public User(Integer userId, String username, String password, RoleType roleType) {
    this.id = userId;
    this.username = username;
    this.password = password;
    this.passwordHash = String.valueOf(password.hashCode());
    this.roleType = roleType;
  }

  public User(String username, String password, RoleType roleType) {
    this.username = username;
    this.password = password;
    this.roleType = roleType;
  }

  public User(String username, String password) {
    this.username = username;
    this.password = password;
    this.roleType = RoleType.USER;
  }


  public void setUsername(String username) {
    this.username = username;
  }

  public int getUserId(){ return this.id;};

  public void setUserId(int id) {this.id = id;}


  @Override
  public String toString() {
    return "User{" +
        "username='" + username + '\'' +
        ", password='" + password + '\'' +
        '}';
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    User user = (User) o;
    return Objects.equals(username, user.username) && Objects.equals(password,
        user.password);
  }

  @Override
  public int hashCode() {
    return Objects.hash(username, password);
  }

}