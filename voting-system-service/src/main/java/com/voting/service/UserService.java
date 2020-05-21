package com.voting.service;

import com.voting.db.VoterRepository;
import com.voting.model.Voter;
import com.voting.service.payload.UserSummary;
import com.voting.service.security.UserPrincipal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    private VoterRepository voterRepository;

    public List<UserSummary> getAllUsers() {
        List<Voter> voters = voterRepository.findAll();
        return voters.stream()
                .map(this::mapToUserSummary)
                .collect(Collectors.toList());
    }

    public UserSummary getUserById(Long userId) {
        Voter voter = voterRepository.findById(userId)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with id : " + userId));
        return mapToUserSummary(voter);
    }

    public UserSummary getCurrentUser(UserPrincipal currentUser) {
        return getUserById(currentUser.getId());
    }

    private UserSummary mapToUserSummary(Voter voter) {
        return UserSummary.builder()
                .id(voter.getId())
                .name(voter.getName())
                .email(voter.getEmail())
                .build();
    }
}
