package com.example.chap10.start.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Setter
@Getter
@NamedQueries({
        @NamedQuery(name = "Member.findByUsername",
                query = "select m from Member m where m.name = :name"),
        @NamedQuery(name = "Member.count",
                query = "select count (m) from Member m")
})
@SqlResultSetMapping(name = "memberWithOrderCount",
        entities = {@EntityResult(entityClass = Member.class)},
        columns = {@ColumnResult(name = "ORDER_COUNT")}
)
public class Member {

    @Id @GeneratedValue
    @Column(name = "MEMBER_ID")
    private Long id;

    private String name;
    private int age;

    @OneToMany(mappedBy ="member")
    private List<Order> orders = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "TEAM_ID")
    private Team team;

    public void setTeam(Team team) {
        team.getMembers().add(this);
        this.team = team;
    }

    @Override
    public String toString() {
        return "Member{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", orders=" + orders +
                ", team=" + team.getName() +
                '}';
    }
}
