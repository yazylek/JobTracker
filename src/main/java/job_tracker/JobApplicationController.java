package job_tracker;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class JobApplicationController {

    private final JobApplicationService jobApplicationService;

    public JobApplicationController(JobApplicationService jobApplicationService) {
        this.jobApplicationService = jobApplicationService;
    }

    @GetMapping("/api/applications")
    public List<JobApplication> getApplications(){
        return jobApplicationService.getAllApplications();
    }

    @PostMapping("/api/applications")
    public void postApplication(@RequestBody JobApplication application){
        jobApplicationService.addApplication(application);
    }
}
