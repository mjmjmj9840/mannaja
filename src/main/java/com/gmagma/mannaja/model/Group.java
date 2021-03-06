package com.gmagma.mannaja.model;

import com.gmagma.mannaja.dto.GroupRequestDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@Table(name = "GROUP_TABLE")
@Entity
public class Group extends Timestamped {
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;  // 그룹명

    @Column(nullable = false, unique = true)
    private String link;  // 그룹 url 링크 식별자

    @Column
    private Long leaderId;  // 그룹장 id

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "group")
    private List<Member> members = new ArrayList<Member>();

    public void addMember(Member member) {
        members.add(member);
        member.setGroup(this);
    }

    public Group(GroupRequestDto requestDto) {
        this.name = requestDto.getName();
        this.link = requestDto.getLink();
        this.leaderId = requestDto.getLeaderId();
    }

    public void update(GroupRequestDto requestDto) {
        this.name = requestDto.getName();
        this.link = requestDto.getLink();
        this.leaderId = requestDto.getLeaderId();
    }
}
