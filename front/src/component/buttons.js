import { styled } from "styled-components";


export const AskButton = styled.button`
    background-color: rgb(10, 149, 255);
    color: white;
    border: none;
    border-radius: 0.5rem;
    padding: 5px;
    cursor: pointer;
    text-decoration: none;
    width: 120px;
    height: 35px;
    font-size: 13px;
  
    &:hover {
      background-color:hsl(206,100%,40%);
    }
  
    &:focus {
      box-shadow: 0px 0px 0px 4px hsl(206,96%,90%);
      border-color: hsl(206,85%,57.5%);
    }
 `;


export const PageButton = styled.button`
  padding-left: 10px;
  padding-right: 10px;
  margin: 2px;
  border: 1px solid black;
  border-radius: 5px;
  font-size: 12px;
  height: 25px;

  &:hover {
    background-color: hsl(210,8%,85%);
  }

`;
