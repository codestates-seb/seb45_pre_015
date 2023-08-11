package com.preproject.seb_pre_15.question.dummy;

import com.preproject.seb_pre_15.member.entity.Member;
import com.preproject.seb_pre_15.member.repository.MemberRepository;
import com.preproject.seb_pre_15.question.entity.Question;
import com.preproject.seb_pre_15.question.repository.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DummyDataLoader implements CommandLineRunner {

  private final QuestionRepository questionRepository;
  private final MemberRepository memberRepository;

  @Autowired
  public DummyDataLoader(QuestionRepository questionRepository, MemberRepository memberRepository) {
    this.questionRepository = questionRepository;
    this.memberRepository = memberRepository;
  }

  @Override
  public void run(String... args) throws Exception {
    Member member1 = new Member();
    member1.setEmail("hgd@gmail.com");
    Member member2 = new Member();
    member2.setEmail("hgd2@gmail.com");
    Member member3 = new Member();
    member3.setEmail("hgd3@gmail.com");
    // 더미 데이터 생성
    Question question1 = new Question();
    question1.setTitle("Sample Title 1");
    question1.setBody("Sample Body 1");
    question1.setView(10L);
    question1.setMember(member1);

    Question question2 = new Question();
    question2.setTitle("Sample Title 2");
    question2.setBody("Sample Body 2");
    question2.setView(5L);
    question2.setMember(member2);

    Question question3 = new Question();
    question3.setTitle("Sample Title 3");
    question3.setBody("Sample Body 3");
    question3.setView(5L);
    question3.setMember(member3);

    // 더미 데이터 저장
    memberRepository.save(member1);
    memberRepository.save(member2);
    memberRepository.save(member3);
    questionRepository.save(question1);
    questionRepository.save(question2);
    questionRepository.save(question3);
  }
}
