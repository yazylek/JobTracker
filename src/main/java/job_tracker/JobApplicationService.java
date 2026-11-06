package job_tracker;

import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class JobApplicationService {
        private final JobApplicationRepository jobApplicationRepository;

    public JobApplicationService(JobApplicationRepository jobApplicationRepository) {
        this.jobApplicationRepository = jobApplicationRepository;
    }

    public List<JobApplication> getAllApplications() {
        return jobApplicationRepository.findAll();
    }

    public void addApplication(JobApplication application){
        jobApplicationRepository.save(application);
    }

}
