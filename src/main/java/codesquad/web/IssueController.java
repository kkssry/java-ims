package codesquad.web;

import codesquad.domain.Issue;
import codesquad.domain.Milestone;
import codesquad.domain.User;
import codesquad.dto.IssueDto;
import codesquad.security.LoginUser;
import codesquad.service.IssueService;
import codesquad.service.MilestoneService;
import org.slf4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

import java.util.Objects;

import static org.slf4j.LoggerFactory.getLogger;

@Controller
@RequestMapping("/issues")
public class IssueController {
    private static final Logger log = getLogger(IssueController.class);

    @Resource(name = "issueService")
    private IssueService issueService;

    @Resource(name = "milestoneService")
    private MilestoneService milestoneService;

    @GetMapping("")
    public String createForm(@LoginUser User loginUser) {
        return "/issue/form";
    }

    @PostMapping("")
    public String create(@LoginUser User loginUser, IssueDto newIssue) {
        log.debug("loginUser:{}", loginUser);
        issueService.create(loginUser, newIssue);
        return "redirect:/";
    }

    @GetMapping("/{id}")        //홈 화면에서 이슈제목을 눌렀을때 (마일스톤 누를때 동적으로 이슈 게시판에 어떤 작성자가 어떤 마일스톤을 누르는지에 대한 구현 포기..)
    public String show(@PathVariable long id, Model model) {
        model.addAttribute("milestones", milestoneService.findAll());
        model.addAttribute("issue", issueService.findByIssueId(id));
        return "/issue/show";
    }

    @GetMapping("/{id}/form")
    public String modifyForm(@LoginUser User loginUser, @PathVariable long id, Model model) {
        Issue issue = issueService.findByIssueId(id);
        if (issue.isOwner(loginUser)) {
            model.addAttribute("issue", issue._toIssueDto());
            return "/issue/updateForm";
        }
        return String.format("redirect:/issues/%d", id);
    }

    @PutMapping("/{id}")
    public String modify(@LoginUser User loginUser, @PathVariable long id, IssueDto updateIssue) {
        issueService.update(loginUser, id, updateIssue);
        return "redirect:/";
    }

    @DeleteMapping("/{id}")
    public String delete(@LoginUser User loginUSer, @PathVariable long id) {
        issueService.deleteIssue(loginUSer, id);
        return "redirect:/";
    }

//    @GetMapping("/{id}/{milestoneId}")      //이슈 상세보기에서 마일스톤1개를 눌렀을때
//    public String showMilestoneComment(@LoginUser User loginUser, @PathVariable long id, @PathVariable long milestoneId) {
//        Milestone milestone = milestoneService.findByMilestoneId(milestoneId);
//        Issue issue = issueService.findByIssueId(id).toMilestone(milestone);
//        issue.getMilestoneDto().setModifier(loginUser);
//        return String.format("redirect:/issues/%d", id);
//    }
}
