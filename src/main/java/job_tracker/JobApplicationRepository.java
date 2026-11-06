package job_tracker;

import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Repository
public class JobApplicationRepository {
    private JobApplication job = new JobApplication("Google", "Software Developer", BigDecimal.valueOf(10000), BigDecimal.valueOf(20000), LocalDate.parse("2026-10-22"), ApplicationStatus.APPLIED);
    private JobApplication job2 = new JobApplication("Microsoft", "Software Developer", BigDecimal.valueOf(10432), BigDecimal.valueOf(20540), LocalDate.parse("2026-10-21"), ApplicationStatus.APPLIED);


    private final List<JobApplication> applications = new ArrayList<>(List.of(job, job2));

    public List<JobApplication> findAll(){
        return List.copyOf(applications);
    }

    public void save(JobApplication application){
        applications.add(application);
    }
}
