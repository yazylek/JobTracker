package job_tracker;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
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

    public JobApplication getApplicationById(Long id){
        return jobApplicationRepository.findById(id).orElseThrow(() -> new ResponseStatusException(
                HttpStatus.NOT_FOUND,
                "Job application not found"
        ));
    }

    public JobApplication updateApplication(Long id, JobApplication updatedApplication){
        JobApplication existingApplication = getApplicationById(id);
        BigDecimal salaryRangeMin = updatedApplication.getSalaryRangeMin();
        BigDecimal salaryRangeMax = updatedApplication.getSalaryRangeMax();
        if (salaryRangeMin != null && salaryRangeMax != null) {
            if (salaryRangeMin.compareTo(salaryRangeMax) > 0) {
                throw new IllegalArgumentException("Salary range cannot be greater than salary range max");
            }
        }
        if (salaryRangeMin != null && salaryRangeMin.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Salary range cannot be less than zero");
        }
        if (salaryRangeMax != null && salaryRangeMax.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Maximum salary cannot be negative");
        }
        existingApplication.setCompanyName(updatedApplication.getCompanyName());
        existingApplication.setApplicationDate(updatedApplication.getApplicationDate());
        existingApplication.setPosition(updatedApplication.getPosition());
        existingApplication.setSalaryRangeMax(updatedApplication.getSalaryRangeMax());
        existingApplication.setSalaryRangeMin(updatedApplication.getSalaryRangeMin());
        existingApplication.setStatus(updatedApplication.getStatus());
        return jobApplicationRepository.save(existingApplication);
    }

    public void removeApplication(Long id){
        jobApplicationRepository.deleteById(id);
    }

}
