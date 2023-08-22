import styled from 'styled-components';
import { QuestionData } from '../type/types';

export const PostSum = styled.div`
  display: flex;
  align-items: flex-end;
  justify-content: flex-start;
  flex-direction: column;
  width: 140px;
  padding: 2px 16px 4px 0;
  text-align: left;
  font-size: 13px;
  `

function PostSummary(question:QuestionData) {
    return (
        <PostSum>
            <span>{question.vote} votes</span>
            <span> answers</span>
            <span>{question.view} views</span>
        </PostSum>
    )
  }
export default PostSummary;