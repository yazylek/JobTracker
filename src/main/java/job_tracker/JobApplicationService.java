package job_tracker;

import job_tracker.dto.CreateJobApplicationRequest;
import job_tracker.dto.UpdateJobApplicationRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class JobApplicationService {
        private final JobApplicationRepository jobApplicationRepository;

    public JobApplicationService(JobApplicationRepository jobApplicationRepository) {
        this.jobApplicationRepository = jobApplicationRepository;
    }

    public Page<JobApplication> getAllApplications(ApplicationStatus status, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        if(status == null){
            return jobApplicationRepository.findAll(pageable);
        } else {
            return jobApplicationRepository.findByStatus(status, pageable);
        }
    }

    public void addApplication(CreateJobApplicationRequest request){
        JobApplication application = new JobApplication(
                request.companyName(),
                request.position(),
                request.salaryRangeMin(),
                request.salaryRangeMax(),
                request.applicationDate(),
                request.status()
        );
        jobApplicationRepository.save(application);
    }

    public JobApplication getApplicationById(Long id){
        return jobApplicationRepository.findById(id).orElseThrow(() -> new ResponseStatusException(
                HttpStatus.NOT_FOUND,
                "Job application not found"
        ));
    }

    public JobApplication updateApplication(Long id, UpdateJobApplicationRequest request){
        JobApplication existingApplication = getApplicationById(id);

        existingApplication.updateDetails(
                request.companyName(),
                request.position(),
                request.salaryRangeMin(),
                request.salaryRangeMax(),
                request.applicationDate(),
                request.status()
        );
        return jobApplicationRepository.save(existingApplication);
    }

    public void removeApplication(Long id){
        jobApplicationRepository.deleteById(id);
    }

}
