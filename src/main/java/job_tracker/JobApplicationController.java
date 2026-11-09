package job_tracker;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

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
    @ResponseStatus(HttpStatus.CREATED)
    public void postApplication(@RequestBody JobApplication application){
        jobApplicationService.addApplication(application);
    }

    @GetMapping("/api/applications/{id}")
    public JobApplication getApplicationById(@PathVariable Long id){
        return jobApplicationService.getApplicationById(id);
    }

    @PutMapping("api/applications/{id}")
    public JobApplication putApplication(@PathVariable Long id, @RequestBody JobApplication application){
        return jobApplicationService.updateApplication(id, application);
    }
}
