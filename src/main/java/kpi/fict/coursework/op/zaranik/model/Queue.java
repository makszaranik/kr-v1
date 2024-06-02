package kpi.fict.coursework.op.zaranik.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Queue {
  private String name;
  private User creator;
  private boolean isBlocked;
  private Integer id;

  public Queue(String name, User creator) {
    this.name = name;
    this.creator = creator;
    this.id = this.name.hashCode();
  }

  public boolean isBlocked() {
    return isBlocked;
  }

  public void setIsBlocked(boolean blocked) {
    isBlocked = blocked;
  }

  public void setId(Integer id){
    this.id = id;
  }

  public void setName(String name) {
    this.name = name;
  }

  public void setCreator(User creator) {
    this.creator = creator;
  }

}
