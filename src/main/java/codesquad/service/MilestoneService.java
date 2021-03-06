package codesquad.service;

import codesquad.domain.Issue;
import codesquad.domain.Milestone;
import codesquad.domain.User;
import codesquad.dto.MilestoneDto;
import codesquad.repository.MilestoneRepository;
import org.slf4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.persistence.EntityNotFoundException;

import java.util.List;

import static org.slf4j.LoggerFactory.getLogger;

@Service
public class MilestoneService {
    private static final Logger log = getLogger(MilestoneService.class);

    @Resource(name = "milestoneRepository")
    private MilestoneRepository milestoneRepository;

    public Milestone create(User loginUser, MilestoneDto milestoneDto) {
       return milestoneRepository.save(milestoneDto._toMilestone(loginUser));
    }

    public List<Milestone> findAll() {
        return milestoneRepository.findAll();
    }

    public Milestone findByMilestoneId(long id) {
        return milestoneRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    }

    @Transactional
    public Milestone addIssue(User loginUser, long id, Issue issue) {
        return findByMilestoneId(id).addIssue(loginUser, issue);
    }
}
