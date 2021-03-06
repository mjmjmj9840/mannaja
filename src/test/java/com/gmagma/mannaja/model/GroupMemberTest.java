package com.gmagma.mannaja.model;

import com.gmagma.mannaja.repository.GroupRepository;
import com.gmagma.mannaja.repository.MemberRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class GroupMemberTest {

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private GroupRepository groupRepository;

    @Transactional
    @Test
    public void saveMember() {
        // given
        Member member = new Member();
        member.setNickname("Kim");
        member.setPassword("2134");

        // when
        Member result = memberRepository.save(member);

        // then
        assertThat(result.getNickname()).isEqualTo(member.getNickname());
        assertThat(result.getPassword()).isEqualTo(member.getPassword());
    }

    @Transactional
    @Test
    public void saveGroup() {
        // given
        Group group = new Group();
        group.setName("group1");
        group.setLink("aaaaaa");

        // when
        Group result = groupRepository.save(group);

        // then
        assertThat(result.getId()).isEqualTo(group.getId());
        System.out.println(result.getLeaderId());  // null
    }

    @Transactional
    @Test
    public void findGroup() {
        // given
        Group group1 = new Group();
        group1.setName("group1");
        group1.setLink("aaaaaa");

        Group group2 = new Group();
        group2.setName("group2");
        group2.setLink("bbbbbb");

        // when
        groupRepository.save(group1);
        groupRepository.save(group2);
        Group result1 = groupRepository.findByName("group1").get();
        Group result2 = groupRepository.findByLink("bbbbbb").get();

        // then
        assertThat(result1.getName()).isEqualTo(group1.getName());
        assertThat(result2.getLink()).isEqualTo(group2.getLink());
    }

    @Transactional
    @Test
    public void addMember() {
        // given
        Group group = new Group();
        group.setName("group1");
        group.setLink("aaaaaa");
        groupRepository.save(group);

        Member member = new Member();
        member.setNickname("Kim");
        member.setPassword("1234");
        memberRepository.save(member);

        // when
        Group g_result = groupRepository.findByLink("aaaaaa").get();
        g_result.setLeaderId(member.getId());
        g_result.addMember(member);

        // then
        assertThat(g_result).isEqualTo(member.getGroup());
        assertThat(g_result.getLeaderId()).isEqualTo(member.getId());
    }

    @Transactional
    @Test
    public void findMemberInGroup() {
        // given
        Group group = new Group();
        group.setName("group1");
        group.setLink("aaaaaa");
        groupRepository.save(group);

        Member member = new Member();
        member.setNickname("Kim");
        member.setPassword("1234");
        memberRepository.save(member);

        group.setLeaderId(member.getId());
        group.addMember(member);

        // when
        Member result = memberRepository.findByGroupAndNickname(group, "Kim").get();

        // then
        assertThat(result).isEqualTo(member);
    }
}
