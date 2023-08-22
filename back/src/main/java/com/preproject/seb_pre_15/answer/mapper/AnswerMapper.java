package com.preproject.seb_pre_15.answer.mapper;

import com.preproject.seb_pre_15.answer.dto.AnswerPatchDto;
import com.preproject.seb_pre_15.answer.dto.AnswerPostDto;
import com.preproject.seb_pre_15.answer.dto.AnswerResponseDto;
import com.preproject.seb_pre_15.answer.dto.AnswerVotePatchDto;
import com.preproject.seb_pre_15.answer.entity.Answer;
import com.preproject.seb_pre_15.comment.answerComment.dto.AnswerCommentDto;
import com.preproject.seb_pre_15.comment.questionComment.dto.QuestionCommentDto;
import com.preproject.seb_pre_15.member.entity.Member;
import com.preproject.seb_pre_15.question.dto.QuestionResponseDto;
import com.preproject.seb_pre_15.question.entity.Question;
import org.mapstruct.Mapper;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface AnswerMapper {
    default Answer answerPostDtoToAnswer(AnswerPostDto answerPostDto, Long memberId){
        Answer answer = new Answer();
        answer.setBody(answerPostDto.getBody());
        Member member = new Member();
        member.setMemberId(memberId);
        answer.setMember(member);
        Question question = new Question();
        question.setQuestionId(answerPostDto.getQuestionId());
        answer.setQuestion(question);

        return answer;
    };
    Answer answerPatchDtoToAnswer(AnswerPatchDto answerPatchDto);
    default AnswerResponseDto answerToAnswerResponseDto(Answer answer){
        if ( answer == null ) {
            return null;
        }

        AnswerResponseDto answerResponseDto = new AnswerResponseDto();

        if ( answer.getAnswerId() != null ) {
            answerResponseDto.setAnswerId(answer.getAnswerId() );
        }
        answerResponseDto.setBody( answer.getBody() );
        answerResponseDto.setMemberEmail( answer.getMember().getEmail());
        answerResponseDto.setVote( answer.getVote() );


        List<AnswerCommentDto.Response> commentsResponses = answer.getAnswerComments().stream().map(
                answerComment -> {
                    AnswerCommentDto.Response commentsResponse = new AnswerCommentDto.Response();
                    commentsResponse.setAnswerCommentId(answerComment.getAnswerCommentId());
                    commentsResponse.setBody(answerComment.getBody());
                    commentsResponse.setMemberEmail(answerComment.getMember().getEmail());
                    return commentsResponse;
                }
        ).collect(Collectors.toList());

        answerResponseDto.setComments(commentsResponses);

        return answerResponseDto;
    };
    List<AnswerResponseDto> answersToAnswerResponseDtos(List<Answer> answers);
    Answer answerVotePatchDtoToAnswer(AnswerVotePatchDto answerVotePatchDto);
}
