package com.voting.service.aspect;

import com.voting.model.VoteResult;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class VoteLogAspect {
    private Logger logger = LoggerFactory.getLogger(VoteLogAspect.class);

    @AfterReturning(value = "execution(* com.voting.service.VoteService.vote(..))", returning = "result")
    public void logAfterVote(JoinPoint joinPoint, VoteResult result) {
        logger.info(result.toString());
    }
}
