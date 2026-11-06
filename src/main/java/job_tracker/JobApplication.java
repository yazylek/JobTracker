package job_tracker;

import java.math.BigDecimal;
import java.time.LocalDate;

public class JobApplication{
    private Long id;
    private String companyName;
    private String position;
    private BigDecimal salaryRangeMin;
    private BigDecimal salaryRangeMax;
    private LocalDate applicationDate;
    private ApplicationStatus status;

    public JobApplication(String companyName, String position, BigDecimal salaryRangeMin, BigDecimal salaryRangeMax, LocalDate applicationDate, ApplicationStatus status) {
        if (salaryRangeMin != null && salaryRangeMax != null) {
            if(salaryRangeMin.compareTo(salaryRangeMax) > 0) {
                throw new IllegalArgumentException("Salary range cannot be greater than salary range max");
            }
        }
        if (salaryRangeMin != null && salaryRangeMin.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Salary range cannot be less than zero");
        }
        if (salaryRangeMax != null && salaryRangeMax.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Salary range cannot be greater than salary range");
        }
        this.companyName = companyName;
        this.position = position;
        this.salaryRangeMin = salaryRangeMin;
        this.salaryRangeMax = salaryRangeMax;
        this.applicationDate = applicationDate;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public String getCompanyName() {
        return companyName;
    }

    public String getPosition() {
        return position;
    }

    public BigDecimal getSalaryRangeMin() {
        return salaryRangeMin;
    }

    public BigDecimal getSalaryRangeMax() {
        return salaryRangeMax;
    }

    public LocalDate getApplicationDate() {
        return applicationDate;
    }

    public ApplicationStatus getStatus() {
        return status;
    }

    @Override
    public String toString() {
        return "JobApplication{" +
                "id=" + id +
                ", companyName='" + companyName + '\'' +
                ", position='" + position + '\'' +
                ", salaryRangeMin=" + salaryRangeMin +
                ", salaryRangeMax=" + salaryRangeMax +
                ", applicationDate=" + applicationDate +
                ", status=" + status +
                '}';
    }
}
