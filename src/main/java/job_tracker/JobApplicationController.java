package job_tracker;

import jakarta.validation.Valid;
import job_tracker.dto.CreateJobApplicationRequest;
import job_tracker.dto.UpdateJobApplicationRequest;
import org.springframework.data.domain.Page;
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
    public Page<JobApplication> getApplications(@RequestParam(required = false) ApplicationStatus status, @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size){
        return jobApplicationService.getAllApplications(status, page, size);
    }

    @PostMapping("/api/applications")
    @ResponseStatus(HttpStatus.CREATED)
    public void postApplication(@Valid @RequestBody CreateJobApplicationRequest application){
        jobApplicationService.addApplication(application);
    }

    @GetMapping("/api/applications/{id}")
    public JobApplication getApplicationById(@PathVariable Long id){
        return jobApplicationService.getApplicationById(id);
    }

    @PutMapping("api/applications/{id}")
    public JobApplication putApplication(@PathVariable Long id, @RequestBody @Valid UpdateJobApplicationRequest application){
        return jobApplicationService.updateApplication(id, application);
    }

    @DeleteMapping("api/applications/{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void deleteApplication(@PathVariable Long id){
        jobApplicationService.removeApplication(id);
    }
}
