package com.preproject.seb_pre_15.member.mapper;

import com.preproject.seb_pre_15.answer.dto.AnswerResponseDto;
import com.preproject.seb_pre_15.comment.answerComment.dto.AnswerCommentDto;
import com.preproject.seb_pre_15.member.dto.MemberPatchDto;
import com.preproject.seb_pre_15.member.dto.MemberResponseDto;
import com.preproject.seb_pre_15.member.entity.Member;
import com.preproject.seb_pre_15.question.dto.QuestionResponseDto;
import org.mapstruct.Mapper;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface MemberMapper {
   default MemberResponseDto memberToMemberResponseDto(Member member){
       MemberResponseDto responseDto = new MemberResponseDto();
       responseDto.setMemberId(member.getMemberId());
       responseDto.setName(member.getName());
       responseDto.setEmail(member.getEmail());
       responseDto.setRoles(member.getRoles());
       responseDto.setQuestions(member.getQuestions().stream().map(
               question -> {
                   QuestionResponseDto questionResponseDto = new QuestionResponseDto();
                   questionResponseDto.setQuestionId(question.getQuestionId());
                   questionResponseDto.setTitle(question.getTitle());
                   questionResponseDto.setBody(question.getBody());
                   return questionResponseDto;
               }).collect(Collectors.toList()));
       responseDto.setAnswers(member.getAnswers().stream().map(
               answer -> {
                   AnswerResponseDto answerResponseDto = new AnswerResponseDto();
                   answerResponseDto.setAnswerId(answer.getAnswerId());
                   answerResponseDto.setBody(answer.getBody());
                   return answerResponseDto;
               }).collect(Collectors.toList()));

       responseDto.setAnswerComments(member.getAnswerComments().stream().map(
               answerComment -> {
                   AnswerCommentDto.Response response = new AnswerCommentDto.Response();
                   response.setAnswerCommentId(answerComment.getAnswerCommentId());
                   response.setMemberEmail(answerComment.getMember().getEmail());
                   response.setBody(answerComment.getBody());
                   return response;
               }
       ).collect(Collectors.toList()));
       responseDto.setProfilePic(member.getProfilePic());
       return responseDto;
   }
    Member memberPatchDtoToMember(MemberPatchDto memberPatchDto);
}
