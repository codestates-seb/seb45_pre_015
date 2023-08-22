import React from "react";
import styled from "styled-components";
import { Link } from "react-router-dom";
import LogoIcon from "../images/small-logo.svg";

const FooterSection = styled.footer`
  width: 100%;
  background-color:#232629;
  padding: 3.125rem 0;

  .ft_wrap{
    max-width: 78.75rem;
    margin: 0 auto;
    display: flex;
    justify-content: space-between;
    align-items: center;
    gap: 1.25rem;
    color: #BABFC3;
  }
  .ft_info{
    display: flex;
    flex-direction: column;
    gap: 1.25rem;
  }
  .ft_info figure{
    width: 1.875rem;
    height: 1.875rem;
  }
  .ft_info h3{
    font-size: 1.125rem;
    font-weight: 700;
  }
  .ft_content p{
    font-size: 1.125rem;
    font-weight: 700;
    margin: 0 0 1.25rem;
  }
  .ft_content span{
    font-size: .875rem;
  }
  .ft_link a{
    font-size: .9375rem;
    margin: 0 .625rem 0;
  }
  .ft_link a:last-child{
    margin: 0;
  }
`

const Footer = () => {
    return (
      <FooterSection>
        <section className="ft_wrap">
          <div className="ft_info">
            <figure>
              <img src={LogoIcon} alt="logoIcon" />
            </figure>  
            <h3> {sessionStorage.getItem("memberEmail")} </h3>
          </div>
          <div className="ft_content">
            <p>STACK OVERFLOW</p>
            <span>
              Site design / logo © 2022 Stack Exchange Inc; user contributions
              licensed under CC BY-SA. rev 2022.10.28.42999
            </span>
          </div>
          <div className="ft_link">
            <Link to="/">
              Questions
            </Link>
            <Link to="https://stackoverflow.com/help">
              Help
            </Link>
          </div>
        </section>
      </FooterSection>
    );
  };
  
  export default Footer;