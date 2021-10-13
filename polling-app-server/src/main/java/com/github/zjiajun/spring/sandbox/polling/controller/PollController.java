package com.github.zjiajun.spring.sandbox.polling.controller;

import com.github.zjiajun.spring.sandbox.polling.model.Poll;
import com.github.zjiajun.spring.sandbox.polling.payload.ApiResponse;
import com.github.zjiajun.spring.sandbox.polling.payload.PollRequest;
import com.github.zjiajun.spring.sandbox.polling.payload.PollResponse;
import com.github.zjiajun.spring.sandbox.polling.payload.VoteRequest;
import com.github.zjiajun.spring.sandbox.polling.repository.PollRepository;
import com.github.zjiajun.spring.sandbox.polling.repository.UserRepository;
import com.github.zjiajun.spring.sandbox.polling.repository.VoteRepository;
import com.github.zjiajun.spring.sandbox.polling.security.CurrentUser;
import com.github.zjiajun.spring.sandbox.polling.security.UserPrincipal;
import com.github.zjiajun.spring.sandbox.polling.service.PollService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

/**
 * @author zhujiajun
 * @version 1.0
 * @since 2021/10/13 22:05
 */
@RestController
@RequestMapping("/api/polls")
public class PollController {

    @Autowired
    private PollRepository pollRepository;

    @Autowired
    private VoteRepository voteRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PollService pollService;

    private static final Logger logger = LoggerFactory.getLogger(PollController.class);

    @PostMapping
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<?> createPoll(@Valid @RequestBody PollRequest pollRequest) {
        Poll poll = pollService.createPoll(pollRequest);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest().path("/{pollId}")
                .buildAndExpand(poll.getId()).toUri();

        return ResponseEntity.created(location)
                .body(new ApiResponse(true, "Poll Created Successfully"));
    }

    @GetMapping("/{pollId}")
    public PollResponse getPollById(@CurrentUser UserPrincipal currentUser,
                                    @PathVariable Long pollId) {
        return pollService.getPollById(pollId, currentUser);
    }

    @PostMapping("/{pollId}/votes")
    @PreAuthorize("hasRole('USER')")
    public PollResponse castVote(@CurrentUser UserPrincipal currentUser,
                                 @PathVariable Long pollId,
                                 @Valid @RequestBody VoteRequest voteRequest) {
        return pollService.castVoteAndGetUpdatedPoll(pollId, voteRequest, currentUser);
    }
}
