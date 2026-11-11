package job_tracker;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "job_applications")
public class JobApplication {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "company_name", nullable = false)
    private String companyName;

    @Column(name = "position", nullable = false)
    private String position;

    @Column(name = "salary_range_min")
    private BigDecimal salaryRangeMin;

    @Column(name = "salary_range_max")
    private BigDecimal salaryRangeMax;

    @Column(name = "application_date", nullable = false)
    private LocalDate applicationDate;

    @Column(name = "status", nullable = false)
    @Enumerated(value = EnumType.STRING)
    private ApplicationStatus status;

    public JobApplication(String companyName, String position, BigDecimal salaryRangeMin, BigDecimal salaryRangeMax, LocalDate applicationDate, ApplicationStatus status) {
        validateSalaryRange(salaryRangeMin, salaryRangeMax);
        this.companyName = companyName;
        this.position = position;
        this.salaryRangeMin = salaryRangeMin;
        this.salaryRangeMax = salaryRangeMax;
        this.applicationDate = applicationDate;
        this.status = status;
    }

    protected JobApplication() {
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

    private static void validateSalaryRange(
            BigDecimal salaryRangeMin,
            BigDecimal salaryRangeMax
    ){
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
    }

    public void updateDetails(
            String companyName,
            String position,
            BigDecimal salaryRangeMin,
            BigDecimal salaryRangeMax,
            LocalDate applicationDate,
            ApplicationStatus status
    ){
        validateSalaryRange(salaryRangeMin, salaryRangeMax);
        this.companyName = companyName;
        this.position = position;
        this.salaryRangeMin = salaryRangeMin;
        this.salaryRangeMax = salaryRangeMax;
        this.applicationDate = applicationDate;
        this.status = status;
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
