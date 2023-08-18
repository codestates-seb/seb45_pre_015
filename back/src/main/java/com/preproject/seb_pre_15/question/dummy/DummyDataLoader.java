//package com.preproject.seb_pre_15.question.dummy;
//
//import com.preproject.seb_pre_15.answer.entity.Answer;
//import com.preproject.seb_pre_15.answer.repository.AnswerRepository;
//import com.preproject.seb_pre_15.member.entity.Member;
//import com.preproject.seb_pre_15.member.repository.MemberRepository;
//import com.preproject.seb_pre_15.question.entity.Question;
//import com.preproject.seb_pre_15.question.repository.QuestionRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.stereotype.Component;
//
//@Component
//public class DummyDataLoader implements CommandLineRunner {
//
//  private final QuestionRepository questionRepository;
//  private final MemberRepository memberRepository;
//  private final AnswerRepository answerRepository;
//
//  @Autowired
//  public DummyDataLoader(QuestionRepository questionRepository, MemberRepository memberRepository, AnswerRepository answerRepository) {
//    this.questionRepository = questionRepository;
//    this.memberRepository = memberRepository;
//    this.answerRepository = answerRepository;
//  }
//
//  @Override
//  public void run(String... args) throws Exception {
//    // 더미 데이터 생성
//    Member member1 = new Member("hgd@gmail.com");
//    Member member2 = new Member("hgd2@gmail.com");
//    Member member3 = new Member("hgd3@gmail.com");
//
//    Question question1 = new Question("Sample Title 1", "Sample Body 1", member1,1);
//    Question question2= new Question("Sample Title 2", "Sample Body 2", member2,5);
//    Question question3 = new Question("Sample Title 3", "Sample Body 3", member3,0);
//    Question question4 = new Question("리트리버", "멍멍", member3,300);
//    Question question5 = new Question("불독", "멍멍멍멍", member3, 20);
//    Question question6 = new Question("진돗개", "왈왈", member3,-1);
//    Question question7 = new Question("페르시안", "야옹야옹야옹", member3, -20);
//    Question question8 = new Question("숏헤어", "야옹야옹야옹야옹야옹야옹야옹야옹야옹", member3, 11);
//    Question question9 = new Question("샴", "야옹야옹야~~~~옹", member3, 0);
//    Question question10 = new Question("러시안블루", "미역", member3, 0);
//
//    Answer answer1 = new Answer("첫번째 질문에 대한 답변", question1.getMember(), question1, 1);
//    Answer answer2 = new Answer("첫번째 질문에 대한 답변", question1.getMember(), question1, 2);
//    Answer answer3 = new Answer("첫번째 질문에 대한 답변", question1.getMember(), question1, 10);
//    Answer answer4 = new Answer("정글리안", question2.getMember(), question2, 5);
//    Answer answer5 = new Answer("펄", question2.getMember(), question2, 100);
//    Answer answer6 = new Answer("골든", question2.getMember(), question2, 0);
//    Answer answer7 = new Answer("다섯번째 질문/멤버3 작성", question5.getMember(), question5, -20);
//
//
//    // 더미 데이터 저장
//    memberRepository.save(member1);
//    memberRepository.save(member2);
//    memberRepository.save(member3);
//
//    questionRepository.save(question1);
//    questionRepository.save(question2);
//    questionRepository.save(question3);
//    questionRepository.save(question4);
//    questionRepository.save(question5);
//    questionRepository.save(question6);
//    questionRepository.save(question7);
//    questionRepository.save(question8);
//    questionRepository.save(question9);
//    questionRepository.save(question10);
//
//    answerRepository.save(answer1);
//    answerRepository.save(answer2);
//    answerRepository.save(answer3);
//    answerRepository.save(answer4);
//    answerRepository.save(answer5);
//    answerRepository.save(answer6);
//    answerRepository.save(answer7);
//  }
//}
